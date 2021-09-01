package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class NewStudyHandler {

  //------------------------------------------------------------------------------------------------
  List<Study> studyList;

  public NewStudyHandler(List<Study> studyList) {
    this.studyList = studyList;
  }
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  public void add() {
    System.out.println("[스터디 등록]");
    Study study = new Study();

    study.setStudyNo(Prompt.inputInt("번호? "));
    study.setStudyTitle(Prompt.inputString("스터디명? "));
    // 0831 eun 추가함
    study.setOwner(LoginHandler.getLoginUser());
    study.setSubject(Prompt.inputString("분야? "));
    study.setArea(Prompt.inputString("지역? "));
    study.setLocation(Prompt.inputString("장소? "));
    study.setNumberOfPeple(Prompt.inputInt("인원수? "));
    study.setFace(Prompt.inputString("대면? "));
    study.setIntroduction(Prompt.inputString("소개글? "));

    studyList.add(study);
  }
  //------------------------------------------------------------------------------------------------




  //------------------------------------------------------------------------------------------------
  public void list() {
    System.out.println("[스터디 목록]");

    Study[] list = studyList.toArray(new Study[0]);

    for (Study study : list) {
      System.out.printf("%s, %s, %s, %s, %s, %s, %d, %s, %s\n",

          // 0831 eun 추가함
          study.getStudyNo(),
          study.getStudyTitle(),
          study.getOwner().getPerNickname(),
          // --------------------------
          study.getArea(), 
          study.getLocation(), 
          study.getSubject(), 
          study.getNumberOfPeple(),
          study.getStudyTitle(),
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
    System.out.printf("장소: %s\n", study.getLocation());
    System.out.printf("인원수: %d\n", study.getNumberOfPeple());
    System.out.printf("대면: %s\n", study.getFace());
    System.out.printf("소개글: %s\n", study.getIntroduction());
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

    // 0831 eun 추가함
    if (study.getOwner().getPerNo() != LoginHandler.getLoginUser().getPerNo()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String studyTitle = Prompt.inputString("스터디명(" + study.getStudyTitle()  + ")? ");
    String location = Prompt.inputString("장소(" + study.getLocation() + ")? ");
    String face = Prompt.inputString("대면(" + study.getFace() + ")? ");
    String introduction = Prompt.inputString("소개글(" + study.getIntroduction() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("스터디 변경을 취소하였습니다.");
      return;
    }

    study.setStudyTitle(studyTitle);
    study.setLocation(location);
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

    // 0831 eun 추가함
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
  // 0831 eun 수정함
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