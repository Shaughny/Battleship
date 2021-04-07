import java.util.Random;
import java.util.Scanner;

public class BGrid {
	static Scanner in = new Scanner(System.in);
	private static Cell[][] position = new Cell[8][8];              //the only attribute of the BGrid class is an 8x8 array of Cell objects
	
	
	public BGrid() {                                            //constructor initializes each individual Cell for the Grid
		
		for(int i = 0;i<8;i++) {
			for(int j = 0;j<8;j++) {
				position[i][j] = new Cell();
				}
			}
		
	}
	                                   
	
	public static void userItems() {                                   //Method contains 2 For loops that take the input of 6 ships and 4 grenades from the Human User
		                                                               
		System.out.print("Hi, let's play Battleship!\n");                
		
		String input;
		int check;                                 //initialize "check" variable for use in each for loop. if condition passes it'll be kept at 0, otherwise it'll be set to 1
		int[] coordinates = new int[2];            // declare and initialize array of 2 integers to hold array of coordinates that is returned by the Coord method;
	
		for(int i = 1;i<=6;i++)
		{
			
			do {
			    System.out.print("Enter the coordinates of your ship #" +i +": ");              //prompt user for coordinates of ship, collect input of coordinates
			    input = in.next();
			    if(input.length() < 2)                 //if the input from the user is only a letter with no number, it is defaulted to an "invalid" position.
					input = "A9";
			    check = 0;
			    coordinates = coord(input);
			
			
			    if(validateP(input) == false)                                                  //validate whether the coordinates are inside the grid
			    {
				    System.out.print("Sorry, coordinates outside the grid. try again.\n");
			    }
			    else {
			    
				    if(position[coordinates[0]][coordinates[1]].getType() != '_') {                       // check whether the coordinates are filled/used already
				        check =1;
				        System.out.print("Sorry, coordinates already used. try again.\n");	
			        }
			    }
			    if(validateP(input) && check ==0) {
			
				    position[coordinates[0]][coordinates[1]].setHuman();                      //if the coordinates pass both validations, then it sets the owner to the human and a ship to that position
				    position[coordinates[0]][coordinates[1]].setShip();
			    }
			    
			}while(!validateP(input) || check == 1  );             //if the coordinates are not valid and check is set to 1, then the loop is repeated until it is valid	
		
	    }
		for(int i =1;i<=4;i++) {
			do {
				
				
				System.out.print("Enter the coordinates of your grenade #"+i+": ");       //prompt Human user for coordinates of their grenades using the same logical process as the ships
				input = in.next();
				if(input.length() < 2)
					input = "A9";
				check = 0;
				coordinates = coord(input);
				if(validateP(input)==false) {
					System.out.print("Sorry, coordinates outside the grid. try again \n");
				}
				else {
					if(position[coordinates[0]][coordinates[1]].getType() != '_') {                       // check whether the coordinates are filled already
				        check =1;
				        System.out.print("Sorry, coordinates already used. try again.\n");
					}
				}
				if(validateP(input) && check == 0) {
					position[coordinates[0]][coordinates[1]].setHuman();                                    //if check is still 0 and coordinates are valid, then owner is set to Human and type is set to greande at that position
					position[coordinates[0]][coordinates[1]].setGrenade();
					
				}
				
				
				
			}while(!validateP(input) || check == 1  );              //if the coordinates are not valid and check is set to 1, then the loop is repeated until it is valid
		}
		
	}
	
	
	public static boolean validateP(String inputV) {  // method to validate whether the coordinate input from the user or computer  is a position inside the grid
		inputV = inputV.toUpperCase();               //change any input to uppercase to allow lowercase inputs from User
		char letter;
		int number;
		letter = inputV.charAt(0);
		number = Character.getNumericValue(inputV.charAt(1));            //separate the character from the number in the string input
		
		if ((letter >= 'A' && letter <= 'H')&&(number>=1&&number<=8))           //if the coordinates fall inside the desired range, then true is returned. If not then false is returned
			return true;
		else
			return false;
		
	}
	
