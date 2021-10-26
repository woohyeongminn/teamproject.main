package com.ogong.pms.handler.study.bookMark;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class StudyBookMarkListHandler implements Command {

  StudyDao studyDao;

  public StudyBookMarkListHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 내 북마크 - 스터디 목록");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();
    if (member == null ) {
      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
      return;
    }

    List<Study> studyList = studyDao.findByMyBookmark(member.getPerNo());

    int count = 0;

    for (Study study : studyList) {
      // 북마크 있는 경우

      if (study.getStudyTitle().contains("탈퇴")) {
        System.out.printf(" (%d)\n 스터디명 : %s\n",
            study.getStudyNo(),
            study.getStudyTitle());
        System.out.println();
      }
      //      List<Member> bookMem = studyDao.findByBookmarkAll(study.getStudyNo());
      //      study.setBookMarkMember(bookMem);
      System.out.printf(" (%d) 🌟%d \n [%s] | 분야 : %s | 인원수 : %s/%s명 | 조장 : %s | 대면/비대면 : %s\n",
          study.getStudyNo(),
          study.getCountBookMember(),
          study.getStudyTitle(),
          study.getSubjectName(),
          study.getCountMember(),
          study.getNumberOfPeple(),
          study.getOwner().getPerNickname(),
          study.getFaceName());
      System.out.println();
      count++;
    }

    if (count == 0) {
      System.out.println(" >> 북마크한 스터디가 없습니다.");
      return;
    }

    System.out.println("----------------------");
    System.out.println("1. 상세");
    System.out.println("0. 이전");

    while (true) {
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1:request.getRequestDispatcher("/study/bookMarkDetail").forward(request); return;
        case 0: return;
        default : 
          System.out.println(" >> 번호를 다시 선택해 주세요.\n");
      }
    }
  }
}
