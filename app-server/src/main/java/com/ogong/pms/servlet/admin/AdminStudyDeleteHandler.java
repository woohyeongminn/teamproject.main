package com.ogong.pms.servlet.admin;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminStudyDeleteHandler implements Command {

  StudyDao studyDao;
  SqlSession sqlSession;

  public AdminStudyDeleteHandler(StudyDao studyDao, SqlSession sqlSession) {
    this.studyDao = studyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 삭제");

    int inputNo = Prompt.inputInt(" 번호 : ");
    Study study = studyDao.findByNo(inputNo);
    if (study == null) {
      System.out.println(" >> 해당 번호의 스터디가 존재 하지않습니다.");
      return;
    }

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
    study.setWatingMember(waitingGuilder);
    List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
    study.setMembers(guilders);

    System.out.println();
    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디 삭제를 취소하였습니다.");
      request.getRequestDispatcher("/study/list").forward(request);
      return;
    }

    try {

      //      studyDao.deleteAllGuilder(study.getStudyNo());
      //      studyDao.deleteAllBookmark(study.getStudyNo());
      studyDao.updateStatusDelete(study);
      sqlSession.commit();
      System.out.println(" >> 스터디를 삭제하였습니다.");
    } catch (Exception e) {
      System.out.println(" 스터디 삭제 오류!");
      sqlSession.rollback();
    }
    request.getRequestDispatcher("/study/list").forward(request);
  }
}