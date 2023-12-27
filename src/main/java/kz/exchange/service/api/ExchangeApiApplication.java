package kz.exchange.service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = {"kz.exchange.service.api"})
public class ExchangeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeApiApplication.class, args);
    }

}
