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
@Path("/menus")

public class RestMenus {
	
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
			+ "<th>Appetizer</th>"
			+ "<th>Main Course</th>"
			+ "<th>Dessert</th>"
			+ "</tr>";
	
	private String html2 = "</table></body></html>";
	
	//MÉTODO POR DEFECTO => SELECT * FROM MENUS
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getMenus() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Menus");
		
		String str = "";
		
		List<Menus> lista = q.list();
		
		for (Menus menus : lista) {
			str += "<tr>"
					+ "<td>"+menus.getId()+"</td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(menus.getDishesByDishAppetizer().getId())+"'>"+menus.getDishesByDishAppetizer().getName()+"("+menus.getDishesByDishAppetizer().getId()+")</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(menus.getDishesByDishMainCourse().getId())+"'>"+menus.getDishesByDishMainCourse().getName()+"("+menus.getDishesByDishMainCourse().getId()+")</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(menus.getDishesByDishDessert().getId())+"'>"+menus.getDishesByDishDessert().getName()+"("+menus.getDishesByDishDessert().getId()+")</a></td>"
					+ "</tr>";
		}
		
		session.close();
		
		String nuevaInsercion = "</table>"
								+ "<form method='POST' action='http://localhost:8080/menusREST/api/menus/'>"
									+ "<input type='text' name='appetizer' placeholder='Appetizer(id)' size='10'>"
									+ "<input type='text' name='mainCourse' placeholder='Main Course(id)' size='12'>"
									+ "<input type='text' name='dessert' placeholder='Dessert(id)' size='10'>"
									+ "<input type='submit' value='Introducir'>"
									+ "</form>"
								+ "</body>"
								+ "</html>";		
		
		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM MENUS WHERE ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getMenusByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
			
		Query q = session.createQuery("from Menus as m where m.id = "+n);
			
		String str = "";
			
		List<Menus> lista = q.list();
		
		for (Menus menus : lista) {
			str += "<tr>"
					+ "<td>"+menus.getId()+"</td>"
					+ "<td>"+menus.getDishesByDishAppetizer().getName()+"</td>"
					+ "<td>"+menus.getDishesByDishMainCourse().getName()+"</td>"
					+ "<td>"+menus.getDishesByDishDessert().getName()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String insertMenu(@FormParam("appetizer") int appetizer, 
			@FormParam("mainCourse") int mainCourse, @FormParam("dessert") int dessert) {
		
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> lista = q.list();
		
		q = session.createQuery("from Menus");
		
		List<Menus> listaMenus = q.list();
		
		//INSERTAMOS UNA FILA 
		
		Menus menu = new Menus();
		menu.setId(listaMenus.get(listaMenus.size()-1).getId()+1);
		menu.setDishesByDishAppetizer(lista.get(appetizer-1));
		menu.setDishesByDishMainCourse(lista.get(mainCourse-1));
		menu.setDishesByDishDessert(lista.get(dessert-1));
		
		session.save(menu);
		tx.commit();
		session.close();
		
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+menu.getId()+"</li>"
					+ "<li>"+lista.get(appetizer-1).getName()+"</li>"
					+ "<li>"+lista.get(mainCourse-1).getName()+"</li>"
					+ "<li>"+lista.get(dessert-1).getName()+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/menus'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//DELETE - BORRAR UN REGISTRO
	@DELETE
	@Path("/{parametre}")
	@Produces (MediaType.TEXT_HTML)
	public String deleteAuthorByParam(@PathParam("parametre") int n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
			
		Session session = sesion.openSession();
			
		Transaction tx = session.beginTransaction();
			
		Menus menu = new Menus();
			
		menu = (Menus) session.load(Menus.class, n);
			
		int id = menu.getId();
			
		session.delete(menu);
		tx.commit();
		session.close();
			
			
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Eliminado:</h2>"
				+ "<ul>"
					+ "<li>"+id+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/menus'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//PUT - ACTUALIZAR REGISTRO
	@PUT
	@Path("/{parametre}")
	@Produces (MediaType.TEXT_HTML)
	public String updateMenuByParam(@PathParam("parametre") int n, @QueryParam("appetizer") int appetizer, 
			@QueryParam("mainCourse") int mainCourse, @QueryParam("dessert") int dessert) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Menus menu = new Menus();
		
		menu = (Menus) session.load(Menus.class, n);
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> lista = q.list();
		
		menu.setDishesByDishAppetizer(lista.get(appetizer-1));
		menu.setDishesByDishMainCourse(lista.get(mainCourse-1));
		menu.setDishesByDishDessert(lista.get(dessert-1));
		
		
		session.update(menu);
		tx.commit();
		session.close();
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Actualizado:</h2>"
				+ "<ul>"
					+ "<li>"+n+"</li>"
					+ "<li>"+lista.get(appetizer-1).getName()+"</li>"
					+ "<li>"+lista.get(mainCourse-1).getName()+"</li>"
					+ "<li>"+lista.get(dessert-1).getName()+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/menus'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//OBJ --> JSON
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String menusJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Menus");
		
		List<Menus> lista = q.list();
		
		session.close();
		
		return gson.toJson(lista);		
	}

	

	
}
