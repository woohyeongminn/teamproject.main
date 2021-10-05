package com.ogong.pms.handler.myStudy;

import java.util.HashMap;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyDetailHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 내 스터디 상세");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    int inputNo = Prompt.inputInt(" 번호  : ");

    HashMap<String,String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(member.getPerNo()));
    params.put("studyNo", String.valueOf(inputNo));

    requestAgent.request("study.my.selectOneByMemberNoStudyNo", params);

    Study s = new Study();
    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {

      Study myStudy = requestAgent.getObject(Study.class);

      System.out.printf(" \n(%s)\n", myStudy.getStudyNo());
      System.out.printf(" [%s]\n", myStudy.getStudyTitle());
      System.out.printf(" >> 조장 : %s\n", myStudy.getOwner().getPerNickname());
      System.out.printf(" >> 분야 : %s\n", myStudy.getSubject());
      System.out.printf(" >> 지역 : %s\n", myStudy.getArea());
      System.out.printf(" >> 인원수 : %s/%s명\n",
          myStudy.getMembers().size() + 1, myStudy.getNumberOfPeple());
      System.out.printf(" >> 대면 : %s\n", myStudy.getFace());
      System.out.printf(" >> 소개글 : %s\n", myStudy.getIntroduction());

      s = myStudy;

    } else {
      System.out.println();
      System.out.println(" >> 스터디 번호가 일치하지 않습니다.");
      return;
    }

    //if (member.getPerNickname().equals(s.getOwner().getPerNickname())) {
    System.out.println("\n----------------------");
    System.out.println("1. 구성원");
    // 대기중인 회원 거절하기
    // 대기중인 회원 승인할때 회원 고유번호로 입력하도록
    // 조장 : 권한 넘겨주기, 탈퇴시키기 까지
    System.out.println("2. 캘린더");
    System.out.println("3. To-do");
    System.out.println("4. 자유게시판");
    System.out.println("5. 화상미팅");
    System.out.println("6. 탈퇴하기");  // 메서드
    // 스터디 삭제는 되는데 탈퇴하기가 없음
    // 구성원은 탈퇴만 해야함, 조장은 스터디삭제도 할수 있음

    if (s.getOwner().getPerNickname().equals(
        AuthPerMemberLoginHandler.loginUser.getPerNickname())) {
      System.out.println("7. 스터디 수정");
      System.out.println("8. 스터디 삭제");
    }
    System.out.println("0. 뒤로 가기");

    request.setAttribute("inputNo", s.getStudyNo());

    int selectNo = Prompt.inputInt("선택> "); 
    switch (selectNo) {
      case 1: request.getRequestDispatcher("/myStudy/guilder").forward(request); return;
      case 2: request.getRequestDispatcher("/myStudy/calenderList"); return;
      //case 3: myStudyToDo.listToDo(myStudy); break;
      case 4: request.getRequestDispatcher("/myStudy/freeBoardList").forward(request); return;
      //case 5:  MyStudyCheating.cheat() ;  // 임시로 넣었음
      case 6: request.getRequestDispatcher("/myStudy/exit").forward(request); return;  
      case 7: request.getRequestDispatcher("/myStudy/update").forward(request); return;
      case 8: request.getRequestDispatcher("/myStudy/delete").forward(request); return;
      default : return;
    }

  }

}
