package com.proyecto.ciclo4G17.mitiendavirtual.service.impl;

import com.proyecto.ciclo4G17.mitiendavirtual.model.Detail;
import com.proyecto.ciclo4G17.mitiendavirtual.repository.DetailRepository;
import com.proyecto.ciclo4G17.mitiendavirtual.service.IDetailService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DetailService implements IDetailService{
    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public void createDetail(Detail detail) {
        this.detailRepository.save(detail);
    }

    @Override
    public List<Detail> getDetailBySale(Integer saleId) {
        return this.detailRepository.findBySale_Id(saleId);
    }
}
