//  수정 중.....

package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public abstract class AbstractStudyHandler implements Command {

  List<Study> studyList;

  public AbstractStudyHandler(List<Study> newStudyList) {
    this.studyList = newStudyList;
  }

  protected void printStudyList(Study newStudy) {
    for (Study study : studyList) {
      System.out.printf("%d, %s, %s명, %s, %s, %s\n",
          study.getStudyNo(),
          study.getSubject(),
          study.getNumberOfPeple(),
          study.getStudyTitle(),
          study.getOwner().getPerNickname(),
          study.getFace());
    }
  }

  protected void detail() {
    System.out.println();
    System.out.println("▶ 스터디 상세");

    String inputTitle = Prompt.inputString("제목 : ");

    Study study = findByTitle(inputTitle);

    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    System.out.printf("스터디명 : %s\n", study.getStudyTitle());
    System.out.printf("조장 : %s\n", study.getOwner().getPerNickname());
    System.out.printf("분야 : %s\n", study.getSubject());
    System.out.printf("지역 : %s\n", study.getArea());
    System.out.printf("인원수 : %d\n", study.getNumberOfPeple());
    System.out.printf("대면 : %s\n", study.getFace());
    System.out.printf("소개글 : %s\n", study.getIntroduction());

    if (AuthPerMemberLoginHandler.loginUser != null) {

      if (study.getOwner().getPerNickname().equals(AuthPerMemberLoginHandler.loginUser.getPerNickname())) {
        System.out.println();
        System.out.println();
        System.out.println("1. 구성원보기");
        System.out.println("0. 뒤로가기");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : listMember(study); break;
          case 2 : return;
          default : return;
        }
      } else {
        System.out.println();
        System.out.println("1. 참여 신청하기");
        System.out.println("2. 구성원보기");
        System.out.println("0. 뒤로가기");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : joinStudy(study); break;
          case 2 : listMember(study); break;
          default : return;
        }
      }

    }
  }

  protected void listMember(Study study) {
    System.out.println();
    System.out.println("▶ 구성원 보기");
    Member member = AuthPerMemberLoginHandler.getLoginUser();

    if (member == null ) {
      System.out.println("로그인 한 회원만 조회 가능합니다.");
      return;
    }

    System.out.println("▼▼스터디 구성원▼▼");
    System.out.println("조장 : " + study.getOwner().getPerNickname());
    System.out.println(study.getMemberNames());

    System.out.println();
    System.out.println("▼▼승인 대기중▼▼");
    System.out.println(study.getWatingMemberNames());

    List<Member> waitingMembers = study.getWatingMember();

    if (member != null && study.getOwner().getPerEmail().equals(member.getPerEmail())) {
      System.out.println();
      if (!study.getWatingMemberNames().equals("")) {
        String input = Prompt.inputString("대기중인 회원 중 승인할 닉네임을 입력하세요 : ");
        Member m = new Member();

        for (Member watingMember : waitingMembers) {        
          if (watingMember.getPerNickname().equals(input)) {
            study.getMembers().add(watingMember);
            System.out.printf("%s님이 구성원으로 승인되었습니다.", watingMember.getPerNickname());
            List<Study> studyList = watingMember.getPerMyStudy();
            studyList.add(study);
            m = watingMember;
          }
        }
        if (m != null) {
          study.getWatingMember().remove(m);
        }
        return;
      }
    }
  }

  protected void joinStudy(Study study) {
    System.out.println();
    System.out.println("▶ 스터디 신청");
    Member member = AuthPerMemberLoginHandler.getLoginUser();

    if (member == null) {
      System.out.println("로그인 한 회원만 신청 가능합니다.");
      return;
    }

    if(study.getOwner().getPerNickname().equals(member.getPerNickname())) {
      System.out.println("참여중인 조장은 신청할 수 없습니다.");
      return;
    }

    for (Study myStudy : member.getPerMyStudy()) {
      if (myStudy.getStudyTitle().equals(study.getStudyTitle())) {
        System.out.println("이미 참여중인 스터디입니다.");
        return;
      }
    }

    for (Member memberStu : study.getWatingMember()) {
      if (memberStu.getPerMyStudy().equals(study.getStudyTitle())) {
        System.out.println("이미 승인대기중인 스터디입니다.");
        return;
      }
    }

    if(study.getMembers().size() == (study.getNumberOfPeple() - 1)) {
      System.out.println("참여 가능 인원수를 초과하였습니다.");
      return;
    }

    String input = Prompt.inputString("스터디에 참여하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("참여 신청이 취소되었습니다.");
      return;
    }
    study.getWatingMember().add(member);
    System.out.println();
    System.out.println("참여 신청이 완료 되었습니다.\n승인이 완료될때까지 기다려주세요.");
  }


  // 제목으로 찾기
  protected Study findByTitle (String title) {
    for (Study study : studyList) {
      if (study.getStudyTitle().equalsIgnoreCase(title)) {
        return study;
      }
    }
    return null;
  }

  // 소개글로 찾기
  protected Study findByIntro (String intro) {
    for (Study study : studyList) {
      if (study.getIntroduction().equalsIgnoreCase(intro)) {
        return study;
      }
    }
    return null;
  }


}