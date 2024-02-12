package com.aasjunior.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aasjunior.ecommerce.converter.DTOConverter;
import com.aasjunior.ecommerce.model.User;
import com.aasjunior.ecommerce.repository.UserRepository;
import com.aasjunior.ecommerce.dto.UserDTO;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest  {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    public static User getUser(Integer id, String name, String cpf){
        User user = new User();
        user.setId(id);
        user.setNome(name);
        user.setCpf(cpf);
        user.setEndereco("endereco");
        user.setTelefone("123456789");
        return user;
    }

    @Test
    public void testListAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(getUser(1, "User Name", "123"));
        users.add(getUser(2, "User Name", "321"));

        Mockito.when(userRepository.findAll())
            .thenReturn(users);

        List<UserDTO> usersReturn = userService.getAll();
        Assertions.assertEquals(2, usersReturn.size());
    }

    @Test
    public void testSaveUser(){
        User userDB = getUser(1, "User Name", "123");
        UserDTO userDTO = DTOConverter.convert(userDB);
        
        Mockito.when(userRepository.save(Mockito.any()))
            .thenReturn(userDB);

        UserDTO user = userService.save(userDTO);
        Assertions.assertEquals("User Name", user.getNome());
        Assertions.assertEquals("123", user.getCpf());
    }

    @Test
    public void testEditUser(){
        User userDB = getUser(1, "User Name", "123");

        Mockito.when(userRepository.findById(1L))
            .thenReturn(Optional.of(userDB));
        
        Mockito.when(userRepository.save(Mockito.any()))
            .thenReturn(userDB);

        UserDTO userDTO = DTOConverter.convert(userDB);
        userDTO.setEndereco("Novo Endereco");
        userDTO.setTelefone("1234");
        
        UserDTO user = userService.editUser(1L, userDTO);
        Assertions.assertEquals("Novo Endereco", user.getEndereco());
        Assertions.assertEquals("1234", user.getTelefone());
    }
}
