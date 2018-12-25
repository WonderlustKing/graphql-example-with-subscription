package com.wonderlastking.graphqlexample.model;

import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    @JoinColumn(name = "book_id")
    private Book book;

    private int numberOfStocks;

    private int price;

    public Stock(){}

    public Stock(Book book, int numberOfStocks, int price) {
	this.book = book;
	this.numberOfStocks = numberOfStocks;
	this.price = price;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Book getBook() {
	return book;
    }

    public void setBook(Book book) {
	this.book = book;
    }

    public int getNumberOfStocks() {
	return numberOfStocks;
    }

    public void setNumberOfStocks(int numberOfStocks) {
	this.numberOfStocks = numberOfStocks;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }
}
