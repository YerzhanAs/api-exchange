package kz.exchange.service.api.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse {

    private List<CurrencyTypeResponse> values;

    private  MetaData meta;

}
