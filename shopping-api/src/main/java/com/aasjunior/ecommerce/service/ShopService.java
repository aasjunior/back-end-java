package com.aasjunior.ecommerce.service;

import com.aasjunior.ecommerce.converter.DTOConverter;
import com.aasjunior.ecommerce.model.Shop;
import com.aasjunior.ecommerce.repository.ShopRepository;
import com.aasjunior.ecommerce.dto.ItemDTO;
import com.aasjunior.ecommerce.dto.ProductDTO;
import com.aasjunior.ecommerce.dto.ShopDTO;
import com.aasjunior.ecommerce.dto.ShopReportDTO;
import com.aasjunior.ecommerce.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository
                .findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository
                .findAllByDateGreaterThan(shopDTO.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long productId){
        Optional<Shop> shop = shopRepository
                .findById(productId);
        if(shop.isPresent()){
            return DTOConverter.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key){
        UserDTO userDTO = userService.getUserByCpf(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());
        shopDTO.setTotal(
            shopDTO
                .getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum)
        );
        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items){
        for(ItemDTO item: items){
            ProductDTO productDTO = productService
                    .getProductByIdentifier(item.getProductIdentifier());
            if(productDTO == null){
                return false;
            }
            item.setPrice(productDTO.getPrice());
        }
        return true;
    }

    public List<ShopDTO> getShopsByFilter(LocalDate dataInicio, LocalDate dataFim, Float valorMinimo) {
		List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);
		return shops.stream().map(DTOConverter::convert).collect(Collectors.toList());		
		
	}
	
	public ShopReportDTO getReportByDate(LocalDate dataInicio, LocalDate dataFim) {
		return shopRepository.getReportByDate(dataInicio, dataFim);
	}
}
