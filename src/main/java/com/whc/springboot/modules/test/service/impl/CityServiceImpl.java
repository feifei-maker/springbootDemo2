package com.whc.springboot.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import com.whc.springboot.modules.test.dao.CityDao;
import com.whc.springboot.modules.test.entity.City;
import com.whc.springboot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: CityServiceImpl <br/>
 * Description: <br/>
 * date: 2020/8/12 13:49<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        //防止出现空指针异常
        return Optional
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo) {
        //初始化当前页
        searchVo.initSearchVo();
        //设置分页初始值
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<City>(Optional
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList()));

    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional
                .ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"Insert success",city);
    }

    @Override
    @Transactional
//    指定发生某个异常时不进行回滚
//    @Transactional(noRollbackFor  = ArithmeticException.class)
//    指定发生某个异常时进行回滚
//    @Transactional(rollbackFor = ArithmeticException.class)
    public Result<City> updateCity(City city) {
//        int a = 1/0;
        cityDao.updateCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"Update success",city);
    }

    @Override
    @Transactional
    public Result<Object> deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status,"Delete success");
    }


}
