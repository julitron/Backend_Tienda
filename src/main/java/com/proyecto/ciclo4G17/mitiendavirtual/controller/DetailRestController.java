package com.proyecto.ciclo4G17.mitiendavirtual.controller;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Detail;
import com.proyecto.ciclo4G17.mitiendavirtual.service.impl.DetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/saleDetail")
public class DetailRestController {
    private final DetailService detailService;

    @Autowired
    public DetailRestController(DetailService detailService) {
        this.detailService = detailService;
    }
    @GetMapping("/{sale_id}")
    public ResponseEntity<List<Detail>> getDetailsBySale(@PathVariable("sale_id")Integer id){
        return new ResponseEntity<>(this.detailService.getDetailBySale(id), HttpStatus.OK);
    }
}
