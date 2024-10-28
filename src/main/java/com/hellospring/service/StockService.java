package com.hellospring.service;

import com.hellospring.model.Stock;
import com.hellospring.model.StockDB;
import com.hellospring.util.FileUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    private final StockDB stockDB;

    private final RestClientService restClientService;

    @Autowired
    public StockService(StockDB stockDB, RestClientService restClientService) {
        this.stockDB = stockDB;
        this.restClientService = restClientService;
    }

    public List<Stock> getStockByCompany(String company){
        return stockDB.getStocksByCompany(company);
    }

    public void addStock(Stock stock){
        stockDB.add(stock);
    }

    public void readStockFile(String fileName) throws IOException {
        // read a json file
        // call rest client to add
        String content = FileUtility.readFileContent(fileName);
        String url = "http://localhost:8080/stock/add";
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<Boolean> response = restClientService.post(url,content,headers,Boolean.class);
        if(response.getStatusCode().isError()){
            log.error("error encountered while calling url {}",url);
            throw new RuntimeException("rest api returned error response");
        }
    }
}
