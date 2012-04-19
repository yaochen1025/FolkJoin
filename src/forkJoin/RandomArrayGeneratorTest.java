package forkJoin;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * 
 * @author Yao Chen
 * @version April 17, 2012
 *
 */
public class RandomArrayGeneratorTest {

	RandomArrayGenerator rag;

	@Test
	public void testSort() {
		rag = new RandomArrayGenerator();
		double[][] array = {{1.0, 4.0},{ 3.0, 3.0}, {2.0, 6.0}};
		double[][] result = {{1.0, 4.0},{2.0, 6.0}, { 3.0, 3.0}};
		RandomArrayGenerator.quickSortX(array, 0, 2);
		assertArrayEquals(result, array);
	}
}
