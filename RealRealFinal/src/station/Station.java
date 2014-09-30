package station;

import clerk.BoxChecker;
import clerk.TicketBox;
import client.AbstractClient;
import client.StationClientQueue;

public abstract class Station  {
	
	// 역마다 클라이언트 큐.
	
	public StationClientQueue stationQueue = new StationClientQueue();
	public String name= "서울역이 아닙니다.";
	boolean isticketboxFull = false;

	// 3개의 티켓박스.
	TicketBox[] boxes = { new TicketBox("Alpha") , new TicketBox("Betha"), new TicketBox("Gamma") };
	BoxChecker boxChecker = new BoxChecker();
	
	
	
	public Station() {
		System.out.println(stationQueue.isEmpty());
		Thread checker = new Thread ( new queueChecker());
		checker.start();
	}
	
	public void match() {
		for (int i=0; i < boxes.length; i++) {
			if (boxes[i].getboxState() == TicketBox.EMPTY) {
			   boxChecker.update(boxes[i]);
			}
		}
	}
	
	public void ticketboxfull() {
		if (boxes[0].getboxState() * boxes[1].getboxState() * boxes[2].getboxState() != 0)
		{
			isticketboxFull = true;
		}
	}
	
	public void add(AbstractClient abstractClient) {
		stationQueue.add(abstractClient);
		print();
		System.out.println("이역의 이름은 " + name + "인지도 모르겠습니다.");
	}
	
	
	public StationClientQueue getStationQueue() {
		return stationQueue;
	}

	class queueChecker implements Runnable {
		boolean Live = true;
		public void run() {
			while(Live) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("queuechecker 내부에서 주기적으로 큐를 확인하는 메소드.");
				boxChecker.setQueue(stationQueue);
			
				if(name.equals("서울역")) {
				System.out.println("이역의 이름은 " + name + "입니다. ");
				} else{
					System.out.println("이 역은 서울역이 아닙니다. 스레드를 종료합니다. ");
					Live= false;
				}
				ticketboxfull();
				
				if (isticketboxFull) {
					Live= false;
				} else {
					 match();
				}
			}
		}
	}


	// 하나의 플랫폼 큐
	
	// 연결된 역의 리스트.
	
	public void print() {
		System.out.println(stationQueue.get(0).getName());
	}

}