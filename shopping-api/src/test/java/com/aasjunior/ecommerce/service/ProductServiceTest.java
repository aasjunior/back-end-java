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

import com.aasjunior.ecommerce.shoppingclient.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    public static MockWebServer mockBackEnd;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() throws IOException{
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();

        String baseUrl = String
            .format("http://localhost:%s", mockBackEnd.getPort());

        ReflectionTestUtils.setField(productService, "productApiURL", baseUrl);
    }

    @AfterEach
    void tearDown() throws IOException{
        mockBackEnd.shutdown();
    }

    @Test
    void test_getProductByIdentifier() throws IOException{
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(1000F);
        productDTO.setProductIdentifier("prod-identifier");

        ObjectMapper objectMapper = new ObjectMapper();
        
        mockBackEnd.enqueue(
            new MockResponse()
                .setBody(objectMapper.writeValueAsString(productDTO))
                .addHeader("Content-Type", "application/json")
        );

        productDTO = productService.getProductByIdentifier("prod-identifier");

        Assertions.assertEquals(1000F, productDTO.getPrice());
        Assertions.assertEquals("prod-identifier", productDTO.getProductIdentifier());
    }
}
