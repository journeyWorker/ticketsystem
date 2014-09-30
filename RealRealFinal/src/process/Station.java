package process;

import clerk.BoxChecker;
import clerk.TicketBox;
import client.AbstractClient;
import client.StationClientQueue;
import editable.Simulator;

public abstract class Station  {
	
	// 역마다 클라이언트 큐.
	
	public StationClientQueue stationQueue = new StationClientQueue();
	public String name= "초깃값";
	
	// 3개의 티켓박스.
	TicketBox[] boxes = { new TicketBox("Alpha", stationQueue) , new TicketBox("Betha", stationQueue) , new TicketBox("Gamma", stationQueue) };
	BoxChecker boxChecker = new BoxChecker();
	
	
	
	public Station() {
	
		Thread checker = new Thread ( new queueChecker());
		checker.setDaemon(true);
		checker.start();
	}
	
	public void match() {
		for (int i=0; i < boxes.length; i++) {
			if (boxes[i].getboxState() == TicketBox.EMPTY ) {
				System.out.println("match 내부입니다." +(i+1)+"번째 티켓박스가 비었습니다.");
			   boxChecker.update(boxes[i]);
			}
		}
	}
	
	public void add(AbstractClient abstractClient) {
		stationQueue.add(abstractClient);		
	}
	
	
	public StationClientQueue getStationQueue() {
		return stationQueue;
	}

	class queueChecker implements Runnable {
		
		public void run() {
			boolean live = true;
			boolean NoOneinStation = true;
			
			while(live) {
				
				while(NoOneinStation) {
					try {
						Thread.sleep(Simulator.CHECK_INTERVAL * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(stationQueue.isEmpty()) { }
					else {
					NoOneinStation = false;
					}
				
				}
				
				boxChecker.setQueue(stationQueue,boxes[0]);
				
				try {
					Thread.sleep(Simulator.CHECK_INTERVAL * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				boxChecker.setQueue(stationQueue,boxes);
				
				if (isTicketBoxFull()) {} 
				else {
					if(stationQueue.isEmpty()) {System.out.println("여기다. 여기서 뭐해야해.");} 
					else {
					 match();
					}
				}
			}
		}
		
		private  boolean isTicketBoxFull() {
			if (boxes[0].getboxState() * boxes[1].getboxState() * boxes[2].getboxState() != 0)
			{
				return true; 
			}
			else { return false;}
		}
		
	}


	// 하나의 플랫폼 큐
	
	// 연결된 역의 리스트.
}
