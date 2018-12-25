package com.wonderlastking.graphqlexample.event;

import com.wonderlastking.graphqlexample.model.Stock;
import org.springframework.context.ApplicationEvent;

public class StockEvent extends ApplicationEvent {

    private Stock stock;

    public StockEvent(Object source, Stock stock) {
	super(source);
	this.stock = stock;
    }

    public Stock getStock() {
	return stock;
    }

    public void setStock(Stock stock) {
	this.stock = stock;
    }
}
