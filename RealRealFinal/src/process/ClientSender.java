package process;

import client.AbstractClient;
import editable.StationCollection;
import exception.SameStationException;

public class ClientSender {
	
	
	StationCollection stationCollection;
	Station station;
	
	public ClientSender(StationCollection stationCollection) {
		this.stationCollection = stationCollection;
	}
	AbstractClient client;
	public void sendClientToStation(AbstractClient client) {
		
		this.client = client;
		String startStation = client.getStartStation();
		String arrivalStation = client.getArrivalStation();
		
		if(startStation.equals(arrivalStation)) {
			try {
				throw new SameStationException("출발역: " +startStation +", 도착역: "
			+ arrivalStation +" \n 으로 출발지와 도착지가 같습니다. " );
			}catch (SameStationException e) {
				e.printStackTrace();
			}
		}

		station = stationCollection.getStation(startStation , true);
		station.add(client);
		client.RecordRealEnqueueTime();
		client.print();
		
	}

}
