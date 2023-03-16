package com.example.musicaProjeto.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String generoNome;

    @OneToMany
    private List<Musica> musicas;

}
