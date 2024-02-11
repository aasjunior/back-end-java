package com.aasjunior.ecommerce.service;

import com.aasjunior.ecommerce.shoppingclient.dto.UserDTO;
import com.aasjunior.ecommerce.shoppingclient.exception.UserNotFoundException;
import com.aasjunior.ecommerce.converter.DTOConverter;
import com.aasjunior.ecommerce.model.User;
import com.aasjunior.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return DTOConverter.convert(user);
    }

    public UserDTO save(UserDTO userDTO){
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return DTOConverter.convert(user);
    }

    public UserDTO delete(Long userId){
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        userRepository.delete(user);
        return DTOConverter.convert(user);
    }

    public UserDTO findByCpf(String cpf, String key){
        User user = userRepository.findByCpfAndKey(cpf, key);
        if(user!=null){
            return DTOConverter.convert(user);
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByName(String name){
        List<User> users = userRepository.queryByNomeLike(name);
        return users
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId, UserDTO userDTO){
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        if(userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())){
            user.setEmail(userDTO.getEmail());
        }
        if(userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())){
            user.setTelefone(userDTO.getTelefone());
        }
        if(userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())){
            user.setEndereco(userDTO.getEndereco());
        }

        user = userRepository.save(user);
        return DTOConverter.convert(user);
    }

    // Consulta com paginação
    public Page<UserDTO> getAllPage(Pageable page){
        Page<User> users = userRepository.findAll(page);
        return users
                .map(DTOConverter::convert);
    }
}