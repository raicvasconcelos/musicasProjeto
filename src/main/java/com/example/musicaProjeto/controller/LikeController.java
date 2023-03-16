package com.example.musicaProjeto.controller;

import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.entity.Pessoa;
import com.example.musicaProjeto.repository.MusicaRepository;
import com.example.musicaProjeto.repository.PessoaRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MusicaRepository musicaRepository;

    @Operation(summary = "Adiciona um nova música as favoritas de uma pessoa")
    @PutMapping("/{pessoaID}/{musicaID}")
    public ResponseEntity updateMyEntity(@PathVariable Integer pessoaID, @PathVariable Integer musicaID) {

        Optional<Pessoa> pessoaFindID = pessoaRepository.findById(pessoaID);
        Optional<Musica> musicaFindID = musicaRepository.findById(musicaID);

        try{
            Musica musicaEncontrada = musicaFindID.orElseThrow(() -> new Exception("Musica não encontrada"));
            Pessoa pessoaEncontrada = pessoaFindID.orElseThrow(() -> new Exception("Pessoa não encontrada"));
            pessoaEncontrada.getLike().add(musicaEncontrada);
            return ResponseEntity.ok().body(pessoaRepository.save(pessoaEncontrada));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

    @Operation(summary = "Remove uma musica das favoritas de uma pessoa")
    @DeleteMapping("/{pessoaID}/{musicaID}")
    public ResponseEntity removeLike(@PathVariable Integer pessoaID, @PathVariable Integer musicaID) {

        Optional<Pessoa> pessoaFindID = pessoaRepository.findById(pessoaID);
        Optional<Musica> musicaFindID = musicaRepository.findById(musicaID);

        try{
            Musica musicaEncontrada = musicaFindID.orElseThrow(() -> new Exception("Musica não encontrada"));
            Pessoa pessoaEncontrada = pessoaFindID.orElseThrow(() -> new Exception("Pessoa não encontrada"));
            pessoaEncontrada.getLike().remove(musicaEncontrada);
            pessoaRepository.save(pessoaEncontrada);
             return ResponseEntity.ok(pessoaRepository.findById(pessoaID));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
    @Operation(summary = "Retorna as musicas favoritadas pela pessoa")
    @GetMapping("/{pessoaID}")
    public ResponseEntity encontraLikePessoa(@PathVariable Integer pessoaID) throws Exception {
        Pessoa pessoaFindID = pessoaRepository.findById(pessoaID)
                .orElseThrow(() -> new Exception("Pessoa não encontrada"));

        return ResponseEntity.ok(pessoaFindID.getLike());

    }
}
