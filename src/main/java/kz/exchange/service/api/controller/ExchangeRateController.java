package kz.exchange.service.api.controller;


import kz.exchange.service.api.data.dto.ExchangeRateDTO;
import kz.exchange.service.api.data.request.ConvertRequest;
import kz.exchange.service.api.data.response.ConvertResponse;
import kz.exchange.service.api.service.impl.CurrencyServiceImpl;
import kz.exchange.service.api.service.impl.ExchangeRateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/exchange")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateServiceImpl exchangeRateService;

    // convert currency
    @PostMapping("/currency-exchange")
    public ResponseEntity<ConvertResponse> currencyExchange(@RequestBody ConvertRequest convertRequest) {
        return ResponseEntity.ok(exchangeRateService.convertCurrency(convertRequest));
    }

    // update Exchange rate
    @GetMapping("/update")
    public ResponseEntity<String> updateExchangeRates() {
        try {
            exchangeRateService.fetchAndSaveExchangeDailyRate();
            return ResponseEntity.ok("Exchange rates updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating exchange rates: " + e.getMessage());
        }
    }
}
