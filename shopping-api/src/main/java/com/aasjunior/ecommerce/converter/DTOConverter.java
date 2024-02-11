package com.aasjunior.ecommerce.converter;

import java.util.stream.Collectors;

import com.aasjunior.ecommerce.model.Item;
import com.aasjunior.ecommerce.model.Shop;
import com.aasjunior.ecommerce.shoppingclient.dto.ItemDTO;
import com.aasjunior.ecommerce.shoppingclient.dto.ShopDTO;

public class DTOConverter {
    public static ItemDTO convert(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO convert(Shop shop){
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());

        // Convertendo os Item para ItemDTO
        shopDTO.setItems(
            shop
                .getItems()
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList())
        );
        return shopDTO;
    }
}
