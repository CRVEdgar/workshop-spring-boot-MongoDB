package com.edgar.workshopmongodb.config;

import com.edgar.workshopmongodb.domain.Post;
import com.edgar.workshopmongodb.domain.User;
import com.edgar.workshopmongodb.dto.AuthorDTO;
import com.edgar.workshopmongodb.repositories.PostRepository;
import com.edgar.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat formatacao = new SimpleDateFormat("dd/MM/yyyy");
        formatacao.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll(); //limpar a colecao antiga de usuarios
        postRepository.deleteAll(); //limpar a colecao antiga de post

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null,formatacao.parse("21/03/2018"),"Partiu Viagem", "Vou viajar para Sao Paulo, abracos", new AuthorDTO(maria));
        Post post2 = new Post(null, formatacao.parse("23/08/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
        postRepository.saveAll(Arrays.asList(post1,post2));
    }
}
