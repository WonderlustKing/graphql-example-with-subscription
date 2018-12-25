package com.wonderlastking.graphqlexample.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wonderlastking.graphqlexample.model.Book;
import com.wonderlastking.graphqlexample.model.Stock;
import com.wonderlastking.graphqlexample.repository.BookRepository;

public class StockResolver implements GraphQLResolver<Stock> {

    private BookRepository bookRepository;

    public StockResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBook(Stock stock) {
        return bookRepository.findById(stock.getBook().getId()).orElse(null);
    }
}
