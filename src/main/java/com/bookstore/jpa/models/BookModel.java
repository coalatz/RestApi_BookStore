package com.bookstore.jpa.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_BOOK")
public class BookModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherModel publisher;

    @ManyToMany
    @JoinTable(name = "tb_book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "Author_id"))
    private Set<AuthorModel> authors = new HashSet<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private ReviewModel review;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }

    public ReviewModel getReview() {
        return review;
    }

    public void setReview(ReviewModel review) {
        this.review = review;
    }

}
