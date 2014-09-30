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
	
	private AbstractClient client = null;
	private StationClientQueue stationQueue;
	
	public void setQueue(StationClientQueue stationQueue) {
		this.stationQueue = stationQueue;
		if ( stationQueue.isEmpty()) {System.out.println("BoxChecker.setQueue.if: 큐가 널임.");}
		else {
			System.out.println("BoxChecker.setQueue.if: 큐가 널이 아님.");
			setClient(stationQueue.get(0));
		}
	}
	
	public void setClient(AbstractClient client) {
		this.client =client;
	}
	
	public void updateClient() {
		try{
			if(client.equals(null)) {
				System.out.println("BoxChecker.setClient.if: 클라가 null임");
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("오늘은 아예 손님이 없나봅니다." );
		}
		
		if (stationQueue.isEmpty()) {
			System.out.println("해당 역에 아무런 승객이 없습니다.");
		
		} else {
			client = stationQueue.targetClient();
			client.print();
		}
	}

	@Override
	public void update(TicketBox ticketBox) {
			updateClient();
			ticketBox.isCameCustomer(client);
	}

	
	
}
