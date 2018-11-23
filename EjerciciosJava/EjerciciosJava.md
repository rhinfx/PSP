# Método estático para Validar un Nif.

```Java
	public static boolean ValidarNIF(String Nif) {
		boolean resultado = false;
		String[] letrasDNI = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		
		if(Nif.length()!=9) {
			System.out.println("Longitud de nif ha de ser de 9");
		}
		
		else if(!Nif.substring(0,8).matches("\\d+")){
			System.out.println("Los primeros 8 carácteres han de ser digitos del 0-9");
		}
		
		else if(!Nif.substring(8).matches("[A-Za-z]")) {
			System.out.println("El último carácter ha de ser una letra de la [A-z]");
		}
		
		else {
			if(letrasDNI[Integer.parseInt(Nif.substring(0, 8))%23].equalsIgnoreCase(Nif.substring(8))) {
				resultado = true;
			}
		}
		
		return resultado;
	}
```
	
# Ejercicio de Herencia

### Clase Empleado
```Java
public class Empleado {
	private String nombre;

	public Empleado(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Empleado() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Empleado "+this.nombre;
	}
}
```

### Clase Operario
```Java
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
```

### Clase Directivo
```Java
public class Directivo extends Empleado{

	public Directivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Directivo(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString()+" -> Directivo";
	}
	
}
```

### Clase Oficial
```Java
public class Oficial extends Operario{

	public Oficial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Oficial(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return super.toString()+" -> Oficial";
	}
	
}
```

### Clase Tecnico
```Java
public class Tecnico extends Operario{

	public Tecnico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tecnico(String nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString()+" -> Técnico";
	}

}
```

### MainEmpleados
```Java
public class MainEmpleados {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Empleado E1 = new Empleado("Rafa");
		Directivo D1 = new Directivo("Mario");
		Operario OP1 = new Operario("Alfonso");
		Oficial OF1 = new Oficial("Luis");
		Tecnico T1 = new Tecnico("Pablo");
		System.out.println(E1);
		System.out.println(D1);
		System.out.println(OP1);
		System.out.println(OF1);
		System.out.println(T1);

	}

}
```
