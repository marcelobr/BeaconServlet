package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

@WebServlet("/AllTopics")
public class AllTopics extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Context
    ServletContext context;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		
		File file = new File(context.getRealPath("/database/topicos.json"));
		
//		if (!file.exists()){
//			file = new File(context.getRealPath("/database/MenuContent1.json"));
//		}
		 
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
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.println(json.toString());
		
	}
	
}
