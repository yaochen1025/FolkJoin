package forkJoin;

/**
 * 
 * @author Yao Chen
 * @version April 17, 2012
 *
 */
public class FindMinDistanceTester {

	/**
	 * Generate one million random points, find the pair of points with shortest distance
	 * @param args
	 */
	public static void main(String[] args) {

		long startTime = System.nanoTime();
		double[][] test = RandomArrayGenerator.generate(1000000);
		long generateTimeL = System.nanoTime() - startTime;
		double generateTime = generateTimeL / 1000000000.0;

		startTime = System.nanoTime();
		RandomArrayGenerator.quickSortX(test, 0, test.length - 1);
		long sortTimeL = System.nanoTime() - startTime;
		double sortTime = sortTimeL / 1000000000.0;
		
		System.out.println("--------------------");
		startTime = System.nanoTime();
		Double[][] sum = FindMinDistance.findShortest(test);
		long findTimeL = System.nanoTime() - startTime;
		double findTime = findTimeL / 1000000000.0;
		
		System.out.println("gene: "+ generateTime +"s; sort: " + sortTime + "s findTime: " + findTime + "s");
		System.out.println("distance");
		System.out.println(FindMinDistance.calculateDistance(sum));
		System.out.println("points are: " + sum[0][0] + "," + sum[0][1] + ";" + sum[1][0] + "," + sum[1][1]);
	}
}
