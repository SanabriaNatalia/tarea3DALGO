package uniandes.algorithms.sorting;

/**
 * Implements the bubble sort algorithm for number arrays
 */
public class BubbleNumbersArraySorter implements NumbersArraySorter {

	@Override
	public void sort(double[] numbers) {
		for(int i = 0; i < numbers.length -2; i++ )
		{
			for(int j = numbers.length-1; j > i; j--)
			{
				double n1 = numbers[j-1];
				double n2 = numbers[j];
				
				if(n1> n2)
				{
					numbers[j-1] = n2;
					numbers[j] = n1;
				}
			}
		}
		
	}

}
