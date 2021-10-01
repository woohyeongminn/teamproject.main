package com.ogong.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;
import com.ogong.pms.table.MemberTable;

// 역할
//- 클라이언트와 통신하는 일을 담당한다.
//- 클라이언트 요청이 들어오면 그 요청을 처리할 객체를 찾아 실행하는 일을 한다.
//- 클라이언트 요청 정보를 객체에 보관하고, 응답 기능을 수행할 객체를 만드는 일을 한다.
//

public class RequestProcessor implements AutoCloseable {

  Socket socket; 
  PrintWriter out;
  BufferedReader in;

  MemberTable memberTable = new MemberTable();

  public RequestProcessor(Socket socket) throws Exception {
    this.socket = socket;
    out = new PrintWriter(socket.getOutputStream());
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  // 아니 개발자님 얘는 왜 에러를 안 던져요?
  // why?
  // 내 선에서 해결할게~
  @Override
  public void close() {
    try {out.close();} catch (Exception e) {}
    try {in.close();} catch (Exception e) {}
    try {socket.close();} catch (Exception e) {}

  }

  public void service() throws Exception {

    while (true) {
      String command = in.readLine();

      if (command.equalsIgnoreCase("quit")) {
        in.readLine();
        out.println("success");
        out.println("goodbye");
        out.flush();
        break;

      } else if (command.startsWith("/member/")) {
        Request request = new Request(command, in.readLine());
        Response response = new Response();

        memberTable.execute(request, response);

        out.println(response.status);

        if (response.getValue() != null) {
          out.println(new Gson().toJson(response.getValue()));

        } else {
          out.println();
        }
        out.flush();

      } else {
        in.readLine();
        out.println("success");
        out.println(command);
        out.flush();
      }
    }
  }

}
