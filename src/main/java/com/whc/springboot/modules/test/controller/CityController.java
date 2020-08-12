package com.whc.springboot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.entity.City;
import com.whc.springboot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: CityController <br/>
 * Description: <br/>
 * date: 2020/8/12 13:57<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/cityController")
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     * 127.0.0.1:81/cityController/city/522------get
     */
    @GetMapping("/city/{countryId}")
    public List<City> getCitiesByCountryId(@PathVariable int countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }

    /**
     * 127.0.0.1:81/cityController/city/522------post
     * {"currentPage":"1","pageSize":"5"}
     */
//如果有多个参数的时候就使用post请求
    @PostMapping(value = "/city/{countryId}", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(
            @PathVariable int countryId, @RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(countryId, searchVo);
    }

}
