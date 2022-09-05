package com.codegym.thuc_hanh_module_4.service.citydetail;

import com.codegym.thuc_hanh_module_4.model.CityDetail;
import com.codegym.thuc_hanh_module_4.repository.ICityDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CityDetailServiceImpl implements ICityDetailService{
    @Autowired
    private ICityDetailRepository cityDetailRepository;
    @Override
    public Iterable<CityDetail> findAll() {
        return cityDetailRepository.findAll();
    }

    @Override
    public Optional<CityDetail> findById(Long id) {
        return cityDetailRepository.findById(id);
    }

    @Override
    public CityDetail save(CityDetail cityDetail) {
        return cityDetailRepository.save(cityDetail);
    }

    @Override
    public void remove(Long id) {
        cityDetailRepository.deleteById(id);
    }
}
