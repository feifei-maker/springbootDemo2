package com.whc.springboot.modules.test.dao;

import com.whc.springboot.modules.test.entity.Country;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: CountryDao <br/>
 * Description: <br/>
 * date: 2020/8/12 11:11<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Repository
@Mapper
public interface CountryDao {
    //通过id查询
    //一对多
    @Select("select * from m_country where country_id = #{countryId}")
    @Results(value = {
            @Result(column = "country_id", property = "countryId"),
            @Result(column = "country_id", property = "cities",
                    javaType = List.class, many = @Many(
                    select = "com.whc.springboot.modules.test.dao.CityDao.getCitiesByCountryId"))
    })
    Country getCountryByCountryId(int countryId);

    @Select("select * from m_country where country_name = #{countryName}")
    Country getCountryByCountryName(String countryName);
}
