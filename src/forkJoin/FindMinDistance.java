package forkJoin;
import java.util.concurrent.RecursiveTask;
/**
 * 
 * @author Yao Chen
 * @version April 17, 2012
 *
 */
public class FindMinDistance extends RecursiveTask<Double[][]> {

	private static final long serialVersionUID = 1L;

	int start;
	int end;
	double[][] array;

	FindMinDistance(double[][] arr, int lo, int hi) {
		array = arr;
		start  = lo;
		end  = hi;
	}

	@Override
	protected Double[][] compute() {


		if (end - start == 1) {
			
			//System.out.println("1: " + end);
			Double[][] c = {{ new Double(array[start][0]), new Double(array[start][1])},
					{new Double(array[end][0]), new Double(array[end][1])}};
			return c;
		}

		if (end - start == 2) {
			double min = 2.0;
			Double[][] c = new Double[2][2];
			for (int i = start; i <= end; i++) {
				for (int j = i + 1; j <= end; j++ ) {
					double y = array[j][1] - array[i][1];
					double x = array[j][0] - array[i][0];
					double dis = Math.sqrt ( x * x + y * y);
					if (dis >= min) continue;
					min = dis;	
					c[0][0] = new Double (array[j][0]);
					c[0][1] = new Double( array[j][1]);
					c[1][0] = new Double(array[i][0]);
					c[1][1] = new Double(array[i][1]);
				}
			}
			return c;
		}


		int midIndex = (end - start) / 2 + start;	
		FindMinDistance left = new FindMinDistance(array, start,  midIndex);
		FindMinDistance right = new FindMinDistance(array, midIndex, end);

		left.fork();
		Double[][] rMin = new Double[2][2]; 
		Double[][] rR = right.compute();
		Double[][] rL = left.join();
		double minR = calculateDistance(rR);
		double minL = calculateDistance(rL) ;

		double min = minL > minR ? minR : minL;

		if (minL > minR) {
			min = minR;
			rMin = rR;
		}else {
			min = minL;
			rMin = rL;
		}
		int l = midIndex;
		int r = midIndex;

		while (r < end && array[r][0] - array[midIndex][0] < min) {
			r++;	
		}


		while (l > start && array[midIndex][0] - array[l][0] < min) {
			l--;
			//System.out.println("l: " + l);
		}
		rMin = naiveFind(array, l, r, min, rMin);
		min = calculateDistance(rMin);
		return rMin;

	}

	/**
	 *  * Takes in array sorted according to x, only compares points of which difference of x smaller than min
	 * @param array sorted array
	 * @param start the start index
	 * @param end end index
	 * @param mini the shortest distance
	 * @param c pair of points with shortest distance
	 * @return pair of points with shortest distance
	 */

	public static Double[][] naiveFind(double[][] array, int start, int end, double mini, Double[][] c) {
		double min = mini;	
		for (int i = start; i <= end ; i++) {
			for (int j = i + 1; j < end; j++ ) {
				if (array[j][0] - array[i][0] > min) break;
				double y = Math.abs(array[j][1] - array[i][1]);
				if ( y > min) continue;
				double x = array[j][0] - array[i][0];
				double dis = Math.sqrt ( x * x + y * y);
				if (dis > min) continue;
				min = dis;	
				c[0][0] = new Double (array[j][0]);
				c[0][1] = new Double( array[j][1]);
				c[1][0] = new Double(array[i][0]);
				c[1][1] = new Double(array[i][1]);
			}
		}
		return c;

	}
	
	/**
	 * Calculates the distance of given two points
	 * @param p coordination of two points
	 * @return distance
	 */
	public static double calculateDistance(Double[][] p) {
		double x = p[0][0].doubleValue() - p[1][0].doubleValue();
		double y = Math.abs(p[0][1].doubleValue() - p[1][1].doubleValue());
		return  Math.sqrt ( x * x + y * y);
		
	}
	
	/**
	 * Invokes findMinDistance
	 * @param array array of points
	 * @return the pair of points with shortest distance
	 */
	static Double[][] findShortest(double[][] array) {
		return Globals.fjPool.invoke(new FindMinDistance(array,0,array.length - 1));
	}

}
