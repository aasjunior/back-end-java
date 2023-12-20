package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/message")
    public String getMessage(){
        return "Spring boot is working!";
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        return users;
    }

    public static List<UserDTO> users = new ArrayList<UserDTO>();

    @PostConstruct
    public void initiateList(){
         UserDTO userDTO = new UserDTO();
         userDTO.setNome("Eduardo");
         userDTO.setCpf("123");
         userDTO.setEndereco("Rua a");
         userDTO.setEmail("eduardo@email.com");
         userDTO.setTelefone("1234-3454");
         userDTO.setDataCadastro(LocalDateTime.now());

         UserDTO userDTO2 = new UserDTO();
         userDTO2.setNome("Luiz");
         userDTO2.setCpf("456");
         userDTO2.setEndereco("Rua b");
         userDTO2.setEmail("luiz@email.com");
         userDTO2.setTelefone("1234-3454");
         userDTO2.setDataCadastro(LocalDateTime.now());

         UserDTO userDTO3 = new UserDTO();
         userDTO3.setNome("Bruna");
         userDTO3.setCpf("789");
         userDTO3.setEndereco("Rua c");
         userDTO3.setEmail("bruna@email.com");
         userDTO3.setTelefone("1234-3454");
         userDTO3.setDataCadastro(LocalDateTime.now());

         users.add(userDTO);
         users.add(userDTO2);
         users.add(userDTO3);
    }
}
