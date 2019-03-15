package uniandes.algorithms.sorting;

/**
 * Implements the quick sort algorithm for number arrays
 */
public class QuickNumbersArraySorter implements NumbersArraySorter {

	@Override
	public void sort(double[] numbers) 
	{
		quickSort(numbers, 0, numbers.length-1);
	}

	private void quickSort(double[] numbers, int first, int last)
	{
		if(first>=last) return;
		int p = makePartition(numbers, first, last);
		quickSort(numbers, first, p-1);
		quickSort(numbers, p+1, last);
	}
	
	private int makePartition(double[] numbers, int first, int last)
	{

		int i = first;
		for(int j = first; j <= last; j++)
		{
			double n1 = numbers[j]; 
			if(n1<=numbers[last])
			{
				numbers[j] = numbers[i];
				numbers[i] = n1;
				i++;
			}
		}
		return i -1;
	}
}
