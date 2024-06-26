package com.example.unitech.service.abstraction;

import com.example.unitech.model.response.CurrencyResponse;

public interface CurrencyService {
    CurrencyResponse allCurrencyRates();

    double convertAmount(Double amount, String source, String target);

    double getSpecificExchangeRate(String source, String target);

    void updateRecentCurrentData();
}
