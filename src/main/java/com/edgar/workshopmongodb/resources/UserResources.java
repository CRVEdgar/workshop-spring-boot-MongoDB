package com.edgar.workshopmongodb.resources;

import com.edgar.workshopmongodb.domain.User;
import com.edgar.workshopmongodb.dto.UserDTO;
import com.edgar.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    // CAMADAS: controlador acessa o servico, que por sua vez acessa o repositorio
    // UserResources -> UserService -> UserRepository
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> userDTOList = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); //conversao de um objeto de uma lista de User para uma lista de UserDTO
        return ResponseEntity.ok().body(userDTOList);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){ //informa que o valor do parametro eh o caminho da linha de codigo acima
        User user = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(user));
    }
}
