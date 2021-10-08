package com.ogong.pms;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import com.ogong.pms.table.AdminTable;
import com.ogong.pms.table.AskBoardTable;
import com.ogong.pms.table.CafeReservationTable;
import com.ogong.pms.table.CafeReviewTable;
import com.ogong.pms.table.CafeRoomTable;
import com.ogong.pms.table.CafeTable;
import com.ogong.pms.table.CeoMemberTable;
import com.ogong.pms.table.MemberTable;
import com.ogong.pms.table.NoticeTable;
import com.ogong.pms.table.StudyTable;
import com.ogong.server.DataProcessor;
import com.ogong.server.RequestProcessor;

public class ServerApp extends Thread {



  public static void main(String[] args) throws Exception {
    System.out.println("[ 🎓 오늘의 공부 서버 ]");   

    System.out.println("서버 실행 중....");

    ServerSocket serverSocket = new ServerSocket(5050);
    //    Socket socket = serverSocket.accept();

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

    while (true) {
      Socket socket = serverSocket.accept();
      System.out.println("클라이언트 접속");

      RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);
      requestProcessor.start();
    }

    //    requestProcessor.close();

    // => 데이터를 파일에 저장한다.
    //    Collection<DataProcessor> dataProcessors = dataProcessorMap.values();
    //    for (DataProcessor dataProcessor : dataProcessors) {
    //      if (dataProcessor instanceof JsonDataTable) {
    //        ((JsonDataTable<?>)dataProcessor).save();
    //      }
    //    }

    //    System.out.println("서버 종료");
    //    serverSocket.close();

  }

}