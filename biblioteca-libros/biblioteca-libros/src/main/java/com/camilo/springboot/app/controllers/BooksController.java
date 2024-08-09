package com.camilo.springboot.app.controllers;


import com.camilo.springboot.app.entity.Books;
import com.camilo.springboot.app.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/books")
public class BooksController {

    @Autowired
    private BooksService service;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Books>> lista() {
        List<Books> lista = service.listaBooks();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<Books> insertar(@RequestBody Books books) {
        Books booksSalida = service.insertaActulizaBooks(books);
        return ResponseEntity.ok(booksSalida);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<Books> updateBook(@RequestBody Books books) {
        if (books == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Optional<Books> optBooks = service.findBooksById(books.getId());
            if (optBooks.isPresent()) {
                Books booksUpdate = service.insertaActulizaBooks(books);
                return ResponseEntity.ok(booksUpdate);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Books> deleteBook(@PathVariable("id") Long id) {
        Optional<Books> optBooks = service.findBooksById(id);

        if (optBooks.isPresent()) {
            service.deleteBooksById(id);
            Optional<Books> optDelete = service.findBooksById(id);

            if (optDelete.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(optBooks.get());
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/paramId/{id}")
    @ResponseBody
    public ResponseEntity<Books> getById(@PathVariable("id") Long id) {
        Optional<Books> optBooks = service.findBooksById(id);
        if (optBooks.isPresent()) {
            return ResponseEntity.ok(optBooks.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
