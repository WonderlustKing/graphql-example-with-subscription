package com.wonderlastking.graphqlexample.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.wonderlastking.graphqlexample.event.StockEventPublisher;
import com.wonderlastking.graphqlexample.model.Author;
import com.wonderlastking.graphqlexample.model.Book;
import com.wonderlastking.graphqlexample.model.Stock;
import com.wonderlastking.graphqlexample.repository.AuthorRepository;
import com.wonderlastking.graphqlexample.repository.BookRepository;
import com.wonderlastking.graphqlexample.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class Mutation implements GraphQLMutationResolver {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private StockRepository stockRepository;

    @Autowired
    private StockEventPublisher stockEventPublisher;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository, StockRepository stockRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.stockRepository = stockRepository;
    }

    public Author addAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return authorRepository.save(author);
    }

    public Book addBook(String title, String isbn, Integer pageCount, Integer price, Long authorId) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);
        book.setPrice(price);
        book.setAuthor(authorRepository.findById(authorId).orElse(null));

        return bookRepository.save(book);
    }

    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    public Book updateBookPageCount(Long bookId, Integer pageCount) {
        Iterable<Book> bookI = bookRepository.findAll();
        Book book = bookRepository.findById(bookId).orElse(null);
        book.setPageCount(pageCount);

        return bookRepository.save(book);
    }

    @Transactional
    public Stock addStock(int numberOfStocks, int price, Long bookId) {
	Book book = bookRepository.findById(bookId).orElse(null);
	Stock stock = new Stock(book, numberOfStocks, price);

	Stock savedStock = stockRepository.save(stock);
	stockEventPublisher.doStuffAndPublishAnEvent(stock);
	return savedStock;
    }
}
