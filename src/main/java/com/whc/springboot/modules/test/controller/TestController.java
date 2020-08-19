package com.whc.springboot.modules.test.controller;

import com.whc.springboot.modules.test.entity.City;
import com.whc.springboot.modules.test.entity.Country;
import com.whc.springboot.modules.test.service.CityService;
import com.whc.springboot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: TestController <br/>
 * Description: <br/>
 * date: 2020/8/11 18:37<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    /**
     *127.0.0.1:81/test/testMethod?paramKey=abc----get
     */
    @GetMapping("/testMethod")
    @ResponseBody
    public String testMethod(@RequestParam(value = "paramKey")String paramKey,HttpServletRequest request) {
        String paramValue = request.getParameter("paramKey");
        return "This is a testMethod "+ paramKey+"=="+paramValue;
    }

    /**
     * 127.0.0.1:81/test/index
     */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap) {
        int countryId = 522;
        List<City> cityList = cityService.getCitiesByCountryId(countryId);
        cityList = cityList.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);
//        设置test/index里面的内容
        modelMap.addAttribute("thymeleafTitle1", "田家少闲月，五月人倍忙。");
        modelMap.addAttribute("thymeleafTitle2", "夜来南风起，小麦覆陇黄。");
        modelMap.addAttribute("checked1", true);
        modelMap.addAttribute("checked2", false);
        modelMap.addAttribute("currentNumber", 98);
        modelMap.addAttribute("thymeleafTitle3", null);
        modelMap.addAttribute("thymeleafTitle4", null);
        modelMap.addAttribute("thymeleafTitle5", null);
        modelMap.addAttribute("thymeleafTitle6", null);
        modelMap.addAttribute("thymeleafTitle7", null);
        modelMap.addAttribute("thymeleafTitle8", null);
        modelMap.addAttribute("thymeleafTitle9", null);
        modelMap.addAttribute("thymeleafTitle10", null);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cityList", cityList);
        modelMap.addAttribute("city", cityList.get(0));
        modelMap.addAttribute("internetUrl", "127.0.0.1:81/testController/testIndex2Page");
        modelMap.addAttribute("shopLogo", "/upload/1111.png");
        modelMap.addAttribute("updateCityUri", "/cityController/city");
//        给test/index设置变量
//        modelMap.addAttribute("template", "test/index");
//        返回到外层的index中
        return "index";
    }

    /**
     * 127.0.0.1:81/testController/testIndex2Page
     */
    @GetMapping("/testIndex2Page")
    public String testIndex2Page(ModelMap modelMap) {
        modelMap.addAttribute("template", "test/index2");
//        返回到外层的index中
        return "index";
    }
}
