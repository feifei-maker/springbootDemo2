package com.whc.springboot.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: TestController <br/>
 * Description: <br/>
 * date: 2020/8/11 18:37<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/testController")
public class TestController {
    @GetMapping("/testMethod")
    @ResponseBody
    public String testMethod(){
        return "This is a testMethod";
    }
}
