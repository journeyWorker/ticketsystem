
public class Clerk implements Releaseable {
	
	Observable observable;
	String name;
	Station startStation;
	int id;
	Command slot;
	
	
	public Clerk() {
		// observable = new Observable(this);
	}
	
	public void setCommand(Command command) {
		slot = command;
	}
	
	@Override
	public void release() {
		System.out.println("Kwak");
		slot.execute();
		notifyObservers();
	}

	@Override
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
		
	}
	
	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}
}