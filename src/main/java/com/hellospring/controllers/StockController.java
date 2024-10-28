package com.hellospring.controllers;

import com.hellospring.jms.JmsProducer;
import com.hellospring.model.Stock;
import com.hellospring.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    private static final Logger log = LoggerFactory.getLogger(StockController.class);

    private final StockService stockService;

    private final JmsProducer jmsProducer;

    @Autowired
    public StockController(StockService stockService, JmsProducer jmsProducer) {
        this.stockService = stockService;
        this.jmsProducer = jmsProducer;
    }

    @GetMapping(value = "/getByCompany")
    public List<Stock> getStockByCompany(@RequestParam(name = "company_name") String company){
        log.info("getStockByCompany called with following parameter {}", company);

        if(company == null || company.isEmpty()){
            throw new IllegalStateException("incorrect company name");
        }
        return stockService.getStockByCompany(company);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> addStock(@RequestBody Stock stock){
        log.info("addStock called with following parameter {}",stock);

        if(stock == null){
            return ResponseEntity.badRequest().body(false);
        }
        try{
            stockService.addStock(stock);
            jmsProducer.produceMessage(stock);
        } catch(Exception e){
            log.error("error occurred while adding stock {}",stock,e);
            return ResponseEntity.internalServerError().body(false);
        }

        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/add_file")
    public ResponseEntity<Boolean> addStockFromFile(@RequestBody String fileName){
        log.info("addStockFromFile called with following parameter {}",fileName);

        if(fileName == null || !fileName.endsWith(".json")){
            return ResponseEntity.badRequest().body(false);
        }
        try{
            stockService.readStockFile(fileName);
        } catch(Exception e){
            log.error("error occurred while adding fileName {}", fileName, e);
            return ResponseEntity.internalServerError().body(false);
        }

        return ResponseEntity.ok(true);
    }
}
