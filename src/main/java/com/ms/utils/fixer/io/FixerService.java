package com.ms.utils.fixer.io;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FixerService {
    public JsonNode getLatestConversionRates(){
        RestTemplate restTemplate = new RestTemplate();
        String fixerUrl = "https://api.fixer.io/latest?base=EUR";
        JsonNode response = restTemplate.getForObject(fixerUrl, JsonNode.class);
        return response;
    }

    public Float convertCurrency(String fromCurrency, String toCurrency){
        RestTemplate restTemplate = new RestTemplate();
        String fixerUrl = "https://api.fixer.io/latest?base="+fromCurrency+"&symbols="+toCurrency;
        JsonNode response = restTemplate.getForObject(fixerUrl, JsonNode.class);
        return response.get("rates").get(toCurrency).floatValue();
    }
}
