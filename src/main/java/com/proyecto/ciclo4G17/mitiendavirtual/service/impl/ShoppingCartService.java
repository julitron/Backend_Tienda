package com.proyecto.ciclo4G17.mitiendavirtual.service.impl;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Sale;
import com.proyecto.ciclo4G17.mitiendavirtual.model.ShoppingCart;
import com.proyecto.ciclo4G17.mitiendavirtual.repository.ShoppingCartRepository;
import com.proyecto.ciclo4G17.mitiendavirtual.service.IShoppingListService;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ShoppingCartService implements IShoppingListService {
    private final ShoppingCartRepository shoppingCartRepository;
    
    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> getListByClient(String userName) {
        return this.shoppingCartRepository.findByClient_UserName(userName);
    }
    
    public void cleanShoppingCart(Integer clientId) {
        this.shoppingCartRepository.deleteByClient_Id(clientId);
    }
    
    public void removeProduct(Integer id){
        this.shoppingCartRepository.deleteById(id);
    }
    
    public void addProduct(ShoppingCart shoppingCart){
        this.shoppingCartRepository.save(shoppingCart);
    }

    public Long getCountByClient(Integer clientId) {
        return this.shoppingCartRepository.countByClient_Id(clientId);
    }
}
