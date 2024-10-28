package com.hellospring.jms;

import com.hellospring.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void produceMessage(Stock stock){
        jmsTemplate.convertAndSend("equity.stock", stock);

    }
}
