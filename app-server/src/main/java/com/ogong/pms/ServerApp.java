package com.ogong.pms;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import com.ogong.pms.table.AdminTable;
import com.ogong.pms.table.AskBoardTable;
import com.ogong.pms.table.CafeReservationTable;
import com.ogong.pms.table.CafeReviewTable;
import com.ogong.pms.table.CafeRoomTable;
import com.ogong.pms.table.CafeTable;
import com.ogong.pms.table.CeoMemberTable;
import com.ogong.pms.table.JsonDataTable;
import com.ogong.pms.table.MemberTable;
import com.ogong.pms.table.NoticeTable;
import com.ogong.pms.table.StudyTable;
import com.ogong.server.DataProcessor;
import com.ogong.server.RequestProcessor;

public class ServerApp extends Thread {

  //  static ArrayList<Socket> list = new ArrayList<Socket>(); // 유저 확인용
  //  static Socket socket = null;

  //  public ServerApp(Socket socket) {
  //    this.socket = socket; // 유저 socket을 할당
  //    list.add(socket); // 유저를 list에 추가
  //  }
  // Thread 에서 start() 메소드 사용 시 자동으로 해당 메소드 시작 (Thread별로 개별적 수행)
  //  @Override
  //  public void run() {
  //    try {
  //      // 연결 확인용
  //      System.out.println("서버 : " + socket.getInetAddress() 
  //      + " IP의 클라이언트와 연결되었습니다");
  //
  //      // InputStream - 클라이언트에서 보낸 메세지 읽기
  //      InputStream input = socket.getInputStream();
  //      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
  //
  //      // OutputStream - 서버에서 클라이언트로 메세지 보내기
  //      OutputStream out = socket.getOutputStream();
  //      PrintWriter writer = new PrintWriter(out, true);
  //
  //      // 클라이언트에게 연결되었다는 메세지 보내기
  //      writer.println("서버에 연결되었습니다! ID를 입력해 주세요!");
  //
  //      String readValue; // Client에서 보낸 값 저장
  //      String name = null; // 클라이언트 이름 설정용
  //      boolean identify = false;
  //
  //      // 클라이언트가 메세지 입력시마다 수행
  //      while((readValue = reader.readLine()) != null ) {
  //        if(!identify) { // 연결 후 한번만 노출
  //          name = readValue; // 이름 할당
  //          identify = true;
  //          writer.println(name + "님이 접속하셨습니다.");
  //          continue;
  //        }
  //
  //        // list 안에 클라이언트 정보가 담겨있음
  //        for(int i = 0; i<list.size(); i++) { 
  //          out = list.get(i).getOutputStream();
  //          writer = new PrintWriter(out, true);
  //          // 클라이언트에게 메세지 발송
  //          writer.println(name + " : " + readValue); 
  //        }
  //      }
  //    } catch (Exception e) {
  //      e.printStackTrace(); // 예외처리
  //    }           
  //  }   

  public static void main(String[] args) throws Exception {
    System.out.println("[오늘의 공부 서버]");   

    System.out.println("서버 실행 중....");

    ServerSocket serverSocket = new ServerSocket(5050);
    Socket socket = serverSocket.accept();

    //    try {
    //      System.out.println("클라이언트 접속");
    //
    //      // 소켓 서버가 종료될 때까지 무한루프
    //      while(true) {
    //        Socket socketUser = serverSocket.accept(); // 서버에 클라이언트 접속 시
    //        // Thread 안에 클라이언트 정보를 담아줌
    //        Thread thd = new MySocketServer(socketUser);
    //        thd.start(); // Thread 시작
    //      }                 
    //
    //    } catch (IOException e) {
    //      e.printStackTrace(); // 예외처리
    //    }

    // RequestProcessor 가 사용할 DataProcessor 맵 준비
    HashMap<String,DataProcessor> dataProcessorMap = new HashMap<String,DataProcessor>();

    // => 데이터 처리 담당자를 등록한다.
    dataProcessorMap.put("member.", new MemberTable());
    dataProcessorMap.put("askBoard.", new AskBoardTable());
    dataProcessorMap.put("ceoMember.", new CeoMemberTable());
    dataProcessorMap.put("admin.", new AdminTable());
    dataProcessorMap.put("study.", new StudyTable());
    dataProcessorMap.put("cafe.", new CafeTable());
    dataProcessorMap.put("cafeReview.", new CafeReviewTable());
    dataProcessorMap.put("cafeRoom.", new CafeRoomTable());
    dataProcessorMap.put("cafeReservation.", new CafeReservationTable());
    dataProcessorMap.put("notice.", new NoticeTable());

    RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);
    requestProcessor.service();
    requestProcessor.close();

    // => 데이터를 파일에 저장한다.
    Collection<DataProcessor> dataProcessors = dataProcessorMap.values();
    for (DataProcessor dataProcessor : dataProcessors) {
      if (dataProcessor instanceof JsonDataTable) {
        ((JsonDataTable<?>)dataProcessor).save();
      }
    }

    System.out.println("서버 종료");
    serverSocket.close();

  }

}