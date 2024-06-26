package com.example.unitech.controller;


import com.example.unitech.model.response.CurrencyResponse;
import com.example.unitech.service.abstraction.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/latest")
    public CurrencyResponse currencyRates() {
        return currencyService.allCurrencyRates();
    }

    @GetMapping("/convert")
    public Double convertAmount(@RequestParam Double amount,
                                @RequestParam String source,
                                @RequestParam String target) {
        return currencyService.convertAmount(amount, source, target);
    }

    @GetMapping("/specific")
    public Double getExchangeRate(@RequestParam String source,
                                  @RequestParam String target) {
        return currencyService.getSpecificExchangeRate(source, target);
    }

}