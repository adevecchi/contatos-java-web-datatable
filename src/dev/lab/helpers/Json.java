package dev.lab.helpers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dev.lab.models.Contato;

public class Json {
	
	private ContatoJson json;
	private Gson gson;
	private PrintWriter out;
	private HttpServletResponse response;
	
	public Json(HttpServletResponse response) {
		json = new ContatoJson();
		gson = new Gson();
		
		this.response = response;
		this.response.setContentType("application/json; charset=utf-8");
		
		try {
			out = response.getWriter();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void contato(Contato contato) {
		json.setStatus(true);
		json.setContato(contato);
		
		sendResponse(gson.toJson(json));
	}
	
	public void contato(boolean status, String message) {
		json.setStatus(status);
		json.setMessage(message);
		
		sendResponse(gson.toJson(json));
	}
	
	private void sendResponse(String response) {
		out.print(response);
		out.flush();
	}

}
