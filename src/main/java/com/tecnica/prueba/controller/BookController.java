package com.tecnica.prueba.controller;

import com.tecnica.prueba.DTO.*;
import com.tecnica.prueba.domain.book.Book;
import com.tecnica.prueba.domain.detbook.DetBook;
import com.tecnica.prueba.repository.IBookRepository;
import com.tecnica.prueba.repository.IDetBookRepository;
import com.tecnica.prueba.response.ResponseBookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Book", description = "CRUD Book")
public class BookController {
    private final IBookRepository iBookRepository;
    private final IDetBookRepository iDetBookRepository;

    public BookController(IBookRepository iBookRepository, IDetBookRepository iDetBookRepository){
        this.iBookRepository = iBookRepository;
        this.iDetBookRepository = iDetBookRepository;
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar libro", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<?>PostBook(@RequestBody @Valid CreateBookDTO createBookDTO, UriComponentsBuilder uriComponentsBuilder){
        Optional<Book> libroExistente = iBookRepository.findByNombre(createBookDTO.nombre());
        if (libroExistente.isPresent()){
            String mensajeError ="Este libro ya se encuentra registrado";
            return ResponseEntity.badRequest().body(mensajeError);
        }
        Book book = new Book(createBookDTO);
        iBookRepository.save(book);
        ResponseBookDTO responseBookDTO =
            new ResponseBookDTO(
                book.getId(),
                book.getNombre()
            );
        URI url = uriComponentsBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri();

        return ResponseEntity.created(url).body(responseBookDTO);
    }

    @GetMapping
    @Operation(summary="Lista de libros", security= @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<List<BookListDTO>> getAllBooks(){
        List<Book> books = iBookRepository.findAll();
        List<BookListDTO> bookListDTOS = books.stream()
                .map(BookListDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookListDTOS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalles del libro", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<BookDetailsDTO> getBookById(@PathVariable Long id) {
        return iBookRepository.findById(id)
                .flatMap(book -> iDetBookRepository.findById(book.getId())
                        .map(detBook -> {
                            BookDetailsDTO detailsDTO = new BookDetailsDTO(
                                    detBook.getId(),
                                    detBook.getAutor(),
                                    detBook.getEditorial(),
                                    detBook.getfecha_publicacion()
                            );
                            return ResponseEntity.ok(detailsDTO);
                        })
                )
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping
    @Operation(summary = "Actualizar libro y detalles asociados", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<?> updateBookAndDetails(@RequestBody UpdateBookAndDetailsDTO updateBookAndDetailsDTO) {
        return iBookRepository.findById(updateBookAndDetailsDTO.Id())
                .map(book -> {
                    book.setNombre(updateBookAndDetailsDTO.name());

                    DetBookDTO detBookDTO = updateBookAndDetailsDTO.detalle_libro();
                    if (detBookDTO != null) {
                        DetBook detBook = book.getDetalle_libro();
                        if (detBook != null) {

                            detBook.setAutor(detBookDTO.autor());
                            detBook.setEditorial(detBookDTO.editorial());
                            detBook.setfecha_publicacion(detBookDTO.fechaPublicacion());
                        }
                        iDetBookRepository.save(detBook);
                    }

                    iBookRepository.save(book);
                    return ResponseEntity.ok().build(); // 200 OK
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar libro y detalles asociados", security = @SecurityRequirement(name = "bearer-key"))
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return iBookRepository.findById(id)
                .map(book -> {
                    iBookRepository.delete(book);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
