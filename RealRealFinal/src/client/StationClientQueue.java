package client;


public class StationClientQueue extends AbstractClientQueue{

	
	//이 클라이언트큐에서 각 티켓박스로 나를 받아달라고 요청할 수 있어야 한다.
	
	
	public boolean isEmpty() {
		if (clients.isEmpty()) {
			System.out.println("역클라큐, clients empty.");
			return true;
		} else {
			System.out.println("clients has a client.");
			return false;
		}
	}

	@Override
	public void initialize() {
		clients.clear();
	}
	
	public AbstractClient targetClient() {
		AbstractClient temp = clients.get(0);
		clients.remove(0);
		temp.print();
		return temp;
	}
	
}
