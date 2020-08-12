package com.whc.springboot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.entity.City;

import java.util.List;

/**
 * ClassName: CityService <br/>
 * Description: <br/>
 * date: 2020/8/12 13:49<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface CityService {
    List<City> getCitiesByCountryId(int countryId);
//    分页
    PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo);
}
