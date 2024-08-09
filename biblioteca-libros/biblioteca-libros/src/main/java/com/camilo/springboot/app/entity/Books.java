package com.camilo.springboot.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "editorial", nullable = false)
    private String editorial;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "idioma", nullable = false)
    private String idioma;

    @Column(name = "paginas", nullable = false)
    private Integer paginas;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "imgUrl", nullable = true)
    private String imgUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaPublicacion", nullable = true)
    private Date fechaPublicacion;
}
