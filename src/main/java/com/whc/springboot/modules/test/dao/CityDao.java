package com.whc.springboot.modules.test.dao;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    @Select("select * from m_city where country_id = #{countryId}")
    List<City> getCitiesByCountryId(int countryId);
    PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);
}