	public static int[] coord(String inputS)   //method to convert the string coordinate input into an array of two integers for 1.row and 2.column
	{
		inputS = inputS.toUpperCase();                             //change any input to uppercase to allow lowercase inputs from User
		char letter = inputS.charAt(0);                                      // take the letter entered by user and save as a char. Take the number entered by user and save as an integer
		int row =  Character.getNumericValue(inputS.charAt(1))-1;     //substract 1 from the integer entered by user to represent 0-7 in an array instead of 1-8 on the grid.
		int column;
	
		
	    if(letter == 'A')                   //determine the value of the letter entered by user in terms of the grid from 0-7 where A is 0
		    column = 0;
	    else if(letter == 'B')
		    column = 1;
	    else if(letter == 'C')
		    column = 2;
	    else if(letter == 'D')
		    column = 3;
	    else if(letter == 'E')
		    column =4;
	    else if(letter == 'F')
		    column=5;
	    else if(letter == 'G')
		    column =6;
	    else
		    column = 7;
	   
	    int ar[] = new int[2];          // save the row and column values entered by user in a integer array , then return that array
	    ar[0] = row;
	    ar[1] = column;
	    return ar;
		
	}
    public static String compCoord() {   // method to create a random coordinate for the computer to use
		
		
		String alphabet = "ABCDEFGH";                   // sample strings containing the desired range of numbers and letters for the random variable 
		String numbers = "12345678";
		
		Random r = new Random();
		
		char L = alphabet.charAt(r.nextInt(alphabet.length()));           //generate random character from "alphabet" string
		char N = numbers.charAt(r.nextInt(numbers.length()));             //generate random character from "numbers" string
		
		
		String coordinate = ""+L+N;                 //concatenate the two char's into a a random String coordinate then return that string as the Computer's coordinate
		
		return coordinate;
	}
	
    public static void fullBoard()                  //displays full board ie. the one with the initial positions from the Human and Computer and used for reference 
    {
    	
    	for(int i = 0;i<8;i++) {
    		System.out.print("\t\t\t");
			for(int j = 0;j<8;j++) {
				System.out.print(position[i][j].getType()+"\t");
				}
			System.out.print("\n");
			} 	
    }
    
    public static void compItems() {            // This method creates computer coordinates and then sets their ships and grenades down on the board
    	String input;
		int check;                                 
		int[] coordinates = new int[2];            // declare and initialize array of 2 integers to hold array of the Coordinates that is returned by the Coord method;
	
    	
    	
    	for(int i = 0;i<6;i++) {                                       //for loop repeats to set 6 different ships
			do {
				input = compCoord();	                                       //use the compCoord method to create a random value for the computer
				check = 0;
				coordinates = coord(input);
				
			    if(position[coordinates[0]][coordinates[1]].getType() != '_')             //sets the check to 1 if the coordinates are already used, therefore looping again
			    	check = 1;
			    	
			    if(validateP(input) && check == 0) {                                                //if the the coordinates pass the validateP method and check is 0, then the owner and type at that position is changed
			    	position[coordinates[0]][coordinates[1]].setComputer();
			    	position[coordinates[0]][coordinates[1]].setShip();
			    	  }
		
			}while(!validateP(input) || check == 1  );
		}
		
		for(int i = 0;i<4;i++) {                                            //for loop repeats to set 4 different grenades
			do {
				input = compCoord();	
				check = 0;
				coordinates = coord(input);
				
			    if(position[coordinates[0]][coordinates[1]].getType() != '_') //repeats the same validation process as ships, but if it passes, a grenade will be set instead.
			    	check = 1;
			    	
			    if(validateP(input) && check == 0) {
			    	position[coordinates[0]][coordinates[1]].setComputer();              
			    	position[coordinates[0]][coordinates[1]].setGrenade();
			    	  }
		
			}while(!validateP(input) || check == 1  );
		}
    }
    
