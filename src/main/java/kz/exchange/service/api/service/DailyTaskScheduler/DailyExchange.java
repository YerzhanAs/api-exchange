package kz.exchange.service.api.service.DailyTaskScheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DailyExchange {

    @Scheduled(cron = "0 0 12 * * ?")
    public void runDailyTask() {
        // Your daily task logic here
        System.out.println("Running daily task...");
    }
}
