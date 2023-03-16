package com.example.musicaProjeto.dto;

import com.example.musicaProjeto.entity.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeneroDTO {
    private Genero genero;
}
