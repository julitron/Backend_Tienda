package com.proyecto.ciclo4G17.mitiendavirtual.service;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Sale;
import java.util.List;

public interface ISaleService {
    List<Sale> getSaleByClient(String clientEmail);
    void createSale(String userName);
}
