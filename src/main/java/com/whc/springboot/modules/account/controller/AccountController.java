package com.whc.springboot.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: AccountController <br/>
 * Description: <br/>
 * date: 2020/8/19 14:57<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    /**
     *127.0.0.1:81/account/users---get
     */
    @GetMapping("/users")
    public String usersPage(){
        return "index";
    }
}
