import java.util.*;

public class DepthFirst<T> {
	public static ArrayList<String> S1;
	public static ArrayList<String> S2;
	public static ArrayList<Node<ArrayList<String>>> Set_1;
	public static ArrayList<ArrayList<String>> checked1;
	public static ArrayList<ArrayList<String>> checked2;
	public static ArrayList<ArrayList<String>> intersection;
	
	
	static void DepthFirstS(Node<ArrayList<String>> node, ArrayList<ArrayList<String>> checker) {
	    Deque<Node<ArrayList<String>>> stack = new LinkedList<>(); //creates a stack and pushes the first node
	    stack.push(node);
	    
	    while(!stack.isEmpty()) { //keeps looping until its empty
	    	
	    	Node<ArrayList<String>> current = stack.pop(); //pops the current state
	    	if (!current.isVisited()) { //checks objects value if it has been visited
	    		if (!checker.contains(current.getData())) { //compares it to already been checked due to duplicate objects being constantly made
	    			checker.add(current.getData());   //adds the current node to visited
	    			int p_no2 = current.getData().indexOf("-") + 1;
	    			branches(current.getData(),p_no2); //finds reachable states (only 1 move) from current state
	    		    current.setChildren(Set_1);  //adds the items in the list as the children nodes of the current node
	    			current.setVisited(true);  
	    			FileWriting writeToFile = new FileWriting("Output.txt", current.getData());
	    		    writeToFile.write(); // outputs current state to external txt file
	    		    Collections.reverse(current.getChildren()); //reverses the list of children to start at the leftmost child
	    		    current.getChildren().forEach(stack :: push); //pushes each of the children node to the stack
	    		    Set_1.clear(); //clears the list of children nodes
	    		}
	    	}
	    }
	    
	}
	
	static void branches(ArrayList<String> S, int p_no) {
		  if (p_no % 3 == 0 || p_no % 3 == 2) { // checks if in the middle or right column of the puzzle
			  lShift(S, p_no);  //applies left shift
		  }
		  if (p_no % 3 == 1 || p_no % 3 == 2) { //checks if in the middle or left column of the puzzle
			  rShift(S, p_no); //applies right shift
		  }
		  if ((p_no-1) / 3 == 1 || (p_no-1) / 3 == 2) { // checks if in the middle or bottom row of the puzzle
			  uShift( S, p_no); // applies up shift
		  }
		  if ((p_no-1) / 3 == 1 || (p_no-1) / 3 == 0) {  // checks if in the middle or top row of the puzzle
			  dShift(S, p_no); // applies down shift
		  }
	  }
	  
	  static void lShift(ArrayList<String> S, int p_no) {  //takes pos as an argument
	      ArrayList<String> S_1 = new ArrayList<String>(S); //creates a deep copy of the state
		  String x = S_1.get(p_no - 1);  //gets the blank space
		  S_1.set(p_no - 1, S_1.get(p_no - 2)); //swaps it with the tile to the left
		  S_1.set(p_no - 2, x);
	      Node<ArrayList<String>> C1 = new Node<>(); // creates a new object
		  C1.setData(S_1); //assigns the new state to the object
	      Set_1.add(C1);  //adds the object to the list of objects
	  }
		  
	  static void rShift(ArrayList<String> S, int p_no) {
		  ArrayList<String> S_2 = new ArrayList<String>(S); 
	      String x = S_2.get(p_no - 1);
	      S_2.set(p_no - 1, S_2.get(p_no)); //swaps blank space with the tile to the right
	      S_2.set(p_no, x);
	      Node<ArrayList<String>> C1 = new Node<>();
		  C1.setData(S_2);
	      Set_1.add(C1);
	      
	  }
	  
	  static void uShift(ArrayList<String> S, int p_no) {
		  ArrayList<String> S_3 = new ArrayList<String>(S); 
	      String x = S_3.get(p_no - 1);
	      S_3.set(p_no - 1, S_3.get(p_no - 4));  //swaps blank space with the tile above
	      S_3.set(p_no - 4, x);
	      Node<ArrayList<String>> C1 = new Node<>();
		  C1.setData(S_3);
	      Set_1.add(C1);
	      
	      
	  }
	  
	  static void dShift(ArrayList<String> S, int p_no) {
		  ArrayList<String> S_4 = new ArrayList<String>(S); 
		  String x = S_4.get(p_no - 1);
		  S_4.set(p_no - 1, S_4.get(p_no + 2));   //swaps blank space with the tile below
		  S_4.set(p_no + 2, x);
		  Node<ArrayList<String>> C1 = new Node<>();
		  C1.setData(S_4);
		  Set_1.add(C1);
	      
	      
	  }
	  
