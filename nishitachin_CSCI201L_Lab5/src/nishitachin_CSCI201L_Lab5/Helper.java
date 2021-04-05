package nishitachin_CSCI201L_Lab5;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class Helper {

	public static void ThreadTest(int target, int[] array) {
		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		MultiThread mt1 = new MultiThread(0, array.length / 4, array, target, start);
		MultiThread mt2 = new MultiThread(array.length / 4, 2 * array.length / 4, array, target, start);
		MultiThread mt3 = new MultiThread(2 * array.length / 4, 3 * array.length / 4, array, target, start);
		MultiThread mt4 = new MultiThread(3 * array.length / 4, array.length, array, target, start);
		executorService.execute(mt1);
		executorService.execute(mt2);
		executorService.execute(mt3);
		executorService.execute(mt4);
		executorService.shutdown();
		while (!executorService.isTerminated()) {

			Thread.yield();
		}
		if ((mt2.getResult() == -1) && (mt1.getResult() == -1) && (mt3.getResult() == -1) && (mt4.getResult() == -1)) {
			long st = System.currentTimeMillis() - start;
			System.out.println("could not find target " + st);
		}
	}

	public static void ParallelTest(int target, int[] array) {
		long start = System.currentTimeMillis();
		ForkJoinPool fPool = new ForkJoinPool();
		ParallelSearch pt1 = new ParallelSearch(0, array.length / 4, target, array, start);
		ParallelSearch pt2 = new ParallelSearch(array.length / 4, 2 * array.length / 4, target, array, start);
		ParallelSearch pt3 = new ParallelSearch(2 * array.length / 4, 3 * array.length / 4, target, array, start);
		ParallelSearch pt4 = new ParallelSearch(3 * array.length / 4, array.length, target, array, start);
		fPool.execute(pt1);
		fPool.execute(pt2);
		fPool.execute(pt3);
		fPool.execute(pt4);
		if ((pt2.compute() == -1) && (pt1.compute() == -1) && (pt3.compute() == -1) && (pt4.compute() == -1)) {
			long st = System.currentTimeMillis() - start;
			System.out.println("could not find target " + st);
		}
	}

	public static void SingleTest(int target, int[] array) {
		long start = System.currentTimeMillis();
		SingleThread sThread = new SingleThread(array, target, start);
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(sThread);
		executorService.shutdown();
		while (!executorService.isTerminated()) {

			Thread.yield();
		}
		if(sThread.getResult() == -1) {
			System.out.println("could not find target" + start);
		}
	}

	public static void main(String[] args) {
		int[] array = new int[100000000];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		Collections.shuffle(Arrays.asList(array));
		int numInRange = ThreadLocalRandom.current().nextInt(0, 100000000 - 1);
		int numOutRange = ThreadLocalRandom.current().nextInt(100000000, 200000000);
		SingleTest(numInRange, array);
		SingleTest(numOutRange, array);

		ThreadTest(numInRange, array);
		ThreadTest(numOutRange, array);

		ParallelTest(numInRange, array);
		ParallelTest(numOutRange, array);

	}

}
