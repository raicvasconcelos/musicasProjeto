package com.example.musicaProjeto.repository;

import com.example.musicaProjeto.entity.Genero;
import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pessoa p WHERE LOWER(p.nome) = ?1")
    boolean existsByName(String nome);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pessoa p WHERE p.cpf = ?1")
    boolean existsByCPF(String cpf);

    @Query("SELECT p FROM Pessoa p WHERE LOWER(p.nome) = LOWER(:searchTerm)")
    Pessoa  findByName(@Param("searchTerm") String pessoaNome);


}
