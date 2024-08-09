package com.biblioteca.api.controller;

import com.biblioteca.api.application.dto.BookDTO;
import com.biblioteca.api.application.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/biblioteca")
public class BookController {

    private final IBookService iBookService;

    @GetMapping("/libros")
    public ResponseEntity<List<BookDTO>> getAllBook(){
        return ResponseEntity.ok(iBookService.getAllBooks());
    }

    @PostMapping("/guardarlibro")
    public ResponseEntity<BookDTO> savedBook(@RequestBody BookDTO book){
        return ResponseEntity.status(HttpStatus.CREATED).body(iBookService.saveBook(book));
    }

    @GetMapping("/libro/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(iBookService.getBookById(id));
    }

    @PutMapping("/actualizarlibro/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO book){
        return ResponseEntity.ok(iBookService.updateBook(id, book));
    }

    @DeleteMapping("/eliminarlibro/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        iBookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
