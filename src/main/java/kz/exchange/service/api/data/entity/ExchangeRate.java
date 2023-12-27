package kz.exchange.service.api.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_rates")
@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    @ManyToOne
    @JoinColumn(name = "currency_id_from", referencedColumnName = "currencyId")
    private Currency currencyFrom;

    @ManyToOne
    @JoinColumn(name = "currency_id_to", referencedColumnName = "currencyId")
    private Currency currencyTo;

    private BigDecimal exchangeRate;

    private Date exchangeDate;
}
