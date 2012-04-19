package forkJoin;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Yao Chen
 * @version April 17, 2012
 *
 */
public class RandomArrayGenerator {

	private static Random rand;


	/**
	 * Generates Given number of random points
	 * @param poitNum number of points
	 * @return array of points
	 */
	public static double[][] generate(int poitNum) {
	   double[][] randomPoints = new double[poitNum][2];
		System.out.println("Enter the seed");
		int seed = 0;
		boolean flag = true;
		while (flag) {
			try {
				Scanner reader = new Scanner(System.in);
				seed = reader.nextInt();
				flag = false;
			}catch (Exception e) {
				System.out.println("input error, please input again");
				flag = true;
			}
		}
		rand = new Random(seed);
		for (int i = 0; i < poitNum; i++) {
			randomPoints[i][0] = rand.nextDouble();
			randomPoints[i][1] = rand.nextDouble();	
		}	
		return randomPoints;
	}
	
	/**
	 * Sorts the array in ascendant order
	 * @param array array to be sorted
	 * @param left the left boundary
	 * @param right the right boundary
	 */
	public static void quickSortX(double[][]array, int left, int right) {
	
		if (left < right) {
			int p = partition(array, left, right);
			quickSortX(array, left, p - 1);
			quickSortX(array, p + 1, right);
			
		}
	}

	private static int partition (double[][] a, int left, int right) {
		double[] p = a[left];
		int l = left + 1;
		int r = right;
		while(l < r) {
			while (l < right && a[l][0] < p[0]) l++;
			while (r > left && a[r][0] >= p[0]) r--;
			if (l < r) {
				double[] temp = a[l];
				a[l] = a[r];
				a[r] = temp;
			}
		}
		a[left] = a[r];
		a[r] = p;
		return r;
	}

}
