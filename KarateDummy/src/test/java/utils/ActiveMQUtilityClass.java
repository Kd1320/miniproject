package utils;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQUtilityClass {
    private static String brokerUrl = "tcp://localhost:61616";
    private static String mqUserName = "admin";
    private static String mqPassword = "admin";

    public void sendMessage(String queueName, String message) {
        try {

            ConnectionFactory factory = new ActiveMQConnectionFactory(mqUserName, mqPassword, brokerUrl);
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException("MQ Send Failed: " + e.getMessage(), e);
        }
    }

    // Read Message
    public String readMessage(String queueName, long timeoutMillis) {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(mqUserName, mqPassword, brokerUrl);
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);

            Message msg = consumer.receive(timeoutMillis); // wait up to timeoutMillis
            String messageText = null;

            if (msg != null && msg instanceof TextMessage) {
                messageText = ((TextMessage) msg).getText();
            }

            connection.close();
            return messageText;
        } catch (Exception e) {
            throw new RuntimeException("MQ Read Failed: " + e.getMessage(), e);
        }
    }

    // New method to purge a queue
    public void purgeQueue(String queueName) {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(mqUserName, mqPassword, brokerUrl);
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(destination);

            Message msg;
            int count = 0;
            while ((msg = consumer.receive(100)) != null) {
                count++;
            }

            connection.close();
            System.out.println("Purged " + count + " messages from queue: " + queueName);
        } catch (Exception e) {
            throw new RuntimeException("Queue Purge Failed: " + e.getMessage(), e);
        }
    }
}
