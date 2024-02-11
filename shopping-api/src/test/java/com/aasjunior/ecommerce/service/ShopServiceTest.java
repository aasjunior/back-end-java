package com.aasjunior.ecommerce.service;

// Importando as bibliotecas necessárias
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.aasjunior.ecommerce.model.Shop;
import com.aasjunior.ecommerce.repository.ShopRepository;
import com.aasjunior.ecommerce.dto.ItemDTO;
import com.aasjunior.ecommerce.dto.ProductDTO;
import com.aasjunior.ecommerce.dto.ShopDTO;
import com.aasjunior.ecommerce.dto.UserDTO;

// Anotando a classe para usar a extensão Mockito
@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {
    // Injetando a classe ShopService para os testes
    @InjectMocks
    private ShopService shopService;

    // Criando mocks para as classes UserService, ProductService e ShopRepository
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;
    @Mock
    private ShopRepository shopRepository;

    // Teste para o método saveShop
    @Test
    public void test_saveShop(){
        // Criando um objeto ItemDTO para o teste
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier("123");
        itemDTO.setPrice(100F);

        // Criando um objeto ShopDTO para o teste
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier("123");
        shopDTO.setItems(new ArrayList<>());
        shopDTO.getItems().add(itemDTO);
        shopDTO.setTotal(100F);

        // Criando um objeto ProductDTO para o teste
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductIdentifier("123");
        productDTO.setPrice(100F);

        // Configurando o comportamento dos mocks
        Mockito.when(userService.getUserByCpf("123", "123"))
            .thenReturn(new UserDTO());
        Mockito.when(productService.getProductByIdentifier("123"))
            .thenReturn(productDTO);
        Mockito.when(shopRepository.save(Mockito.any()))
            .thenReturn(Shop.convert(shopDTO));
            
        // Chamando o método save e armazenando o resultado
        shopDTO = shopService.save(shopDTO, "123");

        // Verificando se o total e o número de itens estão corretos
        Assertions.assertEquals(100F, shopDTO.getTotal());
        Assertions.assertEquals(1, shopDTO.getItems().size());

        // Verificando se o método save foi chamado apenas uma vez no repositório
        Mockito.verify(shopRepository, Mockito.times(1))
            .save(Mockito.any());
    }
}