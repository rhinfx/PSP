
public class Numbers {

	public static void main(String[] args) {
		NumberThread n1, n2, n3, n4, n5, n6;
		
		n1 = new NumberThread(1); n1.start();
		n2 = new NumberThread(2); n2.start();
		n3 = new NumberThread(3); n3.start();
		n4 = new NumberThread(4); n4.start();
		n5 = new NumberThread(5); n5.start();
		n6 = new NumberThread(6); n6.start();
		
		
		Thread x1, x2, x3, x4, x5, x6;
		
		x1 = new Thread(new NumberRunnable(71)); x1.start();
		x2 = new Thread(new NumberRunnable(72)); x2.start();
		x3 = new Thread(new NumberRunnable(73)); x3.start();
		x4 = new Thread(new NumberRunnable(74)); x4.start();
		x5 = new Thread(new NumberRunnable(75)); x5.start();
		x6 = new Thread(new NumberRunnable(76)); x6.start();

	}

}
