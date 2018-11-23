import java.io.IOException;

public class Uno {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		try {
			ProcessBuilder pb = new ProcessBuilder("cmd.exe","/C","start");
			pb.start();
		} catch (Exception e) {
			System.out.println("Exception "+ e);
		}

	}

}
