package com.aasjunior.ecommerce.converter;

import com.aasjunior.ecommerce.model.Category;
import com.aasjunior.ecommerce.model.Product;
import com.aasjunior.ecommerce.shoppingclient.dto.CategoryDTO;
import com.aasjunior.ecommerce.shoppingclient.dto.ProductDTO;

public class DTOConverter {
    public static CategoryDTO convert(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static ProductDTO convert(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setDescription(product.getDescription());
        if(product.getCategory() != null){
            productDTO.setCategory(
                DTOConverter.convert(product.getCategory())
            );
        }
        return productDTO;
    }
}
