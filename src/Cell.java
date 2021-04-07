//Cell class to describe the attributes inside each Cell, which are inside the 8x8 grid.



public class Cell {
	private char type;                 //used to show types on the hidden board, used for referencing from the "called" attribute and for the initial placing.
	private char owner;                // sets who is the owner of that position
	private char called;                //used to show types on the public board during game play
	
	
	
	
	
	public Cell() {             //constructor sets the default types and called to "_" and default owner to "N" for No one.
		
		type = '_';
		owner ='N';
		called = '_';
	}
	
	
	
	public char getType() {                //returns the type that is at that position on the hidden board
	    return type;
	
    }
    public void setGrenade(){              //sets a grenade on the hidden board, "g" or "G" depending on who the owner is. "H" for Human and else for Computer
	    if (owner == 'H')
	        type = 'g';
	    else 
		    type = 'G';
    }
    public void setHuman() {             //sets the owner at that position to Human
	    owner = 'H';
    }
    public void setComputer() {        //sets the owner at that position to Computer
	    owner = 'C';
    }
    
    public void setShip() {           //sets an "s" or "S" at that position to represent a Ship on the hidden board, depending on who the owner is
    	if (owner == 'H')
	        type = 's';
	    else 
		    type = 'S';
    }
    public char getCalled() {           //returns the type at a certain position on the called board used during gameplay
    	return called;
    
    }
    public void setMiss() {             //sets the type to a "miss" on the public board used during gameplay
    	called ='*';
    	  }
    public void shipHit() {          //sets an "s" or "S" at that position to represent a Ship being hit on the public board used druing gameplay, depending on who the owner is
    	if (owner == 'H')
	        called = 's';
	    else 
		    called = 'S';
    	
    }
    public void grenadeHit() {        //sets a "g" or "G" at that position to represent a Grenade being hit on the public board used during gameplay, depending on who the owner is
    	if (owner == 'H')
	        called = '*';
	    else 
		    called = '*';
    	
    }
    
}
