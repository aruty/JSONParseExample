package csci201.edu.usc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WristCuff {	
	
	private List<Shelter> shelterList = new ArrayList<>();
	private FastList<Shelter> fastList = new FastList<>();
	
	/**
	 * Loads shelters specified in path (JSON file)
	 */
	public WristCuff(String path) throws IOException {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Shelter>>(){}.getType();
		shelterList = gson.fromJson(new FileReader(path), listType);
		
		for (Shelter shelter : shelterList) {
			System.out.println("Inserting Shelter with Chiral Frequency: " + shelter.getChiralFrequency());
			fastList.insert(shelter);
		}
		
		printList();
	}
	
	/**
	 * Finds an available shelter that matches one of the provided Chiral Frequencies
	 */
	public Shelter findShelter(List<Integer> chiralFrequencies) {
		Shelter foundShelter = null;
		for (Integer chiralFrequency : chiralFrequencies) {
			System.out.println("\nStarting search for Chiral Frequency: " + chiralFrequency);
			System.out.println("=======================================");
			Shelter tmpShelter = new Shelter();
			tmpShelter.setChiralFrequency(chiralFrequency);
			foundShelter = fastList.contains(tmpShelter);
		}
		return foundShelter;
	}
	
	public void deleteShelter(Shelter shelter) {
		fastList.delete(shelter);
	}
	
	public void printList() {
		fastList.printAll();
	}
		
	/**
	 * Saves the updated list of shelters to disk
	 */
	public void save() throws FileNotFoundException {
		
	}
}
