package com.whc.springboot.modules.account.dao;

import com.whc.springboot.modules.account.entity.User;
import com.whc.springboot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
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

    @Update("update user set user_name = #{userName},user_img = #{userImg} where user_id = #{userId}")
    void updateUser(User user);

    @Delete("delete from user where user_id = #{userId}")
    void delete(int userId);

    @Select("select * from user where user_id = #{userId}")
    @Results(id = "userResults",value = {
            //user_id第一次映射(如果没有对它本身进行映射得到的结果中user_id就不会有值)
            @Result(column = "user_id", property = "userId"),
            //user_id第二次映射
            @Result(column = "user_id", property = "roles", javaType = List.class,
                    many = @Many(select = "com.whc.springboot.modules.account.dao.RoleDao.getRolesByUserId"))
    })
    User getUserByUserId(int userId);
}
