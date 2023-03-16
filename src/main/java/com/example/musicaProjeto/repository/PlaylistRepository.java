package com.example.musicaProjeto.repository;

import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Query("SELECT pl FROM Playlist pl WHERE LOWER(pl.playlistNome) = LOWER(:searchTerm)")
    Optional<Playlist> findByName(@Param("searchTerm") String playlistNome);

    @Query("SELECT pl FROM Playlist pl WHERE LOWER(pl.pessoa.nome) = LOWER(:pessoaNome)")
    List<Playlist> findAllByPessoaNome(@Param("pessoaNome") String pessoaNome);
}
