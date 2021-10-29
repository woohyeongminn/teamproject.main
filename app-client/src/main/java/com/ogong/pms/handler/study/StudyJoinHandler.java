package com.ogong.pms.handler.study;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class StudyJoinHandler implements Command {

  StudyDao studyDao;
  SqlSession sqlSession;

  public StudyJoinHandler(StudyDao studyDao, SqlSession sqlSession) {
    this.studyDao = studyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 신청");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    int inputNo = (int) request.getAttribute("inputNo");

    Study study = studyDao.findByNo(inputNo);

    if (study.getStatus()==2) {
      System.out.println(" >> 완료된 스터디 입니다.");
      return;
    }
    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
    study.setWatingMember(waitingGuilder);

    List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
    study.setMembers(guilders);

    for (Member guilder : study.getMembers()) {
      if (guilder.getPerNo() == member.getPerNo()) {
        System.out.println(" >> 이미 참여 중인 스터디입니다.");
        return;
      }
    }

    for (Member memberWating : study.getWatingMember()) {
      if (memberWating.getPerNo() == member.getPerNo()) {
        System.out.println(" >> 이미 승인 대기 중인 스터디입니다.");
        return;
      }
    }

    if(study.getMembers().size() == study.getNumberOfPeple()) {
      System.out.println(" >> 참여 가능 인원수를 초과하였습니다.");
      return;
    }

    String input = Prompt.inputString(" 스터디에 참여하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 참여 신청이 취소되었습니다.");
      return;
    }
    study.getWatingMember().add(member);

    try {
      studyDao.insertGuilder(study.getStudyNo(),member.getPerNo());
      sqlSession.commit();
    } catch (Exception e) {
      System.out.println(" 참여 신청 오류!");
      sqlSession.rollback();
    }

    System.out.println();
    System.out.println(" >> 참여 신청이 완료되었습니다.\n   승인이 완료될 때까지 기다려 주세요.");
  }
}
