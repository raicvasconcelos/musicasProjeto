package com.example.musicaProjeto.controller;

import com.example.musicaProjeto.dto.GeneroDTO;
import com.example.musicaProjeto.entity.Genero;
import com.example.musicaProjeto.repository.GeneroRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/genero")
@AllArgsConstructor
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;
    @Operation(summary = "Adiciona um novo gênero.")
    @PostMapping
    public ResponseEntity cadastroGenero(@RequestBody GeneroDTO genero){
        if(!generoRepository.existsByName(genero.getGenero().getGeneroNome())){
            return ResponseEntity.status(HttpStatus.OK).body(generoRepository.save(genero.getGenero()));
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, String.format("Gênero já cadastrado"));
        }
    }
    @Operation(summary = "Lista todos os gêneros.")
    @GetMapping
    public List<Genero> listaGenero(){
        return generoRepository.findAll();
    }



}
