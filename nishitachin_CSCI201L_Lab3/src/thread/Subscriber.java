package thread;
import java.util.Random;

public class Subscriber extends Thread{
	
	private MessageQueue mQueue;
	
	public Subscriber(MessageQueue m) {
		this.mQueue = m;
	}
	
	public void run() {
		Random rand = new Random();
		int counter = 0;
		while(counter != 20) {
			String message = mQueue.getMessage();
			try {
				//sits still for a little
				Thread.sleep(rand.nextInt(1000));
			} catch(InterruptedException e) {
				e.getMessage();
			}
			if (message.isEmpty()) {
				System.out.println("empty");
			}
			else {
				counter++;
				System.out.println("subscriber read" + message);
				//counter++;
				//System.out.println(counter);
			}		
		}
	}
}
