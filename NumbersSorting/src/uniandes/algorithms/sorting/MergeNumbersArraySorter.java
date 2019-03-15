package uniandes.algorithms.sorting;

import java.util.List;
import java.util.ArrayList;

/**
 * Implements the merge sort algorithm for number arrays
 */
public class MergeNumbersArraySorter implements NumbersArraySorter {

	@Override
	public void sort(double[] numbers) 
	{
		mergeSort(numbers, 0, numbers.length-1);
	}
	
	private void mergeSort(double[] numbers, int first, int last)
	{
		if(first == last) return;
		int m = (first+last)/2;
		mergeSort(numbers, first, m);
		mergeSort(numbers, m+1, last);
		mergeSortedLists(numbers, first, m, last);
	}

	private void mergeSortedLists(double[] numbers, int first, int last1, int last2)
	{
		List<Double> mergedList = new ArrayList<>( );
		int i = first;
		int j = last1+1;
		
		while(i <= last1 && j <= last2)
		{
			double n1 = numbers[i];
			double n2 = numbers[j];
			
			if(n1 <= n2)
			{
				mergedList.add(n1);
				i++;
			}
			else 
			{
				mergedList.add(n2);
				j++;
			}
		}
		
		for(; i <= last1; i++) mergedList.add(numbers[i]);
		for(; j <= last2; j++) mergedList.add(numbers[j]);
		for(int k = 0; k < mergedList.size(); k++)
		{
			numbers[first+k] = mergedList.get(k);
		}
	}
}
