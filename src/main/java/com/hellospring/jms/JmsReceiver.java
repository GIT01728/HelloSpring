package com.hellospring.jms;

import com.hellospring.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;

@Component
public class JmsReceiver {

    private static final Logger log = LoggerFactory.getLogger(JmsReceiver.class);


    @JmsListener(destination = "equity.stock", containerFactory = "jmsFactory")
    public void receiveMessage(Stock stock){
        log.info("message received : {}",stock);
    }
}
