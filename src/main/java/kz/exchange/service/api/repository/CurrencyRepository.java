package kz.exchange.service.api.repository;

import kz.exchange.service.api.data.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Currency findByCode(String code);
}
