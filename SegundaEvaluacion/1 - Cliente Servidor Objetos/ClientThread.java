import java.io.BufferedReader;
import java.io.InputStreamReader;
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
			out.println("Introduce un NIA (1,2,3,4 o 5)...");
			
			// 3.- IN <- cliente
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientInput = input.readLine();
			
			// 4.- OUT -> cliente
			switch (clientInput) {
			case "1": out.println(MultiThreadedServer.listaAlumnos.get(0).toString());
				break;
			case "2": out.println(MultiThreadedServer.listaAlumnos.get(1).toString());
				break;
			case "3": out.println(MultiThreadedServer.listaAlumnos.get(2).toString());
				break;
			case "4": out.println(MultiThreadedServer.listaAlumnos.get(3).toString());
				break;
			case "5": out.println(MultiThreadedServer.listaAlumnos.get(4).toString());
				break;
			default:  out.println("Alumno no encontrado.");
				break;
			}
			
			System.out.println("Información de alumno enviada al cliente.");
			
			// 5.- Cerrar stream socket
			input.close();
			out.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
