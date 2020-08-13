package com.whc.springboot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.common.vo.Result;
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

    /**
     * 127.0.0.1:81/cityController/city------post
     * {"currentPage":"1","pageSize":"5","keyWord":"Sh","orderBy":"city_name","sort":"desc"}
     */
    @PostMapping(value = "/city", consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVo(@RequestBody SearchVo searchVo) {
        return cityService.getCitiesBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1:81/cityController/icity---post
     * {"cityName":"test1","localCityName":"testCity","countryId":"522"}
     */
    @PostMapping(value = "/icity", consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city) {
        return cityService.insertCity(city);
    }

    /**
     * 127.0.0.1:81/cityController/city---put
     * "cityId":"2258"cityName":"testUpdate"
     */
    //数据类型是表单的修改方法
    @PutMapping(value = "/city", consumes = "application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city) {
        return cityService.updateCity(city);
    }

    /**
     * 127.0.0.1:81/cityController/city---put
     * {"cityId":"2258"cityName":"testUpdate"}
     */
    //数据类型是json对象的修改方法
//    @PutMapping(value = "/city", consumes = "application/json")
//    public Result<City> updateCity(@RequestBody City city) {
//        return cityService.updateCity(city);
//    }

    /**
     * 127.0.0.1:81/cityController/city/2258---delete
     */
    @DeleteMapping("/city/{cityId}")
    public Result<Object> deleteCity(@PathVariable int cityId) {
        return cityService.deleteCity(cityId);
    }

}
