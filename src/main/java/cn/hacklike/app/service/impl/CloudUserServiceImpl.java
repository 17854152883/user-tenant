package cn.hacklike.app.service.impl;

import cn.hacklike.app.entity.CloudUser;
import cn.hacklike.app.mapper.CloudUserMapper;
import cn.hacklike.app.service.CloudUserService;
import cn.hacklike.app.utils.Activemq;
import cn.hacklike.app.utils.FastdfsUtils;
import cn.hacklike.app.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;


@Service
public class CloudUserServiceImpl implements CloudUserService {

    @Resource
    private CloudUserMapper cloudUserMapper;

    @Resource
    BCryptPasswordEncoder encode;

    @Autowired
    private Activemq activemq;

    @Autowired
    private FastdfsUtils fastdfsUtils;

//    @Autowired
//    private JmsTemplate jmsTemplate;

    @Autowired
    private RedisUtils redisUtils;

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

        redisUtils.setString(cloudUser.getId(),JSON.toJSONString(cloudUser));

        return cloudUser;
    }

    @Override
    public String testUpload(MultipartFile file) {

        try {
            StorePath upload = fastdfsUtils.upload(file);
            String path = upload.getPath();
            System.out.println("上传的路径是: "+path);
            System.out.println("上传的全路径是" + upload.getFullPath());
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
