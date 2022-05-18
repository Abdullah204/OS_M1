import java.util.ArrayList;

public class Process {
	int id;
	ArrayList<String[]> program;
	State state;
	int currentInstruction;
	String temp;
	boolean instructionFinished;
	
	public Process(int id , ArrayList<String[]> program) {
		currentInstruction = 0;
		this.id = id;
		this.state = State.READY;
		this.program = program;
		instructionFinished = true;
		temp ="";
	}
	
	public Process() {
		
	}
	public String toString() {
		return "id: " + id;
	}
}
