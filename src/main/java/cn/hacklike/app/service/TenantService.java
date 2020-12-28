package cn.hacklike.app.service;

import cn.hacklike.app.entity.Tenant;

import java.util.List;

public interface TenantService {

    List<Tenant> selectTenant();

}
