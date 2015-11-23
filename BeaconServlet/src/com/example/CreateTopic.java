package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

@WebServlet("/CreateTopic")
public class CreateTopic extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Context
    ServletContext context;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paramTopic = req.getParameter("topic");
		ServletContext context = getServletContext();
//		try {
//			ServletContext context = getServletContext();
//			SAXBuilder builder = new SAXBuilder();
//			File xmlFile = new File(context.getRealPath("/database/topicos.xml"));
//
//			Document doc = (Document) builder.build(xmlFile);
//			Element rootNode = doc.getRootElement();
//			
//			Element topic = new Element("topico").setText(paramTopic);
//			rootNode.addContent(topic);
//			
			// update staff id attribute
//			Element staff = rootNode.getChild("staff");
//			staff.getAttribute("id").setValue("2");

			// add new age element
//			Element age = new Element("age").setText("28");
//			staff.addContent(age);

			// update salary value
//			staff.getChild("salary").setText("7000");

			// remove firstname element
//			staff.removeChild("firstname");

//			XMLOutputter xmlOutput = new XMLOutputter();
//
//			// display nice nice
//			xmlOutput.setFormat(Format.getPrettyFormat());
//			xmlOutput.output(doc, new FileWriter(context.getRealPath("/database/topicos.xml")));
//
//			//xmlOutput.output(doc, System.out);
//
//			//System.out.println("File updated!");
//		  } catch (IOException io) {
//			io.printStackTrace();
//		  } catch (JDOMException e) {
//			e.printStackTrace();
//		  }
		
//		JSONObject json = new JSONObject(context.getRealPath("/database/topicos.json"));
//		json.putOnce("topico", paramTopic);
//		JSONArray topicos = json.getJSONArray("topico");
		Gson gson = new Gson();
		
		try {			
			BufferedReader br = new BufferedReader(
					new FileReader(context.getRealPath("/database/topicos.json")));

			JsonElement topic = gson.fromJson(br, JsonElement.class);
			JsonArray jsonArr = topic.getAsJsonArray();
			JsonElement newElement = new JsonParser().parse(paramTopic);
			if (!jsonArr.contains(newElement)) {
				jsonArr.add(newElement);
				 
				String json = gson.toJson(jsonArr);  
				    
				FileWriter writer = new FileWriter(context.getRealPath("/database/topicos.json"));  
				writer.write(json);  
				writer.close();   

//				System.out.println(json);
				resp.setStatus(resp.SC_OK);
			}
			else
				resp.setStatus(resp.SC_INTERNAL_SERVER_ERROR);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
}
