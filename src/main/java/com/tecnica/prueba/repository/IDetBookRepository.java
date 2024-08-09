package com.tecnica.prueba.repository;

import com.tecnica.prueba.domain.detbook.DetBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetBookRepository  extends JpaRepository<DetBook, Long> {
}
