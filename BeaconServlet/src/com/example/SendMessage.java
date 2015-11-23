package com.example;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

//	@Resource(name = "teste")
//    private Topic topic;
 
//    @Resource(name = "bar")
//    private Queue barQueue;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String paramTopic = req.getParameter("topic");
    	String paramMessage = req.getParameter("message");
    	
		MessageBroker msgBroker = new MessageBroker();
		msgBroker.send(paramTopic, paramMessage);
    }
}
