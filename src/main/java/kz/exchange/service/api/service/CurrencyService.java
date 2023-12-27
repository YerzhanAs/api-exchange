package kz.exchange.service.api.service;

import kz.exchange.service.api.data.dto.ExchangeRateDTO;
import kz.exchange.service.api.data.entity.Currency;

public interface CurrencyService {

     Currency currencyByCode(String code);

     ExchangeRateDTO getLatestExchangeRateByCurrencyCode(String currencyCode);

}
