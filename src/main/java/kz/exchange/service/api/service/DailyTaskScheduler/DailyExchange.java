package kz.exchange.service.api.service.DailyTaskScheduler;

import kz.exchange.service.api.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DailyExchange {

    private final ExchangeRateService exchangeRateService;

    // 12 часов ночи записывает новые данные курса валюты и записывает в базу данных№
    // Можно настроить чтобы получал данные каждую минуту
    @Scheduled(cron = "0 0 12 * * ?")
    public void DailyExchange() {
        try {
            exchangeRateService.fetchAndSaveExchangeDailyRate();
            System.out.println("Running daily task...");
        } catch (Exception e) {
            System.err.println("Error in daily task: " + e.getMessage());
        }
    }
}
