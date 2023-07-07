package com.klasha.task.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



@Component
public class CsvUtil {
    @Value("${csv.file}")
    public  String CSV_FILE;

    public Map<String, Double> readCsv() {
        Map<String, Double> exchangeRates = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(CSV_FILE).openStream()))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                String sourceCurrency = columns[0];
                String targetCurrency = columns[1];
                double rate = Double.parseDouble(columns[2]);
                exchangeRates.put(sourceCurrency.concat("-").concat(targetCurrency), rate);
                double reverseRate = 1 / rate;
                exchangeRates.put(targetCurrency.concat("-").concat(sourceCurrency), reverseRate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }

    public Double getRate(String pair) {
        return readCsv().getOrDefault(pair, null);
    }


}
