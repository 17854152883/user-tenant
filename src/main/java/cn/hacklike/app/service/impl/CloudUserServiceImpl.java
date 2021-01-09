package cn.hacklike.app.service.impl;

import cn.hacklike.app.entity.CloudUser;
import cn.hacklike.app.mapper.CloudUserMapper;
import cn.hacklike.app.service.CloudUserService;
import cn.hacklike.app.utils.Activemq;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;


@Service
public class CloudUserServiceImpl implements CloudUserService {

    @Resource
    private CloudUserMapper cloudUserMapper;

    @Resource
    BCryptPasswordEncoder encode;

    @Autowired
    private Activemq activemq;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    @Transactional
    public CloudUser addUser(CloudUser cloudUser) {

        cloudUser.setPassword(encode.encode(cloudUser.getPassword()));

        this.cloudUserMapper.insert(cloudUser);
//        for (int i = 0; i < 6; i++) {
//            activemq.activeMqDian(JSON.toJSONString(cloudUser));
//        }
//        jmsTemplate.send("like新用户", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage();
//                textMessage.setText(JSON.toJSONString(cloudUser));
//                return textMessage;
//            }
//        });
        activemq.sendMessage(JSON.toJSONString(cloudUser));

        return cloudUser;
    }

}
