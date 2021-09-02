package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class StudyHandler {

  //------------------------------------------------------------------------------------------------
  List<Study> studyList;
  MemberHandler memberHandler;

  public StudyHandler(List<Study> newStudyList, MemberHandler memberHandler) {
    this.studyList = newStudyList;
    this.memberHandler = memberHandler;

    Study testStudy = new Study();
    testStudy.setStudyNo(1);
    testStudy.setStudyTitle("삼성 NCS 뿌셔뿌셔");
    testStudy.setOwner(memberHandler.memberList.get(0));
    testStudy.setSubject("취업");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(5);
    testStudy.setFace("대면");
    testStudy.setIntroduction("취업 뿌셔뿌셔");
    newStudyList.add(testStudy);

    testStudy = new Study();
    testStudy.setStudyNo(2);
    testStudy.setStudyTitle("하반기 삼성 공모전");
    testStudy.setOwner(memberHandler.memberList.get(1));
    testStudy.setSubject("공모전");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(6);
    testStudy.setFace("대면/비대면");
    testStudy.setIntroduction("공모전 아자");
    newStudyList.add(testStudy);

    testStudy = new Study();
    testStudy.setStudyNo(3);
    testStudy.setStudyTitle("중앙대 컴공 기말고사");
    testStudy.setOwner(memberHandler.memberList.get(2));
    testStudy.setSubject("기말고사");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(3);
    testStudy.setFace("대면/비대면");
    testStudy.setIntroduction("시험 아자");
    newStudyList.add(testStudy);

    testStudy = new Study();
    testStudy.setStudyNo(1);
    testStudy.setStudyTitle("알고리즘 스터디");
    testStudy.setOwner(memberHandler.memberList.get(3));
    testStudy.setSubject("IT");
    testStudy.setArea("서울");
    testStudy.setNumberOfPeple(2);
    testStudy.setFace("비대면");
    testStudy.setIntroduction("지옥같은 SI 탈출");
    newStudyList.add(testStudy);
  }
  //------------------------------------------------------------------------------------------------

  //------------------------------------------------------------------------------------------------
  public void add() {
    System.out.println();
    System.out.println("[스터디 등록]");
    Study study = new Study();

    study.setStudyNo(Prompt.inputInt("번호? "));
    study.setStudyTitle(Prompt.inputString("스터디명? "));
    study.setOwner(LoginHandler.getLoginUser());
    study.setSubject(Prompt.inputString("분야? "));
    study.setArea(Prompt.inputString("지역? "));
    study.setNumberOfPeple(Prompt.inputInt("인원수? "));
    study.setFace(Prompt.inputString("대면? "));
    study.setIntroduction(Prompt.inputString("소개글? "));

    studyList.add(study);
  }
  //------------------------------------------------------------------------------------------------



  //------------------------------------------------------------------------------------------------

  public void list() {
    System.out.println();
    System.out.println("[스터디 목록]");

    Study newStudy = new Study();
    printStudyList(newStudy);

    System.out.println("----------------------");
    System.out.println("1. 상세 보기");
    System.out.println("2. 뒤로가기");
    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : detail(); break;
      case 2 : System.out.println("뒤로가기"); break;
      default : return;

    }
  }
  private void printStudyList(Study newStudy) {
    System.out.printf("%d: ", newStudy.getStudyNo());
    for (Study study : studyList) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
          study.getStudyNo(),
          study.getSubject(),
          study.getNumberOfPeple(),
          study.getStudyTitle(),
          study.getOwner().getPerNickname(),
          study.getFace());
    }

  }
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  public void detail() {
    System.out.println("[스터디 상세보기]");

    String inputTitle = Prompt.inputString("제목? ");

    Study study = findByTitle(inputTitle);

    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    System.out.printf("스터디명: %s\n", study.getStudyTitle());
    System.out.printf("조장: %s\n", study.getOwner().getPerNickname());
    System.out.printf("분야: %s\n", study.getSubject());
    System.out.printf("지역: %s\n", study.getArea());
    System.out.printf("인원수: %d\n", study.getNumberOfPeple());
    System.out.printf("대면: %s\n", study.getFace());
    System.out.printf("소개글: %s\n", study.getIntroduction());

    System.out.println("1. 참여 신청하기");
    System.out.println("2. 뒤로가기");
    int selectNo = Prompt.inputInt("선택 ");
    switch (selectNo) {
      case 1 : System.out.println("참여 신청하기"); break;
      default : return;
    }
  }
  //------------------------------------------------------------------------------------------------


  //------------------------------------------------------------------------------------------------
  public void update() {
    System.out.println("[스터디 변경]");

    String inputTitle = Prompt.inputString("제목? ");

    Study study = findByTitle(inputTitle);

    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    if (study.getOwner().getPerNo() != LoginHandler.getLoginUser().getPerNo()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String studyTitle = Prompt.inputString("스터디명(" + study.getStudyTitle()  + ")? ");
    String face = Prompt.inputString("대면(" + study.getFace() + ")? ");
    String introduction = Prompt.inputString("소개글(" + study.getIntroduction() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("스터디 변경을 취소하였습니다.");
      return;
    }

    study.setStudyTitle(studyTitle);
    study.setFace(face);
    study.setIntroduction(introduction);

    System.out.println("스터디가 수정되었습니다.");
  }
  //------------------------------------------------------------------------------------------------


  //------------------------------------------------------------------------------------------------
  public void delete() {
    System.out.println("[스터디 삭제]");

    String inputTitle = Prompt.inputString("제목? ");

    Study study = findByTitle(inputTitle);

    if (study == null) {
      System.out.println("해당 제목의 스터디가 없습니다.");
      return;
    }

    if (study.getOwner().getPerNo() != LoginHandler.getLoginUser().getPerNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("스터디 삭제를 취소하였습니다.");
      return;
    }

    studyList.remove(study);

    System.out.println("스터디를 삭제하였습니다.");
  }
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  // 제목으로 찾기
  private Study findByTitle (String title) {
    for (Study study : studyList) {
      if (study.getStudyTitle().equalsIgnoreCase(title)) {
        return study;
      }
    }
    return null;
  }

  // 소개글로 찾기
  private Study findByIntro (String intro) {
    for (Study study : studyList) {
      if (study.getIntroduction().equalsIgnoreCase(intro)) {
        return study;
      }
    }
    return null;
  }


}