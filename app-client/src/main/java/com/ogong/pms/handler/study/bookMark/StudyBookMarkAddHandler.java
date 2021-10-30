package com.ogong.pms.handler.study.bookMark;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class StudyBookMarkAddHandler implements Command {

  StudyDao studyDao;
  SqlSession sqlSession;

  public StudyBookMarkAddHandler(StudyDao studyDao, SqlSession sqlSession) {
    this.studyDao = studyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 내 북마크 - 스터디 추가");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    int inputNo = (int) request.getAttribute("inputNo");

    Study study = studyDao.findByNo(inputNo);

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
    study.setWatingMember(waitingGuilder);

    List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
    study.setMembers(guilders);

    List<Member> bookmark = studyDao.findByBookmarkAll(study.getStudyNo());
    study.setBookMarkMember(bookmark);

    String input = Prompt.inputString(" 이 스터디를 북마크하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 북마크가 취소되었습니다.");
      return;
    }

    try {
      studyDao.insertBookmark(study.getStudyNo(), member.getPerNo());
      sqlSession.commit();
    } catch (Exception e) {
      System.out.println("북마크 등록 실패!");
      sqlSession.rollback();
    }

    System.out.println();
    System.out.println(" >> 북마크가 완료되었습니다.");
  }
}
