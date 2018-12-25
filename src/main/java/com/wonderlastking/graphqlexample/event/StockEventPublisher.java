package com.wonderlastking.graphqlexample.event;

import com.wonderlastking.graphqlexample.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StockEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent(Stock stock) {
	System.out.println("Publishing stock event. ");
	StockEvent stockEvent = new StockEvent(this, stock);
	applicationEventPublisher.publishEvent(stockEvent);
    }
}
