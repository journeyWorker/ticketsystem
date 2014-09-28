
public class DuckSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		
		DuckSimulator simulator = new DuckSimulator();
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		
		simulator.simulate(duckFactory);
	}
	
	void simulate( AbstractDuckFactory duckFactory) {
		
		Client redheadDuck = duckFactory.createRedheadDuck();
		Client duckCall = duckFactory.createDuckcall();
		Client rubberDuck = duckFactory.createRubberDuck();
		Client gooseDuck = new GooseAdapter(new Goose());
	
		System.out.println("\nDuck Simulator with Decorator With Abstract Factory With Composite");
		
		Flock flockOfDucks = new Flock();
		
		
		flockOfDucks.add(redheadDuck);
		flockOfDucks.add(duckCall);
		flockOfDucks.add(rubberDuck);
		flockOfDucks.add(gooseDuck);
		
		Flock flockOfMallards = new Flock();
		
		Client mallardOne = duckFactory.createMallardDuck();
		Client mallardTwo = duckFactory.createMallardDuck();
		Client mallardThree = duckFactory.createMallardDuck();
		Client mallardFour = duckFactory.createMallardDuck();
		
		flockOfMallards.add(mallardOne);
		flockOfMallards.add(mallardTwo);
		flockOfMallards.add(mallardThree);
		flockOfMallards.add(mallardFour);
		
		flockOfDucks.add(flockOfMallards);
		
		
		Quackologist quackologist = new Quackologist();
		flockOfDucks.registerObserver(quackologist);
		
		
		System.out.println("\nDuck Simulator: Whole Flock Simulation");
		simulate(flockOfDucks);
		
		/*
		System.out.println("\nDuck Simulator: Mallard Flock Simulation");
		simulate(flockOfMallards);
		*/
		
		
		
		
		
		System.out.println("The ducks quacked " + 
		QuackCounter.getQuacks() + "times" );
		
	}
	
	void simulate(Client duck) {
		duck.quack();
	}

}