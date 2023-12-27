package kz.exchange.service.api.repository;

import kz.exchange.service.api.data.entity.Currency;
import kz.exchange.service.api.data.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("SELECT er FROM ExchangeRate er " +
            "WHERE er.currencyFrom = :currency " +
            "ORDER BY er.exchangeDate DESC")
    ExchangeRate findTopByCurrencyFromOrderByExchangeDateDesc(@Param("currency") Currency currency);

    Optional<ExchangeRate> findTopByCurrencyFromAndCurrencyToOrderByExchangeDateDesc(Currency currencyFrom, Currency currencyTo);
}
