package com.proyecto.ciclo4G17.mitiendavirtual.repository;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Detail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
    List<Detail> findBySale_Id(Integer saleId);
}
