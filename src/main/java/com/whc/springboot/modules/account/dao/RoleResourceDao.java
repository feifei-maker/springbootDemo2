package com.whc.springboot.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
 * ClassName: RoleResourceDao <br/>
 * Description: <br/>
 * date: 2020/8/25 20:11<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */


@Mapper
@Repository
public interface RoleResourceDao {

    @Delete("delete from role_resource where resource_id = #{resourceId}")
    void deletRoleResourceByResourceId(int resourceId);

    @Insert("insert role_resource(role_id, resource_id) value(#{roleId}, #{resourceId})")
    void addRoleResource(@Param("roleId") int roleId, @Param("resourceId") int resourceId);

}