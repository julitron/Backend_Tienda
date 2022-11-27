package com.proyecto.ciclo4G17.mitiendavirtual.repository;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    
}
