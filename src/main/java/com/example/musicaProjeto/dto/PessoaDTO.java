package com.example.musicaProjeto.dto;

import com.example.musicaProjeto.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PessoaDTO {

    private Pessoa pessoa;
}
