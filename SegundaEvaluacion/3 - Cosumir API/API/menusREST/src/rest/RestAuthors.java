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
@Path("/authors")

public class RestAuthors {
	
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
			+ "<th>Surname</th>"
			+ "<th>Age</th>"
			+ "<th>Email</th>"
			+ "<th>Gender</th>"
			+ "<th>Nationality</th>"
			+ "</tr>";
	
	private String html2 = "</table></body></html>";
	
	//MÉTODO POR DEFECTO => SELECT * FROM AUTHORS
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getAuthors() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Authors");
		
		String str = "";
		
		List<Authors> lista = q.list();
		
		for (Authors authors : lista) {
			str += "<tr>"
					+ "<td><a href='http://localhost:8080/menusREST/api/authors/"+String.valueOf(authors.getId())+"'>"+authors.getId()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/authors/"+String.valueOf(authors.getId())+"'>"+authors.getName()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/authors/"+String.valueOf(authors.getId())+"'>"+authors.getSurname()+"</a></td>"
					+ "<td>"+authors.getAge()+"</td>"
					+ "<td>"+authors.getEmail()+"</td>"
					+ "<td>"+authors.getGender()+"</td>"
					+ "<td>"+authors.getNationality()+"</td>"
					+ "</tr>";
		}
		
		session.close();
		
		String nuevaInsercion = "</table>"
									+ "<form method='POST' action='http://localhost:8080/menusREST/api/authors/'>"
										+ "<input type='text' name='name' placeholder='Name' size='20'>"
										+ "<input type='text' name='surname' placeholder='Surname' size='19'>"
										+ "<input type='text' name='age' placeholder='Age' size='2'>"
										+ "<input type='text' name='email' placeholder='Email' size='19'>"
										+ "<input type='text' name='gender' placeholder='Gender' size='19'>"
										+ "<input type='text' name='nationality' placeholder='Nationality' size='19'>"
										+ "<input type='submit' value='Introducir'>"
									+ "</form>"
								+ "</body>"
								+ "</html>";
		
		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM AUTHORS WHERE ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getAuthorsByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
			
		Query q = session.createQuery("from Authors as a where a.id = "+n);
			
		String str = "";
			
		List<Authors> lista = q.list();
		
		for (Authors authors : lista) {
			str += "<tr>"
					+ "<td>"+authors.getId()+"</td>"
					+ "<td>"+authors.getName()+"</td>"
					+ "<td>"+authors.getSurname()+"</td>"
					+ "<td>"+authors.getAge()+"</td>"
					+ "<td>"+authors.getEmail()+"</td>"
					+ "<td>"+authors.getGender()+"</td>"
					+ "<td>"+authors.getNationality()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String insertAuthor(@FormParam("name") String name, 
			@FormParam("surname") String surname, @FormParam("age") int age, 
			@FormParam("email") String email, @FormParam("gender") String gender, @FormParam("nationality") String nationality) {
		
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Authors");
		
		List<Authors> lista = q.list();
		
		//INSERTAMOS UNA FILA 
		
		Authors author = new Authors();
		author.setId(lista.get(lista.size()-1).getId()+1);
		author.setName(name);
		author.setSurname(surname);
		author.setAge(age);
		author.setEmail(email);
		author.setGender(gender);
		author.setNationality(nationality);
		
		session.save(author);
		tx.commit();
		session.close();
	
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+author.getId()+"</li>"
					+ "<li>"+name+"</li>"
					+ "<li>"+surname+"</li>"
					+ "<li>"+age+"</li>"
					+ "<li>"+email+"</li>"
					+ "<li>"+gender+"</li>"
					+ "<li>"+nationality+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/authors'>Volver</a></h4>"
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
			
			Authors author = new Authors();
			
			author = (Authors) session.load(Authors.class, n);
			
			int id = author.getId();
			String name = author.getName();
			String surname = author.getSurname();
			int age = author.getAge();
			String email = author.getEmail();
			String gender = author.getGender();
			String nationality = author.getNationality();
			
			session.delete(author);
			tx.commit();
			session.close();
			
			return 	"<html>"
					+ "<body>"
					+ "<h2>Registro Eliminado:</h2>"
					+ "<ul>"
						+ "<li>"+id+"</li>"
						+ "<li>"+name+"</li>"
						+ "<li>"+surname+"</li>"
						+ "<li>"+age+"</li>"
						+ "<li>"+email+"</li>"
						+ "<li>"+gender+"</li>"
						+ "<li>"+nationality+"</li>"
					+ "</ul>"
					+ "<h4><a href='http://localhost:8080/menusREST/api/authors'>Volver</a></h4>"
					+ "</body>"
					+ "</html>";
		}
		
		//PUT - ACTUALIZAR REGISTRO
		@PUT
		@Path("/{parametre}")
		@Produces (MediaType.TEXT_HTML)
		public String updateAuthosByParam(@PathParam("parametre") int n, @QueryParam("name") String name, 
				@QueryParam("surname") String surname, @QueryParam("age") int age, @QueryParam("email") String email, 
				@QueryParam("gender") String gender, @QueryParam("nationality") String nationality) {
			
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			
			Session session = sesion.openSession();
			
			Transaction tx = session.beginTransaction();
			
			Authors author = new Authors();
			
			author = (Authors) session.load(Authors.class, n);
			
			author.setName(name);
			author.setSurname(surname);
			author.setAge(age);
			author.setEmail(email);
			author.setGender(gender);
			author.setNationality(nationality);
			
			
			session.update(author);
			tx.commit();
			session.close();
			
			return 	"<html>"
					+ "<body>"
					+ "<h2>Registro Actualizado:</h2>"
					+ "<ul>"
						+ "<li>"+n+"</li>"
						+ "<li>"+name+"</li>"
						+ "<li>"+surname+"</li>"
						+ "<li>"+age+"</li>"
						+ "<li>"+email+"</li>"
						+ "<li>"+gender+"</li>"
						+ "<li>"+nationality+"</li>"
					+ "</ul>"
					+ "<h4><a href='http://localhost:8080/menusREST/api/authors'>Volver</a></h4>"
					+ "</body>"
					+ "</html>";
		}
		
		//OBJ --> JSON
		@GET
		@Path("/json")
		@Produces(MediaType.APPLICATION_JSON)
		public String authorJson() {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			
			Session session = sesion.openSession();
			
			Query q = session.createQuery("from Authors");
			
			List<Authors> lista = q.list();
			
			session.close();
			
			
			return gson.toJson(lista);		
		}
	

	
}
