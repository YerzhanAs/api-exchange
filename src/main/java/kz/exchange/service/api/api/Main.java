package kz.exchange.service.api.api;

import kz.exchange.service.api.models.response.ExchangeResponse;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();
        String url="https://api.twelvedata.com/time_series?symbol=USD/KZT&interval=1day&apikey=f4a116dea9a246729d0b0f6bebfea33a";
        ExchangeResponse response=restTemplate.getForObject(url,  ExchangeResponse.class);

        System.out.println(response.getMeta().getCurrency_base());

//        String url="https://api.twelvedata.com/time_series?symbol=USD/KZT&interval=1day&apikey=f4a116dea9a246729d0b0f6bebfea33a";
//        String response=restTemplate.getForObject(url, String.class);
//
//        System.out.println(response);




    }
}
