package com.example.musicaProjeto.controller;

import com.example.musicaProjeto.dto.MusicaDTO;
import com.example.musicaProjeto.entity.Genero;
import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.repository.GeneroRepository;
import com.example.musicaProjeto.repository.MusicaRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musica")
public class MusicaController {

    @Autowired
    private MusicaRepository musicaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Operation(summary = "Adiciona uma nova música")
    @PostMapping
    public ResponseEntity criaMusica(@RequestBody MusicaDTO novaMusica){

        Optional<Genero> genero = generoRepository.findBySearchTerm(novaMusica.getGeneroNome());

        try{
            Genero generoEncontrado = genero.orElseThrow(() -> new Exception("Gênero não encontrado"));
            Musica musica = new Musica();
            musica.setMusicaNome(novaMusica.getMusica().getMusicaNome());
            musica.setArtista(novaMusica.getMusica().getArtista());
            musica.setGenero(generoEncontrado);
            return ResponseEntity.ok(musicaRepository.save(musica));

        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }


    }

    @Operation(summary = "Retorna todos as musica do mesmo gênero")
    @GetMapping("/{generoNome}")
    public ResponseEntity listaMusicaGenero(@PathVariable String generoNome)throws Exception{

        Optional<Genero> generoFind = generoRepository.findBySearchTerm(generoNome);
        System.out.println(generoNome);
        try{
            Genero generoEncontrado = generoFind.orElseThrow(() -> new Exception("Gênero não encontrado"));
            List<Musica> listaMusicasEncontradas = musicaRepository.findAllByGeneroNome(generoNome);

            return ResponseEntity.ok().body(listaMusicasEncontradas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Operation(summary = "Lista todos as músicas.")
    @GetMapping
    public List<Musica> listaMusica(){
        return musicaRepository.findAll();
    }

}
