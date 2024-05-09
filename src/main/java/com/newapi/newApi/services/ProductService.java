package com.newapi.newApi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newapi.newApi.models.ProductModel;
import com.newapi.newApi.repositories.ProductRepository;

@Service
public class ProductService implements IProductService {
    
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public ProductModel findById(UUID id){
        return productRepository.findById(id).orElse(null);
    }

    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    public ProductModel update(UUID id, ProductModel productModel){
        var productFound = findById(id);
        if(productFound == null)
            return null;

        var copyProductModel = productModel;
        copyProductModel.setId(id);

        return productRepository.save(productModel);
    }

}
