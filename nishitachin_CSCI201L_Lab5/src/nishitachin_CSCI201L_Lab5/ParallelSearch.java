package nishitachin_CSCI201L_Lab5;

import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int st;
	private int end;
	private int target;
	private long startString;
	private int[] array;
	public ParallelSearch(int s, int e, int target, int [] array, long starttime) {
		this.st = s;
		this.end = e;
		this.target = target;
		this.startString = starttime;
		this.array = array;
	}
	
	@Override
	protected Integer compute() {
		for(int i = st; i < end; i++) {
			if (target == array[i]) {
				long st =  System.currentTimeMillis() - startString;
				System.out.println(target + " " + st);
				return i;
			}
		}
		return -1;
	}
}
