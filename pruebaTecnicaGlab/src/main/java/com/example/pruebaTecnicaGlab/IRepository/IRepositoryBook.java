package com.example.pruebaTecnicaGlab.IRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pruebaTecnicaGlab.entity.Book;





public interface IRepositoryBook extends JpaRepository<Book, Long> {
    
}
