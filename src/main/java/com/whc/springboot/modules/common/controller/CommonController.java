package com.whc.springboot.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: ConmmonController <br/>
 * Description: <br/>
 * date: 2020/8/24 11:08<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    /**
     *127.0.0.1:81/common/dashboard----get
     */
    @GetMapping("/dashboard")
    public String dashboard(){
        return "index";
    }
}
