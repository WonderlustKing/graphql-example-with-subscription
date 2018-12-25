package com.wonderlastking.graphqlexample.publisher;

import com.wonderlastking.graphqlexample.event.StockEvent;
import com.wonderlastking.graphqlexample.model.Stock;
import io.reactivex.*;
import io.reactivex.observables.ConnectableObservable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class StockTickerPublisher {

    private final Flowable<Stock> publisher;

    private List<Stock> stocks = new ArrayList<>();

    @TransactionalEventListener
    public void handleStockEvent(StockEvent event) {
	System.out.println("Received stocks event: " + event.getStock().getId());
	this.stocks.add(event.getStock());
    }

    public StockTickerPublisher() {
	Observable<Stock> stockObservable = Observable.create(e -> {
	    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	    executorService.scheduleAtFixedRate(newStock(e), 15, 20, TimeUnit.SECONDS);
	});

	ConnectableObservable<Stock> connectableObservable = stockObservable.share().publish();
	connectableObservable.connect();

	publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    private Runnable newStock(ObservableEmitter<Stock> emitter) {
	return () -> emitStocks(emitter, stocks);
    }

    private void emitStocks(ObservableEmitter<Stock> emitter, List<Stock> stocks) {
        if (!stocks.isEmpty()) {
            List<Stock> copies = new ArrayList<>(stocks);
            for (Stock s : copies) {
		try {
		    emitter.onNext(s);
		    stocks.remove(s);
		} catch (RuntimeException e) {
		    System.out.println("Error on stock emit: " + e.getMessage());
		}
	    }
	}
    }

    public Flowable<Stock> getPublisher() {
	return publisher;
    }

    public Flowable<Stock> getPublisher(List<Long> stockCodes) {
	if (stockCodes != null) {
	    return publisher.filter(stock -> stockCodes.contains(stock.getId()));
	}
	return publisher;
    }

}
