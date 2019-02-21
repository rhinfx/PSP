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
@Path("/dishes-authors")

public class RestDishesAuthors {
	
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
			+ "<th>Dish ID</th>"
			+ "<th>Dish</th>"
			+ "<th>Autor</th>"
			+ "<th>Rating</th>"
			+ "</tr>";
	
	private String html2 = "</table></body></html>";
	
	//MÉTODO POR DEFECTO => SELECT * FROM dishes_authors
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getDishesAuthors() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		//el underscore de la tabla (dishes_authors) se omite para la query
		Query q = session.createQuery("from DishesAuthors");
		
		String str = "";
		
		List<DishesAuthors> lista = q.list();
		
		for (DishesAuthors dishesauthors : lista) {
			str += "<tr>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(dishesauthors.getDishes().getId())+"'>"+dishesauthors.getDishes().getId()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(dishesauthors.getDishes().getId())+"'>"+dishesauthors.getDishes().getName()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/authors/"+String.valueOf(dishesauthors.getAuthors().getId())+"'>"+dishesauthors.getAuthors().getName()+"("+dishesauthors.getAuthors().getId()+")</a></td>"
					+ "<td>"+dishesauthors.getRating()+"</td>"
					+ "</tr>";
		}
		
		session.close();
		
		String nuevaInsercion = "</table>"
				+ "<form method='POST' action='http://localhost:8080/menusREST/api/dishes-authors/'>"
					+ "<input type='text' name='dishId' placeholder='Dish ID' size=5'>"
					+ "<input type='text' name='authorId' placeholder='Author ID' size='5'>"
					+ "<input type='text' name='rating' placeholder='Rating' size='5'>"
					+ "<input type='submit' value='Introducir'>"
					+ "</form>"
				+ "</body>"
				+ "</html>";		

		
		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM DISHESAUTHORS WHERE DISH_ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getDishesAuthorsByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		//dishes es lo que hibernate ha mapeado de dish_id
		Query q = session.createQuery("from DishesAuthors as da where da.dishes = "+n);
			
		String str = "";
			
		List<DishesAuthors> lista = q.list();
		
		for (DishesAuthors dishesauthors : lista) {
			str += "<tr>"
					+ "<td>"+dishesauthors.getDishes().getId()+"</td>"
					+ "<td>"+dishesauthors.getDishes().getName()+"</td>"
					+ "<td>"+dishesauthors.getAuthors().getName()+"</td>"
					+ "<td>"+dishesauthors.getRating()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String insertDishesAuthors(@FormParam("dishId") int dishId, @FormParam("authorId") int authorId, 
			@FormParam("rating") short rating) {
		
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> listaPlatos = q.list();
		
		q = session.createQuery("from Authors");
		
		List<Authors> listaAutores = q.list();
		
		//INSERTAMOS UNA FILA 
		
		DishesAuthors dishAuthor = new DishesAuthors();
		dishAuthor.setId(new DishesAuthorsId(dishId,authorId));
		dishAuthor.setAuthors(listaAutores.get(authorId-1));
		dishAuthor.setDishes(listaPlatos.get(dishId-1));
		dishAuthor.setRating(rating);
		
		session.save(dishAuthor);
		tx.commit();
		session.close();
		
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+listaAutores.get(authorId-1).getName()+"</li>"
					+ "<li>"+listaPlatos.get(dishId-1).getName()+"</li>"
					+ "<li>"+rating+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes-authors'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//DELETE - BORRAR UN REGISTRO
	@DELETE
	@Path("/{x}/{y}")
	@Produces (MediaType.TEXT_HTML)
	public String deleteDishAuthorByParam(@PathParam("x") int x, @PathParam("y") int y) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
			
		Session session = sesion.openSession();
			
		Transaction tx = session.beginTransaction();
			
		DishesAuthors dishAuthor = new DishesAuthors();
			
		dishAuthor = (DishesAuthors) session.load(DishesAuthors.class, new DishesAuthorsId(x,y));
			
		String dish = dishAuthor.getDishes().getName();
		String author = dishAuthor.getAuthors().getName();
			
		session.delete(dishAuthor);
		tx.commit();
		session.close();
			
			
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Eliminado:</h2>"
				+ "<ul>"
					+ "<li>"+dish+"</li>"
					+ "<li>"+author+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes-authors'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//PUT - ACTUALIZAR REGISTRO
	@PUT
	@Path("/{x}/{y}")
	@Produces (MediaType.TEXT_HTML)
	public String updateDishAuthorByParam(@PathParam("x") int x, @PathParam("y") int y, @QueryParam("dish") int dish, 
			@QueryParam("author") int author, @QueryParam("rating") short rating) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		DishesAuthors dishAuthor = new DishesAuthors();
		
		dishAuthor = (DishesAuthors) session.load(DishesAuthors.class, new DishesAuthorsId(x,y));
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> listaPlatos = q.list();
		
		q = session.createQuery("from Authors");
		
		List<Authors> listaAutores = q.list();
		
		dishAuthor.setDishes(listaPlatos.get(dish-1));
		dishAuthor.setAuthors(listaAutores.get(author-1));
		dishAuthor.setRating(rating);
	
		
		session.update(dishAuthor);
		tx.commit();
		session.close();
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Actualizado:</h2>"
				+ "<ul>"
					+ "<li>"+listaPlatos.get(dish-1).getName()+"</li>"
					+ "<li>"+listaAutores.get(author-1).getName()+"</li>"
					+ "<li>"+rating+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes-authors'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//OBJ --> JSON
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String dishesAuthorsJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from DishesAuthors");
		
		List<DishesAuthors> lista = q.list();
		
		session.close();
		
		return gson.toJson(lista);		
	}

	

	
}
