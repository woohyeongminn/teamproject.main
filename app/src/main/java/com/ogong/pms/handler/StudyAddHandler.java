//  수정 중.....

package com.ogong.pms.handler;

import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class StudyAddHandler extends AbstractStudyHandler {

  PromptPerMember promptPerMember;

  public StudyAddHandler(List<Study> newStudyList, PromptPerMember promptPerMember) {
    super(newStudyList);
    this.promptPerMember = promptPerMember;

    Study testStudy = new Study();
    testStudy.setStudyNo(1);
    testStudy.setStudyTitle("삼성 NCS 뿌셔뿌셔");
    testStudy.setOwner(promptPerMember.memberList.get(0));
    testStudy.setSubject("취업");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(5);
    testStudy.setFace("대면");
    testStudy.setIntroduction("취업 뿌셔뿌셔");
    testStudy.setMembers(new ArrayList<>());
    testStudy.setWatingMember(new ArrayList<>());
    newStudyList.add(testStudy);
    promptPerMember.memberList.get(0).getPerMyStudy().add(testStudy);

    testStudy = new Study();
    testStudy.setStudyNo(2);
    testStudy.setStudyTitle("하반기 삼성 공모전");
    testStudy.setOwner(promptPerMember.memberList.get(1));
    testStudy.setSubject("공모전");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(6);
    testStudy.setFace("대면/비대면");
    testStudy.setIntroduction("공모전 아자");
    testStudy.setMembers(new ArrayList<>());
    testStudy.setWatingMember(new ArrayList<>());
    newStudyList.add(testStudy);
    promptPerMember.memberList.get(1).getPerMyStudy().add(testStudy);

    testStudy = new Study();
    testStudy.setStudyNo(3);
    testStudy.setStudyTitle("중앙대 컴공 기말고사");
    testStudy.setOwner(promptPerMember.memberList.get(2));
    testStudy.setSubject("기말고사");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(3);
    testStudy.setFace("대면/비대면");
    testStudy.setIntroduction("시험 아자");
    testStudy.setMembers(new ArrayList<>());
    testStudy.setWatingMember(new ArrayList<>());
    newStudyList.add(testStudy);
    promptPerMember.memberList.get(2).getPerMyStudy().add(testStudy);

    testStudy = new Study();
    testStudy.setStudyNo(1);
    testStudy.setStudyTitle("알고리즘 스터디");
    testStudy.setOwner(promptPerMember.memberList.get(3));
    testStudy.setSubject("IT");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(2);
    testStudy.setFace("비대면");
    testStudy.setIntroduction("지옥같은 SI 탈출");
    testStudy.setMembers(new ArrayList<>());
    testStudy.setWatingMember(new ArrayList<>());
    newStudyList.add(testStudy);
    promptPerMember.memberList.get(3).getPerMyStudy().add(testStudy);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 스터디 등록");
    Study study = new Study();

    study.setStudyNo(Prompt.inputInt("번호 : "));
    study.setStudyTitle(Prompt.inputString("스터디명 : "));
    study.setOwner(AuthPerMemberLoginHandler.getLoginUser());
    study.setSubject(Prompt.inputString("분야 : "));
    study.setArea(Prompt.inputString("지역 : "));
    study.setNumberOfPeple(Prompt.inputInt("인원수 : "));
    study.setFace(Prompt.inputString("대면 : "));
    study.setIntroduction(Prompt.inputString("소개글 : "));
    study.setMembers(new ArrayList<>());
    study.setWatingMember(new ArrayList<>());

    System.out.println();
    String input = Prompt.inputString("등록하시겠습니까? (네 / 아니오)");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("등록이 취소되었습니다.");
    }
    System.out.println("스터디가 등록되었습니다.");
    studyList.add(study);
  }
}