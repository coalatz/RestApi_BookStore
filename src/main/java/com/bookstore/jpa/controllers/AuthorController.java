package com.bookstore.jpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.repositorys.AuthorRepository;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/save")
    public ResponseEntity<AuthorModel> save(@RequestBody @Valid AuthorModel author) {
        authorRepository.save(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarId(@PathVariable Integer id) {
        Optional<AuthorModel> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return new ResponseEntity<>("Autor nao encontrado.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        authorRepository
                .findById(id)
                .map(encontrado -> {
                    authorRepository.delete(encontrado);
                    return encontrado;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao foi possivel deletar o author"));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateId(@RequestBody @Valid AuthorModel author, @PathVariable Integer id) {
        authorRepository.findById(id).map(encontrado -> {
            author.setId(encontrado.getId());
            authorRepository.save(author);
            return author;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor nao encontrado"));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorModel> allAuthors() {
        return authorRepository.findAll();
    }

}