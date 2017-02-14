import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NYCStreetTrees {
	
	public static TreeList listoftrees = new TreeList();
	
	public static void main(String[] args) {	
		
		boolean exit = false;
		
		//argument validation
		if(!(args.length == 1)){
			System.err.println("Error: You can only specify one file name");
			System.exit(1);
		}
		
		//file validation
		File treefile = new File(args[0]);
		validateFile(treefile);
		
		//populates tree list
		populateTreeList(treefile);
		
		
		
		//user interaction
		Scanner userinput = new Scanner(System.in);
		String input;
		do{
			System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");
			input = userinput.nextLine().trim();
			if(input.equalsIgnoreCase("quit")){
				System.out.println("Program has quit");
				System.exit(2);
			}
			else{
				
				//System.out.println(listoftrees.getTest());
				ArrayList<String> relevanttree = new ArrayList<String>();
				relevanttree = listoftrees.getMatchingSpecies(input);
				if(relevanttree.size() == 0){
					System.out.println("There are no records of "+input+" on NYC streets");
				}
				else{
					System.out.println("All matching species:");
					for(int i = 0;i<relevanttree.size();i++){
						System.out.println("\t"+relevanttree.get(i));
					}
					
					//additional statistics
					
					//variable for finding totals of each species in each borough
					double nyctotal = 0, manhattotal = 0, bronxtotal = 0, brooktotal = 0, queentotal = 0, islandtotal = 0;
					
					for(int i=0;i<relevanttree.size();i++){
						nyctotal += listoftrees.getCountByTreeSpecies(relevanttree.get(i));
						manhattotal += listoftrees.getCountByTreeSpeciesBorough(relevanttree.get(i),"manhattan");
						bronxtotal += listoftrees.getCountByTreeSpeciesBorough(relevanttree.get(i),"bronx");
						brooktotal += listoftrees.getCountByTreeSpeciesBorough(relevanttree.get(i),"brooklyn");
						queentotal += listoftrees.getCountByTreeSpeciesBorough(relevanttree.get(i),"queens");
						islandtotal += listoftrees.getCountByTreeSpeciesBorough(relevanttree.get(i),"staten island");
					}
					
					//variables for percentage in boroughs and nyc as a whole
					double nycstat,  manhatstat, bronxstat, brookstat, queenstat, islandstat;
					nycstat = nyctotal/listoftrees.getTotalNumberOfTrees();
					manhatstat = manhattotal/listoftrees.getCountByBorough("manhattan");
					bronxstat = bronxtotal/listoftrees.getCountByBorough("bronx");
					brookstat = brooktotal/listoftrees.getCountByBorough("brooklyn");
					queenstat = queentotal/listoftrees.getCountByBorough("queens");
					islandstat = islandtotal/listoftrees.getCountByBorough("staten island");
						
					System.out.println(nycstat);
					System.out.println(manhatstat);
					System.out.println(bronxstat);
					System.out.println(brookstat);
					System.out.println(queenstat);
					System.out.println(islandstat);
				}
				
			}
		}
		while(!exit);
		
		//unreachable code but here just as good practice
		userinput.close();
		
	}
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround  multi-word entries that may contain commas). 
	 * 
	 * @param textLine - line of text to be parsed
	 * @return an ArrayList object containing all individual entries/tokens
	 *         found on the line.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry= false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes 
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') { 
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false; 
				}
				else {
					insideQuotes = true; 
					insideEntry = true; 
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if  ( insideQuotes || insideEntry ) {
					// add it to the current entry
					nextWord.append( nextChar );
				}
				else  { // skip all spaces between entries 
					continue;
				}
			}
			else if ( nextChar == ',') {
				if (insideQuotes) //comma inside an entry 
					nextWord.append(nextChar);
				else { //end of entry found 
					insideEntry = false; 
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord 
				nextWord.append(nextChar);
				insideEntry = true; 
			}

		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
	
	private static void validateFile(File f){
		
		if(!f.exists()){
			System.err.println("Error: The file you specified cannot be found");
			System.exit(1);
		}
		else if(!f.canRead()){
			System.err.println("Error: You do not have permission to read the specified file");
			System.exit(1);
		}
		
	}
	
	private static void populateTreeList(File f){
		ArrayList<String> fileline = new ArrayList<String>();
		try{
			System.out.println("Loading file...");
			Scanner parser = new Scanner(f);
			parser.nextLine();
			while(parser.hasNextLine()){
				 
				 fileline = splitCSVLine(parser.nextLine());
				 int id = Integer.parseInt(fileline.get(0).trim());
				 int diam = Integer.parseInt(fileline.get(3).trim());
				 String status = fileline.get(6).trim();
				 String health = fileline.get(7).trim();
				 String spc = fileline.get(9).trim();
				 int zip = Integer.parseInt(fileline.get(25).trim());
				 String boro = fileline.get(29).trim();
				 double x = Double.parseDouble(fileline.get(39).trim());
				 double y = Double.parseDouble(fileline.get(40).trim());			 
				 listoftrees.add(new Tree(id, diam, status, health, spc, zip, boro, x, y));
			}
			parser.close();
			
			System.out.println("File has been successfully loaded!");
		}
		catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}
		
	}
}
