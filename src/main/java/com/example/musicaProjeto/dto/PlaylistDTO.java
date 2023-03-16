package com.example.musicaProjeto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaylistDTO {

    @NotBlank(message="O nome da playlist é obrigatório.")
    private String playlistNome;
    @NotBlank(message="O ID da pessoa é obrigatório.")
    private int pessoaID;
    @NotBlank(message="O ID da música é obrigatório.")
    private int MusicaID;
}
