package thread;
import java.util.Random;

public class Messenger extends Thread{
	
	private MessageQueue mQueue;
	public Messenger(MessageQueue m) {
		this.mQueue = m;
	}
	
	//run method overwrite
	public void run() {
		//loop iterates 20 times
		Random random = new Random();
		for(int i = 0; i < 20; i++) {
			//add message w unique identifier
			//System.out.println("test");
			String messageString = Util.getCurrentTime() + " swag " + i;
			mQueue.addMessage(messageString);
			System.out.println("messenger added: " + messageString);
			try {
				Thread.sleep(random.nextInt(1000));
			}catch(InterruptedException e) {
				System.out.println("interrupted");
			}
			//System.out.println(mQueue);
		}
	}
}
