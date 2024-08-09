package com.natalyrodriguez.ApiTest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.natalyrodriguez.ApiTest.model.Book;
import com.natalyrodriguez.ApiTest.dto.BookDTO;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);
}
