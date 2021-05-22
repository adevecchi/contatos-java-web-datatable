package dev.lab.helpers;

import dev.lab.models.Contato;

public class ContatoJson {
	
	private boolean status;
	private String message = null;
	private Contato contato = null;
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Contato getContato() {
		return contato;
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
