package cn.hacklike.app.controller;

import cn.hacklike.app.entity.CloudUser;
import cn.hacklike.app.entity.Tenant;
import cn.hacklike.app.service.TenantService;
import cn.hacklike.common.Response;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tenant/")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @RequestMapping("selectList")
    public Response<List<Tenant>> selectTenant(){
        try {
            List<Tenant> tenants = tenantService.selectTenant();
            return Response.newInstance(tenants);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.newErrorInstance("查询租户失败",e);
        }
    }

    @RequestMapping("selectListFeign")
    public String selectTenantFeign(){
        try {
            List<Tenant> tenants = this.tenantService.selectTenant();
            return JSON.toJSONString(tenants);
        } catch (Exception e) {
            e.printStackTrace();
            return "查询租户失败";
        }
    }


}
