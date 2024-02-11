package com.aasjunior.ecommerce.service;

import java.io.IOException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import com.aasjunior.ecommerce.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

// Anotando a classe para usar a extensão Mockito
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    // Criando um servidor mock para simular o back-end
    public static MockWebServer mockBackEnd;

    // Injetando a classe ProductService para os testes
    @InjectMocks
    private ProductService productService;

    // Método executado antes de cada teste
    @BeforeEach
    void setup() throws IOException{
        // Inicializando o servidor mock
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        // Configurando a URL base para o servidor mock
        String baseUrl = String
            .format("http://localhost:%s", mockBackEnd.getPort());

        // Configurando a URL do ProductService para apontar para o servidor mock
        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }

    // Método executado após cada teste
    @AfterEach
    void tearDown() throws IOException{
        // Desligando o servidor mock
        mockBackEnd.shutdown();
    }

    // Teste para o método getProductByIdentifier
    @Test
    void test_getProductByIdentifier() throws IOException{
        // Criando um objeto ProductDTO para o teste
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(1000F);
        productDTO.setProductIdentifier("prod-identifier");

        // Criando um ObjectMapper para converter o objeto em JSON
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Configurando o servidor mock para retornar o objeto ProductDTO quando for chamado
        mockBackEnd.enqueue(
            new MockResponse()
                .setBody(objectMapper.writeValueAsString(productDTO))
                .addHeader("Content-Type", "application/json")
        );

        // Chamando o método getProductByIdentifier e armazenando o resultado
        productDTO = productService.getProductByIdentifier("prod-identifier");

        // Verificando se o preço e o identificador do produto estão corretos
        Assertions.assertEquals(1000F, productDTO.getPrice());
        Assertions.assertEquals("prod-identifier", productDTO.getProductIdentifier());
    }
}