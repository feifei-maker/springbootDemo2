package com.whc.springboot.modules.test.dao;

import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: CityDao <br/>
 * Description: <br/>
 * date: 2020/8/12 11:10<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Repository
@Mapper
public interface CityDao {
    //通过id查询
    @Select("select * from m_city where country_id = #{countryId}")
    List<City> getCitiesByCountryId(int countryId);

    //多条件查询
    @Select("<script>" +
            "select * from m_city "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by city_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<City> getCitiesBySearchVo(SearchVo searchVo);

    //增加(@Options是用来设置主键的)
    @Insert("insert into m_city (city_name,local_city_name,country_id,date_created)" +
            "values(#{cityName},#{localCityName},#{countryId},#{dateCreated})")
    @Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
    void insertCity(City city);

    //修改
    @Update("update m_city set city_name = #{cityName} where city_id = #{cityId}")
    void updateCity(City city);

    //删除
    @Delete("delete from m_city where city_id = #{cityId}")
    void deleteCity(int cityId);
}
