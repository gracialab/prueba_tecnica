package com.api.libraryapirest.presentation.controller;

import com.api.libraryapirest.presentation.dto.BookDTO;
import com.api.libraryapirest.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    //FindAll Book
    @GetMapping("/find")
    public ResponseEntity<List<BookDTO>> findAll() {
        return new ResponseEntity<>(this.bookService.findAll(), HttpStatus.OK);
    }

    //Find by id
    @GetMapping("/find/{id}")
    public ResponseEntity<BookDTO> findAll(@PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.findById(id), HttpStatus.OK);
    }

    //Create Book
    @PostMapping("/book")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO BookDTO) {
        return new ResponseEntity<>(this.bookService.createBook(BookDTO), HttpStatus.CREATED);
    }

    //Update Book
    @PutMapping("/update/{id}")
    public  ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO BookDTO, @PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.updateBook(BookDTO, id), HttpStatus.OK);
    }

    //Delete Book
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        return new ResponseEntity<>(this.bookService.deleteBook(id), HttpStatus.NO_CONTENT);
    }
}
