package forkJoin;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Yao Chen
 * @version April 17, 2012
 *
 */
public class BasicFindTest {

	RandomArrayGenerator rag;
	
	@Test
	public void testNaive0() {
		rag = new RandomArrayGenerator();
		double[][] test = {{0.1, 0.2},{0.4, 0.4},{0.6, 0.5},
				{0.5, 0.2},{0.2, 0.3},{0.1, 0.2},{0.2, 0.1},{0.3, 0.5},{0.6, 0.9}};
		long startTime = System.nanoTime();
		RandomArrayGenerator.quickSortX(test, 0, test.length - 1);
		long sortTimeL = System.nanoTime() - startTime;
		double sortTime  = sortTimeL / 1000000000.0;
		startTime = System.nanoTime();		
		double x = BasicFind.improvedFind(test, 0, test.length - 1 );
		long findTimeL = System.nanoTime() - startTime;
		double findTime = findTimeL / 1000000000.0;
		System.out.println("sort: " + sortTime + "findTime: " + findTime);
		System.out.println("result");
		System.out.println("length: " + x);
		System.out.println("-------------------");
	}
	
	
	@Test
	public void testNaive1() {
		rag = new RandomArrayGenerator();
		double[][] test = {{0, 0.5},{0.1, 0.7},  {0.2, 0.4}, {0.3, 0.9}, {0.4, 0.4},  {0.5, 0.7}, {0.6, 0.5}};
		RandomArrayGenerator.quickSortX(test, 0, test.length - 1);
		double x = BasicFind.improvedFind(test, 0, test.length - 1 );
		System.out.println("result");
		System.out.println("length: " + x);
		System.out.println("-------------------");
		
		
	}

	@Test
	public void testNaive2() {
		rag = new RandomArrayGenerator();
		
		long startTime = System.nanoTime();
		double[][] test = RandomArrayGenerator.generate(1000);
		long generateTimeL = System.nanoTime() - startTime;
		double generateTime  = generateTimeL / 1000000000.0;
		

		
		
		startTime = System.nanoTime();
		RandomArrayGenerator.quickSortX(test, 0, test.length - 1);
		long sortTimeL = System.nanoTime() - startTime;
		double sortTime  = sortTimeL / 1000000000.0;
	

		
		startTime = System.nanoTime();
		double x = BasicFind.improvedFind(test,  0, test.length - 1);
		long findTimeL = System.nanoTime() - startTime;
		double findTime = findTimeL / 1000000000.0;
		
		startTime = System.nanoTime();
		double y = BasicFind.naiveRevisedFind(test,  0, test.length - 1, 2.0);
		long findTimeL1 = System.nanoTime() - startTime;
		double findTime1 = findTimeL1 / 1000000000.0;
		
		startTime = System.nanoTime();
		double z = BasicFind.nnaiveFind(test);
		long findTimeL2 = System.nanoTime() - startTime;
		double findTime2 = findTimeL2 / 1000000000.0;
		
		assertTrue(x == y);
		assertTrue(y == z);
		System.out.println("1000 points");
		System.out.println("gene: "+ generateTime +"; sort: " + sortTime + "s findTimeRecursive: " + findTime + "s findTimeRevised: " + findTime1 + "s findTimeNaive: " + findTime2 +"s");
		System.out.println("result");
		System.out.println("lengthNR: " + x);
		System.out.println("lengthIm: " + y);
		System.out.println("------------------");
		
	}
	
	
	@Test
	public void testNaive3() {
		rag = new RandomArrayGenerator();
		
		long startTime = System.nanoTime();
		double[][] test = RandomArrayGenerator.generate(1000000);
		long generateTimeL = System.nanoTime() - startTime;
		double generateTime  = generateTimeL / 1000000000.0;
		

		
		
		startTime = System.nanoTime();
		RandomArrayGenerator.quickSortX(test, 0, test.length - 1);
		long sortTimeL = System.nanoTime() - startTime;
		double sortTime  = sortTimeL / 1000000000.0;
	

		
		startTime = System.nanoTime();
		double x = BasicFind.improvedFind(test,  0, test.length - 1);
		long findTimeL = System.nanoTime() - startTime;
		double findTime = findTimeL / 1000000000.0;
		
		startTime = System.nanoTime();
		double y = BasicFind.naiveRevisedFind(test,  0, test.length - 1, 2.0);
		long findTimeL1 = System.nanoTime() - startTime;
		double findTime1 = findTimeL1 / 1000000000.0;
		
		
		
		assertTrue(x == y);
		System.out.println("1000000 points");
		System.out.println("gene: "+ generateTime +"; sort: " + sortTime + "s findTimeRecursive: " + findTime + "s findTimeRevised: " + findTime1 + "s");
		System.out.println("result");
		System.out.println("lengthNR: " + x);
		System.out.println("lengthIm: " + y);
		System.out.println("------------------");
		
	}




}
