package rest;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import hibernate.*;



//METODOS REST
@Path("/dishes")

public class RestDishes {
	
	// ESTRUCTURA DEL HTML
	
	private String html1 = "<html>"
			+ "<head>"
			+ "<style> "
			+ "table { "
			+ "font-family: arial, sans-serif;"
			+ "border-collapse: collapse;"
			+ "width: 100%;"
			+ "}"
			+ "td, th {"
			+ "boder: 1px solid #dddddd;"
			+ "text-align: left;"
			+ "padding: 8px;"
			+ "}"
			+ "tr:nth-child(even) {"
			+ "background-color: #dddddd;"
			+ "}"
			+ "</style>"
			+ "</head>"
			+ "<body>"
			+ "<table>"
			+ "<tr>"
			+ "<th>ID</th>"
			+ "<th>Name</th>"
			+ "<th>Time</th>"
			+ "<th>Category</th>"
			+ "<th>Cuisine</th>"
			+ "<th>Description</th>"
			+ "</tr>";
	
	private String html2 = "</table></body></html>";
	
	//MÉTODO POR DEFECTO => SELECT * FROM DISHES
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getDishes() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Dishes");
		
		String str = "";
		
		List<Dishes> lista = q.list();
		
		for (Dishes dishes : lista) {
			str += "<tr>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(dishes.getId())+"'>"+dishes.getId()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(dishes.getId())+"'>"+dishes.getName()+"</a></td>"
					+ "<td>"+dishes.getTime()+"</td>"
					+ "<td>"+dishes.getCategory()+"</td>"
					+ "<td>"+dishes.getCuisine()+"</td>"
					+ "<td>"+dishes.getDescription()+"</td>"
					+ "</tr>";
		}
		
		session.close();
		
		String nuevaInsercion = 	"</table>"
									+ "<form method='POST' action='http://localhost:8080/menusREST/api/dishes/'>"
										+ "<input type='text' name='name' placeholder='Name' size='20'>"
										+ "<input type='text' name='time' placeholder='Time' size='5'>"
										+ "<input type='text' name='category' placeholder='Category' size='12'>"
										+ "<input type='text' name='cuisine' placeholder='Cuisine' size='12'>"
										+ "<input type='text' name='description' placeholder='Description' size='250'>"
										+ "<input type='submit' value='Introducir'>"
									+ "</form>"
									+ "</body>"
									+ "</html>";
		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM DISHES WHERE ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getDishesByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
			
		Query q = session.createQuery("from Dishes as d where d.id = "+n);
			
		String str = "";
			
		List<Dishes> lista = q.list();
			
		for (Dishes dishes : lista) {
			str += "<tr>"
					+ "<td>"+dishes.getId()+"</td>"
					+ "<td>"+dishes.getName()+"</td>"
					+ "<td>"+dishes.getTime()+"</td>"
					+ "<td>"+dishes.getCategory()+"</td>"
					+ "<td>"+dishes.getCuisine()+"</td>"
					+ "<td>"+dishes.getDescription()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String insertDish(@FormParam("name") String name, 
			@FormParam("time") int time, @FormParam("category") String category, 
			@FormParam("cuisine") String cuisine, @FormParam("description") String description) {
		
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> lista = q.list();
		
		//INSERTAMOS UNA FILA 
		
		Dishes dish = new Dishes();
		dish.setId(lista.get(lista.size()-1).getId()+1);
		dish.setName(name);
		dish.setTime(time);
		dish.setCategory(category);
		dish.setCuisine(cuisine);
		dish.setDescription(description);
		
		session.save(dish);
		tx.commit();
		session.close();
		
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+dish.getId()+"</li>"
					+ "<li>"+name+"</li>"
					+ "<li>"+time+"</li>"
					+ "<li>"+category+"</li>"
					+ "<li>"+cuisine+"</li>"
					+ "<li>"+description+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//DELETE - BORRAR UN REGISTRO
	@DELETE
	@Path("/{parametre}")
	@Produces (MediaType.TEXT_HTML)
	public String deleteDishByParam(@PathParam("parametre") int n) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Dishes dish = new Dishes();
		
		dish = (Dishes) session.load(Dishes.class, n);
		
		int id = dish.getId();
		String name = dish.getName();
		int time = dish.getTime();
		String category = dish.getCategory();
		String cuisine = dish.getCategory();
		String description = dish.getDescription();
		
		session.delete(dish);
		tx.commit();
		session.close();
		
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Eliminado:</h2>"
				+ "<ul>"
					+ "<li>"+id+"</li>"
					+ "<li>"+name+"</li>"
					+ "<li>"+time+"</li>"
					+ "<li>"+category+"</li>"
					+ "<li>"+cuisine+"</li>"
					+ "<li>"+description+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//PUT - ACTUALIZAR REGISTRO
	@PUT
	@Path("/{parametre}")
	@Produces (MediaType.TEXT_HTML)
	public String updateDishByParam(@PathParam("parametre") int n, @QueryParam("name") String name, 
			@QueryParam("time") int time, @QueryParam("category") String category, @QueryParam("cuisine") String cuisine, 
			@QueryParam("description") String description) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Dishes dish = new Dishes();
		
		dish = (Dishes) session.load(Dishes.class, n);
		
		dish.setName(name);
		dish.setTime(time);
		dish.setCategory(category);
		dish.setCuisine(cuisine);
		dish.setDescription(description);
		
		
		session.update(dish);
		tx.commit();
		session.close();
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Actualizado:</h2>"
				+ "<ul>"
					+ "<li>"+n+"</li>"
					+ "<li>"+name+"</li>"
					+ "<li>"+time+"</li>"
					+ "<li>"+category+"</li>"
					+ "<li>"+cuisine+"</li>"
					+ "<li>"+description+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	

	//OBJ --> JSON
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String dishJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> lista = q.list();
		
		session.close();
		
		
		return gson.toJson(lista);		
	}
	
	
	
	
	

	
}
