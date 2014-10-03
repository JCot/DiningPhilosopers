/**
 * The driver class for the dining philosophers implementation.
 * This class takes in an optional 5 paramters as follows:
 * 		-l : if given half of the philosophers are left handed, otherwise
 *           all of them are right handed
 *      np : number of philosophers and forks
 *      nt : number of think/eat cycles
 *      tm : think time for each philosopher
 *      em : eat time for each philosopher
 * @author Andrew Popovich (ajp7560@rit.edu)
 */
import java.util.ArrayList;

public class Driver {

	/**
	 * Main method that runs the dining philosopher problem.
	 * @param args - String array with args
	 */
	public static void main(String[] args) {
        boolean allRightHanded = true;
        int numPhilosophers = 4;
        int numCycles = 10;
        int thinkTime = 0;
        int eatTime = 0;
        ArrayList<Fork> forks = new ArrayList<Fork>();
        ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
        
        //Initialize parameters
        for(int count = 0; count < args.length; count++){
        	switch(count) {
        		case 0:
        			if(args[count].equals("-l")) {
        				allRightHanded = false;
        			} else {
        				numPhilosophers = Integer.parseInt(args[count]);
        			}
        			break;
        		case 1:
        			if(args[0].equals("-l")) {
        				numPhilosophers = Integer.parseInt(args[count]);
        			} else {
        				numCycles = Integer.parseInt(args[count]);
        			}
        			break;
        		case 2:
        			if(args[0].equals("-l")) {
        				numCycles = Integer.parseInt(args[count]);
        			} else {
        				thinkTime = Integer.parseInt(args[count]);
        			}
        			break;
        		case 3:
        			if(args[0].equals("-l")) {
        				thinkTime = Integer.parseInt(args[count]);
        			} else {
        				eatTime = Integer.parseInt(args[count]);
        			}
        			break;
        		case 4:
        			if(args[0].equals("-l")) {
        				eatTime = Integer.parseInt(args[count]);
        			}
        			break;
        	}
        }
        
        //Create forks
        for(int index = 0; index < numPhilosophers; index++) {
        	forks.add(new Fork());
        }
        
        //Create philosophers
        Philosopher philosopher;
        for(int index = 0; index < numPhilosophers; index++) {
        	//If all philosophers aren't right handed, then the odd numbered philosophers
        	//are left handed
        	boolean rightHanded = (allRightHanded == false && index % 2 == 1) ? false : true;
        	philosopher = new Philosopher(index,
        			      forks.get((numPhilosophers + index - 1) % numPhilosophers),
        			      forks.get(index),
        			      rightHanded,
        			      numCycles,
        			      thinkTime,
        			      eatTime);
        	philosophers.add(philosopher);
        }
        
        for(int index = 0; index < numPhilosophers; index++) {
        	philosophers.get(index).start();
        }
	}
}
