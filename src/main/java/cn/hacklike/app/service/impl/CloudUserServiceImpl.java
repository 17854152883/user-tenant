package cn.hacklike.app.service.impl;

import cn.hacklike.app.entity.CloudUser;
import cn.hacklike.app.mapper.CloudUserMapper;
import cn.hacklike.app.service.CloudUserService;
import cn.hacklike.app.utils.Activemq;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CloudUserServiceImpl implements CloudUserService {

    @Resource
    private CloudUserMapper cloudUserMapper;

    @Resource
    BCryptPasswordEncoder encode;

    @Autowired
    private Activemq activemq;

    @Override
    @Transactional
    public CloudUser addUser(CloudUser cloudUser) {

        cloudUser.setPassword(encode.encode(cloudUser.getPassword()));

        this.cloudUserMapper.insert(cloudUser);
//        for (int i = 0; i < 6; i++) {
//            activemq.activeMqDian(JSON.toJSONString(cloudUser));
//        }

        return cloudUser;
    }

}
