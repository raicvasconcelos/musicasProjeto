package com.example.musicaProjeto.repository;

import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Musica m WHERE m.musicaNome = ?1")
    boolean existsByName(String musicaNome);


    @Query("SELECT m FROM Musica m WHERE LOWER(m.genero.generoNome) = LOWER(:generoNome)")
    List<Musica> findAllByGeneroNome(@Param("generoNome") String generoNome);

    @Query("SELECT m FROM Musica m WHERE LOWER(m.musicaNome) = LOWER(:searchTerm)")
    Musica findByName(@Param("searchTerm") String musicaNome);
}


