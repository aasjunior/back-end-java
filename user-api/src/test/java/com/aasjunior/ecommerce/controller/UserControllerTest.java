package com.aasjunior.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.aasjunior.ecommerce.converter.DTOConverter;
import com.aasjunior.ecommerce.service.UserService;
import com.aasjunior.ecommerce.service.UserServiceTest;
import com.aasjunior.ecommerce.dto.UserDTO;

// Anotando a classe para usar a extensão Mockito
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    // Injetando a classe UserController para os testes
    @InjectMocks
    private UserController userController;
    
    // Criando um mock para a classe UserService
    @Mock
    private UserService userService;
    
    // Criando um objeto MockMvc para simular requisições HTTP
    private MockMvc mockMvc;

    // Método executado antes de cada teste
    @BeforeEach
    public void setup(){
        // Configurando o objeto MockMvc para testar o UserController
        mockMvc = MockMvcBuilders
            .standaloneSetup(userController)
            .build();
    }

    // Teste para o método listUsers
    @Test
    public void testListUsers() throws Exception{
        // Criando uma lista de usuários para o teste
        List<UserDTO> users = new ArrayList<>();
        users.add(DTOConverter.convert(
            UserServiceTest.getUser(1, "Nome 1", "123")
        ));

        // Configurando o mock userService para retornar a lista de usuários quando o método getAll for chamado
        Mockito.when(userService.getAll())
            .thenReturn(users);

        // Simulando uma requisição GET para a rota "/user" e armazenando o resultado
        MvcResult result = mockMvc
            .perform(MockMvcRequestBuilders.get("/user"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        // Obtendo a resposta da requisição como uma string
        String resp = result.getResponse()
                .getContentAsString();

        // Verificando se a resposta é igual ao esperado
        Assertions.assertEquals(
            "[{\"nome\":\"Nome 1\"," +
            "\"cpf\":\"123\",\"endereco\":\"endereco\",\"key\":null," +
            "\"email\":null,\"telefone\":\"123456789\",\"dataCadastro\":null}]", 
            resp
        );
    }
}
