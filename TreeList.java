/**
 * Creates ArrayList of Tree objects and contains methods to perform calculations
 * on the ArrayList
 *
 * @author Jephrey Liu
 * @version 1.0
 */
import java.util.ArrayList;

public class TreeList extends ArrayList<Tree>{
	
	//Reference to a list for storing trees
	ArrayList<Tree> treelist;
	
	/**
	 * Creates an empty ArrayList for storing Tree objects
	 */
	public TreeList(){
		 treelist = new ArrayList<Tree>();
	}
	
	/**
	 * Returns the total number of tree objects in the list.
	 * 
	 * @return number of tree objects in the list
	 */
	public int getTotalNumberOfTrees(){
		return this.size();
	}
	
	/**
	 * Counts and returns the number of trees of a particular species name
	 * 
	 * @param name of species
	 * @returns number of trees of the specified species
	 */
	public int getCountByTreeSpecies(String speciesName){
		int count = 0;
		for(int i=0;i<this.size();i++){
			if(!(this.get(i)==null)){
				if(this.get(i).getSpc().compareToIgnoreCase(speciesName) == 0){
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * Counts and returns number of trees in a specified borough
	 * 
	 * @param name of borough
	 * @return number of trees in a specified borough
	 */
	public int getCountByBorough(String boroName){
		int count = 0;
		for(int i=0;i<this.size();i++){
			if(!(this.get(i)==null)){
				if(this.get(i).getBoro().compareToIgnoreCase(boroName) == 0){
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * Counts and returns number of trees of a specified species that reside in the specified borough
	 * 
	 * @param name of species
	 * @param name of borough
	 * @return number of trees of a specified species that reside in the specified borough
	 */
	public int getCountByTreeSpeciesBorough (String speciesName, String boroName){
		int count = 0;
		for(int i=0;i<this.size();i++){
			if(!(this.get(i)==null)){
				if(this.get(i).getBoro().compareToIgnoreCase(boroName) == 0){
					if(this.get(i).getSpc().compareToIgnoreCase(speciesName) == 0){
						count++;
					}
				}
			}
		}
		return count;
	}
	
	/**
	 * Counts and returns all the actual species of a particular species type
	 * 
	 * @param name of species to search for
	 * @return ArrayList of all the actual species of a given species name
	 */
	public ArrayList<String> getMatchingSpecies(String speciesName){
		ArrayList<String> speciestreeslist = new ArrayList<String>();
		int count = 0;

		for(int i=0;i<this.size();i++){
			if(this.get(i).getSpc().toUpperCase().contains(speciesName.toUpperCase())){
				if(count == 0){
					speciestreeslist.add(this.get(i).getSpc());
					count++;
				}
				for(int a=0;a<speciestreeslist.size();a++){
					if((speciestreeslist.get(a).compareToIgnoreCase(this.get(i).getSpc()) == 0)){
						break;
					}
					else if(a+1 == speciestreeslist.size()){
						speciestreeslist.add(this.get(i).getSpc());
					}
				}
			}
		}
		return speciestreeslist;
	}
	
	/**
	 * Displays relevant info about the tree
	 * 
	 * @return String of relevant information
	 */
	@Override
	public String toString(){
		return this.size()+" of tree objects are in this TreeList";
	}
}
