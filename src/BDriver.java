//Assignment 4
//Written by: Mark O'Shaughnessy, 40105211
//For COMP 248 Section S
//This program contains a Battleship game played on an 8x8 grid , with a Human user playing against a computer opponent
//The program uses 3 classes , 1 containing the description of the "Cell" instances used as an attribute inside the other "BGrid" class which contains the 8x8 Grid (2D array of objects) and finally a Driver "BDiver" class to run the game.

public class BDriver {

	public static void main(String[] args) 
	{
		int gCount = 0;         // keeps count of whether a grenade has been hit by the user
		BGrid Grid = new BGrid();                       //initialize the Grid for the game
		
		BGrid.userItems();
		BGrid.compItems();
		System.out.print("\nOK, the computer placed its ships and grenades at random. Let's play.\n\n"); //opening greeting
		
		while(BGrid.Winner(BGrid.checkShips()) == false) {             //the game will continue as long as the Winner method returns false.
			gCount = BGrid.userRocket(gCount);                         // If the Winner method returns true, then the loop will exit and the game ends.
			if(BGrid.Winner(BGrid.checkShips()) == true)                 //the "gCount" variable is used to pass into each method and returned by each method to track if the "grenade" count and see if a user skips a turn
				break;
			gCount = BGrid.computerRocket(gCount);
			
		}
		
		BGrid.in.close();           //close the scanner in "BGrid"

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
