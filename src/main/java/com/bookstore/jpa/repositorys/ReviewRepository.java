package com.bookstore.jpa.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.ReviewModel;

public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {

}
