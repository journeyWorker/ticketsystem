package station;

import java.util.ArrayList;
import java.util.HashMap;

import exception.NoEntryArrivalStationException;
import exception.NoEntryStartStationException;


public class StationCollection {

	public StationCollection() {
		initialize();
	}
	
	interface LOCATION {
		Integer SEOUL = 0, 
		DAEJEON = 1, 
		GWANGJU = 2, 
		CHUNCHEON = 3, 
		WONJU = 4,
		ASAN = 5,
		GYEONGJU= 6;
	}
	
	HashMap<String , Integer > stationMap = new HashMap<String ,Integer>();
	ArrayList<Station> stationList = new ArrayList<Station>();
	
	public void initialize() {
		
		MapInitialize();
		ListInitalize();
	}
	
	
	
	public ArrayList<Station> getStationList() {
		return stationList;
	}

	public void MapInitialize() {
		
		stationMap.put("Seoul", LOCATION.SEOUL);
		stationMap.put("Chuncheon" , LOCATION.CHUNCHEON);
		stationMap.put("Wonju" , LOCATION.WONJU);
		stationMap.put("Asan" , LOCATION.ASAN);
		stationMap.put("Gwangju" , LOCATION.GWANGJU);
		stationMap.put("Daejeon" , LOCATION.DAEJEON);
		stationMap.put("Gyeongju", LOCATION.GYEONGJU);

	}

	
	public void ListInitalize(){
		
		stationList.add( LOCATION.SEOUL,  new SeoulStation());
		stationList.add( LOCATION.DAEJEON,new DaejeonStation());
		stationList.add( LOCATION.GWANGJU , new GwangjuStation());
		stationList.add( LOCATION.CHUNCHEON ,new ChuncheonStation());
		stationList.add( LOCATION.WONJU , new WonjuStation());
		stationList.add( LOCATION.ASAN , new AsanStation());
		stationList.add( LOCATION.GYEONGJU , new GyeongjuStation());
		
	}
	
	// name 은 지역 이름, isStartStation이 True면 출발역, false면 도착역 

	public Station getStation(String name , boolean isStartStation) {
		if (stationMap.containsKey(name)) {
			int index = stationMap.get(name);
			System.out.println(index +"가 0이 아닙니까,,?");
			System.out.println(stationList.get(index).name + "은 서울이여야해.");
			return stationList.get(index);
		}else {
			if (isStartStation) {
				try {
					throw new NoEntryStartStationException(name + ", 해당 출발역이 없습니다.");
				}catch(NoEntryStartStationException e) {
						e.printStackTrace();
						return null;
				}
				
			}else {
				try {
					throw new NoEntryArrivalStationException(name + ", 해당 도착역이 없습니다.");
				}catch(NoEntryArrivalStationException e) {
						e.printStackTrace();
						return null;
				}
				
			}
		
		}
	}
}