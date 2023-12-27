package kz.exchange.service.api.service.impl;


import kz.exchange.service.api.data.entity.Currency;
import kz.exchange.service.api.data.entity.ExchangeRate;
import kz.exchange.service.api.data.request.ConvertRequest;
import kz.exchange.service.api.data.response.ConvertResponse;
import kz.exchange.service.api.data.response.api.CurrencyTypeResponse;
import kz.exchange.service.api.data.response.api.ExchangeResponse;
import kz.exchange.service.api.repository.CurrencyRepository;
import kz.exchange.service.api.repository.ExchangeRateRepository;
import kz.exchange.service.api.service.ExchangeRateService;
import kz.exchange.service.api.utils.NegativeAmountException;
import kz.exchange.service.api.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final CurrencyRepository currencyRepository;

    private final ExchangeRateRepository exchangeRateRepository;

    private final RestTemplate restTemplate;

    // я сделал функциальность для базовых валют
    @Override
    public ConvertResponse convertCurrency(ConvertRequest convertRequest){

        Currency currencyFrom = currencyRepository.findByCode(convertRequest.getFrom());
        Currency currencyTo = currencyRepository.findByCode(convertRequest.getTo());

        if (convertRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmountException("Amount must not be less than zero");
        }

        if(currencyTo == null || currencyFrom == null){
            throw new NotFoundException("No information about this currencies");
        }

        Optional<ExchangeRate> exchangeRate =exchangeRateRepository.findTopByCurrencyFromAndCurrencyToOrderByExchangeDateDesc(currencyFrom, currencyTo);

        if (exchangeRate.isEmpty()){
            throw new NotFoundException("No information about this exchange rate");
        }

        ConvertResponse convertResponse = new ConvertResponse();

        if(currencyFrom.getCode().equals("KZT")){
            convertResponse.setAmount(deleteConvertOperation(exchangeRate.get(), convertRequest.getAmount()));
        }else {
            convertResponse.setAmount(multipleConvertOperation(exchangeRate.get(), convertRequest.getAmount()));
        }

        return convertResponse;

    }

    public BigDecimal multipleConvertOperation(ExchangeRate exchangeRate, BigDecimal amount){
        return exchangeRate.getExchangeRate().multiply(amount);
    }

    public BigDecimal deleteConvertOperation(ExchangeRate exchangeRate, BigDecimal amount){
        return exchangeRate.getExchangeRate().divide(amount);
    }


    public void fetchAndSaveExchangeDailyRate() {
        fetchExchange("USD","KZT");
        fetchExchange("EUR","KZT");
        fetchExchange("KZT","USD");
        fetchExchange("KZT","EUR");
    }

    public void fetchExchange(String fromExchange, String toExchange){
        String url = "https://api.twelvedata.com/time_series?symbol="+fromExchange+"/"+toExchange+"&interval=1day&apikey=f4a116dea9a246729d0b0f6bebfea33a";
        ExchangeResponse response = restTemplate.getForObject(url, ExchangeResponse.class);

        if (response != null && response.getValues() != null && !response.getValues().isEmpty()) {
            CurrencyTypeResponse data = response.getValues().get(0);
            BigDecimal rate = data.getClose();

            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setExchangeDate(new Date());
            exchangeRate.setExchangeRate(rate);
            exchangeRate.setCurrencyTo(currencyRepository.findByCode(toExchange));
            exchangeRate.setCurrencyFrom(currencyRepository.findByCode(fromExchange));

            exchangeRateRepository.save(exchangeRate);
        }
    }
}
