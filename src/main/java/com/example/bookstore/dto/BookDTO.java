package com.example.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    @Id
    private String ISBN;
    private String title;
    private String genre;

    public BookDTO(String ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
    }
    public BookDTO(String ISBN, String title, String genre) {
        this.ISBN = ISBN;
        this.title = title;
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    @Column(name = "genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }
}