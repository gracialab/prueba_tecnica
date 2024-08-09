package com.example.spring_boot_app.model;

import com.example.spring_boot_app.dto.AutorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@NoArgsConstructor
@Entity //Esta clase Autor se va a mapear con una tabla de datos
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //autogenera numeros para la clave primaria
    private Integer id;
    private String nombres;
    private String apellidos;
    private String telefono;
    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;


    public Autor(AutorDTO autorDTO) {
        this.id = autorDTO.getId();
        this.nombres = autorDTO.getNombres();
        this.apellidos = autorDTO.getApellidos();
        this.telefono = autorDTO.getTelefono();
    }
}

