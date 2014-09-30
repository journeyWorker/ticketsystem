package client;


//맨처음 클라이언트 데이터를 받는 큐 
// 그리고 역 대기실 마다 있는 큐
// 그리고 플랫폼에 있는 큐
// 스트래티지 패턴으로 리팩토링하기.
// 또는 메소드 템플릿 패턴인데..봐바야겠음.



public class ClientQueue extends AbstractClientQueue {

	
	
	public void initialize() {
		//파일  IO 구현 요함
		
		add(new Client("고객0", 1, 14,"Seoul","Gwangju"));
		add(new Client("고객0", 2, 14,"Seoul","Gwangju"));
		add(new Client("고객0", 3, 14,"Seoul","Gwangju"));
		add(new Client("고객0", 4, 14,"Seoul","Gwangju"));
		add(new Client("고객0", 10, 4,"Seoul","Gwangju"));
		add(new Client("고객0", 16, 4,"Seoul","Gwangju"));
		add(new Client("고객0", 13, 4,"Seoul","Gwangju"));
		add(new Client("고객0", 12, 4,"Seoul","Gwangju"));
		add(new Client("고객0", 14, 4,"Seoul","Gwangju"));
		add(new Client("고객0", 13, 4,"Seoul","Gwangju"));
		add(new Client("고객0", 15, 4,"Wonju","Gwangju"));
		
		
		/*
		add(new Client("고객1", 0, 3,"Seoul","Gwangju"));
		add(new Client("고객2", 1, 3,"Seoul","Gwangju"));
		add(new Client("고객3", 2, 4,"Seoul","Gwangju"));
		add(new Client("고객4", 3, 4,"Seoul","Gwangju"));
		add(new Client("고객5", 4, 4,"Seoul","Gwangju"));
		add(new Client("고객6", 4, 4,"Gwangju","Wonju"));
		add(new Client("고객7", 5, 4,"Seoul","Wonju"));
		add(new Client("고객8", 6, 4,"Daejeon","Wonju"));
		add(new Client("고객9", 6, 4,"Chuncheon","Wonju"));
		add(new Client("고객0", 7, 4,"Seoul","Wonju"));
		*/
		timerStart();
		
	}
}
