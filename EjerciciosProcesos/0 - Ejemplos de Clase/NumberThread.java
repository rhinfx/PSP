
public class NumberThread extends Thread {
	int num;
	
	public NumberThread(int n) {
		num = n;
	}

	public void run() {
		for (int k=0; k<10; k++) {
			System.out.print(num);
		}
	}

}
