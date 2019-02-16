
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9090);
			boolean stop = false;
			while(!stop) {
				System.out.println("esperando clientes...");
				Socket clientSocket = serverSocket.accept();
				System.out.println("El cliente se ha conectado");
				
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
