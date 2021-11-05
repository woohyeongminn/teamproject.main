package com.ogong.pms.servlet.myStudy;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class MyStudyGuilderDetailController implements Command {

  StudyDao studyDao;

  public MyStudyGuilderDetailController(StudyDao studyDao) {
    this.studyDao = studyDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 내 스터디 상세");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    int studyNo = Prompt.inputInt(" 번호  : ");

    Study myStudy = studyDao.findByMyNo(studyNo, member.getPerNo());

    if (myStudy == null) {
      System.out.println();
      System.out.println(" >> 스터디 번호가 일치하지 않습니다.");
      return;
    } 

    List<Member> guilders = studyDao.findByGuildersAll(myStudy.getStudyNo());
    myStudy.setMembers(guilders);


    System.out.printf( " (%d)", myStudy.getStudyNo());

    if(myStudy.getCountMember() != myStudy.getNumberOfPeple()) {
      System.out.printf(" [모집중] " );
    } else {
      System.out.printf(" [모집완료] " );
    }

    System.out.printf("🌟%d\n", myStudy.getCountBookMember());
    System.out.printf(" [%s]\n", myStudy.getStudyTitle());
    System.out.printf(" >> 조장 : %s\n", myStudy.getOwner().getPerNickname());
    System.out.printf(" >> 분야 : %s\n", myStudy.getSubjectName());
    System.out.printf(" >> 지역 : %s\n", myStudy.getArea());
    System.out.printf(" >> 인원수 : %s/%s명\n",
        myStudy.getCountMember(), myStudy.getNumberOfPeple());
    System.out.printf(" >> 대면 : %s\n", myStudy.getFaceName());
    System.out.printf(" >> 소개글 : %s\n", myStudy.getIntroduction());
    System.out.printf(" >> 활동점수 : %d\n", myStudy.getPoint());

    System.out.println("\n----------------------");
    System.out.println("1. 구성원");
    System.out.println("2. 캘린더");
    System.out.println("3. To-do");
    System.out.println("4. 자유게시판");
    System.out.println("5. 화상미팅");
    System.out.println("6. 탈퇴하기");  

    System.out.println("0. 뒤로 가기");

    request.setAttribute("inputNo", myStudy.getStudyNo());

    int selectNo = Prompt.inputInt("선택> "); 
    switch (selectNo) {
      case 1: request.getRequestDispatcher("/myStudy/guilder").forward(request); return;
      case 2: request.getRequestDispatcher("/myStudy/calenderList").forward(request); return;
      case 3: request.getRequestDispatcher("/myStudy/todoList").forward(request); break;
      case 4: request.getRequestDispatcher("/myStudy/freeBoardList").forward(request); return;
      case 5: request.getRequestDispatcher("/myStudy/chat").forward(request); return;
      case 6: request.getRequestDispatcher("/myStudy/exit").forward(request); return;  
      case 7: request.getRequestDispatcher("/myStudy/update").forward(request); return;
      case 8: request.getRequestDispatcher("/myStudy/delete").forward(request); return;
      default : return;
    }
  }
}
