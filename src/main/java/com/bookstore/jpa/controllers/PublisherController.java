package com.bookstore.jpa.controllers;

import java.util.Optional;

import javax.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.jpa.models.PublisherModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositorys.PublisherRespository;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    PublisherRespository publisherRespository;

    @GetMapping("/{id}")
    public ResponseEntity getPublisher(@PathVariable Integer id) {
        Optional<PublisherModel> encontrado = publisherRespository.findById(id);
        if (encontrado.isPresent()) {
            return new ResponseEntity<>(encontrado, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<PublisherModel> save(@RequestBody @Valid PublisherModel publisher) {
        PublisherModel salvo = publisherRespository.save(publisher);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        publisherRespository.findById(id).map(encontrado -> {
            publisherRespository.delete(encontrado);
            return encontrado;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PublisherModel> allPublishers() {
        return publisherRespository.findAll();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid PublisherModel publisher, @PathVariable Integer id) {
        publisherRespository.findById(id).map(encontrado -> {
            publisher.setId(encontrado.getId());
            publisherRespository.save(publisher);
            return publisher;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
