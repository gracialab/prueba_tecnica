package com.tecnica.prueba.domain.detbook;

import jakarta.persistence.*;

@Entity
@Table(name = "DetBook")
public class DetBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autor;
    private String editorial;
    private String fecha_publicacion;

    public DetBook() {
    }

    public DetBook(String autor, String editorial, String fecha_publicacion) {
        this.autor = autor;
        this.editorial = editorial;
        this.fecha_publicacion = fecha_publicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getfecha_publicacion() {
        return fecha_publicacion;
    }

    public void setfecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }


}
