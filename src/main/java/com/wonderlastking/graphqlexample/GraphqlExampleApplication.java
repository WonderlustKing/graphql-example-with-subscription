package com.wonderlastking.graphqlexample;

import com.wonderlastking.graphqlexample.model.Author;
import com.wonderlastking.graphqlexample.model.Book;
import com.wonderlastking.graphqlexample.publisher.StockTickerPublisher;
import com.wonderlastking.graphqlexample.repository.AuthorRepository;
import com.wonderlastking.graphqlexample.repository.BookRepository;
import com.wonderlastking.graphqlexample.repository.StockRepository;
import com.wonderlastking.graphqlexample.resolver.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GraphqlExampleApplication {

    public static void main(String[] args) {
	SpringApplication.run(GraphqlExampleApplication.class, args);
    }

    @Bean
    public BookResolver bookResolver(AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    @Bean
    public StockResolver stockResolver(BookRepository bookRepository) {
        return new StockResolver(bookRepository);
    }

    @Bean
    public Query query(AuthorRepository authorRepository, BookRepository bookRepository, StockRepository stockRepository) {
        return new Query(authorRepository, bookRepository, stockRepository);
    }

    @Bean
    public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository, StockRepository stockRepository) {
        return new Mutation(authorRepository, bookRepository, stockRepository);
    }

    @Bean
    public Subscription subscription(StockTickerPublisher stockTickerPublisher) {
	return new Subscription(stockTickerPublisher);
    }

    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
        return (args) -> {
	    Author author = new Author("Giorgos", "Giorgaras");
	    authorRepository.save(author);

	    Book book = new Book("TitleHere", "219821298323", 280, author, 34);
	    bookRepository.save(book);
	};
    }
}
