package com.example.unitech.service.concrete;

import com.example.unitech.dao.entity.CurrencyEntity;
import com.example.unitech.dao.repository.CurrencyRepository;
import com.example.unitech.exception.IllegalArgumentException;
import com.example.unitech.model.response.CurrencyResponse;
import com.example.unitech.service.abstraction.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.unitech.exception.ExceptionConstants.*;
import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@Service
public class CurrencyServiceHandler implements CurrencyService {

    @Value("${currency.api.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyResponse allCurrencyRates() {
        return restTemplate.getForObject(url, CurrencyResponse.class);
    }

    @Override
    public double getSpecificExchangeRate(String sourceCurrency, String targetCurrency) {
        CurrencyResponse response = restTemplate.getForObject(url, CurrencyResponse.class);
        if (response == null) {
            throw new IllegalArgumentException(INVALID_DATA_CODE, INVALID_DATA_MESSAGE);
        }
        double sourceRate = response.getRates().get(sourceCurrency);
        double targetRate = response.getRates().get(targetCurrency);
        if (sourceRate == 0 || targetRate == 0) {
            throw new IllegalArgumentException(INVALID_PAIR_CODE, INVALID_PAIR_MESSAGE);
        }
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setCurrencyType(sourceCurrency);
        currencyEntity.setRate(targetRate / sourceRate);
        currencyEntity.setUpdatedOn(now());
        currencyRepository.save(currencyEntity);
        return targetRate / sourceRate;
    }

    @Override
    public void updateRecentCurrentData() {
        var oneMinuteBeforeNow = now().minusMinutes(1);
        List<CurrencyEntity> currencies = currencyRepository.findByUpdatedOnBefore(oneMinuteBeforeNow);
        for (CurrencyEntity currency : currencies) {
            currency.setUpdatedOn(now());
            currencyRepository.save(currency);
        }
    }

    @Override
    public double convertAmount(Double amount, String source, String target) {
        double exchangeRate = getSpecificExchangeRate(source, target);
        if (exchangeRate == 0) {
            throw new IllegalArgumentException(INVALID_PAIR_CODE, INVALID_PAIR_MESSAGE);
        }
        return amount * exchangeRate;
    }
}
