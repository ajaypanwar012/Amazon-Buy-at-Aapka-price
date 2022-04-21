package com.amazon.service;

import com.amazon.dto.ProductDto;
import com.amazon.models.Product;
import com.amazon.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import com.amazon.models.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductService {

    @NotNull Iterable<Product> getAllProducts();

    Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);

    Product save(Product product);
}

//
//@Service
//public class ProductService {
//    @Autowired
//    private ProductRepository productRepository;
//
//    public static Product getProductFromDto(ProductDto productDto) {
//        Product product = new Product();
//        product.setDescription(productDto.getDescription());
//        product.setImageURL(productDto.getImageURL());
//        product.setPrice(productDto.getPrice());
//        product.setName(productDto.getName());
//        return product;
//    }
//
//    public void addProduct(ProductDto productDto) {
//        Product product = getProductFromDto(productDto  );
//        System.out.println(product.getPrice());
//        productRepository.save(product);
//    }
//
//    // list of all the products
//    public List<ProductDto> listProducts() {
//        // first fetch all the products
//        List<Product> products = productRepository.findAll();
//        List<ProductDto> productDtos = new ArrayList<>();
//
//        for(Product product : products) {
//            // for each product change it to DTO
//            productDtos.add(new ProductDto(product));
//        }
//        return productDtos;
//    }
//
//    // update a product
//    public void updateProduct(Long id, ProductDto productDto) {
//        Product product = getProductFromDto(productDto);
//        // set the id for updating
//        product.setId(id);
//        // update
//        productRepository.save(product);
//    }
//}

