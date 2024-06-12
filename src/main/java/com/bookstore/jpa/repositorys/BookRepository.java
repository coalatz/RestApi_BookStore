package com.bookstore.jpa.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Integer> {

}
