package com.example.musicaProjeto.config;

import com.example.musicaProjeto.entity.Genero;
import com.example.musicaProjeto.entity.Musica;
import com.example.musicaProjeto.entity.Pessoa;
import com.example.musicaProjeto.entity.Playlist;
import com.example.musicaProjeto.repository.GeneroRepository;
import com.example.musicaProjeto.repository.MusicaRepository;
import com.example.musicaProjeto.repository.PessoaRepository;
import com.example.musicaProjeto.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Configuration
@AllArgsConstructor
public class DBConfig {

    private PessoaRepository pessoaRepository;

    private GeneroRepository generoRepository;

    private MusicaRepository musicaRepository;

    private PlaylistRepository playlistRepository;




    @PostConstruct
    public void initDB(){
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("Anthony Fantano");
        pessoa1.setCpf("552.986.098-07");
        pessoaRepository.saveAndFlush(pessoa1);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Frank");
        pessoa2.setCpf("822.966.112-97");
        pessoaRepository.saveAndFlush(pessoa2);


        Genero genero1 = new Genero();
        genero1.setGeneroNome("Dream Pop");
        generoRepository.saveAndFlush(genero1);
        Genero genero2 = new Genero();
        genero2.setGeneroNome("Post-Punk Sovietico");
        generoRepository.saveAndFlush(genero2);
        Genero genero3 = new Genero();
        genero3.setGeneroNome("Metal Progressivo");
        generoRepository.saveAndFlush(genero3);

        Musica musica1 = new Musica();
        musica1.setMusicaNome("Silver Soul");
        musica1.setArtista("Beach House");
        Genero dreamPop = generoRepository.findGenero("Dream Pop");
        musica1.setGenero(dreamPop );
        musicaRepository.saveAndFlush(musica1);

        Musica musica2 = new Musica();
        musica2.setMusicaNome("Parabola");
        musica2.setArtista("Tool");
        Genero metalProg = generoRepository.findGenero("Metal Progressivo");
        musica2.setGenero(metalProg );
        musicaRepository.saveAndFlush(musica2);

        Musica musica3 = new Musica();
        musica3.setMusicaNome("Na Zare");
        musica3.setArtista("Alyans");
        Genero sovietPunk= generoRepository.findGenero("Post-Punk Sovietico");
        musica3.setGenero(sovietPunk);
        musicaRepository.saveAndFlush(musica3);


        Pessoa anthony = pessoaRepository.findByName("Anthony Fantano");
        Musica parabol = musicaRepository.findByName("Parabola");
        Musica silverSoul = musicaRepository.findByName("Silver Soul");
        anthony.setLike(new ArrayList<>());
        anthony.getLike().add(parabol);
        anthony.getLike().add(silverSoul);
        pessoaRepository.saveAndFlush(anthony);

        Playlist playlist1 = new Playlist();
        playlist1.setPlaylistNome("playlist1");
        playlist1.setPessoa(anthony);
        playlist1.setMusicas(new ArrayList<>());
        playlist1.getMusicas().add(parabol);
        playlist1.getMusicas().add(silverSoul);
        Musica naZare = musicaRepository.findByName("Na Zare");
        playlist1.getMusicas().add(naZare);
        playlistRepository.saveAndFlush(playlist1);








    }


}
