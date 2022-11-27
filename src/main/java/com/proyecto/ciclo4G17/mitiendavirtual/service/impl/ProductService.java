package com.proyecto.ciclo4G17.mitiendavirtual.service.impl;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Product;
import com.proyecto.ciclo4G17.mitiendavirtual.repository.ProductRepository;
import com.proyecto.ciclo4G17.mitiendavirtual.service.IProductService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProductService implements IProductService  {
    
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }
    public void saveProduct(Product product){
       this.productRepository.save(product);
    }

    public Optional<Product> getProductById(Integer id) {
        return this.productRepository.findById(id);
    }

    @Override
    @Transactional()
    public void deleteProductoById(Integer id) {
        productRepository.deleteById(id);
    }
}
