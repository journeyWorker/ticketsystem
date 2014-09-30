package clerk;

import client.AbstractClient;
import client.StationClientQueue;

public class BoxChecker implements Observer  {

	// box를 돌면서 비어있는 곳이 있으면 바로 캐치해 고객들을 밀어넣는다.
	// 얘가 고객큐에 접근할 수 있어야해..;ㅅ;
	// 반대로 고객이 있는지도 알아야해..;ㅅ;
	// 그냥 양쪽 모두 옵저버 걸어서 boolean 값으로 티켓머신과 대기 큐가 모두 비지니스 로직이 돌아가게 하는게 좋겠다.
	// 지금 한쪽만 옵저버 쓰고 대기 큐는 안쓰니까, 완전 코드가 꼬여버렸음.
	// 결국 다시 하는 수밖에 없음...;ㅅ;
	
	private AbstractClient client;
	private StationClientQueue stationQueue;
	private TicketBox ticketbox ;
	
	public void setQueue(StationClientQueue stationQueue, TicketBox ticketBox) {
		this.stationQueue = stationQueue;
		this.ticketbox = ticketBox;
		if ( stationQueue.isEmpty()) {System.out.println("BoxChecker: 고객이 없답니다.");}
		else {
			System.out.println("BoxChecker: 고객이 있습니다.");
			try{
				if(ticketbox .equals(null)) {}
				else{update(ticketbox);}
			} catch (NullPointerException e) {}
		}
	}
	
	public void setQueue(StationClientQueue stationQueue, TicketBox[] boxes) {
		TicketBox ticketbox = findEmptyBox(boxes);
		setQueue(stationQueue,ticketbox);
	}

	
	private TicketBox findEmptyBox(TicketBox[] boxes) { 
		for(int i=0; i<boxes.length; i++) {
			if(boxes[i].getboxState() == TicketBox.EMPTY){
				return boxes[i];
			}
		}
		return null;
	}
	
	
	private void setClient(AbstractClient client) {
		this.client =client;
	}
	
	@Override
	public void update(TicketBox ticketBox) {
		
		try { setClient(stationQueue.deliverClient());
		ticketBox.isCameCustomer(client); }
		catch (NullPointerException e){}
	}
	
	
	
	
	
}
