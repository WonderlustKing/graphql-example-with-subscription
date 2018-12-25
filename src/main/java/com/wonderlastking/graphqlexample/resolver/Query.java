package com.wonderlastking.graphqlexample.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wonderlastking.graphqlexample.model.Author;
import com.wonderlastking.graphqlexample.model.Book;
import com.wonderlastking.graphqlexample.model.Stock;
import com.wonderlastking.graphqlexample.repository.AuthorRepository;
import com.wonderlastking.graphqlexample.repository.BookRepository;
import com.wonderlastking.graphqlexample.repository.StockRepository;

public class Query implements GraphQLQueryResolver {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private StockRepository stockRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository, StockRepository stockRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.stockRepository = stockRepository;
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    public long countAuthors() {
        return authorRepository.count();
    }

    public long countBooks() {
        return bookRepository.count();
    }
}
