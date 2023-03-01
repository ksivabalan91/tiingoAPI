package com.stockmonkey.stockpicker.services;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.stockmonkey.stockpicker.models.DCAmodel;
import com.stockmonkey.stockpicker.repositories.DCArepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@Service
public class DCAservice {

    @Value(value = "${api.key}")
    private String API_KEY;
    @Autowired
    private DCArepository dcaRepo;

    //! method to display all created models    
    public void getAll(){dcaRepo.getAll();}
    public void printAll(){dcaRepo.printAll();}

    //! main methods to calculate DCA
    public boolean computeDCA(String ticker, String start, String end, int investInterval, double investAmount, double initialAmount) {
        //! Date formatting and extraction
        System.out.println("Service data extraction");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        LocalDate startDate = LocalDate.parse(start, format);
        LocalDate endDate = LocalDate.parse(end, format);

        dcaRepo.checkInstance(ticker, startDate, endDate);
        
        int duration = (int) ChronoUnit.DAYS.between(startDate, endDate);
        int repetitions = duration/investInterval;
        double quantity=0.0;
        double investTotal = repetitions*investAmount;
        
        
        //! Rest Request from API
        //* example: https://api.tiingo.com/tiingo/daily/<ticker>/prices?startDate=2012-1-1&endDate=2016-1-1
        System.out.println("Service API request");
        String url = UriComponentsBuilder
            .fromUriString("https://api.tiingo.com/tiingo/daily/" + ticker + "/prices")
            .queryParam("startDate", startDate)
            .queryParam("endDate", endDate.toString())
            .toUriString();
    
        //! method created below to create jsonReader
        System.out.println("Service JsonReader");
        JsonReader jsonReader = readJson(url);
        JsonArray jsonArr = jsonReader.readArray(); 
        HashMap<LocalDate, Double> priceMap = new HashMap<>();

        // System.out.println(jsonArr.toString());

        //! Creating new map with date as key and stock price as value
        System.out.println("Service creating price map...");
        for (int i = 0; i < jsonArr.size(); i++) {
            String keyDate = jsonArr.getJsonObject(i).getString("date").split("T")[0];
            LocalDate localDate = LocalDate.parse(keyDate);
            Double valPrice = jsonArr.getJsonObject(i).getJsonNumber("adjClose").doubleValue();
            priceMap.put(localDate, valPrice);
        }

        //! instantiate model to store parameters
        System.out.println("Service calculate key parameters");
        DCAmodel dcaModel = new DCAmodel();
        
        for(int i=0;i<repetitions;i++){
            //todo - incase stock market is closed on any given date, take the next available date
            while(!priceMap.containsKey(startDate) && startDate.isBefore(endDate)){
                startDate = startDate.plusDays(1);
            }
            //todo - initial purchase of stock with initial investment amount
            if(i==0){quantity+= initialAmount/priceMap.get(startDate);}
            //todo - buy stock and update quantity
            quantity+= investAmount/priceMap.get(startDate);
            //todo - update to next date of purchase
            startDate = startDate.plusDays(investInterval);
        }
        
        //todo - get latest price of stock
        while(!priceMap.containsKey(endDate)){endDate = endDate.minusDays(1);}
        double lastPrice = priceMap.get(endDate);

        //! calculate key parameters
        double avgCost = investTotal/quantity;
        double portValue = lastPrice*quantity;
        double profitLoss = portValue-investTotal;
        
        //! add parameters to model
        //todo - from input
        System.out.println("Service add to model");
        dcaModel.setTicker(ticker);
        dcaModel.setStartDate(LocalDate.parse(start, format));
        dcaModel.setEndDate(LocalDate.parse(end, format));
        dcaModel.setInvestInterval(investInterval);
        dcaModel.setInvestAmount(investAmount);
        dcaModel.setInitialAmount(initialAmount);
        //todo - derived values
        dcaModel.setDuration(duration);
        dcaModel.setRepetitions(repetitions);        
        dcaModel.setInvestTotal(investTotal);
        dcaModel.setQuantity(quantity);
        dcaModel.setLastPrice(lastPrice);
        dcaModel.setAvgCost(avgCost);
        dcaModel.setPortValue(portValue);
        dcaModel.setProfitLoss(profitLoss);
        
        //! add model to repository, return true if successful
        System.out.println("Service add to repo");
        return dcaRepo.add(dcaModel);        
    }

    //! Json reader method
    public JsonReader readJson(String url) {
        RequestEntity<Void> req = RequestEntity
                .get(url)
                .header("Authorization", "Token " + API_KEY)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        String payload;
        int statuscode;

        try {
            resp = template.exchange(req, String.class);
            payload = resp.getBody();
            statuscode = resp.getStatusCode().value();
        } catch (HttpClientErrorException ex) {
            payload = ex.getResponseBodyAsString();
            statuscode = ex.getStatusCode().value();
        }
        
        System.out.println(statuscode);
        JsonReader jsonReader = Json.createReader(new StringReader(payload));

        return jsonReader;
    }
}
