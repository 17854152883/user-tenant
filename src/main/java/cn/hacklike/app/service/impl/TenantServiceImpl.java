package cn.hacklike.app.service.impl;

import cn.hacklike.app.entity.Tenant;
import cn.hacklike.app.mapper.TenantMapper;
import cn.hacklike.app.service.TenantService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public List<Tenant> selectTenant() {

        List<Tenant> tenants = this.tenantMapper.selectList(new QueryWrapper<Tenant>());

        return tenants;
    }

    @JmsListener(destination = "like新用户",containerFactory = "jmsListenerContainerTopic")
    public void rctiveMsg(String message){
        System.out.println("------监听到activemq的数据"+message);
    }

}
