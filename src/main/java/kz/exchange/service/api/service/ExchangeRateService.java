package kz.exchange.service.api.service;

import kz.exchange.service.api.data.entity.ExchangeRate;
import kz.exchange.service.api.data.request.ConvertRequest;
import kz.exchange.service.api.data.response.ConvertResponse;

import java.util.Optional;

public interface ExchangeRateService {
    ConvertResponse convertCurrency(ConvertRequest convertRequest);

    void fetchAndSaveExchangeDailyRate();
}
