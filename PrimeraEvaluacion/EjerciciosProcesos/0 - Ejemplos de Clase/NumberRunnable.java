
public class NumberRunnable implements Runnable {
	int num;
	
	public NumberRunnable(int n) {
		num = n;
	}

	public void run() {
		for (int k=0; k<10; k++) {
			System.out.print(num);
		}
	}
}
