package com.edgar.workshopmongodb.services;

import com.edgar.workshopmongodb.domain.User;
import com.edgar.workshopmongodb.dto.UserDTO;
import com.edgar.workshopmongodb.repositories.UserRepository;
import com.edgar.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //CAMADA: O servico vai acessa o repositorio, que por sua vez vai acessar o banco (CRUD)
    // UserService -> UserRepository -> CRUD
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return  repository.findAll();
    }

    public User findById(String id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj){
        return repository.insert(obj);
    }

    public void delete(String id){
        findById(id); // aproveitando o metodo pra caso nao encontre o objeto com o id inf, sera retornada uma excessao
        repository.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDTO){ //serve para converter um UserDTO em USER
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
}
