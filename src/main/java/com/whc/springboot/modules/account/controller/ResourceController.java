package com.whc.springboot.modules.account.controller;
import com.whc.springboot.modules.account.entity.Resource;
import com.whc.springboot.modules.account.service.ResourceService;
import com.whc.springboot.modules.common.vo.Result;
import com.whc.springboot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

/**
 * ClassName: ResourceController <br/>
 * Description: <br/>
 * date: 2020/8/25 20:09<br/>
 *
 * @author FEI FEI<br/>
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/api")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/resources", consumes = "application/json")
    public PageInfo<Resource> getResources(@RequestBody SearchVo searchVo) {
        return resourceService.getResources(searchVo);
    }

    @PostMapping(value = "/resource", consumes = "application/json")
    public Result<Resource> insertResource(@RequestBody Resource resource) {
        return resourceService.editResource(resource);
    }

    @PutMapping(value = "/resource", consumes = "application/json")
    public Result<Resource> updateResource(@RequestBody Resource resource) {
        return resourceService.editResource(resource);
    }

    @RequestMapping("/resource/{resourceId}")
    public Resource getResourceById(@PathVariable int resourceId) {
        return resourceService.getResourceById(resourceId);
    }

    @DeleteMapping("/resource/{resourceId}")
    public Result<Resource> deleteResource(@PathVariable int resourceId) {
        return resourceService.deleteResource(resourceId);
    }
}
