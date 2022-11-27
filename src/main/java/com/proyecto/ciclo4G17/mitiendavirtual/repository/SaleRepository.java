package com.proyecto.ciclo4G17.mitiendavirtual.repository;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Sale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    List<Sale> findByClient_UserName(String userName);
}
