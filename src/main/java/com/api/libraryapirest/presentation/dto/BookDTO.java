package com.api.libraryapirest.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private long id;
    private String title;
    private String author;
    private LocalDate publisherDate;
    private String isbn;
    private BigDecimal price;

}
