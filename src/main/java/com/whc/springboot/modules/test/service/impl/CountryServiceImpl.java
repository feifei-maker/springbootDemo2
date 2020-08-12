package com.whc.springboot.modules.test.service.impl;

import com.whc.springboot.modules.test.dao.CountryDao;
import com.whc.springboot.modules.test.entity.Country;
import com.whc.springboot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }
}
