package com.wonderlastking.graphqlexample.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wonderlastking.graphqlexample.model.Author;
import com.wonderlastking.graphqlexample.model.Book;
import com.wonderlastking.graphqlexample.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {

    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor().getId())
	    .orElse(null);
    }
}
