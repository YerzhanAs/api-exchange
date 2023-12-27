package kz.exchange.service.api.mapper;

import kz.exchange.service.api.data.dto.ExchangeRateDTO;
import kz.exchange.service.api.data.entity.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


// Можно сделать через MapStruct, MapStruct быстрее чем ModelMappe. Были ошибки в настройки библиотеки и не успел
@Component
@RequiredArgsConstructor
public class ExchangeRateMapper {

    private final ModelMapper modelMapper;


    public ExchangeRateDTO convertToExchangeRateDTO(ExchangeRate exchangeRate) {
        return modelMapper.map(exchangeRate, ExchangeRateDTO.class);
    }

    public ExchangeRate convertToExchangeRate(ExchangeRateDTO exchangeRateDTO){
        return modelMapper.map(exchangeRateDTO,  ExchangeRate.class);
    }
}
