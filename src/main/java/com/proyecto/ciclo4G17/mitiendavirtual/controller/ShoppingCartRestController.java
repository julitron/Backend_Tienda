package com.proyecto.ciclo4G17.mitiendavirtual.controller;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Message;
import com.proyecto.ciclo4G17.mitiendavirtual.model.ShoppingCart;
import com.proyecto.ciclo4G17.mitiendavirtual.service.impl.ShoppingCartService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingCartRestController {
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartRestController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    
    @GetMapping()
    public ResponseEntity<List<ShoppingCart>> getListByClent(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.shoppingCartService.getListByClient(userName),HttpStatus.OK);
    }
    
    @GetMapping("/count/{client_id}")
    public ResponseEntity<Long> counyByClent(@PathVariable("client_id") Integer id){
        return new ResponseEntity<>(this.shoppingCartService.getCountByClient(id),HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<Message> addProduct(@Valid @RequestBody ShoppingCart shoppingCart,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos"),HttpStatus.BAD_REQUEST);
        this.shoppingCartService.addProduct(shoppingCart);
        return new ResponseEntity<>(new Message("Producto agregado"),HttpStatus.OK);
    }
    
    @DeleteMapping("/clean/{item_id}")
    public ResponseEntity<Message> removeProduct(@PathVariable("item_id")Integer id){
        this.shoppingCartService.removeProduct(id);
        return new ResponseEntity<>(new Message("Producto eliminado"),HttpStatus.OK);
    }
} 
