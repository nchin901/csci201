package nishitachin_CSCI201L_Lab5;

public class SingleThread extends Thread{
	private int result = -1;
	private int array[];
	private int key;
	private long startTime;
	
	public SingleThread(int [] a, int key, long startTime) {
		array = a;
		this.key = key;
		this.startTime = startTime;
	}
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
	public void run() {
		for(int i = 0; i < array.length; i++) {
			if (key == array[i]) {
				setResult(key);
				long st =  System.currentTimeMillis() - startTime;
				System.out.println(key + " " + st);
			}
		}
	}
	
}
