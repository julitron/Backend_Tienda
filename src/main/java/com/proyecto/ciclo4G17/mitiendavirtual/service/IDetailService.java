package com.proyecto.ciclo4G17.mitiendavirtual.service;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Detail;
import java.util.List;

public interface IDetailService {
    void createDetail(Detail detail);
    List<Detail> getDetailBySale(Integer saleId);
}
