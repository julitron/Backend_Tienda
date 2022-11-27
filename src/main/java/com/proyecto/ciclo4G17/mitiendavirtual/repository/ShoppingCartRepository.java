package com.proyecto.ciclo4G17.mitiendavirtual.repository;

import com.proyecto.ciclo4G17.mitiendavirtual.model.ShoppingCart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCart> findByClient_Id(Integer clientId);
    List<ShoppingCart> findByClient_UserName(String userName);
    void deleteByClient_Id(Integer clientId);
    Long countByClient_Id(Integer id);   
}
