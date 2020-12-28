package cn.hacklike.app.service.impl;

import cn.hacklike.app.entity.CloudUser;
import cn.hacklike.app.mapper.CloudUserMapper;
import cn.hacklike.app.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CloudUserServiceImpl implements CloudUserService {

    @Resource
    private CloudUserMapper cloudUserMapper;

    @Override
    @Transactional
    public CloudUser addUser(CloudUser cloudUser) {

        this.cloudUserMapper.insert(cloudUser);

        return cloudUser;
    }

}
