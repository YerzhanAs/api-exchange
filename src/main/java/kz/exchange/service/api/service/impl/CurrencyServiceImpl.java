package kz.exchange.service.api.service.impl;


import kz.exchange.service.api.data.dto.ExchangeRateDTO;
import kz.exchange.service.api.data.entity.Currency;
import kz.exchange.service.api.data.entity.ExchangeRate;
import kz.exchange.service.api.mapper.ExchangeRateMapper;
import kz.exchange.service.api.repository.CurrencyRepository;
import kz.exchange.service.api.repository.ExchangeRateRepository;
import kz.exchange.service.api.service.CurrencyService;
import kz.exchange.service.api.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateMapper exchangeRateMapper;

    @Override
    public Currency currencyByCode(String code){
        return currencyRepository.findByCode(code);
    }

    @Override
    public ExchangeRateDTO getLatestExchangeRateByCurrencyCode(String currencyCode) {
        Currency currency = currencyRepository.findByCode(currencyCode);

        ExchangeRate exchangeRate =exchangeRateRepository.findTopByCurrencyFromOrderByExchangeDateDesc(currency);

        if(exchangeRate == null){
            throw  new NotFoundException("Information not found with this code: "+currencyCode);
        }

        return exchangeRateMapper.convertToExchangeRateDTO(exchangeRate);
    }


}
