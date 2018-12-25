package com.wonderlastking.graphqlexample.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.wonderlastking.graphqlexample.model.Stock;
import com.wonderlastking.graphqlexample.publisher.StockTickerPublisher;
import org.reactivestreams.Publisher;


public class Subscription implements GraphQLSubscriptionResolver {

    private StockTickerPublisher stockTickerPublisher;

    public Subscription(StockTickerPublisher stockTickerPublisher) {
        this.stockTickerPublisher = stockTickerPublisher;
    }

    public Publisher<Stock> stockQuotes() {
	return stockTickerPublisher.getPublisher();
    }
}
