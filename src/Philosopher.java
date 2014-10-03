import java.util.Random;

/**
 * Simulates a philosopher in the Dining Philosophers problem.
 * A philosopher thinks for a while reaches for a fork, reaches
 * for the other fork, and then eats only when both forks are acquired.
 * 
 * @author Justin
 *
 */

public class Philosopher extends Thread{
	
	int id;
	Fork left;
	Fork right;
	boolean rHanded;
	int nTimes;
	long thinkMillis;
	long eatMillis;
	
	/**
	 * Constructor for a Philosopher thread that represents a philosopher in
	 * the dining philosophers problem.
	 * 
	 * @param id - integer id of the Philosopher
	 * @param left - left hand fork object
	 * @param right - right hand fork object
	 * @param rHanded - boolean indicating if the philosopher is right handed
	 * @param nTimes - number of eat/think cycles
	 * @param thinkMillis - amount of time for thinking
	 * @param eatMillis - amount of time for eating
	 */
	public Philosopher(int id, Fork left, Fork right, boolean rHanded,
			int nTimes, long thinkMillis, long eatMillis) {
		this.id = id;
		this.left = left;
		this.right = right;
		this.rHanded = rHanded;
		this.nTimes = nTimes;
		this.thinkMillis = thinkMillis;
		this.eatMillis = eatMillis;
	}
	
	/**
	 * Philosophers run method that allows the philosopher to eat nTimes.
	 */
	public void run(){
		int count = 0;
		Random rand = new Random();
		
		do{
			int t = rand.nextInt(((int)thinkMillis/1000)+1);
			
			System.out.println("Philosopher " + id + " sleeps for " + t + " time units.");
			
			try{
				sleep(t * 1000);
			}
			catch(InterruptedException e){}
			
			if(rHanded){
				System.out.println("Philosopher " + id + " goes for right fork.");
				
				right.acquire();
				
				System.out.println("Philosopher " + id + " has right fork.");
				
				yield();
			}
			
			System.out.println("Philosopher " + id + " goes for left fork.");
			
			left.acquire();
			
			System.out.println("Philosopher " + id + " has left fork.");
			
			yield();
			
			if(!rHanded){
				System.out.println("Philosopher " + id + " goes for right fork.");
				
				right.acquire();
				
				System.out.println("Philosopher " + id + " has right fork.");
			}
			
			t = rand.nextInt(((int)eatMillis/1000)+1);
			
			System.out.println("Philosopher " + id + " eats for " + t + " time units.");
			
			try{
				sleep(t * 1000);
			}
			catch(InterruptedException e){}
			
			right.release();
			
			System.out.println("Philosopher " + id + " releases right fork.");
			
			left.release();
			
			System.out.println("Philosopher " + id + " releases left fork.");
			
			if(nTimes > 0){
				count++;
			}
		}while(count < nTimes);
	}

}
