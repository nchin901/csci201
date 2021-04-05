package nishitachin_CSCI201L_Lab5;

public class MultiThread extends Thread{
	private int s;
	private int e;
	private int result = -1;
	private int array[];
	private int key;
	private long startTime;
	
	public MultiThread(int start, int end, int [] array, int target, long startTime) {
		this.s = start;
		this.e = end;
		this.array = array;
		this.key = target;
		this.startTime = startTime;
	}
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	public void run() {
//				(System.currentTimeMillis()-startTime);
		for(int i = s; i < e; i++) {
			if (key == array[i]) {
				setResult(key);
				long st =  System.currentTimeMillis() - startTime;
				System.out.println(key + " " + st);
			}
		}
	}
}
