import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SystemCalls {
	
	
	
	public void print(String v ){
		String a = Memory.stringvariables.get(v);
		System.out.println(a);
	}
	

	public String input() {
		System.out.println("Please enter a value");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}
	
	public void assign(String varName , int val) {
		
		Memory.intvariables.put(varName, val);
	}
	
	public void assign(String varName , String val) {
			
			try {
				Memory.intvariables.put(varName, Integer.parseInt(val));

			}
			catch(Exception e) {
				Memory.stringvariables.put(varName, val);
				//System.out.println(varName +" " + input);
			}


	}

	public  void printFromTo(String v1 , String v2) {
		
		int a = Memory.intvariables.get(v1);
		int b = Memory.intvariables.get(v2);
		for(int i = a+1 ; i < b ; i++) {
			System.out.print(i+ " ");
		}
		System.out.println();
	}
	
	public  void writeFile(String v1 , String v2) {
		

		String path = Memory.stringvariables.get(v1);
		String value = Memory.stringvariables.get(v2);
		try {
			String s = readFile(path);
			s+=value;
			try {
				FileWriter fw = new FileWriter(path);
//				
				fw.write(s);
				fw.close();
			}catch(Exception e1){
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				FileWriter fw = new FileWriter(path);
//				
				fw.write(value);
				fw.close();
			}
			catch(Exception e1) {
				
			}
			
		}
		
	}
	public String readFile(String path) throws FileNotFoundException {
		File file = new File(path);
		String text="";
		Scanner myReader = new Scanner(file);
	      while (myReader.hasNextLine()) {
	       text  += myReader.nextLine()+"\n";
	        //System.out.println(Arrays.toString(data));
	      }
		return text;
	}

}
