package clerk;

import client.AbstractClient;

public class TicketBox implements StateObservable{
	
	public final static int EMPTY = 0;
	final static int HAS_CUSTOMER = 1;
	final static int SOLD = 2;
	
	public String name;
	boolean isLive = true;
	
	Ticket ticket = new Ticket();
	private int boxState = EMPTY;

	AbstractClient client = null;
	Observable observable;
	
	
	public int getboxState() {
		return boxState;
	}
	public void setClient(AbstractClient client) {
		this.client = client;
	}
	
	public TicketBox(String name) {
		this.name = name;
		observable = new Observable(this);
	}

	public void isCameCustomer(AbstractClient client) {
		System.out.println("hahahahaha");
		if (boxState == EMPTY) {
			boxState = HAS_CUSTOMER;
			setClient(client);
			client.RecordWaitTime();
			System.out.println("어서오세요. 티켓발부 해드릴게요.");
			takeOrder();
		} else if (boxState == HAS_CUSTOMER) {
			System.out.println("다른 분 이후에 안내도와드릴게요. ");	
		} else if (boxState == SOLD) {
			System.out.println("잠시만요, 티켓 발급 중입니다.");
		}
	}
	
	private void takeOrder() {
		if (boxState == EMPTY) {
			System.out.println("줄 서서 기다리셔야해요.");
		} else if (boxState == HAS_CUSTOMER) {
			boxState = SOLD;
			ticketing();
			System.out.println("멀리 가시네요~ 티켓 준비중이니 잠시만 기다려주세요.");	
		} else if (boxState == SOLD) {
			System.out.println("잠시만요, 티켓 발급 중입니다.");
		}
	}
	

	private void ticketing() {
		System.out.println("잠시 기다립시다.스레드 불러서 잠시 멈출게요.");
		
		Thread t = new Thread(new TiketBoxMachine(ticket , client.getTicketingTime()));
	
		t.setDaemon(true);
		t.start();
		client.RecordRealTicketingTime();
		StateIsEmpty();
	}

	
	public void StateIsEmpty() {
		boxState = ticket.state;
		setClient(null);
		notifyObservers();
	}
	
	@Override
	public void notifyObservers() {
		observable.notifyObservers();
	}

}
