package com.example.musicaProjeto.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String musicaNome;
    private String artista;
    @ManyToOne
    private Genero genero;
//    @JoinColumn(name = "genero_id", referencedColumnName = "id")
//    @OneToOne(cascade = CascadeType.PERSIST)
//    private Like like;
    @ManyToOne
    private Pessoa like;





}
