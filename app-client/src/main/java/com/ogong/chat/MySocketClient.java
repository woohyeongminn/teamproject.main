//package com.ogong.chat;
//
//import java.io.IOException;
//import java.net.Socket;
//import com.ogong.pms.handler.Command;
//import com.ogong.pms.handler.CommandRequest;
//import com.ogong.request.RequestAgent;
//
//// 소켓통신용 클라이언트 부분
//public class MySocketClient implements Command {
//
//  RequestAgent requestAgent;
//
//  public MySocketClient(RequestAgent requestAgent) {
//    this.requestAgent = requestAgent;
//  }
//
//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    try {
//      Socket socket = null;
//      // 소켓 서버에 접속
//      socket = new Socket("172.16.101.93", 1234); 
//      System.out.println("서버에 접속 성공!"); // 접속 확인용
//
//      // 서버에서 보낸 메세지 읽는 Thread
//      ListeningThread t1 = new ListeningThread(socket);
//      WritingThread t2 = new WritingThread(socket); // 서버로 메세지 보내는 Thread
//
//      t1.start(); // ListeningThread Start
//      t2.start(); // WritingThread Start
//
//    } catch (IOException e) {
//      e.printStackTrace(); // 예외처리
//    }
//  }
//}