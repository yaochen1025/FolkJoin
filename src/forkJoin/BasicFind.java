package forkJoin;

/**
 * 
 * @author Yao Chen
 * @version April 17, 2012
 *
 */
public class BasicFind {


	/**
	 * Compares every pair of points, finds the shortest distance (O(n2))
	 * @param array Points
	 * @return shortest distance
	 */
	public static double nnaiveFind(double[][] array){
		double min = 2.0;
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++ ) {
				double y = array[j][1] - array[i][1];
				double x = array[j][0] - array[i][0];
				double dis = Math.sqrt ( x * x + y * y);
				if (dis >= min) continue;
				min = dis;	
			}

		}
		return min;
	}

	/**
	 *  * Takes in array sorted according to x, only compares points of which difference of x smaller than min
	 * @param array sorted array
	 * @param start the start index
	 * @param end end index
	 * @param mini the shortest distance
	 * @return the pair of points with shortest distance
	 */
	public static double naiveRevisedFind(double[][] array, int start, int end, double mini) {
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
			}
		}
		return min;

	}

	/**
	 * Recursively compares distance of points
	 * @param array points
	 * @param start the start index
	 * @param end end index
	 * @return the pair of points with shortest distance
	 */
	public static double improvedFind(double[][] array, int start, int end) {

		if (end - start == 1) {
			double x = array[start][0] - array[end][0];
			double y = array[start][1] - array[end][1];
			//System.out.println("1: " + end);
			return Math.sqrt ( x * x + y * y);
		}

		if (end - start == 2) {
			double min = 1.0;
			for (int i = start; i <= end; i++) {
				for (int j = i + 1; j <= end; j++ ) {
					double y = array[j][1] - array[i][1];
					double x = array[j][0] - array[i][0];
					double dis = Math.sqrt ( x * x + y * y);
					if (dis >= min) continue;
					min = dis;	
				}
			}
			return min;
		}


		int midIndex = (end - start) / 2 + start;	
		double minL = improvedFind(array, start,  midIndex);
		double minR = improvedFind(array, midIndex, end);
		double min = minL > minR ? minR : minL;

		int l = midIndex;
		int r = midIndex;

		while (r < end && array[r][0] - array[midIndex][0] < min) {
			r++;	
		}


		while (l > start && array[midIndex][0] - array[l][0] < min) {
			l--;
		}
		min = naiveRevisedFind(array, l, r, min);
		return min;
	}

}









