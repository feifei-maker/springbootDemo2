package com.whc.springboot.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.account.dao.UserDao;
import com.whc.springboot.modules.account.dao.UserRoleDao;
import com.whc.springboot.modules.account.entity.Role;
import com.whc.springboot.modules.account.entity.User;
import com.whc.springboot.modules.account.service.UserService;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * ClassName: UserServiceImpl <br/>
 * Description: <br/>
 * date: 2020/8/21 13:36<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        //在新增之前先判断数据库中是否有该对象
        User userSql = userDao.getUserByUserName(user.getUserName());
        if (userSql != null) {
            return new Result<User>(Result.ResultStatus.FAILED.status, "The user already exists", user);
        } else {
            //设置时间和加密后的密码
            user.setCreateDate(LocalDateTime.now());
            user.setPassword(MD5Util.getMD5(user.getPassword()));
            userDao.insertUser(user);

            //在给用户分配角色之前先删除原来的与该Id相关的信息(中间表的信息)
            userRoleDao.deleteUserRoleByUserId(user.getUserId());
            List<Role> roles = user.getRoles();
            if (roles != null&& !roles.isEmpty()){
//                for (int i = 0; i < roles.size(); i++) {
//                    userRoleDao.insertUserRole(user.getUserId(),roles.get(i).getRoleId());
//                }
//                for (Role role : roles) {
//                    userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
//                }
                //JDK1.8之后的循化使用方式
                roles.stream().forEach(item->{
                    userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
                });
            }
            return new Result<User>(Result.ResultStatus.SUCCESS.status, "Insert success", user);
        }
    }

    @Override
    public Result<User> login(User user) {
        User userSql = userDao.getUserByUserName(user.getUserName());
        if (userSql != null && userSql.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
            return new Result<User>(Result.ResultStatus.SUCCESS.status, "select success", user);
        } else {
            return new Result<User>(Result.ResultStatus.FAILED.status, "userName or password is error", user);
        }
    }

    @Override
    public PageInfo<User> getUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(Optional
                .ofNullable(userDao.getUsersBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userSql = userDao.getUserByUserName(user.getUserName());
        if (userSql != null && userSql.getUserId() != user.getUserId()) {
            return new Result<User>(Result.ResultStatus.FAILED.status, "The user already exists", user);
        } else {
            userDao.updateUser(user);

            //在给用户分配角色之前先删除原来的与该Id相关的信息
            userRoleDao.deleteUserRoleByUserId(user.getUserId());
            List<Role> roles = user.getRoles();
            if (roles != null&& !roles.isEmpty()){
                //JDK1.8之后的循化使用方式
                roles.stream().forEach(item->{
                    userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
                });
            }
            return new Result<User>(Result.ResultStatus.SUCCESS.status, "Update success", user);
        }
    }

    @Override
    public Result<User> deleteUser(int userId) {
        //还要删除和该id相关的信息
        userDao.delete(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status, "Delete success");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }
}
