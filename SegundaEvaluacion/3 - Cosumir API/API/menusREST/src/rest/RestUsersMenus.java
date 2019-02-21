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
@Path("/users-menus")

public class RestUsersMenus {
	
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
			+ "<th>User ID</th>"
			+ "<th>User</th>"
			+ "<th>Menu</th>"
			+ "<th>Category</th>"
			+ "<th>Rating</th>"
			+ "</tr>";
	
	private String html2 = "</table></body></html>";
	
	//MÉTODO POR DEFECTO => SELECT * FROM users_menus
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getUsersMenus() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		//el underscore de la tabla (users_menus) se omite para la query
		Query q = session.createQuery("from UsersMenus");
		
		String str = "";
		
		List<UsersMenus> lista = q.list();
		
		for (UsersMenus usersmenus : lista) {
			str += "<tr>"
					+ "<td><a href='http://localhost:8080/menusREST/api/users/"+String.valueOf(usersmenus.getUsers().getId())+"'>"+usersmenus.getUsers().getId()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/users/"+String.valueOf(usersmenus.getUsers().getId())+"'>"+usersmenus.getUsers().getName()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/menus/"+String.valueOf(usersmenus.getId().getMenuId())+"'>"+usersmenus.getId().getMenuId()+"</a></td>"
					+ "<td>"+usersmenus.getCategory()+"</td>"
					+ "<td>"+usersmenus.getRating()+"</td>"
					+ "</tr>";
		}
		
		session.close();
		
		String nuevaInsercion = "</table>"
									+ "<form method='POST' action='http://localhost:8080/menusREST/api/users-menus/'>"
									+ "<input type='text' name='userId' placeholder='User ID' size=10'>"
									+ "<input type='text' name='menuId' placeholder='Menu ID' size='10'>"
									+ "<input type='text' name='category' placeholder='Category' size='10'>"
									+ "<input type='text' name='rating' placeholder='Rating' size='10'>"
									+ "<input type='submit' value='Introducir'>"
								+ "</form>"
								+ "</body>"
								+ "</html>";		

		
		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM USERSMENUS WHERE USER_ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getUsersMenusByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		//users es lo que hibernate ha mapeado de user_id
		Query q = session.createQuery("from UsersMenus as um where um.users = "+n);
			
		String str = "";
			
		List<UsersMenus> lista = q.list();
		
		for (UsersMenus usersmenus : lista) {
			str += "<tr>"
					+ "<td>"+usersmenus.getUsers().getId()+"</td>"
					+ "<td>"+usersmenus.getUsers().getName()+"</td>"
					+ "<td>"+usersmenus.getId().getMenuId()+"</td>"
					+ "<td>"+usersmenus.getCategory()+"</td>"
					+ "<td>"+usersmenus.getRating()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String insertUsersMenus(@FormParam("userId") int userId, @FormParam("menuId") int menuId, 
			@FormParam("category") String category, @FormParam("rating") short rating) {
		
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Users");
		
		List<Users> listaUsuarios = q.list();
		
		q = session.createQuery("from Menus");
		
		List<Menus> listaMenus = q.list();
		
		//INSERTAMOS UNA FILA 
		
		UsersMenus userMenu = new UsersMenus();
		userMenu.setId(new UsersMenusId(userId, menuId));
		userMenu.setMenus(listaMenus.get(menuId-1));
		userMenu.setUsers(listaUsuarios.get(userId-1));
		userMenu.setCategory(category);
		userMenu.setRating(rating);
		
		session.save(userMenu);
		tx.commit();
		session.close();
		
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+listaUsuarios.get(userId-1).getName()+"</li>"
					+ "<li>"+listaMenus.get(menuId-1).getId()+"</li>"
					+ "<li>"+category+"</li>"
					+ "<li>"+rating+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/users-menus'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//DELETE - BORRAR UN REGISTRO
		@DELETE
		@Path("/{x}/{y}")
		@Produces (MediaType.TEXT_HTML)
		public String deleteUserMenuByParam(@PathParam("x") int x, @PathParam("y") int y) {
				
			SessionFactory sesion = HibernateUtil.getSessionFactory();
				
			Session session = sesion.openSession();
				
			Transaction tx = session.beginTransaction();
				
			UsersMenus userMenu = new UsersMenus();
				
			userMenu = (UsersMenus) session.load(UsersMenus.class, new UsersMenusId(x,y));
				
			String user = userMenu.getUsers().getName();
			int menu = userMenu.getMenus().getId();
				
			session.delete(userMenu);
			tx.commit();
			session.close();
				
				
			return 	"<html>"
					+ "<body>"
					+ "<h2>Registro Eliminado:</h2>"
					+ "<ul>"
						+ "<li>"+user+"</li>"
						+ "<li>"+menu+"</li>"
					+ "</ul>"
					+ "<h4><a href='http://localhost:8080/menusREST/api/users-menus'>Volver</a></h4>"
					+ "</body>"
					+ "</html>";
		}
		
	//PUT - ACTUALIZAR REGISTRO
	@PUT
	@Path("/{x}/{y}")
	@Produces (MediaType.TEXT_HTML)
	public String updateUserMenuByParam(@PathParam("x") int x, @PathParam("y") int y, @QueryParam("user") int user, 
			@QueryParam("menu") int menu, @QueryParam("category") String category, @QueryParam("rating") short rating) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		UsersMenus userMenu = new UsersMenus();
		
		userMenu = (UsersMenus) session.load(UsersMenus.class, new UsersMenusId(x,y));
		
		Query q = session.createQuery("from Users");
		
		List<Users> listaUsuarios = q.list();
		
		q = session.createQuery("from Menus");
		
		List<Menus> listaMenus = q.list();
		
		userMenu.setUsers(listaUsuarios.get(user-1));
		userMenu.setMenus(listaMenus.get(menu-1));
		userMenu.setCategory(category);
		userMenu.setRating(rating);
	
		
		session.update(userMenu);
		tx.commit();
		session.close();
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Actualizado:</h2>"
				+ "<ul>"
					+ "<li>"+listaUsuarios.get(user-1)+"</li>"
					+ "<li>"+listaMenus.get(menu-1)+"</li>"
					+ "<li>"+category+"</li>"
					+ "<li>"+rating+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/users-menus'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//OBJ --> JSON
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String usersMenusJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from UsersMenus");
		
		List<UsersMenus> lista = q.list();
		
		session.close();
		
		return gson.toJson(lista);		
	}

	

	
}
