package com.proyecto.ciclo4G17.mitiendavirtual.controller;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Message;
import com.proyecto.ciclo4G17.mitiendavirtual.model.Sale;
import com.proyecto.ciclo4G17.mitiendavirtual.service.impl.SaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/sale")
public class SaleRestController {
    private final SaleService saleService;
    
    @Autowired
    public SaleRestController(SaleService saleService) {
        this.saleService = saleService;
    }
    
    @GetMapping("/client")
    public ResponseEntity<List<Sale>> getByClient(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.saleService.getSaleByClient(userName), HttpStatus.OK);
    }
    
     @PostMapping(path = "/create")
    public ResponseEntity<Message> createSale(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
        String userName = userDetails.getUsername();
        this.saleService.createSale(userName);
        return new ResponseEntity<>(new Message("Compra exitosa"), HttpStatus.OK);
    }
}
