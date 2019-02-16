
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiThreadedServer {
	
	
	public static ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
	public static void generarAlumnos() {
		listaAlumnos.add(new Alumno (1,"Pepe","García", 15));
		listaAlumnos.add(new Alumno (2,"María","Gutierrez", 16));
		listaAlumnos.add(new Alumno (3,"Juan","Martínez", 12));
		listaAlumnos.add(new Alumno (4,"Ismael","Fernández", 17));
		listaAlumnos.add(new Alumno (5,"Alicia","García", 19));
	}


	public static void main(String[] args) {
		
		generarAlumnos();
		
		try {
			ServerSocket serverSocket = new ServerSocket(9090);
			boolean stop = false;
			while(!stop) {
				System.out.println("Esperando Clientes...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("Cliente Conectado.");
				
				// usamos la clase ClientThread
				ClientThread clientThread = new ClientThread(clientSocket);
				clientThread.start();
			}
			serverSocket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
