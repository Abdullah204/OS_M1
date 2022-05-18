import java.util.LinkedList;
import java.util.Queue;
public class Mutex {

	int procID;
	boolean available;
	Queue<Process> queue;
	
	public Mutex() {
		procID = -1;
		available = true;
		queue = new LinkedList<Process>();
	}
	void semWait(Process process , Queue<Process> blockedQueue){
		if(this.available) {
			procID = process.id;
			this.available = false;
		}
		else {
			queue.add(process);
			process.state = State.BLOCKED;
			Scheduler.addToBlockedQueue(process,blockedQueue);
			
		}
	}
	void semSignal(Process process ,Queue<Process> blockedQueue,Queue<Process> readyQueue) {
		if(queue.isEmpty()) {
			this.available =true;
			procID = -1;
		}
		else {
			Process blockedprocess = (Process) queue.poll();
			blockedprocess.state = State.READY;
			procID=blockedprocess.id;
			//make process ready 
			Scheduler.removeFromBlockedQueue(blockedprocess, blockedQueue);
			Scheduler.addToReadyQueue(blockedprocess,readyQueue);
		}
	}

}
