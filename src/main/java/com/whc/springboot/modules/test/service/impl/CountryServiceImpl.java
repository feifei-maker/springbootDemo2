package com.whc.springboot.modules.test.service.impl;

import com.whc.springboot.modules.test.dao.CountryDao;
import com.whc.springboot.modules.test.entity.Country;
import com.whc.springboot.modules.test.service.CountryService;
import com.whc.springboot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName: CountryServiceImpl <br/>
 * Description: <br/>
 * date: 2020/8/12 11:17<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country getCountryByRedis(int countryId) {
        Country country = countryDao.getCountryByCountryId(countryId);
        String countryKey = String.format("country%d", countryId);
        redisUtils.set(countryKey,country);
        return (Country) redisUtils.get(countryKey);
    }
}
