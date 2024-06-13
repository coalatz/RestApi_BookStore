package com.bookstore.jpa.controllers;

import java.util.*;

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

import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositorys.ReviewRepository;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @PostMapping("/save")
    public ResponseEntity<ReviewModel> save(@RequestBody @Valid ReviewModel review) {
        ReviewModel salvo = reviewRepository.save(review);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getId(@PathVariable Integer id) {
        Optional<ReviewModel> encontrado = reviewRepository.findById(id);
        if (encontrado.isPresent()) {
            return new ResponseEntity<>(encontrado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        reviewRepository.findById(id).map(encontrado -> {
            reviewRepository.delete(encontrado);
            return encontrado;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<ReviewModel> allReviews() {
        return reviewRepository.findAll();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid ReviewModel review, @PathVariable Integer id) {
        reviewRepository.findById(id).map(encontrado -> {
            review.setId(encontrado.getId());
            reviewRepository.save(review);
            return review;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
