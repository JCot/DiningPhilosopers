/**
 * The fork class represents a fork for which a philsopher
 * can acquire to eat.  In other works the fork is a shared mutable
 * object for the philsopher threads.
 * 
 * @author Andrew Popovich (ajp7560@rit.edu)
 *
 */
public class Fork implements IFork {

	private boolean allocated = false;
	
	@Override
	public void acquire() {
		synchronized(this) {
			while (this.allocated){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		allocated = true;
	}

	@Override
	public void release() {
		synchronized(this){
			allocated = false;
			notifyAll();
		}
	}

}
