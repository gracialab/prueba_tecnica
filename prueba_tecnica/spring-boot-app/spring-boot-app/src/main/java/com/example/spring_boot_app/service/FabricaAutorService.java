package com.example.spring_boot_app.service;


import org.springframework.stereotype.Service;
import com.example.spring_boot_app.model.Autor;
import com.example.spring_boot_app.dto.AutorDTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class FabricaAutorService {
    public Autor crearAutor(AutorDTO autorDTO) {
        return new Autor(autorDTO);
    }

    public AutorDTO crearAutorDTO(Autor autor){
        return new AutorDTO(autor);
    }

    public List<AutorDTO> crearAutoresDTO(List<Autor> listaAutores){
        List<AutorDTO> autorDTOS= new ArrayList<>();
        listaAutores.stream().forEach(
                autor -> {
                    autorDTOS.add(crearAutorDTO(autor));
                }
        );
        return  autorDTOS;
    }

}
