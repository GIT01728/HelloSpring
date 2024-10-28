package com.hellospring.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StockDB {
    //CRUD operations

    // key - company name
    // value - list of stock of that company
    private final Map<String, List<Stock>> stockByCompany;

    public StockDB() {
        stockByCompany = new HashMap<>();
        dummyData();
    }

    public void add(Stock stock){
        /*
         * 1. check if list of stocks already exist for a company
         * 2. if true,then add to existing list
         * 3. if false, create a new list and add the stock to that list. also add this company and list to the map
         */
        if(stockByCompany.containsKey(stock.getCompany())){
            stockByCompany.get(stock.getCompany()).add(stock);
        } else {
            List<Stock> stocks = new ArrayList<>();
            stocks.add(stock);
            stockByCompany.put(stock.getCompany(), stocks);
        }
    }

    public List<Stock> getStocksByCompany(String company){
        return stockByCompany.getOrDefault(company,new ArrayList<>());
    }


    // private methods
    private void dummyData(){
        List<Stock> msftStock = new ArrayList<>();
        msftStock.add(new Stock("MSFT", 100, 20000));
        msftStock.add(new Stock("MSFT", 10, 25000));
        stockByCompany.put("MSFT", msftStock);

        List<Stock> tcsStock = new ArrayList<>();
        tcsStock.add(new Stock("TCS", 200, 20000));
        tcsStock.add(new Stock("TCS", 100, 25000));
        stockByCompany.put("TCS", tcsStock);
    }
}
