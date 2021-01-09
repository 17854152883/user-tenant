package cn.hacklike.app.utils;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Activemq {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL = "failover://tcp://192.168.8.132:61616";

    ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, URL);

    public void activeMqDian(String message){
//        ConnectionFactory connectionFactory = null; // 连接工厂
        Connection connection = null; // 连接
        Session session = null; // 会话
        Destination destination = null; // 消息目的地
        MessageProducer messageProducer = null; // 消息生产者
        try {

            connection = factory.createConnection();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("新用户租户信息");
            messageProducer = session.createProducer(destination);

            TextMessage textMessage = session.createTextMessage(message);

            messageProducer.send(textMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                messageProducer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * @Description 将接受到的消息及消息模式(topic或queue)放到队列里面，然后消费
     * 者只需要正确的添加注解@JmsListener（destination = "目的地"），监听队列消息就会主动获取
     * @Param destination 目的地
     */
    public void sendMessage(String msg){
        ActiveMQTopic destination = new ActiveMQTopic("like新用户");
        jmsMessagingTemplate.convertAndSend(destination,msg);
    }


}
