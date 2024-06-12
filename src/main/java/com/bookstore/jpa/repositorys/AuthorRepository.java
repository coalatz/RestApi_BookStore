package com.bookstore.jpa.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.AuthorModel;

public interface AuthorRepository extends JpaRepository<AuthorModel, Integer> {

}
