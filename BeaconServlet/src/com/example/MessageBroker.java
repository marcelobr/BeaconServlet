package com.example;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class MessageBroker {
	@Resource
    private ConnectionFactory connectionFactory;
	
	public void send(String paramTopic, String paramMessage) {
		try {
			Connection connection = connectionFactory.createConnection();
			
			connection.start();
			
			 // Create a Session
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        
	        Topic topic = session.createTopic(paramTopic);
	        
	        // Create a MessageProducer from the Session to the Topic or Queue
	        MessageProducer producer = session.createProducer(topic);
	        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	        
	        // Create a message
	        TextMessage message = session.createTextMessage(paramMessage);
	        		
	        // Tell the producer to send the message
	        producer.send(message);
	        
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
