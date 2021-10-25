package com.ogong.pms.handler.study.bookMark;

import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class StudyBookMarkDetailHandler implements Command {

  StudyDao studyDao;

  public StudyBookMarkDetailHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 내 북마크 - 스터디 상세\n");

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    int inputNo;
    inputNo = Prompt.inputInt(" 번호 : ");

    Study study = studyDao.findByBookmark(inputNo, member.getPerNo());

    if (study == null) {
      System.out.println(" >> 내가 북마크한 스터디 번호를 입력하세요.");
      return;
    }

    System.out.printf(" \n (%s) 🌟%d\n", study.getStudyNo(), study.getBookMarkMember().size());
    System.out.printf(" [%s]\n", study.getStudyTitle());
    System.out.printf(" >> 조장 : %s\n", study.getOwner().getPerNickname());
    System.out.printf(" >> 분야 : %s\n", study.getSubjectName());
    System.out.printf(" >> 지역 : %s\n", study.getArea());
    System.out.printf(" >> 인원수 : %s/%s명\n",
        study.getMembers().size() + 1, study.getNumberOfPeple());
    System.out.printf(" >> 대면 : %s\n", study.getFaceName());
    System.out.printf(" >> 소개글 : %s\n", study.getIntroduction());

    request.setAttribute("inputNo", study.getStudyNo());

    System.out.println("\n----------------------");
    System.out.println("1. 참여 신청하기");
    System.out.println("2. 북마크 삭제");
    System.out.println("0. 이전");

    while (true) {
      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1: request.getRequestDispatcher("/study/join").forward(request); return;
        case 2: request.getRequestDispatcher("/study/bookMarkDelete").forward(request); return;
        case 0: return;
        default : 
          System.out.println(" >> 번호를 다시 선택해 주세요.\n");
      }
    }
  }
}