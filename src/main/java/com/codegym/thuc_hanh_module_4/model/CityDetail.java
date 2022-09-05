package com.codegym.thuc_hanh_module_4.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CityDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Tên thành phố không được bỏ trống.")
    private String ten;
    @NotBlank(message = "Diện tích không được bỏ trống.")
    private String dienTich;
    @NotBlank(message = "Dân số không được bỏ trống.")
    private String danSo;
    @NotBlank(message = "GDP không được bỏ trống.")
    private String gdp;
    @Size( max = 255 , message = "Mô tả không được quá > 255")
    private String gioiThieu;
    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;
}
