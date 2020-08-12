package com.whc.springboot.modules.test.controller;

import com.whc.springboot.modules.test.entity.Country;
import com.whc.springboot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CountryController <br/>
 * Description: <br/>
 * date: 2020/8/12 11:21<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/countryController")
public class CountryController {
    @Autowired
    private CountryService countryService;

    /**
     * 127.0.0.1:81/countryController/country/522------get
     */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId) {
        return countryService.getCountryByCountryId(countryId);
    }

    /**
     * 127.0.0.1:81/countryController/country?countryName=China------get
     */
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){
        return countryService.getCountryByCountryName(countryName);
    }
}
