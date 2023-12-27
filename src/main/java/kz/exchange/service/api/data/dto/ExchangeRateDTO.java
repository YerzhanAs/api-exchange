package kz.exchange.service.api.data.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ExchangeRateDTO {

    private CurrencyDTO currencyFrom;

    private CurrencyDTO currencyTo;

    private BigDecimal exchangeRate;

    private Date exchangeDate;
}
