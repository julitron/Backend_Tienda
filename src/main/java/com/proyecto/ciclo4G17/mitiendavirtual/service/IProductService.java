package com.proyecto.ciclo4G17.mitiendavirtual.service;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> getAllProducts();
    public Optional<Product> getProductById(Integer id);
    public void  deleteProductoById(Integer id);
}
