package com.newapi.newApi.services;

import java.util.List;
import java.util.UUID;

import com.newapi.newApi.models.ProductModel;

public interface IProductService {
    
    public List<ProductModel> findAll() ;

    public ProductModel findById(UUID id);

    public ProductModel save(ProductModel productModel);
    
    public void delete(UUID id);

    public ProductModel update(UUID id, ProductModel productModel);

}
