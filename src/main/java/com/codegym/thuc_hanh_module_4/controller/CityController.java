package com.codegym.thuc_hanh_module_4.controller;


import com.codegym.thuc_hanh_module_4.model.City;
import com.codegym.thuc_hanh_module_4.model.CityDetail;
import com.codegym.thuc_hanh_module_4.service.city.ICityService;
import com.codegym.thuc_hanh_module_4.service.citydetail.ICityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utils.AppUtils;
import utils.ValidDateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/city")
public class CityController {
    @Autowired
    private ICityService cityService;

    @Autowired
    private ICityDetailService cityDetailService;


    @ModelAttribute("city")
    public Iterable<City> cityIterable() {
        return cityService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView showCity() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<CityDetail> cityDetails = cityDetailService.findAll();
        Iterable<City> city = cityService.findAll();
        modelAndView.addObject("city", city);
        modelAndView.addObject("cityDetails", cityDetails);
        modelAndView.setViewName("/listCity");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("cityDetail", new CityDetail());
        modelAndView.setViewName("/create");
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<CityDetail> cityDetail = cityDetailService.findById(id);
        modelAndView.addObject("cityDetail", cityDetail);
        modelAndView.setViewName("/detail");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<CityDetail> cityDetail = cityDetailService.findById(id);
        modelAndView.addObject("cityDetail", cityDetail);
        modelAndView.setViewName("/edit");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<CityDetail> cityDetail = cityDetailService.findById(id);
        modelAndView.addObject("cityDetail", cityDetail.get());
        modelAndView.setViewName("/delete");
        return modelAndView;
    }

    @PostMapping("/delete")
    public String doDelete(@ModelAttribute CityDetail cityDetail) {
        cityDetailService.remove(cityDetail.getId());
        return "redirect:list";
    }

    @PostMapping("/edit/{id}")
    public ModelAndView doEdit(@Validated @PathVariable Long id, @ModelAttribute CityDetail cityDetail , BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();


        List<String> errors = new ArrayList<>();
        String dienTich = cityDetail.getDienTich();
        String gdp = cityDetail.getGdp();
        String danSo = cityDetail.getDanSo();

        if (!ValidDateUtils.isNumberValid(dienTich)) {
            errors.add("Vui l??ng nh???p di???n t??ch h???p l???");
        } else if (dienTich.length() > 10) {
            errors.add("Di???n t??ch qu?? l???n v?????t qu?? di???n t??ch th???c c???a th??nh ph???!");
        } else {
            if (Integer.parseInt(dienTich) <= 0) {

                errors.add("Di???n t??ch c???a m???t th??nh ph??? ph???i l?? m???t s??? d????ng!");
            }
        }
        if (!ValidDateUtils.isNumberValid(gdp)) {
            errors.add("Vui l??ng GDP h???p l???!");
        } else if (gdp.length() > 10) {
            errors.add("GDP qu?? l???n v?????t qu?? GDP th???c c???a th??nh ph???!");
        } else {
            if (Integer.parseInt(gdp) <= 0) {
                errors.add("GDP c???a m???t th??nh ph??? ph???i l?? m???t s??? d????ng!");
            }
        }

        if (!ValidDateUtils.isNumberValid(danSo)) {
            errors.add("Vui l??ng nh???p d??n s??? h???p l???!");
        } else if (danSo.length() > 10) {
            errors.add("D??n s??? qu?? l???n v?????t qu?? d??n s??? th???c c???a th??nh ph???!");
        } else {
            if (Integer.parseInt(danSo) <= 0) {
                errors.add("D??n s??? c???a m???t th??nh ph??? ph???i l?? m???t s??? d????ng!");
            }
        }

        if (bindingResult.hasErrors() || errors.size() > 0) {
            modelAndView = new ModelAndView("/edit");
            Iterable<City> city = cityService.findAll();
            modelAndView.addObject("hasError", true);
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("city", city);
            return modelAndView;
        }
        cityDetailService.save(cityDetail);
        modelAndView.addObject("cityDetail", new CityDetail());
        modelAndView.addObject("message", "Ch???nh S???a Th??nh C??ng");
        modelAndView.addObject("cityDetail", cityDetail);
        modelAndView.setViewName("/edit");
        return modelAndView;
    }


    @PostMapping("/do-create")
    public ModelAndView doCreate(@Validated @ModelAttribute CityDetail cityDetail, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        List<String> errors = new ArrayList<>();
        String dienTich = cityDetail.getDienTich();
        String gdp = cityDetail.getGdp();
        String danSo = cityDetail.getDanSo();

        if (!ValidDateUtils.isNumberValid(dienTich)) {
            errors.add("Vui l??ng nh???p di???n t??ch h???p l???");
        } else if (dienTich.length() > 10) {
            errors.add("Di???n t??ch qu?? l???n v?????t qu?? di???n t??ch th???c c???a th??nh ph???!");
        } else {
            if (Integer.parseInt(dienTich) <= 0) {

                errors.add("Di???n t??ch c???a m???t th??nh ph??? ph???i l?? m???t s??? d????ng!");
            }
        }
        if (!ValidDateUtils.isNumberValid(gdp)) {
            errors.add("Vui l??ng GDP h???p l???!");
        } else if (gdp.length() > 10) {
            errors.add("GDP qu?? l???n v?????t qu?? GDP th???c c???a th??nh ph???!");
        } else {
            if (Integer.parseInt(gdp) <= 0) {
                errors.add("GDP c???a m???t th??nh ph??? ph???i l?? m???t s??? d????ng!");
            }
        }

        if (!ValidDateUtils.isNumberValid(danSo)) {
            errors.add("Vui l??ng nh???p d??n s??? h???p l???!");
        } else if (danSo.length() > 10) {
            errors.add("D??n s??? qu?? l???n v?????t qu?? d??n s??? th???c c???a th??nh ph???!");
        } else {
            if (Integer.parseInt(danSo) <= 0) {
                errors.add("D??n s??? c???a m???t th??nh ph??? ph???i l?? m???t s??? d????ng!");
            }
        }

        if (bindingResult.hasErrors() || errors.size() > 0) {
            modelAndView = new ModelAndView("/create");
            Iterable<City> city = cityService.findAll();
            modelAndView.addObject("hasError", true);
            modelAndView.addObject("errors", errors);
            modelAndView.addObject("city", city);
            return modelAndView;
        }

        cityDetailService.save(cityDetail);
        modelAndView.addObject("cityDetail", new CityDetail());
        modelAndView.addObject("message", "Th??m Th??nh C??ng");
        modelAndView.setViewName("/create");
        return modelAndView;
    }

}
