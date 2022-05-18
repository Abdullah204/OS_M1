import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    
    static SystemCalls systemCalls = new SystemCalls();
    
    static Mutex userInput = new Mutex();
    static Mutex userOutput = new Mutex();
    static Mutex file = new Mutex();
    
    
	public static void main(String[] args) throws IOException {
		
		
		File file1 = new File("Program_1.txt");
		File file2 = new File("Program_2.txt");
		File file3 = new File("Program_3.txt");
		
		Parser parser = new Parser();
		Process p1 = parser.readProgram(1,file1);
		Process p2 = parser.readProgram(2,file2);
		Process p3 = parser.readProgram(3,file3);
		
		Memory.processes = new Process[3];
		Memory.intvariables=new HashMap<String, Integer>(); 
		Memory.stringvariables=new HashMap<String, String>(); 
		Memory.processes[0] = p1;
		Memory.processes[1] = p2;
		Memory.processes[2] = p3;
		Scheduler scheduler = new Scheduler();
		scheduler.run(Memory.processes);

	}


	
	

}
