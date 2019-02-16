import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	
	private Socket socket = null;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			
			// 2.- OUT -> cliente
			// Necesitamos un stream para enviar datos al cliente
			// vamos a enviar un mensaje al cliente con PrintWriter
			PrintWriter out = null;
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Introduce el ID del alumno (1, 2 o 3): ");
			
			// 3.- IN <- cliente
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientInput = input.readLine();
			switch (clientInput) {
				case "1":
					System.out.println("ID: "+MultiThreadedServer.listaAlumnos.get(0).getId());
					System.out.println("Nombre: "+MultiThreadedServer.listaAlumnos.get(0).getNombre());
					break;
				case "2":
					System.out.println("ID: "+MultiThreadedServer.listaAlumnos.get(1).getId());
					System.out.println("Nombre: "+MultiThreadedServer.listaAlumnos.get(1).getNombre());
					break;
				case "3":
					System.out.println("ID: "+MultiThreadedServer.listaAlumnos.get(2).getId());
					System.out.println("Nombre: "+MultiThreadedServer.listaAlumnos.get(2).getNombre());
					break;
				default:
					System.out.println("No existe ese alumno");
					break;
			}
			
			// 4.- Cerrar stream socket
			input.close();
			out.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
