package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import javax.lang.model.type.ExecutableType;

public class MessageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		for(int i = 0; i < 2; i++) {
			MessageQueue mQueue = new MessageQueue();
			Subscriber subscriber = new Subscriber(mQueue);
			Messenger messenger = new Messenger(mQueue);
			executorService.execute(messenger);
			executorService.execute(subscriber);
			//stopping creation of new threads
			while(!executorService.isTerminated()) {
				
				Thread.yield();
			}
		}
		executorService.shutdown();
	}
	
}
