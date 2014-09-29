
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
