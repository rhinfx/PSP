package rest;
import java.util.List;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hibernate.*;



//METODOS REST
@Path("/ingredients")

public class RestIngredients {
	
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
			+ "</tr>";
	
	private String html2 = "</table></body></html>";
	
	//MÉTODO POR DEFECTO => SELECT * FROM INGREDIENTS
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getIngredients() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Ingredients");
		
		String str = "";
		
		List<Ingredients> lista = q.list();
		
		for (Ingredients ingredients : lista) {
			str += "<tr>"
					+ "<td><a href='http://localhost:8080/menusREST/api/ingredients/"+String.valueOf(ingredients.getId())+"'>"+ingredients.getId()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/ingredients/"+String.valueOf(ingredients.getId())+"'>"+ingredients.getName()+"</a></td>"
					+ "</tr>";
		}
		
		session.close();
		
		String nuevaInsercion = "</table>"
								+ "<form method='POST' action='http://localhost:8080/menusREST/api/ingredients/'>"
									+ "<input type='text' name='name' placeholder='Name' size='20'>"
									+ "<input type='submit' value='Introducir'>"
								+ "</form>"
								+ "</body>"
								+ "</html>";

		
		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM INGREDIENTS WHERE ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getIngredientsByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
			
		Query q = session.createQuery("from Ingredients as i where i.id = "+n);
			
		String str = "";
			
		List<Ingredients> lista = q.list();
		
		for (Ingredients ingredients : lista) {
			str += "<tr>"
					+ "<td>"+ingredients.getId()+"</td>"
					+ "<td>"+ingredients.getName()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String insertIngredient(@FormParam("name") String name) {
		
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Ingredients");
		
		List<Ingredients> lista = q.list();
		
		//INSERTAMOS UNA FILA 
		
		Ingredients ingredient = new Ingredients();
		ingredient.setId(lista.get(lista.size()-1).getId()+1);
		ingredient.setName(name);
		
		session.save(ingredient);
		tx.commit();
		session.close();
	
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+ingredient.getId()+"</li>"
					+ "<li>"+name+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/ingredients'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//DELETE - BORRAR UN REGISTRO
	@DELETE
	@Path("/{parametre}")
	@Produces (MediaType.TEXT_HTML)
	public String deleteIngredientByParam(@PathParam("parametre") int n) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Ingredients ingredient = new Ingredients();
		
		ingredient = (Ingredients) session.load(Ingredients.class, n);
		
		int id = ingredient.getId();
		String name = ingredient.getName();
		
		session.delete(ingredient);
		tx.commit();
		session.close();
		
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Eliminado:</h2>"
				+ "<ul>"
					+ "<li>"+id+"</li>"
					+ "<li>"+name+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/ingredients'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//PUT - ACTUALIZAR REGISTRO
	@PUT
	@Path("/{parametre}")
	@Produces (MediaType.TEXT_HTML)
	public String updateIngredientByParam(@PathParam("parametre") int n, @QueryParam("name") String name) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Ingredients ingredient = new Ingredients();
		
		ingredient = (Ingredients) session.load(Ingredients.class, n);
		
		ingredient.setName(name);
			
		session.update(ingredient);
		tx.commit();
		session.close();
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Actualizado:</h2>"
				+ "<ul>"
					+ "<li>"+n+"</li>"
					+ "<li>"+name+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/ingredients'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//OBJ --> JSON
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String IngredientJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Ingredients");
		
		List<Ingredients> lista = q.list();
		
		session.close();
		
		return gson.toJson(lista);		
	}

	

	
}
