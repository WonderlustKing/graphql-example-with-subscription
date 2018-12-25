package com.wonderlastking.graphqlexample.repository;

import com.wonderlastking.graphqlexample.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
