package com.edgar.workshopmongodb.resources;

import com.edgar.workshopmongodb.domain.User;
import com.edgar.workshopmongodb.dto.UserDTO;
import com.edgar.workshopmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
        User user = service.fromDTO(objDto); //convertendo userDTO em user
        user = service.insert(user); //insere no banco, passando como argumento o proprio objeto que acabou de ser convertido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build(); //retorna o codigo 201
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){ //informa que o valor do parametro eh o caminho da linha de codigo acima
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
        User user = service.fromDTO(objDto); //convertendo userDTO em user
        user.setId(id);

        user = service.update(user);

        return ResponseEntity.noContent().build();
    }

}
