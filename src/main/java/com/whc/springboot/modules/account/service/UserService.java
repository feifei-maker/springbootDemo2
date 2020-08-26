package com.whc.springboot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.account.entity.User;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;

/**
 * ClassName: UserService <br/>
 * Description: <br/>
 * date: 2020/8/21 13:36<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface UserService {
    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<User> deleteUser(int userId);

    User getUserByUserId(int userId);

}