  public static void partBoard() {                                     //displays the public board ie. the one that is constantly updated and shown during gameplay
	  for(int i = 0;i<8;i++) {
		  System.out.print("\t\t\t");
			for(int j = 0;j<8;j++) {
				System.out.print(position[i][j].getCalled()+"\t");
		
		
			}
			System.out.print("\n");

		}
	  
	  
  }
  
  
  public static int userRocket(int gValue) {       //method to gather a coordinate from the user to launch a rocket at.
	  String input;
	  int value = gValue;
      int check;                                 //initialize check variable for use in each while loop. if condition passes it'll be kept at 0, otherwise it'll be set to 1
      int grenade = 0;                           // if the user hits a grenade, "grenade" variable is set to 1 and then returned from this method to the "BDriver" and passed into the next user's turn
	  int[] coordinates = new int[2]; 
	  
	  for(int i = 0;i<=gValue;i++) {                             // this loop will loop twice if the gValue("grenade") passed in is 1(due to a grenade being hit by the other user)
	      do {
		        System.out.print("position of your rocket: ");              
		        input = in.next();
		        check = 0;
		        coordinates = coord(input);
		
		
	    	    if(validateP(input) == false)                                                  //validate whether the coordinates are inside the grid
		        {
			        System.out.print("Sorry, coordinates outside the grid. try again.\n");
			        check =1;
		        }
		        else if(position[coordinates[0]][coordinates[1]].getCalled() == '_'){                 // these if statements check whether a grenade,ship or nothing is hit.
		    
			        if(position[coordinates[0]][coordinates[1]].getType() == '_') {                       //depending on what's hit, that position is changed to the proper type on the "partial board"
			            System.out.print("nothing.\n");
			            position[coordinates[0]][coordinates[1]].setMiss();
		            }
			        else if(position[coordinates[0]][coordinates[1]].getType() == 'g' ||position[coordinates[0]][coordinates[1]].getType() == 'G' ) {
			    	
			    	    System.out.print("boom! grenade.\n");                                                //if a grenade is hit, then the "grenade" variable is returned as 1 instead of 0
			    	    position[coordinates[0]][coordinates[1]].grenadeHit();                               //This is then used the next time the method is called to determine if 1 or 2 rockets will be shot.
			    	    grenade =  1;
			    	    	
			        }
			        else if(position[coordinates[0]][coordinates[1]].getType() == 'S') {
			    	    System.out.print("ship hit.\n");
			    	    position[coordinates[0]][coordinates[1]].shipHit();
			    	
			        }
			        else if(position[coordinates[0]][coordinates[1]].getType() == 's') {
			    	    System.out.print("Friendly ship hit.\n");
			    	    position[coordinates[0]][coordinates[1]].shipHit();
			    	
			        }
			      }
		        else if(position[coordinates[0]][coordinates[1]].getCalled() != '_') {
		    	    System.out.print("position already called.\n");
		    	
		    	
		        }  
		    
		    
		    
		        if(validateP(input) && check ==0) {                      //print the newly updated "partial" board if the inputs pass the conditions
	               partBoard();
			     }
		    
		}while(!validateP(input) || check == 1  );             //if the coordinates are not valid, then the loop is repeated until it is valid	
	     
	      
	      if(grenade ==1 && value ==1) {                    //fail safe to determine if a grenade is hit while a user is on their "second consecutive" turn.
	    	  grenade = 0;                                  //if a grenade is hit when they were originally supposed to play twice, then the game returns to normal turns again by breaking from the for loop.
	    	  break;
	      }
	  }
	 
	  return grenade;                        //return whether a grenade was hit(1) or not (0)
	  
  }
  public static int computerRocket(int gValue) {    //method to generate an input from the computer to launch a rocket at
	  String input;
	  int value = gValue;
      int check;                                 //initialize check variable for use in each while loop. if condition passes it'll be kept at 0, otherwise it'll be set to 1
      int grenade = 0;
	  int[] coordinates = new int[2]; 
	  
	  for(int i = 0;i<=gValue;i++) {
	      do {
		                                    //create coordinates for computer rocket
		        input =  compCoord();
		        check = 0;
		        coordinates = coord(input);
		
		
	     	    if(validateP(input) == false)                                                  //validate whether the coordinates are inside the grid
		        {
			        check =1;
		        }
		        else if(position[coordinates[0]][coordinates[1]].getCalled() == '_'){
		    
			        if(position[coordinates[0]][coordinates[1]].getType() == '_') {                       // these if statements check whether a grenade,ship or nothing is hit.
			    	    System.out.println("position of my rocket: " + input);                             //depending on what's hit, that position is changed to the proper type.
			    	    System.out.print("nothing.\n");
			            position[coordinates[0]][coordinates[1]].setMiss();             
		            }
			        else if(position[coordinates[0]][coordinates[1]].getType() == 'g' ||position[coordinates[0]][coordinates[1]].getType() == 'G' ) {
			    	    System.out.println("position of my rocket: " + input);
			    	    System.out.print("boom! grenade.\n");
			    	    position[coordinates[0]][coordinates[1]].grenadeHit();                              //if a grenade is hit, then the "grenade" variable is returned as 1 instead of 0
			    	    grenade =  1;                                                                       //This is then used the next time the method is called to determine if 1 or 2 rockets will be shot. 
			    	    	
			        }
			        else if(position[coordinates[0]][coordinates[1]].getType() == 's') {
			    	    System.out.println("position of my rocket: " + input);
			    	    System.out.print("ship hit.\n");
			    	    position[coordinates[0]][coordinates[1]].shipHit();
			    	
			        }
			        else if(position[coordinates[0]][coordinates[1]].getType() == 'S') {                //check if the ship hit is friendly or an enemy.
			    	    System.out.println("position of my rocket: " + input);
			    	    System.out.print("Friendly ship hit.\n");
			    	    position[coordinates[0]][coordinates[1]].shipHit();
			    	
			        }
			     }
		        else if(position[coordinates[0]][coordinates[1]].getCalled() != '_') {
		    	    System.out.println("position of my rocket: " + input);
		    	    System.out.print("position already called.\n");
		    	
		    	
		        }
		    
		    
		    
		    if(validateP(input) && check ==0) {                                         //print the newly updated "partial" board if the coordinates are accepted and the check variable passes
		
			    
			    partBoard();
			    
		    }
		    
		}while(!validateP(input) || check == 1  );             //if the coordinates are not valid, then the loop is repeated until it is valid	
	      
	      if(grenade ==1 && value ==1) {                     //if a grenade is hit in the turn before skipping the other users turn, then the order returns to normal
	    	  grenade = 0;
	    	  break;
	      }
	  }
	  return grenade;
	   
  }

  
  public static String checkShips() {    // method to check whether either of the users has hit all 6 of the opponents ships, returns who has hit all 6 ships.
	  
	  int Hcount=0;
	  int Ccount = 0;
	  
	  for(int i = 0;i<8;i++) {                         //parses through all positions to get for 6 hit human ships or 6 hit computer ships
			for(int j = 0;j<8;j++) {
				if(position[i][j].getCalled() == 'S'&& Character.isUpperCase(position[i][j].getCalled())) {
					Hcount++;
				    }
				else if(position[i][j].getCalled() == 's'&& !Character.isUpperCase(position[i][j].getCalled())) {         
					Ccount++;
				    }
				}
			} 	
	  
	  
	  if(Hcount ==6) {                    //returns who the winner is depending on who has lost all 6 ships or nobody if the game isn't finished yet.
		  return "Human";
	  }
	  else if(Ccount ==6) {
		  return "Computer";
	  }
	  else  
		  return "Nobody";
	  
	  
  }
  
  public static boolean Winner(String shipCheck) {          //this method takes the result of the checkShips method and then displays the winner,the "partial" board and returns True
	  
	  String winner = shipCheck;                              // if neither player has won yet, it displays nothing and returns false
	  
	  if(winner.contains("Human")) {
		  System.out.print("...\n<After a difficult, but fair game...>\n...\nYou Win!\n");        //Human wins closing remarks
		  fullBoard();
		  return true;
	  }
	  else if(winner.contains("Computer")) {
		  System.out.print("...\n<After a difficult, but fair game...>\n...\nComputer Wins!\n");   //Computer wins closing remarks
		  fullBoard();
		  return true;
	  }
	  else {
		  return false;
	  }
		  
	  
  }
  
}
