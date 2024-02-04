package com.aasjunior.ecommerce.shoppingapi.model;

import com.aasjunior.ecommerce.shoppingapi.dto.ShopDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userIdentifier;
    private float total;
    private LocalDateTime date;

    /* ElementCollection -> mapear a relação entre a compra e a relação de itens
    *  Eager -> indica que os valores devem ser recuperados do BD junto da entidade principal
    */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    public static Shop convert(ShopDTO shopDTO){
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDTO.getUserIdentifier());
        shop.setTotal(shopDTO.getTotal());
        shop.setDate(shopDTO.getDate());
        shop.setItems(
            shopDTO
                .getItems()
                .stream()
                .map(Item::convert)
                .collect(Collectors.toList())
        );
        return shop;
    }
}
