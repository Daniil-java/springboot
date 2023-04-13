package ru.gb.springboot.currency;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.HashMap;

public class ProxyCurrencyRateService implements CurrencyRateService{

    private final CbrCurrencyRateService currencyRateService = new CbrCurrencyRateService();

    private final HashMap<String, Double> rates = new HashMap<>();

    @Override
    public Double getCurrencyRate(String currency) throws IOException {
        if (rates.containsKey(currency)) {
            return rates.get(currency);
        } else {
            Double rate = currencyRateService.getCurrencyRate(currency);
            rates.put(currency, rate);
            return rate;
        }
    }

    @Scheduled(cron = "0 12 * * ?" , zone = "Europe/Paris")
    private void deleteCache() {
        rates.clear();
    }
}
