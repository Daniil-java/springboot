package ru.gb.springboot.currency;



import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;


public class CbrCurrencyRateService implements CurrencyRateService{
    @Override
    public Double getCurrencyRate(String currency) throws IOException {
        JSONObject currencyJson = getJson(new URL("https://www.cbr-xml-daily.ru/daily_json.js"));
        return currencyJson.getJSONObject(currency).getDouble("Value");
    }

    @Scheduled
    public static JSONObject getJson(URL url) throws IOException {
        String json = IOUtils.toString(url, Charset.forName("UTF-8"));
        return new JSONObject(json);
    }
}
