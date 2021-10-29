package com.ogong.pms.handler.study;

import java.util.List;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;

public class StudyListHandler implements Command {

  StudyDao studyDao;

  public StudyListHandler(StudyDao studyDao) {
    this.studyDao = studyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디 목록");
    System.out.println();

    List<Study> studyList = studyDao.findAll();

    if (studyList.isEmpty()) {
      System.out.println(" >> 스터디 목록이 없습니다.");  
      return;
    }

    for (Study study : studyList) {

      if (study.getStudyTitle().contains("탈퇴")) {
        System.out.printf(" (%d)\n 스터디명 : %s\n", study.getStudyNo(), study.getStudyTitle());
        System.out.println();
      }

      else {
        System.out.printf(
            " (%d) 🌟%d \n [%s] | %s | 조장 : %s | 분야 : %s | 지역 : %s | 인원수 : %s/%s명\n",
            study.getStudyNo(),
            study.getCountBookMember(),
            study.getStudyTitle(),
            study.getFaceName(),
            study.getOwner().getPerNickname(),
            study.getSubjectName(),
            study.getArea(),
            study.getCountMember(),
            study.getNumberOfPeple()
            );
        System.out.println();
      }
    }
  }
}
