package com.wonderlastking.graphqlexample.model;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String isbn;

    private int pageCount;

    private int price;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private Author author;

    public Book(){}

    public Book(String title, String isbn, int pageCount, Author author, int price) {
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.author = author;
        this.price = price;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public int getPageCount() {
	return pageCount;
    }

    public void setPageCount(int pageCount) {
	this.pageCount = pageCount;
    }

    public Author getAuthor() {
	return author;
    }

    public void setAuthor(Author author) {
	this.author = author;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) { return true; }
	if (o == null || getClass() != o.getClass()) { return false; }
	Book book = (Book) o;
	return Objects.equals(id, book.id) &&
	    Objects.equals(title, book.title) &&
	    Objects.equals(isbn, book.isbn) &&
	    Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id, title, isbn, author);
    }
}
