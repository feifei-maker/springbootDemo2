package com.whc.springboot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.whc.springboot.modules.account.entity.Resource;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;

import java.util.List;

/**
 * ClassName: ResourceService <br/>
 * Description: <br/>
 * date: 2020/8/25 20:16<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
public interface ResourceService {
    Result<Resource> editResource(Resource resource);

    Result<Resource> deleteResource(int resourceId);

    PageInfo<Resource> getResources(SearchVo searchVo);

    List<Resource> getResourcesByRoleId(int roleId);

    Resource getResourceById(int resourceId);
}
