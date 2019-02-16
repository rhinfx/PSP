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
			out.println("Hola cliente");
			
			// 3.- IN <- cliente
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientInput = input.readLine();
			System.out.println(clientInput);
			
			// 4.- Cerrar stream socket
			input.close();
			out.close();
			socket.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
