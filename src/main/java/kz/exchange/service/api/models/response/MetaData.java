package kz.exchange.service.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MetaData {

    // не получилось сделать так currencyBase, не правильная конвертация
    private String currency_base;

    private String currency_quote;
}
