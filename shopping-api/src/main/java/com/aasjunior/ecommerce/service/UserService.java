package com.aasjunior.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aasjunior.ecommerce.dto.UserDTO;
import com.aasjunior.ecommerce.exception.UserNotFoundException;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    // hardcoded
    private String userApiURL = "http://localhost:8080";

    public UserDTO getUserByCpf(String cpf, String key){
        try{
            WebClient webClient = WebClient
                    .builder()
                    .baseUrl(userApiURL)
                    .build();
            
            Mono<UserDTO> user = webClient.get()
                    .uri("/user/" + cpf + "/cpf?key=" + key)
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return user.block();
        }catch(Exception e){
            throw new UserNotFoundException();
        }
    }
}
