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
@Path("/dishes-ingredients")

public class RestDishesIngredients {
	
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
			+ "<th>Ingredient</th>"
			+ "<th>Quantity</th>"
			+ "</tr>";
	
	private String html2 = "</table></body></html>";
	
	//MÉTODO POR DEFECTO => SELECT * FROM dishes_ingredients
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getDishesIngredients() {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		//el underscore de la tabla (dishes_ingredients) se omite para la query
		Query q = session.createQuery("from DishesIngredients");
		
		String str = "";
		
		List<DishesIngredients> lista = q.list();
		
		for (DishesIngredients dishesingredients : lista) {
			str += "<tr>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(dishesingredients.getDishes().getId())+"'>"+dishesingredients.getDishes().getId()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/dishes/"+String.valueOf(dishesingredients.getDishes().getId())+"'>"+dishesingredients.getDishes().getName()+"</a></td>"
					+ "<td><a href='http://localhost:8080/menusREST/api/ingredients/"+String.valueOf(dishesingredients.getIngredients().getId())+"'>"+dishesingredients.getIngredients().getName()+"("+dishesingredients.getIngredients().getId()+")</a></td>"
					+ "<td>"+dishesingredients.getQuantity()+"</td>"
					+ "</tr>";
		}
		
		session.close();
		
		String nuevaInsercion = "</table>"
								+ "<form method='POST' action='http://localhost:8080/menusREST/api/dishes-ingredients/'>"
									+ "<input type='text' name='dishId' placeholder='Dish ID' size=10'>"
									+ "<input type='text' name='ingredientId' placeholder='Ingredient ID' size='10'>"
									+ "<input type='text' name='quantity' placeholder='Quantity' size='10'>"
									+ "<input type='submit' value='Introducir'>"
								+ "</form>"
								+ "</body>"
								+ "</html>";		

		
		return html1+str+nuevaInsercion;
	}
	
	//MÉTODO CON PARÁMETROS => SELECT * FROM DISHES_INGREDIENTS WHERE DISH_ID = PARÁMETRO
	@GET
	@Path("/{parametre}")
	@Produces(MediaType.TEXT_HTML)
	public String getDishesAuthorsByParam(@PathParam("parametre") String n) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		//dishes es lo que hibernate ha mapeado de dish_id
		Query q = session.createQuery("from DishesIngredients as di where di.dishes = "+n);
			
		String str = "";
			
		
		List<DishesIngredients> lista = q.list();
		
		for (DishesIngredients dishesingredients : lista) {
			str += "<tr>"
					+ "<td>"+dishesingredients.getDishes().getId()+"</td>"
					+ "<td>"+dishesingredients.getDishes().getName()+"</td>"
					+ "<td>"+dishesingredients.getIngredients().getName()+"</td>"
					+ "<td>"+dishesingredients.getQuantity()+"</td>"
					+ "</tr>";
		}
			
		session.close();
			
			
		return html1+str+html2;
	}
	
	//POST PARA INTRODUCIR UN REGISTRO NUEVO
	@POST
	@Produces(MediaType.TEXT_HTML)
	public String insertDishesIngredients(@FormParam("dishId") int dishId, @FormParam("ingredientId") int ingredientId, 
			@FormParam("quantity") short quantity) {
		
		//obtenemos sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		//crear session
		Session session = sesion.openSession();
		//crear una transacion
		Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> listaPlatos = q.list();
		
		q = session.createQuery("from Ingredients");
		
		List<Ingredients> listaIngredientes = q.list();
		
		//INSERTAMOS UNA FILA 
		
		DishesIngredients dishIngredient = new DishesIngredients();
		dishIngredient.setId(new DishesIngredientsId(dishId,ingredientId));
		dishIngredient.setDishes(listaPlatos.get(dishId-1));
		dishIngredient.setIngredients(listaIngredientes.get(ingredientId-1));
		dishIngredient.setQuantity(quantity);
		
		session.save(dishIngredient);
		tx.commit();
		session.close();
		
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Introducido:</h2>"
				+ "<ul>"
					+ "<li>"+listaPlatos.get(dishId-1).getName()+"</li>"
					+ "<li>"+listaIngredientes.get(ingredientId-1).getName()+"</li>"
					+ "<li>"+quantity+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes-ingredients'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//DELETE - BORRAR UN REGISTRO
	@DELETE
	@Path("/{x}/{y}")
	@Produces (MediaType.TEXT_HTML)
	public String deleteDishIngredientByParam(@PathParam("x") int x, @PathParam("y") int y) {
			
		SessionFactory sesion = HibernateUtil.getSessionFactory();
			
		Session session = sesion.openSession();
			
		Transaction tx = session.beginTransaction();
			
		DishesIngredients dishIngredient = new DishesIngredients();
			
		dishIngredient = (DishesIngredients) session.load(DishesIngredients.class, new DishesIngredientsId(x,y));
			
		String dish = dishIngredient.getDishes().getName();
		String ingredient = dishIngredient.getIngredients().getName();
			
		session.delete(dishIngredient);
		tx.commit();
		session.close();
			
			
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Eliminado:</h2>"
				+ "<ul>"
					+ "<li>"+dish+"</li>"
					+ "<li>"+ingredient+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes-ingredients'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//PUT - ACTUALIZAR REGISTRO
	@PUT
	@Path("/{x}/{y}")
	@Produces (MediaType.TEXT_HTML)
	public String updateDishIngredientByParam(@PathParam("x") int x, @PathParam("y") int y, @QueryParam("dish") int dish, 
			@QueryParam("ingredient") int ingredient, @QueryParam("quantity") short quantity) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		DishesIngredients dishIngredient = new DishesIngredients();
		
		dishIngredient = (DishesIngredients) session.load(DishesIngredients.class, new DishesIngredientsId(x,y));
		
		Query q = session.createQuery("from Dishes");
		
		List<Dishes> listaPlatos = q.list();
		
		q = session.createQuery("from Ingredients");
		
		List<Ingredients> listaIngredientes = q.list();
		
		dishIngredient.setDishes(listaPlatos.get(dish-1));
		dishIngredient.setIngredients(listaIngredientes.get(ingredient-1));
		dishIngredient.setQuantity(quantity);
	
		
		session.update(dishIngredient);
		tx.commit();
		session.close();
		
		return 	"<html>"
				+ "<body>"
				+ "<h2>Registro Actualizado:</h2>"
				+ "<ul>"
					+ "<li>"+listaPlatos.get(dish-1).getName()+"</li>"
					+ "<li>"+listaIngredientes.get(ingredient-1).getName()+"</li>"
					+ "<li>"+quantity+"</li>"
				+ "</ul>"
				+ "<h4><a href='http://localhost:8080/menusREST/api/dishes-ingredients'>Volver</a></h4>"
				+ "</body>"
				+ "</html>";
	}
	
	//OBJ --> JSON
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String dishesIngredientsJson() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();
		
		Query q = session.createQuery("from DishesIngredients");
		
		List<DishesIngredients> lista = q.list();
		
		session.close();
		
		return gson.toJson(lista);		
	}

	

	
}
