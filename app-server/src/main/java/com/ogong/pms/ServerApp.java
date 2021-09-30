package com.ogong.pms;

import java.net.ServerSocket;
import java.net.Socket;
import com.ogong.pms.server.RequestProcessor;

public class ServerApp {

  public static void main(String[] args) throws Exception {
    System.out.println("[오늘의 공부 서버]");

    System.out.println("서버 실행 중....");

    ServerSocket serverSocket = new ServerSocket(5050);
    Socket socket = serverSocket.accept();

    System.out.println("클라이언트 접속");

    RequestProcessor requestProcessor = new RequestProcessor(socket);

    requestProcessor.service();
    requestProcessor.close();

    System.out.println("서버 종료");
    serverSocket.close();
  }

}