package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@WebServlet("/SendMacAddress")
public class SendMacAddress extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Context
    ServletContext context;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramMACAddress = req.getParameter("macaddress");
		
		File file = new File(context.getRealPath("/database/mensagens.json"));
		
		 
		StringBuilder json = new StringBuilder();
		try {
			FileReader reader = new FileReader(file);
		 	BufferedReader input = new BufferedReader(reader);
		 	String linha;
		 	while ((linha = input.readLine()) != null) {
		 		json.append(linha.trim());
		 	}
		 	input.close();
		 } catch (IOException ioe) {
			 System.out.println(ioe);
		 }
		
//		resp.setContentType("application/json");
//		PrintWriter out = resp.getWriter();
//		out.println(json.toString());
		Gson gson = new Gson();
		Mensagem[] mensagem = gson.fromJson(json.toString(), Mensagem[].class);
		
		MessageBroker msgBroker = new MessageBroker();
		msgBroker.send(paramMACAddress, mensagem[0].texto);
		
	}
}
