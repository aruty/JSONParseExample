package csci201.edu.usc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	static BufferedReader reader =  
            new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Use user-input to create a WristCuff object.
	 */
	public static WristCuff getWristCuff() throws IOException {
		System.out.println("Please provide timefall shelter data source:");
		String inputFileName = reader.readLine();
		return new WristCuff(inputFileName);
	}
	
	/**
	 * Use user-input to create a list of supported Chiral Frequencies
	 */
	public static List<Integer> getChiralFrequencies() throws IOException {		
		System.out.println("Please provide support chiral frequencies:");
		String str = reader.readLine();
		List<String> nums= Arrays.asList(str.split(","));
		return nums.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Bridges Link.\n");
		try {
			WristCuff wristCuff = getWristCuff();
			List<Integer> list = getChiralFrequencies();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
