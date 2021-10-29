package com.ogong.pms.handler.myStudy.guilder;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class GuilderDeleteHandler implements Command {

  StudyDao studyDao;
  SqlSession sqlSession;

  public GuilderDeleteHandler(StudyDao studyDao, SqlSession sqlSession) {
    this.studyDao = studyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 구성원 탈퇴시키기");
    System.out.println();

    int inputNo = (int) request.getAttribute("inputNo");

    Study myStudy = studyDao.findByNo(inputNo);

    List<Member> guilders = studyDao.findByGuildersAll(myStudy.getStudyNo());
    myStudy.setMembers(guilders);

    if (myStudy.getMembers().isEmpty()) {
      System.out.println(" >> 탈퇴시킬 구성원이 없습니다.");
      return;
    }

    System.out.printf("구성원 : %s\n", myStudy.getMemberNames());
    System.out.println();
    String inputGuilderName = Prompt.inputString(" >> 탈퇴시킬 구성원의 닉네임을 입력하세요 : ");

    for (Member guilderMember : myStudy.getMembers()) { 

      if (guilderMember.getPerNickname().equals(inputGuilderName)) {
        String input = Prompt.inputString("\n 정말 탈퇴시키겠습니까? (네 / 아니오) ");

        if (!input.equalsIgnoreCase("네")) {
          System.out.println("\n >> 구성원 탈퇴를 취소하였습니다.");
          return;
        }

        try {
          studyDao.updateGuilderExpulsion(myStudy.getStudyNo(), guilderMember.getPerNo());
          sqlSession.commit();
        } catch (Exception e) {
          System.out.println(" 구성원 탈퇴 오류!");
          sqlSession.rollback();
        }
        System.out.println("\n >> 구성원이 탈퇴되었습니다.");
        return;
      }
    } 
    System.out.println("\n >> 해당 구성원이 존재하지 않습니다.");
    return;
  }
}