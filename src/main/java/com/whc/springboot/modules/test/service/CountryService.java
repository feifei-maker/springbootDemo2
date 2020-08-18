package com.whc.springboot.modules.test.service;

import com.whc.springboot.modules.test.entity.Country;

/**
 * ClassName: CountryService <br/>
 * Description: <br/>
 * date: 2020/8/12 11:16<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);

    Country getCountryByRedis(int countryId);
}
