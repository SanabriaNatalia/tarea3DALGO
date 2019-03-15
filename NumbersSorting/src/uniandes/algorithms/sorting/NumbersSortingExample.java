package uniandes.algorithms.sorting;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NumbersSortingExample {

	/**
	 * Main method for the numbers sorter example. It requires three parameters:
	 * args[0]: Input file with numbers to sort. It must be a text file with one number per line
	 * args[1]: Output file to save the sorted numbers
	 * args[2]: (Optional) Algorithm used to sort the numbers. It can be Bubble, Merge or Quick.
	 * argas[3] (Optional) Number of lines to sort
	 * If not provided, the default algorithm implemented in the static method sort of the class
	 * java.util.Arrays is used
	 * @param args Array with the arguments described above 
	 * @throws Exception if the input file does not exist or it can not be read
	 * @throws Exception If the algorithm is not implemented
	 */
	public static void main(String[] args) throws Exception {
		
		args = new String[4];
		args[0] = "./data/numbers.txt";
		args[1] = "./data/respuesta.txt";
		args[2] = "Bubble";
		args[3] = "1000000";
		
		//Read parameters
		String inFilename = args[0];
		String outFilename = args[1];
		String algorithm = null;
		if(args.length>2) algorithm = args[2];
		int sizeToSort = Integer.parseInt(args[3]);
		
		double[] numbers = new double[sizeToSort];
		
		if(sizeToSort > 100000)
		{
			Random random = new Random( );
			for(int i = 0; i < sizeToSort; i ++)
			{
				numbers[i] = random.nextDouble();
			}
		}
		else
		{
			//Read input file
			List<Double> numbersList = new ArrayList<>();


			try (FileReader reader = new FileReader(inFilename);
					BufferedReader in = new BufferedReader(reader)) { 
				String line = in.readLine();
				for (int i=0;line != null;i++) {
					try {
						numbersList.add(Double.parseDouble(line));
					} catch (Exception e) {
						System.err.println("Can not read number from line "+i+" content: "+line);
						e.printStackTrace();
					}
					line = in.readLine();
				}
			}
			for(int i=0;i<numbers.length;i++) numbers[i] = numbersList.get(i);
		}
		System.out.print("Sorting");
		//Sort values
		long startTime;
		long endTime;
		if(algorithm==null) 
		{
			startTime = System.currentTimeMillis();
			Arrays.sort(numbers);
			endTime = System.currentTimeMillis();
		} 
		else 
		{
			String classname = NumbersArraySorter.class.getPackage().getName()+"."+algorithm+"NumbersArraySorter";
			NumbersArraySorter sortAlgorithm;
			try 
			{
				sortAlgorithm = (NumbersArraySorter)Class.forName(classname).newInstance();
			} 
			catch (Exception e) {
				throw new Exception("Invalid algorithm "+algorithm,e);
			}
			startTime = System.currentTimeMillis();
			sortAlgorithm.sort(numbers);
			endTime = System.currentTimeMillis();
		}
		
		//Output answer
		try (PrintStream out = new PrintStream(outFilename)) {
			for(int i=0;i<numbers.length;i++) {
				if(i>0 && (numbers[i]<numbers[i-1])) throw new RuntimeException("ERROR: Disorder detected at position "+i+" values: "+numbers[i-1]+","+numbers[i]);
				out.println(numbers[i]);
			}
		}
		System.out.println("Sorting by: " + algorithm + "Sort");
		System.out.println("Amount of numbers sorted: " + numbers.length);
		System.out.println("Numbers sorted. Total time(milliseconds): "+(endTime-startTime));
	}

}
