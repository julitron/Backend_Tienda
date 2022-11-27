package com.proyecto.ciclo4G17.mitiendavirtual.service;

import com.proyecto.ciclo4G17.mitiendavirtual.model.ShoppingCart;
import java.util.List;

public interface IShoppingListService {
    List<ShoppingCart>  getListByClient(String userName);
    void cleanShoppingCart(Integer clientId);
    void removeProduct(Integer id);
    void addProduct(ShoppingCart shoppingCart);
    Long getCountByClient(Integer clientId);
}
