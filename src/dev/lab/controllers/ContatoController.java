package dev.lab.controllers;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.lab.models.Contato;
import dev.lab.dao.ContatoDao;

import dev.lab.helpers.Json;

@WebServlet("/contatos")
public class ContatoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("UTF8");
		String action = req.getParameter("cmd") != null ? req.getParameter("cmd") : "list";
		
		try {
			switch (action) {
				case "post":
					save(req, res);
					break;
				case "edit":
					edit(req, res);
					break;
				case "put":
					update(req, res);
					break;
				case "del":
					delete(req, res);
					break;
				default:
					list(req, res);
					break;
			}
		}
		catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doGet(req, res);
	}
	
	private void list(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		List<Contato> contatos = ContatoDao.getInstance().findAll();
		
		req.setAttribute("contatos", contatos);
		
		RequestDispatcher rd = req.getRequestDispatcher("contato/index.jsp");
		rd.forward(req, res);
	}
	
	private void save(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		Contato contato = new Contato();
		
		contato.setNome(req.getParameter("nome"));
		contato.setEmail(req.getParameter("email"));
		contato.setTelefone(req.getParameter("telefone"));
		
		ContatoDao.getInstance().save(contato);
		
		new Json(res).contato(true, "Novo contato adicionado.");
	}
	
	private void edit(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Contato contato = ContatoDao.getInstance().find(id);
		
		new Json(res).contato(contato);
	}
	
	private void update(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Contato contato = new Contato();
		
		contato.setId(id);
		contato.setNome(req.getParameter("nome"));
		contato.setEmail(req.getParameter("email"));
		contato.setTelefone(req.getParameter("telefone"));
		
		ContatoDao.getInstance().update(contato);
		
		new Json(res).contato(true, "Contato atualizado.");
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		ContatoDao.getInstance().delete(id);
		
		new Json(res).contato(true, "Contato excluido");
	}

}
