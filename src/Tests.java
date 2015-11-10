

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

public class Tests {
	// make our global variable to hold the arrays to test
	private int arrays[][];
	
	// function to generate arrays before tests
	@Before
	public void before(){
		// init our random number generator
		Random rand = new Random();
		// make our array to hold arrays to test
		arrays = new int[100][];
		
		for(int x = 0; x < 100; x++){
			// get a random length between 0 and 999 for this array
			int length = rand.nextInt(1000);
			// create array with generated length
			arrays[x] = new int[length];
			
			// cycle through generated array
			for(int y = 0; y < length; y++){
				// get random value for this index and assign it in
				int val = rand.nextInt();
				arrays[x][y] = val;
			}
		}
	}

	/*
	 * Test that the output array of the sort function always has the same 
	 * size/length as the input array did.
	 */
	@Test
	public void testSize() {
		// cycle through each array in the global array
		for(int x = 0; x < 100; x++){
			
			// store the length before the sort
			int beforeLength = arrays[x].length;
			
			// sort the array
			Arrays.sort(arrays[x]);
			
			// store the length after the sort
			int afterLength = arrays[x].length;
			
			// assert that the length before and after the sort are equal
			assertEquals(beforeLength, afterLength);
		}
	}
	
	/*
	 * Test that the values in the output array are always increasing or
	 * staying the same.
	 */
	@Test
	public void testIncreasing() {
		// cycle through each array in the global array
		for(int x = 0; x < 100; x++){
			
			// sort the array
			Arrays.sort(arrays[x]);
			
			// cycle through every element in the newly sorted array
			// starting with index 1 to prevent an out of bound index
			for(int y = 1; y < arrays[x].length; y++) {
				
				// if the current element is less than the element before it, then fail
				if(arrays[x][y] < arrays[x][y-1]) {
					fail("Array not in increasing order.");
				}
			}
		}
	}
	
	/*
	 * Test that every element in the input array is in the output array.
	 */
	@Test
	public void testExists() {
		// cycle through each array in the global array
		for(int x = 0; x < 100; x++){
			// create a temp array to hold the values of the original array
			int[] tempArray = new int[arrays[x].length];
			
			// cycle through original array
			for(int y = 0; y < arrays[x].length; y++) {
				// copy value at each index to the temp array
				tempArray[y] = arrays[x][y];
			}	
			
			// sort the array
			Arrays.sort(arrays[x]);
			
			// cycle through every element in the temp array
			for(int y = 0; y < tempArray.length; y++) {
				// if the element in the temp array is not found the newly sorted array, then fail
				if(Arrays.binarySearch(arrays[x], tempArray[y]) < 0) {
					fail("Item in input array not found in output array.");
				}
			}
		}
	}
}
