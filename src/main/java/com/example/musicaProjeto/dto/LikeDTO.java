package com.example.musicaProjeto.dto;

import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LikeDTO {

    private int pessoaID;
    private int musicaID;
}
