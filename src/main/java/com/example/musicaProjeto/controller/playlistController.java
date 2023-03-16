package com.example.musicaProjeto.controller;

import com.example.musicaProjeto.dto.PlaylistDTO;
import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.entity.Pessoa;
import com.example.musicaProjeto.entity.Playlist;
import com.example.musicaProjeto.repository.MusicaRepository;
import com.example.musicaProjeto.repository.PessoaRepository;
import com.example.musicaProjeto.repository.PlaylistRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/playlist")
public class playlistController {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaRepository musicaRepository;

    @Operation(summary = "Cria uma playlist nova")
    @PostMapping
    public ResponseEntity<?> criaPlaylist(@RequestBody PlaylistDTO playlist){

        Optional<Pessoa> pessoaFindID = pessoaRepository.findById(playlist.getPessoaID());
        Optional<Musica> musicaFindID = musicaRepository.findById(playlist.getMusicaID());
        Optional<Playlist> playlistFind = playlistRepository.findByName(playlist.getPlaylistNome());


        try{
            Musica musicaEncontrada = musicaFindID.orElseThrow(() -> new Exception("Musica não encontrada"));
            Pessoa pessoaEncontrada = pessoaFindID.orElseThrow(() -> new Exception("Pessoa não encontrada"));

            boolean playlistExiste = playlistRepository.findAllByPessoaNome(pessoaEncontrada.getNome())
                    .stream().anyMatch(pl -> pl.getPlaylistNome().equalsIgnoreCase(playlist.getPlaylistNome()));

          if(playlistExiste){
              return ResponseEntity.badRequest().body("já existe uma playlist com esse  nome");
          }


            Playlist playlist1 = new Playlist();
            playlist1.setMusicas(new ArrayList<>());

            playlist1.setPlaylistNome(playlist.getPlaylistNome());
            playlist1.setPessoa(pessoaEncontrada);
            playlist1.getMusicas().add(musicaEncontrada);

            return ResponseEntity.ok().body(playlistRepository.save(playlist1));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
    @Operation(summary = "Adiciona uma nova música à playlist")
    @PutMapping
    public ResponseEntity adicionaMusicaPlaylist(@RequestBody PlaylistDTO addMusica){
        Optional<Pessoa> pessoaFindID = pessoaRepository.findById(addMusica.getPessoaID());
        Optional<Musica> musicaFindID = musicaRepository.findById(addMusica.getMusicaID());
        Optional<Playlist> playlistFind = playlistRepository.findByName(addMusica.getPlaylistNome());

        try{
            Musica musicaEncontrada = musicaFindID.orElseThrow(() -> new Exception("Musica não encontrada"));
            Pessoa pessoaEncontrada = pessoaFindID.orElseThrow(() -> new Exception("Pessoa não encontrada"));
            Playlist playlistEncontrada = playlistFind.orElseThrow(() -> new Exception("Playlist não encontrada"));
            playlistEncontrada.getMusicas().add(musicaEncontrada);
            return ResponseEntity.ok().body(playlistRepository.save(playlistEncontrada));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }



    }


    @Operation(summary = "Remove uma playlist")
    @DeleteMapping("/{pessoaID}/{playlistNome}")
    public ResponseEntity deletePlaylist(@PathVariable Integer pessoaID, @PathVariable String playlistNome) {
        Optional<Pessoa> pessoaFindID = pessoaRepository.findById(pessoaID);
        Optional<Playlist> playlistFind = playlistRepository.findByName(playlistNome);

        try {
            Pessoa pessoaEncontrada = pessoaFindID.orElseThrow(() -> new Exception("Pessoa não encontrada"));
            Playlist playlistEncontrada = playlistFind.orElseThrow(() -> new Exception("Playlist não encontrada"));
             playlistRepository.delete(playlistEncontrada);

            List<Playlist> playlistsRestantes = playlistRepository.findAllByPessoaNome(pessoaEncontrada.getNome());
            return ResponseEntity.ok().body(playlistsRestantes);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
    @Operation(summary = "Remove uma musica de uma playlist")
    @DeleteMapping("/deletaMusicaPlaylist")
    public ResponseEntity deleteMusicaPlaylist(@RequestBody PlaylistDTO deletaMusicaPlaylist) {
        Optional<Pessoa> pessoaFindID = pessoaRepository.findById(deletaMusicaPlaylist.getPessoaID());
        Optional<Musica> musicaFindID = musicaRepository.findById(deletaMusicaPlaylist.getMusicaID());

        Optional<Playlist> playlistFind = playlistRepository.findByName(deletaMusicaPlaylist.getPlaylistNome());

        try {
            Musica musicaEncontrada = musicaFindID.orElseThrow(() -> new Exception("Musica não encontrada"));
            Pessoa pessoaEncontrada = pessoaFindID.orElseThrow(() -> new Exception("Pessoa não encontrada"));
            Playlist playlistEncontrada = playlistFind.orElseThrow(() -> new Exception("Playlist não encontrada"));

            boolean pessoaNaLista = playlistEncontrada.getPessoa().equals(pessoaEncontrada);

            if(!pessoaNaLista){return ResponseEntity.badRequest().body("Essa pessoa não possue essa playlist");}

           boolean musicaNaLista = playlistEncontrada.getMusicas().stream().anyMatch(m -> m.getMusicaNome()
                   .equalsIgnoreCase(musicaEncontrada.getMusicaNome()));

            if(!musicaNaLista){return ResponseEntity.badRequest().body("Música não encontrada na playlist");}

            playlistEncontrada.getMusicas().remove(musicaEncontrada);
            return ResponseEntity.ok().body(playlistRepository.save(playlistEncontrada));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

    @Operation(summary = "Encontra playlists no nome da pessoa")
    @GetMapping("/{pessoaNome}")
    public ResponseEntity listaPlaylistPessoa(@PathVariable String pessoaNome)throws Exception{

        List<Playlist> playlistsFind = playlistRepository.findAllByPessoaNome(pessoaNome);

        if(playlistsFind.isEmpty()){
            return ResponseEntity.badRequest().body("playlist não encontrada");
        }
        return ResponseEntity.ok(playlistsFind);


    }



}

