package br.com.alura.loja.model;

import javax.persistence.Entity;

@Entity
public class Book extends Product {

    private String author;
    private Integer numberOfPages;

    public Book(){
    }

    public Book(String author, Integer numberOfPages) {
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
