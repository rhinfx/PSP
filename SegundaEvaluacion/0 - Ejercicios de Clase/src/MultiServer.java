import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

public static void main(String[] args) throws IOException {
		
		// 1.- Creamos un socket esperando clientes
		ServerSocket serverSocket = new ServerSocket(9090);
		System.out.println("esperando clientes...");
		boolean stop = false;
		while(!stop) {
		
			// cliente intenta conectar con el servidor
			Socket socket = serverSocket.accept();
			
			// 2.- OUT -> cliente
			// Necesitamos un stream para enviar datos al cliente
			// vamos a enviar un mensaje al cliente con PrintWriter
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Hola cliente");
			
			// 3.- IN <- cliente
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientInput = input.readLine();
			System.out.println(clientInput);
			
			// 4.- Cerrar stream socket
			input.close();
			out.close();
			socket.close();
		}
		serverSocket.close();
		
	}

}
