package com.bookstore.jpa.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.repositorys.BookRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/livros")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/save")
    public ResponseEntity<BookModel> save(@RequestBody BookModel bookModel) {
        bookRepository.save(bookModel);
        return new ResponseEntity<>(bookModel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> buscarId(@PathVariable("id") Integer id) {
        Optional<BookModel> encontrado = bookRepository.findById(id);
        if (encontrado.isPresent()) {
            return new ResponseEntity<>(encontrado.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteId(@PathVariable("id") Integer id) {
        bookRepository.findById(id).map(livro -> {
            bookRepository.delete(livro);
            return livro;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao foi possivel deletar o livro"));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable Integer id, @RequestBody BookModel book) {
        bookRepository.findById(id).map(encontrado -> {
            book.setId(encontrado.getId());
            bookRepository.save(book);
            return book;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado."));
    }

    @GetMapping("/all")
    public List<BookModel> allBooks() {
        return bookRepository.findAll();
    }

}
