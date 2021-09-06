package mx.chux.cs.alg.sorting;

import java.util.Arrays;
import java.util.function.Supplier;

public class Mergesort<T extends Comparable<T>> implements Supplier<T[]> {

	public static final <T extends Comparable<T>> Mergesort<T> sorter(T[] items) {

		final int size = items.length;

		if (size == 0) {
			throw new IllegalArgumentException("array must not be empty");
		}

		return new Mergesort<>(items, size);
		
	}

	private final T[] array;
	private final int length;

	private Mergesort(final T[] array, final int size) {
		this.array = array;
		this.length = size;
	}

	public T[] sort() {
		final T[] target = Arrays.copyOf(this.array, this.length);
		this.mergeSort(target);
		return target;
	}
	
	public T[] sortInPlace() {
		final T[] target = Arrays.copyOf(this.array, this.length);
		this.inPlaceMergeSort(target, 0, this.length-1);
		return target;
	}
	
	private void mergeSort(final T[] array) {

		final int size = array.length;

		if (size < 2) {
			return;
		}

		// calculate midpoint of the array
		final int middle = size/2;

		// make a copy from start to middle
		final T[] left = Arrays.copyOfRange(array, 0, middle);
		// recurse into left half
		mergeSort(left);

		// make a copy from middle to size of array
		final T[] right = Arrays.copyOfRange(array, middle, size);
		// recurse into right half
		mergeSort(right);

		// merge left and right halves into original array
		merge(left, right, array);
	}

	private T[] merge(T[] left, T[] right, T[] target) {

		final int sizeOfLeft = left.length;
		final int sizeOfRight = right.length;

		// initialize pointer for both halves
		int i = 0;
		int l = 0;
		int r = 0;

		// move along left and right halves
		while ((l < sizeOfLeft) && (r < sizeOfRight)) {

			// if current element at left half is smaller
			// current element at right half, then the one
			// in the left must me placed in the final array
			// once an element has been picked, advance the
			// index for its half; global index always moves
			if( isLessThanOrEqual(left[l], right[r]) ) {
				set(target, i++, get(left, l++));
			} else {
				set(target, i++, get(right, r++));
			}

		}

		// check for non visited element in both halves
		// all elements from both halves must get to final array
		copyFrom(left, sizeOfLeft, l, target, i);
		copyFrom(right, sizeOfRight, r, target, i);

		// return the sorted (sub-)array
		return target;
	}
	
	private void copyFrom(T[] source, int size, int s, T[] target, int t) {
		while (s < size) {
			set(target, t++, get(source, s++));
		}
	}
	
	private void inPlaceMergeSort(final T[] array, final int start, final int end) {

		if( start >= end ) { 
			return;
		}

		// find middle point of current range
		int middle = start + (end-start)/2;

		// recurse on left and right halves
		inPlaceMergeSort(array, start, middle);
		inPlaceMergeSort(array, middle + 1, end);

		// merge halves
		merge(array, start, middle, end);
			
	}

	/*
	 * this process is not faster than the version that
	 * creates copies of ranges; it is more space efficient
	 * note: it also probably is harder to parallelize
	 */
	private void merge(final T[] array, int start, int middle, int end) {
		
		int middlePlusOne = middle+1;

		// halves are already sorted if right-most element from left half
		// is less than or equal than left-most element from right half
		if( isLessThanOrEqual(get(array, middle), get(array, middlePlusOne)) ) {
			return;
		}

		// move along both halves one step at a time
		while( (start <= middle) && (middlePlusOne <= end) ) {

			// If current element from left half is less than or equal than 
			// current element from right half then advance left pointer
			if ( isLessThanOrEqual(get(array, start), get(array, middlePlusOne)) ) {
				start++;
			} else {
				
				// save value at middlePlusOne as it will be overwritten
				// when elements at its left are shifted right by 1
				final T value = get(array, middlePlusOne);
				
				int index = middlePlusOne;

				// shift-right all elements from start to middlePlusOne; the goal 
				// is to make 1 space for the element in position middlePlusOne
				while( index != start ) {
					set(array, index, get(array, index-1));
					index--;
				}
				
				// set value of element at start to the value at middlePlusOne 
				set(array, start, value);

				// shift-right pointers by 1 to the right
				start++;
				middle++;
				middlePlusOne++;
			}
			
		}
	}

	private boolean isLessThanOrEqual(T x, T y) {
		return x.compareTo(y) <= 0;
	}

	private T get(T[] array, int index) {
		return array[index];
	}

	private void set(T[] array, int index, T value) {
		array[index] = value;
	}

	@Override
	public T[] get() {
		return this.array;
	}
	
	public int size() {
		return this.length;
	}

}
