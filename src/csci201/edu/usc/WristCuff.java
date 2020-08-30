package csci201.edu.usc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WristCuff {	
	
	private List<Shelter> shelterList = new ArrayList<>();
	
	/**
	 * Loads shelters specified in path (JSON file)
	 */
	public WristCuff(String path) throws IOException {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Shelter>>(){}.getType();
		shelterList = gson.fromJson(new FileReader(path), listType);
	}
	
	/**
	 * Finds an available shelter that matches one of the provided Chiral Frequencies
	 */
	public Shelter findShelter(List<Integer> chiralFrequencies) {
		return null;
	}
	
		
	/**
	 * Saves the updated list of shelters to disk
	 */
	public void save() throws FileNotFoundException {
		
	}
}
