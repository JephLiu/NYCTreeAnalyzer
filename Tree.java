/**
 * Stores 9 data fields about a particular tree. Allows other classes to access the data fields.
 *
 * @author Jephrey Liu
 * @version 1.0
 */
public class Tree implements Comparable<Tree>{

	//data fields for storing tree information
	private int tree_id, tree_dbh, zipcode;
	private double x_sp, y_sp;
	private String status, health, spc_common, boroname;

	
	/**
	 * Takes the parameters and places them into the correct fields in the tree object
	 * 
	 * @param id - ID of tree
	 * @param diam - Diameter of Tree
	 * @param status - Status of Tree
	 * @param health - Health of Tree
	 * @param spc - Species of Tree
	 * @param zip - Zipcode of Tree
	 * @param boro - Borough of Tree
	 * @param x - x Pos of Tree
	 * @param y - y Pos of Tree
	 */
	public Tree ( int id, int diam, String status, String health, String spc,
			int zip, String boro, double x, double y ){

		//store 9 values from parameter into 9 data fields
		setID(id);
		setDiam(diam);
		setStatus(status);
		setHealth(health);
		setSpc(spc);
		setZip(zip);
		setBoro(boro);
		setX(x);
		setY(y);

	}

	/**
	 * Stores the ID of the tree. Checks if ID is valid.
	 *
	 * @param in - ID of tree to be stored
	 * @throws IllegalArgumentException if ID to be stored is negative
	 */
	private void setID(int in) throws IllegalArgumentException{
		if(in >= 0){
			tree_id = in;
		}
		else{
			throw new IllegalArgumentException("Error: Tree ID must not be negative");
		}

	}

	/**
	 * Stores the diameter of the tree. Checks if diameter is valid.
	 *
	 * @param in - Diameter of tree to be stored
	 * @throws IllegalArgumentException if diameter to be stored is negative
	 */
	private void setDiam(int in) throws IllegalArgumentException{

		if(in >= 0){
			tree_dbh = in;
		}
		else{
			throw new IllegalArgumentException("Error: Tree diameter must not be negative");
		}
	}

	/**
	 * Stores the status of the tree. Checks if status is valid.
	 *
	 * @param in - Status of tree to be stored
	 * @throws IllegalArgumentException if status to be stored is not alive, dead, stump, or null
	 */
	private void setStatus(String in) throws IllegalArgumentException{
		if(in.compareToIgnoreCase("alive") == 0 || in.compareToIgnoreCase("dead") == 0
				|| in.compareToIgnoreCase("stump") == 0){
			status = in;
		}
		else if(in.equals("")){
			status = null;
		}
		else{
			throw new IllegalArgumentException("Error: Tree status must not be valid. ex -> "
					+ "(alive, dead, stump, null)");
		}

	}

	/**
	 * Stores the health of the tree. Checks if health is valid.
	 *
	 * @param  in - Health of tree to be stored
	 * @throws IllegalArgumentException if health to be stored is not good, poor, fair, or null
	 */
	private void setHealth(String in) throws IllegalArgumentException{
		if(in.compareToIgnoreCase("good") == 0 || in.compareToIgnoreCase("fair") == 0
				|| in.compareToIgnoreCase("poor") == 0){
			health = in;
		}
		else if(in.equals("")){
			health = null;
		}
		else{
			throw new IllegalArgumentException("Error: Tree health must be valid. ex -> "
					+ "(good, fair, poor, null)");
		}
	}

	/**
	 * Stores the name of the tree. Checks if name is valid.
	 *
	 * @param in - Name of tree to be stored
	 * @throws IllegalArgumentException if name to be stored is null
	 */
	private void setSpc(String in) throws IllegalArgumentException{
		if(!(in.equals(null))){
			spc_common = in;
		}
		else{
			throw new IllegalArgumentException("Error: Tree spc_common cannot be null");
		}
	}

	/**
	 * Stores the zipcode location of the tree. Checks if zipcode is valid.
	 *
	 * @param in - Zipcode of tree to be stored
	 * @throws IllegalArgumentException if zipcode to be stored is not from 0 to 99999
	 */
	private void setZip(int in) throws IllegalArgumentException{
		if(in > -1 && in < 100000){
			zipcode = in;
		}
		else{
			throw new IllegalArgumentException("Error: Zipcode must be valid");
		}

	}

	/**
	 * Stores the borough location of the tree. Checks if borough is valid.
	 *
	 * @param in - Borough of tree to be stored
	 * @throws IllegalArgumentException if borough to be stored does not exist
	 */
	private void setBoro(String in) throws IllegalArgumentException{
		if(in.compareToIgnoreCase("manhattan") == 0 || in.compareToIgnoreCase("bronx") == 0
				|| in.compareToIgnoreCase("brooklyn") == 0 || in.compareToIgnoreCase("queens") == 0
				|| in.compareToIgnoreCase("staten island") == 0){
			boroname = in;
		}
		else{
			throw new IllegalArgumentException("Error: NYC borough must be valid");
		}
	}

	/**
	 * Stores the x value of tree
	 *
	 * @param in - x value of tree to be stored
	 */
	private void setX(double in){
		x_sp = in;
	}

	/**
	 * Stores the y value of tree
	 *
	 * @param in - y value of tree to be stored
	 */
	private void setY(double in ){
		y_sp = in;
	}
	
	/**
	 * Returns the id of the tree
	 * 
	 * @return id of tree
	 */
	public int getID(){
		return tree_id;
	}
	
	/**
	 * Returns the species name of the tree
	 * 
	 * @return species name
	 */
	public String getSpc(){
		return spc_common;
	}
	
	/**
	 * Returns the name of the borough the tree resides in
	 * 
	 * @return name of borough the tree resides in
	 */
	public String getBoro(){
		return boroname;
	}

	/**
	 * Compares two trees to see if they are the same
	 * 
	 * @param o - object to compare this tree with
	 * @return true if trees are the same and false if they differ
	 * @throws IllegalArgumentException if tree IDs are the same but tree names are different
	 */
	@Override
	public boolean equals(Object o){
		if(this.getID() == ((Tree) o).getID()){
			if( this.getSpc().compareToIgnoreCase(((Tree) o).getSpc()) == 0 ){
				return true;
			}
			else{
				throw new IllegalArgumentException("The species names must match.");
			}
		}
		else{
			return false;
		}
	}

	/**
	 * Returns integer based on whether the object is "greater than or less than" the other
	 * A tree is "greater" when it's species name comes first in alphabetical order
	 * A tree is "greater" when it has the same species name as another but a lower ID
	 * 
	 * @return 1 if greater, -1 if less than, 0 if equal to tree
	 */
	@Override
	public int compareTo(Tree o) {
		if(this.getSpc().compareToIgnoreCase(o.getSpc()) > 0){
			return -1;
		}
		else if(this.getSpc().compareToIgnoreCase(o.getSpc()) < 0){
			return 1;
		}
		else{
			if(this.getID() > o.getID()){
				return -1;
			}
			else if(this.getID() < o.getID()){
				return 1;
			}
			else{
				return 0;
			}
		}
	}
	
	/**
	 * Displays relevant info about the tree
	 * 
	 * @return String of relevant information
	 */
	@Override
	public String toString(){
		return "ID: "+tree_id+" Diameter: "+tree_dbh+" Status: "+status+" Health: "+health
				+" Species: "+spc_common+" Borough: "+boroname+" Zipcode: "+zipcode+" x-Pos: "
				+x_sp+" y-Pos: "+y_sp;
	}


}
