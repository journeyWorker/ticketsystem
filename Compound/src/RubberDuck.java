
public class RubberDuck implements Client {
	
	Observable observable;
	
	
	public RubberDuck() {
		observable = new Observable(this);
	}
	
	@Override
	public void quack() {
		// TODO Auto-generated method stub
		System.out.println("Squeak");
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