	  public static boolean validateString(String a) { //this validates the inputted string
		  if (a.length() != 17){    //checks if the length of the string is 17 (the length of 8 letters, the - and the 8 ",") 
			  return false;
		  }
		  int count = 0;
		  int point = 0;
		  for (int i = 0; i<a.length(); i++) {
			  if (a.charAt(i) == ',') {    // makes sure that there will be exactly 8 commas
				  count++;
			  }
		  }
		  if (count != 8) {
			  return false;
		  }
		  for (int i = 0; i<a.length(); i++) {
			  if (a.charAt(i) == '-') {       // makes sure that there is only 1 blank space
				  point++;
			  }
		  }
		  if (point != 1) {
			  return false;
		  }
		  
		  return true;  // if all of these have been passed then it will be in the correct format
	  }
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		S1 = new ArrayList<String>();  // it is a list of string
	    S2 = new ArrayList<String>();
	    
	    boolean textCheck;
	    boolean textCheck1;
	    Scanner reader = new Scanner(System.in); // allows the user to input the state
	    System.out.print("Input S1: ");
		String text = reader.nextLine();	  // reads the input as a string
		textCheck = validateString(text);    // validates the string
	    while (textCheck == false) {
	    	  System.out.println("Incorrect Formatting");  // if the string did not pass validation
	    	  System.out.print("Input S1: ");
	    	  text = reader.nextLine();	           //re-enter the input
	    	  textCheck = validateString(text);  // validate the string again
	    };
	      
	    String[] state1 = text.split(","); //converts the string to an array of strings separating them by the comma
	      
	      
	      
	    System.out.print("Input S2: ");
	    String text1 = reader.nextLine();
	    textCheck1 = validateString(text1);
	    while (textCheck1 == false) {
	    	  System.out.println("Incorrect Formatting");
	    	  System.out.print("Input S2: ");
	    	  text1 = reader.nextLine();	  
	    	  textCheck1 = validateString(text1);
	    };
	    String[] state2 = text1.split(",");
	    reader.close();                         // closes the scanner for inputting
	    
	    for (int i = 0; i<=8; i++ ) {
	         S1.add(state1[i]);         //adds the string inputted into the S1 array
	         S2.add(state2[i]);
	     }
	    
	     Set_1 = new ArrayList<Node<ArrayList<String>>>(4);   //this is a 3d array that holds the children nodes, it has a preset size as no of children cannot exceed that and it saves space
	     checked1 = new ArrayList<ArrayList<String>>(181450);  //2d array that holds all the reachable states that have been checked from S1
	     checked2 = new ArrayList<ArrayList<String>>(181450);  // the same for S2, this size is a close approximation of the full amount of states
	     
	     //Print the inputs S1 and S2
	     System.out.println("Inputted S1: " + S1);
	     System.out.println("Inputted S2: " + S2);
	     
	     WriteFile wFile = new WriteFile("Output.txt", "Part A: ");
 	     wFile.write();  // Writes headers to the output txt file
	     
	     Node<ArrayList<String>> Start1 = new Node<>();
	     Start1.setData(S1);  // assigns S1 as an object that has its own methods such as add children and a check if visited already
	     DepthFirstS(Start1, checked1);  //performs DFS with the state object and the corresponding list
	     //The output of the full set is too large so it goes to txt file
	     System.out.println("Size of the Set of S1: " + checked1.size()); // the size of the list is outputted answer to b
	     WriteFile wFile1 = new WriteFile("Output.txt", "Part B: Size of the Set of S1 - " + checked1.size()); 
 	     wFile1.write();   //adds to file
	     
 	     WriteFile wFile2 = new WriteFile("Output.txt", "Part C: ");
	     wFile2.write();
	     
	     Node<ArrayList<String>> Start2 = new Node<>();
	     Start2.setData(S2);    // sets S2 as and object and perform DFS
	     DepthFirstS(Start2,checked2);
	     
	     System.out.println("Size of the Set of S2: " + checked2.size());
	     WriteFile wFile3 = new WriteFile("Output.txt", "Part D: Size of the Set of S2 - " + checked2.size());
 	     wFile3.write();
 	     
	     intersection = new ArrayList<ArrayList<String>>(checked1);   // a list that copies all items in checked1
	     intersection.retainAll(checked2);                           // keeps all the items that are also in checked2 so it gets the intersection
	     WriteFile wFile4 = new WriteFile("Output.txt", "Part E: ");
	     wFile4.write();
	     for (ArrayList<String> data: intersection) {
	    	 if (!data.isEmpty()) {
	    		 FileWriting writeFile = new FileWriting("Output.txt", data); //outputs intersection to txt file
		 	     writeFile.write(); 
	    	 } 
	     }
	     System.out.println("Size of the Intersection of the Sets of S1 and S2: " + intersection.size());
	     WriteFile wFile5 = new WriteFile("Output.txt", "Part F: Size of the Intersection of the Sets of S1 and S2 - " + intersection.size());
 	     wFile5.write();
	     System.out.println("Finished");
	}

}
