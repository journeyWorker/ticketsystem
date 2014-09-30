package clerk;

public class TiketBoxMachine implements Runnable {
		Ticket ticket;
		int ticketingTime;
	public TiketBoxMachine(Ticket ticket, int ticketingTime) {
		this.ticket = ticket;
		this.ticketingTime = ticketingTime;
	}
	public void run() {
		try {
			Thread.sleep(ticketingTime * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ticket.state = changeStateToEmpty();
		System.out.println("발급 완료 어서가시죠.");
		
	}
	
	private int changeStateToEmpty() {
		return TicketBox.EMPTY;
	}
}
