package com.example.musicaProjeto.dto;

import com.example.musicaProjeto.entity.Musica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MusicaDTO {

    private Musica musica;

    private String generoNome;
}
