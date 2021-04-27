package com.edgar.workshopmongodb.services;

import com.edgar.workshopmongodb.domain.User;
import com.edgar.workshopmongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    //CAMADA: O servico vai acessa o repositorio, que por sua vez vai acessar o banco (CRUD)
    // UserService -> UserRepository -> CRUD
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return  repository.findAll();
    }
}
