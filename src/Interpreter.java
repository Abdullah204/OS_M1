import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
 
public class Interpreter {
	
	public void interpret(String[] ins, Process process ,Queue<Process> blockedQueue,Queue<Process> readyQueue) {
		
		if(ins[0].equals("assign")) {
			
			
			if(process.instructionFinished) {
				process.currentInstruction--;

				process.instructionFinished = false;
				
				if(ins[2].equals("input")) {
					process.temp = Main.systemCalls.input();
				}
				else {
					try {
						process.temp = Main.systemCalls.readFile(Memory.stringvariables.get(process.id+ins[3]));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}


			}
			else {
				Main.systemCalls.assign(process.id+ins[1], process.temp);				
				process.instructionFinished = true;
				process.temp = "feeh 7aga ghalat!";

			}
		}

		
		else if(ins[0].equals("semWait")){
			
			if(ins[1].equals("userInput")) {
				Main.userInput.semWait(process ,blockedQueue);
			}
			else if (ins[1].equals("userOutput")) {
				Main.userOutput.semWait(process,blockedQueue);
			}
			else {
				Main.file.semWait(process,blockedQueue);
			}
			
		}
		else if(ins[0].equals("semSignal")){
			if(ins[1].equals("userInput")) {
				Main.userInput.semSignal(process, blockedQueue, readyQueue);
			}
			else if (ins[1].equals("userOutput")) {
				Main.userOutput.semSignal(process, blockedQueue, readyQueue);
			}
			else {
				Main.file.semSignal(process, blockedQueue, readyQueue);
			}
			
		}
		else if(ins[0].equals("print")){
			Main.systemCalls.print(process.id+ ins[1]);
		}
//		else if(ins[0].equals("readFile")){
//			
//		}
		else if(ins[0].equals("writeFile")){
			
			Main.systemCalls.writeFile(process.id+ins[1],process.id+ins[2]);
		}
		else if(ins[0].equals("printFromTo")){
			
			Main.systemCalls.printFromTo(process.id+ins[1],process.id+ins[2]);
		}
		process.currentInstruction++;
	}
	
}
