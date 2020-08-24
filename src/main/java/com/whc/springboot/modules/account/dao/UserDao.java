package com.whc.springboot.modules.account.dao;

import com.whc.springboot.modules.account.entity.User;
import com.whc.springboot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: UserDao <br/>
 * Description: <br/>
 * date: 2020/8/21 13:33<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Repository
@Mapper
public interface UserDao {
    @Insert("insert into user (user_name,password,user_img,create_date)"
            + "values(#{userName},#{password},#{userImg},#{createDate})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    void insertUser(User user);

    @Select("select * from user where user_name = #{userName}")
    User getUserByUserName(String userName);

    @Select("<script>" +
            "select * from user "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by user_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<User> getUsersBySearchVo(SearchVo searchVo);
}
