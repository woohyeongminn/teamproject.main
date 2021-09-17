//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public abstract class AbstractStudyHandler implements Command {

  List<Study> studyList;

  public AbstractStudyHandler(List<Study> studyList) {
    this.studyList = studyList;
  }

  protected void printStudyList() {
    for (Study study : studyList) {
      System.out.printf(" (%d)\n 스터디명 : %s\n 분류 : %s\n 인원수 : %s/%s명\n 조장 : %s\n 대면/비대면 : %s\n",
          study.getStudyNo(),
          study.getStudyTitle(),
          study.getSubject(),
          study.getMembers().size() + 1,
          study.getNumberOfPeple(),
          study.getOwner().getPerNickname(),
          study.getFace());
      System.out.println();
    }
  }

  // 스터디 가입
  protected void joinStudy(Study study) {
    System.out.println();
    System.out.println("▶ 스터디 신청");
    System.out.println();

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    for (Member pM : study.getMembers()) {
      if (pM.getPerNickname().equals(member.getPerNickname())) {
        System.out.println(" >> 이미 참여 중인 스터디입니다.");
        return;
      }
    }

    for (Member memberWating : study.getWatingMember()) {
      if (member.getPerNickname().equals(memberWating.getPerNickname())) {
        System.out.println(" >> 이미 승인 대기 중인 스터디입니다.");
        return;
      }
    }

    if(study.getMembers().size() == (study.getNumberOfPeple() - 1)) {
      System.out.println(" >> 참여 가능 인원수를 초과하였습니다.");
      return;
    }

    String input = Prompt.inputString(" 스터디에 참여하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase(" 네")) {
      System.out.println(" >> 참여 신청이 취소되었습니다.");
      return;
    }
    study.getWatingMember().add(member);
    System.out.println();
    System.out.println(" >> 참여 신청이 완료되었습니다.\n   승인이 완료될 때까지 기다려 주세요.");
  }

  // 전체 스터디에서 번호로 찾기
  protected Study findByNo(int inputNo) {
    for (Study study : studyList) {
      if (study.getStudyNo() == inputNo) {
        return study;
      }
    }
    return null;
  }

  // 내 스터디에서만 번호 찾기
  protected Study findByMyStudyNo(int inputNo) {

    Member member = AuthPerMemberLoginHandler.getLoginUser();

    try {
      for (int i = 0; i < studyList.size(); i++) {
        if (studyList.get(i).getMemberNames().contains(member.getPerNickname()) ||
            studyList.get(i).getOwner().getPerNickname().equals(member.getPerNickname())) {
          return findByNo(inputNo);
        }
      }
    } catch (Exception e) {
      System.out.println(" >> 해당 번호의 스터디가 없습니다.");
    }
    return null;
  }

}