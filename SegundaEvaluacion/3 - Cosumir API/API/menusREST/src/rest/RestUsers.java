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
@Path("/users")

public class RestUsers {
	
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
	
	//MÉTODO POR DEFECTO => SELECT * FROM USERS
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getUsers() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from Users");
		
		String str = "";
		
		List<Users> lista = q.list();
		
		for (Users users : lista) {
			str += "<tr>"
					+ "<td><a href='http://localhost:8080/menusREST/api/users/"+String.valueOf(users.getId())+"'>"+users.getId()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/users/"+String.valueOf(users.getId())+"'>"+users.getName()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/users/"+String.valueOf(users.getId())+"'>"+users.getSurname()+"</a></td>"
					+ "<td>"+users.getAge()+"</td>"
					+ "<td>"+users.getEmail()+"</td>"
					+ "<td>"+users.getGender()+"</td>"
					+ "<td>"+users.getNationality()+"</td>"
					+ "</tr>";
		}
		
		String nuevaInsercion = "</table>"
								+ "<form method='POST' action='http://localhost:8080/menusREST/api/users/'>"
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

		
		session.close();
		
		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM USERS WHERE ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getUsersByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
			
		Query q = session.createQuery("from Users as u where u.id = "+n);
			
		String str = "";
			
		List<Users> lista = q.list();
		
		for (Users users : lista) {
			str += "<tr>"
					+ "<td>"+users.getId()+"</td>"
					+ "<td>"+users.getName()+"</td>"
					+ "<td>"+users.getSurname()+"</td>"
					+ "<td>"+users.getAge()+"</td>"
					+ "<td>"+users.getEmail()+"</td>"
					+ "<td>"+users.getGender()+"</td>"
					+ "<td>"+users.getNationality()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
		@POST
		@Produces(MediaType.TEXT_HTML)
		public String insertUser(@FormParam("name") String name, 
				@FormParam("surname") String surname, @FormParam("age") int age, 
				@FormParam("email") String email, @FormParam("gender") String gender, @FormParam("nationality") String nationality) {
			
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Users");
		
		List<Users> lista = q.list();
		
		//INSERTAMOS UNA FILA 
		
		Users user = new Users();
		user.setId(lista.get(lista.size()-1).getId()+1);
		user.setName(name);
		user.setSurname(surname);
		user.setAge(age);
		user.setEmail(email);
		user.setGender(gender);
		user.setNationality(nationality);
		
		session.save(user);
		tx.commit();
		session.close();
					
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+user.getId()+"</li>"
					+ "<li>"+name+"</li>"
					+ "<li>"+surname+"</li>"
					+ "<li>"+age+"</li>"
					+ "<li>"+email+"</li>"
					+ "<li>"+gender+"</li>"
					+ "<li>"+nationality+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/users'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
		}
		
		//DELETE - BORRAR UN REGISTRO
		@DELETE
		@Path("/{parametre}")
		@Produces (MediaType.TEXT_HTML)
		public String deleteUserByParam(@PathParam("parametre") int n) {
			
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			
			Session session = sesion.openSession();
			
			Transaction tx = session.beginTransaction();
			
			Users user = new Users();
			
			user = (Users) session.load(Users.class, n);
			
			int id = user.getId();
			String name = user.getName();
			String surname = user.getSurname();
			int age = user.getAge();
			String email = user.getEmail();
			String gender = user.getGender();
			String nationality = user.getNationality();
			
			session.delete(user);
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
					+ "<h4><a href='http://localhost:8080/menusREST/api/users'>Volver</a></h4>"
					+ "</body>"
					+ "</html>";
		}
		
		//PUT - ACTUALIZAR REGISTRO
		@PUT
		@Path("/{parametre}")
		@Produces (MediaType.TEXT_HTML)
		public String updateUserByParam(@PathParam("parametre") int n, @QueryParam("name") String name, 
				@QueryParam("surname") String surname, @QueryParam("age") int age, @QueryParam("email") String email, 
				@QueryParam("gender") String gender, @QueryParam("nationality") String nationality) {
			
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			
			Session session = sesion.openSession();
			
			Transaction tx = session.beginTransaction();
			
			Users user = new Users();
			
			user = (Users) session.load(Users.class, n);
			
			user.setName(name);
			user.setSurname(surname);
			user.setAge(age);
			user.setEmail(email);
			user.setGender(gender);
			user.setNationality(nationality);
			
			
			session.update(user);
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
					+ "<h4><a href='http://localhost:8080/menusREST/api/users'>Volver</a></h4>"
					+ "</body>"
					+ "</html>";
		}
		
		//OBJ --> JSON
		@GET
		@Path("/json")
		@Produces(MediaType.APPLICATION_JSON)
		public String usersJson() {
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			
			Session session = sesion.openSession();
			
			Query q = session.createQuery("from Users");
			
			List<Users> lista = q.list();
			
			session.close();
			
			return gson.toJson(lista);		
		}


	

	
}
