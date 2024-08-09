package com.example.pruebaTecnicaGlab.repository;




import com.example.pruebaTecnicaGlab.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    
}
