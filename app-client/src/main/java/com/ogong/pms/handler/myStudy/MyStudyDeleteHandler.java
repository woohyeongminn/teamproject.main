package com.ogong.pms.handler.myStudy;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class MyStudyDeleteHandler implements Command {

  StudyDao studyDao;
  SqlSession sqlSession;

  public MyStudyDeleteHandler(StudyDao studyDao, SqlSession sqlSession) {
    this.studyDao = studyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 삭제\n");

    int no = (int) request.getAttribute("inputNo");
    Member member = AuthPerMemberLoginHandler.getLoginUser();
    Study myStudy = studyDao.findByNo(no);

    if (myStudy.getOwner().getPerNo() != member.getPerNo()) {
      System.out.println(" >> 삭제 권한이 없습니다.");
      return;
    }

    // 참여중인 구성원이 있을 때
    if (myStudy.getOwner().getPerNo() == member.getPerNo() &&
        myStudy.getCountMember() > 0) {
      System.out.println(" >> 구성원이 있는 스터디는 삭제할 수 없습니다.");
      return;
    }

    // 승인 대기중인 구성원이 있을때
    if (myStudy.getOwner().getPerNo() == member.getPerNo() &&
        myStudy.getWatingCountMember() > 0) {

      System.out.println(" >> 승인 대기 중인 구성원이 없어야 스터디 삭제가 가능합니다.");
      String input = Prompt.inputString(" 승인 대기 중인 구성원을 모두 거절하시겠습니까? (네 / 아니오) ");

      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 스터디 삭제를 취소하였습니다.");
        return;
      }

      studyDao.deleteAllGuilder(myStudy.getStudyNo());
      System.out.println(" >> 구성원을 모두 삭제하였습니다.\n");
    }

    String input = Prompt.inputString(" 정말 스터디를 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디 삭제를 취소하였습니다.");
      return;
    }

    try {
      studyDao.deleteGuilder(myStudy.getStudyNo(), member.getPerNo());
      studyDao.deleteAllBookmark(myStudy.getStudyNo());
      studyDao.deleteStudy(myStudy.getStudyNo(), member.getPerNo());
      sqlSession.commit();
    } catch (Exception e) {
      System.out.println(" 스터디 삭제 오류!");
      sqlSession.rollback();
    }
    System.out.println(" >> 스터디가 삭제 되었습니다.");
  }
}