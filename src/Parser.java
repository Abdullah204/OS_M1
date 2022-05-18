import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	
    public Process readProgram(int id , File program) throws FileNotFoundException {
  	  
  	  
  	  Scanner myReader = new Scanner(program);
  	  ArrayList<String[]> prog = new ArrayList<String[]>();
	      while (myReader.hasNextLine()) {
	    	 
	        String []data = myReader.nextLine().split(" ");
	        prog.add(data);
	     	  
	      }
	      
		return new Process(id,prog);
    }
}
