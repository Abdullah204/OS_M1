import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
	
	public void run(Process[] processes){
		Interpreter interpreter = new Interpreter();

		int clockcycle = 0;
		final int timeslice = 5;
		final int numberOfProcesses = 3;
		Process chosen = null;
		Queue<Process> readyQueue = new LinkedList<Process>();
		Queue<Process> blockedQueue = new LinkedList<Process>();
		
		int processtime = 0;
		int finishedProcesses = 0;
		
		while(true) {
			//program finished
			System.out.println("clock cycle: " + clockcycle );
			if(finishedProcesses == numberOfProcesses) {
				System.out.println("all of the " +finishedProcesses + " processes have finished execution" );
				break;

			}
			//begin processes in desired time
			if(clockcycle == 6) {
				readyQueue.add(processes[0]);
				System.out.println("process 1 is now ready");

			}
			if(clockcycle == 0) {
				readyQueue.add(processes[1]);
				System.out.println("process 2 is now ready");

			}
			if(clockcycle == 4) {
				readyQueue.add(processes[2]);
				System.out.println("process 3 is now ready");

			}
			
			//preempt process when required
			if(processtime==timeslice) {
				readyQueue.add(chosen);
				System.out.println("process " + chosen.id +" got preempted");
				printQueues(readyQueue,blockedQueue);
				chosen.state = State.READY;
				processtime = 0;
				chosen = null;
			}
			//dispatch
			if(chosen == null) {
				if(readyQueue.isEmpty()) {
					clockcycle++;
					continue;
				}
				chosen = readyQueue.poll();
				chosen.state=State.CHOSEN;
				processtime = 0;	
				System.out.println("process " + chosen.id +" got dispatched");
				printQueues(readyQueue,blockedQueue);
			}
			
			if(chosen!=null) {
				printCurrentProcess(chosen);
				printCurrentInstruction(chosen);
			}
			
			//run instruction from process
			interpreter.interpret(chosen.program.get(chosen.currentInstruction), chosen,blockedQueue,readyQueue);
			
			//block if blocked
			if(chosen.state == State.BLOCKED) {
				System.out.println("process " + chosen.id + " is blocked");
				chosen=null;
				printQueues(readyQueue,blockedQueue);
				processtime = 0;
			}
			if(chosen != null && chosen.currentInstruction >= chosen.program.size()) {
				chosen.state = State.FINISHED;
				System.out.println("process " + chosen.id + " finished");
				chosen = null;
				processtime = 0;
				finishedProcesses++;

			}
			
			
			//check if process has finished

			// output 
			
			clockcycle++;
			processtime++;
			
		}
	}
	
	private void printQueues(Queue<Process> readyQueue, Queue<Process> blockedQueue) {
		// TODO Auto-generated method stub
		System.out.println("readyQueue: " + readyQueue);
		System.out.println("blockedQueue: " + blockedQueue);
		
	}

	private void printCurrentInstruction(Process process) {
		// TODO Auto-generated method stub
		String s[] = process.program.get(process.currentInstruction);
		if(s[0].equals("assign")) {
			if(process.instructionFinished) {
				System.out.print("current instruction executing: ");
				for(int i = 2 ; i<s.length ; i++)
					System.out.print(s[i]+ " ");
				System.out.println();
			}
			
			
			else {
				System.out.println("assign " + s[1]);
			}
		}
		else {
			System.out.print("current instruction executing: ");
			for(int i = 0 ; i<s.length ; i++)
				System.out.print(s[i]+ " ");
			System.out.println();
		}
	}

	public void printCurrentProcess(Process process) {
		System.out.println("current process executing id: " + process.id);
	}

	public static void addToBlockedQueue(Process process ,Queue<Process> blockedQueue) {
		blockedQueue.add(process);
	}
	public static void removeFromBlockedQueue(Process process ,Queue<Process> blockedQueue) {
		blockedQueue.remove(process);
		
	}

	public static void addToReadyQueue(Process process, Queue<Process> readyQueue) {
		// TODO Auto-generated method stub
		readyQueue.add(process);
		
	}
}
