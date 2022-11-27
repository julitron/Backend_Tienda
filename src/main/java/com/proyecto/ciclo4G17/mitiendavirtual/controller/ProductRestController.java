package com.proyecto.ciclo4G17.mitiendavirtual.controller;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Message;
import com.proyecto.ciclo4G17.mitiendavirtual.model.Product;
import com.proyecto.ciclo4G17.mitiendavirtual.service.impl.ProductService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/product")
public class ProductRestController {
    private final ProductService productService;
    
    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Message> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Los campos ingresados son incorrectos"), HttpStatus.BAD_REQUEST);
        this.productService.saveProduct(product);
        return new ResponseEntity<>(new Message("Producto guardado"), HttpStatus.OK);
    }
    
    @GetMapping("/{product_Id}")
    public ResponseEntity<Object> getProductById(@PathVariable(value="product_id") Integer productId){
        Optional<Product> productOptional = this.productService.getProductById(productId);
        if(productOptional.isEmpty()){
            return new ResponseEntity<>(new Message("No encontrado"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Object> getAllProduct(){
        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
    }
    
    @PutMapping("/update/{product_id}")
    public ResponseEntity<Message> updateProduct(@Valid @RequestBody Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Los campos ingresados son incorrectos"),HttpStatus.BAD_REQUEST);
        this.productService.saveProduct(product);
        return new ResponseEntity<>(new Message("Producto actualizado"),HttpStatus.OK);
    }
     
    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<Message> deleteProduct(@PathVariable("product_id")Integer id){
        this.productService.deleteProductoById(id);
        return new ResponseEntity<>(new Message("Producto eliminado"),HttpStatus.OK);
    }
}
