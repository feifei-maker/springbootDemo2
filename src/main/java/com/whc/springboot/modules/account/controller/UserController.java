package com.whc.springboot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.account.entity.User;
import com.whc.springboot.modules.account.service.UserService;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: UserController <br/>
 * Description: <br/>
 * date: 2020/8/21 13:49<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1:81/api/user----post
     * {"userName":"admin","password":"111111","userImg":"prod-1.jpg"}
     */
//    注册
//    @PostMapping(value = "/register",consumes = "application/json")
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    /**
     * 127.0.0.1:81/api/login----post
     * {"userName":"admin","password":"111111"}
     */
    //登录
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * 127.0.0.1:81/api/users----post
     * {"currentPage":"1","pageSize":"5","keyWord":"wh"}
     */
    //分页查询
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUsersBySearchVo(@RequestBody SearchVo searchVo) {
        return userService.getUsersBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1:81/api/user----put
     * {"userName":"whc2","userImg":"prod-1.jpg","userId":"27"}
     */
    //修改
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     *127.0.0.1:81/api/user/33----delete
     */
    //删除
    @DeleteMapping("/user/{userId}")
    public Result<User> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    /**
     *127.0.0.1:81/api/user/27----get
     */
    //通过id查询user
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId) {
        return userService.getUserByUserId(userId);
    }
}
