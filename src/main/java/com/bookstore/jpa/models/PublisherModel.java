package com.bookstore.jpa.models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_PUBLISHER")
public class PublisherModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<BookModel> books = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<BookModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }

}
