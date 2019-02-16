
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiThreadedServer {
	
	public static ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
	
	//telnet localhost 9090

	public static void main(String[] args) {
		
		listaAlumnos.add(new Alumno(1,"Pepe"));
		listaAlumnos.add(new Alumno(2,"Juan"));
		listaAlumnos.add(new Alumno(3,"María"));
		
		try {
			ServerSocket serverSocket = new ServerSocket(9090);
			boolean stop = false;
			while(!stop) {
				System.out.println("Información de alumnos");
				Socket clientSocket = serverSocket.accept();
				System.out.println("...");
				
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
