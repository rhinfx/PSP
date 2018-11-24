
public class Operario extends Empleado {

	public Operario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operario(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString()+" -> Operario";
	}
}