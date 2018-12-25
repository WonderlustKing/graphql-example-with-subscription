package com.wonderlastking.graphqlexample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

@Entity
public class Author {

    @Id
    @GeneratedValue()
    private Long id;

    private String firstName;

    private String lastName;

    public Author() {}

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) { return true; }
	if (o == null || getClass() != o.getClass()) { return false; }
	Author author = (Author) o;
	return Objects.equals(id, author.id) &&
	    Objects.equals(firstName, author.firstName) &&
	    Objects.equals(lastName, author.lastName);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id, firstName, lastName);
    }
}
