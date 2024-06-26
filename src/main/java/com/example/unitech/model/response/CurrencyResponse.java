package com.example.unitech.model.response;

import lombok.*;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyResponse {

    private Map<String, Double> rates;

}