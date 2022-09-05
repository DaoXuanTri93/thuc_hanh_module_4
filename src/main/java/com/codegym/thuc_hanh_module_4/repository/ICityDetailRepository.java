package com.codegym.thuc_hanh_module_4.repository;

import com.codegym.thuc_hanh_module_4.model.CityDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICityDetailRepository extends JpaRepository<CityDetail, Long> {
}
