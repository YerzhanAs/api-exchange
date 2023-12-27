package kz.exchange.service.api.controller;


import kz.exchange.service.api.data.dto.ExchangeRateDTO;
import kz.exchange.service.api.data.entity.Currency;
import kz.exchange.service.api.data.entity.ExchangeRate;
import kz.exchange.service.api.service.impl.CurrencyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyServiceImpl currencyService;


    @GetMapping("/{code}")
    public Currency getCurrency(@PathVariable("code") String code){
        return currencyService.currencyByCode(code);
    }

    // get rate of currency
    @GetMapping("/latest-rate/{currencyCode}")
    public ResponseEntity<ExchangeRateDTO> getLatestExchangeRateByCurrencyCode(@PathVariable String currencyCode) {
        return ResponseEntity.ok(currencyService.getLatestExchangeRateByCurrencyCode(currencyCode));
    }

}
