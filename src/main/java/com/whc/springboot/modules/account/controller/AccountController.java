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
    /**
     *127.0.0.1:81/account/login---get
     */
    @GetMapping("/login")
    public String loginPage(){
        return "indexSimple";
    }
    /**
     *127.0.0.1:81/account/register---get
     */
    @GetMapping("/register")
    public String registerPage(){
        return "indexSimple";
    }
    /**
     * 127.0.0.1:81/account/roles ---- get
     */
    @GetMapping("/roles")
    public String rolesPage() {
        return "index";
    }

    /**
     * 127.0.0.1:81/account/resources ---- get
     */
    @GetMapping("/resources")
    public String resourcesPage() {
        return "index";
    }

    /**
     * 127.0.0.1:81/account/profile ---- get
     */
    @GetMapping("/profile")
    public String profilePage() {
        return "index";
    }
}
