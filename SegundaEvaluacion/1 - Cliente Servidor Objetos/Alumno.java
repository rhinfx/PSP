
public class Alumno {
	
	private int nia;
	private String nombre;
	private String apellidos;
	private int edad;
	
	
	public Alumno(int nia, String nombre, String apellidos, int edad) {
		super();
		this.nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}


	public int getNia() {
		return nia;
	}


	public void setNia(int nia) {
		this.nia = nia;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	@Override
	public String toString() {
		return "Alumno [nia=" + nia + ", nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + "]";
	}
	
	
	
	

}
