package ru.gb.springboot.currency;

import java.io.IOException;


public interface CurrencyRateService {
    Double getCurrencyRate(String currency) throws IOException;
}
