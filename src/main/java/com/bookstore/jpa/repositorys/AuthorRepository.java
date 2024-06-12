package com.bookstore.jpa.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.jpa.models.AuthorModel;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Integer> {

}
