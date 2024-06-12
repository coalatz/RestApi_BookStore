package com.bookstore.jpa.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.jpa.models.PublisherModel;

public interface PublisherRespository extends JpaRepository<PublisherModel, Integer> {

}
