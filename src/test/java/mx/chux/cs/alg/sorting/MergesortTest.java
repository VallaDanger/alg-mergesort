package mx.chux.cs.alg.sorting;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MergesortTest {

	Integer[] array;

	@Before
	public void initializeArray() {
		this.array = new Integer[] { 43, 3, 55, 23, 90, 5, 41, 69, 18, 62, 68, 22, 42, 35, 37, 58, 54, 79, 34, 9, 70,
				84, 50, 52, 31, 10, 2, 77, 11, 98, 67, 76, 24, 93, 15, 63, 26, 28, 100, 86, 81, 72, 97, 48, 13, 40, 78,
				19, 47, 99, 85, 7, 57, 51, 27, 73, 60, 61, 92, 4, 53, 96, 80, 88, 45, 30, 21, 29, 16, 66, 65, 64, 8, 91,
				94, 71, 56, 12, 17, 36, 49, 75, 32, 1, 6, 44, 33, 89, 20, 82, 46, 39, 74, 25, 95, 83, 38, 87, 14, 59 };
	}

	private void assertSortedArray(final Integer[] array, int size) {
		for (int i = 1; i < size; i++) {
			assertThat(array[i]).isGreaterThanOrEqualTo(array[i - 1]);
		}
	}

	@Test
	public void sortArrayTest() {
		final Mergesort<Integer> sorter = Mergesort.sorter(this.array);
		assertSortedArray(sorter.sort(), sorter.size());
	}

	@Test
	public void sortArrayInPlaceTest() {
		final Mergesort<Integer> sorter = Mergesort.sorter(this.array);
		assertSortedArray(sorter.sortInPlace(), sorter.size());
	}

	@Test
	public void bothWaysYieldTheSameResultTest() {
		final Mergesort<Integer> sorter = Mergesort.sorter(this.array);
		assertThat(sorter.sort()).isEqualTo(sorter.sortInPlace());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exceptionOnEmptyArrayTest() {
		Mergesort.sorter(new Integer[]{});
	}

}
