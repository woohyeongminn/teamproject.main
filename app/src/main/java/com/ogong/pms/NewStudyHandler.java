package com.ogong.pms;

import java.util.List;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class NewStudyHandler {

  List<Study> studyList;

  public NewStudyHandler(List<Study> studyList) {
    this.studyList = studyList;
  }

  public void add() {
    System.out.println("[회원 등록]");
    Study study = new Study();

    study.setNo(Prompt.inputInt("번호? "));
    study.setTitle(Prompt.inputString("스터디명? "));
    study.setOwner(Prompt.inputString("작성자? "));
    study.setSubject(Prompt.inputString("분야? "));
    study.setArea(Prompt.inputString("지역? "));
    study.setLocation(Prompt.inputString("장소? "));
    study.setNumberOfPeple(Prompt.inputInt("인원수? "));
    study.setFace(Prompt.inputString("대면? "));
    study.setIntroduction(Prompt.inputString("소개글? "));

    studyList.add(study);
  }

  public void list() {
    System.out.println("[회원 목록]");

    Study[] list = studyList.toArray(new Study[0]);

    for (Study study : list) {
      System.out.printf("%s, %s, %s, %d, %s, %s\n", 
          study.getArea(), 
          study.getLocation(), 
          study.getSubject(), 
          study.getNumberOfPeple(),
          study.getTitle(),
          study.getFace());
    }
  }

  public void detail() {
    System.out.println("[회원 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Study study = findByNo(no);

    if (study == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    System.out.printf("스터디명: %s\n", study.getTitle());
    System.out.printf("조장: %s\n", study.getOwner());
    System.out.printf("분야: %s\n", study.getSubject());
    System.out.printf("지역: %s\n", study.getArea());
    System.out.printf("장소: %s\n", study.getLocation());
    System.out.printf("인원수: %d\n", study.getNumberOfPeple());
    System.out.printf("대면: %s\n", study.getFace());
    System.out.printf("소개글: %s\n", study.getIntroduction());
  }

  public void update() {
    System.out.println("[회원 변경]");
    int no = Prompt.inputInt("번호? ");

    Study study = findByNo(no);

    if (study == null) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    String title = Prompt.inputString("스터디명(" + study.getTitle()  + ")? ");
    String owner = Prompt.inputString("조장(" + study.getOwner() + ")? ");
    String location = Prompt.inputString("장소(" + study.getLocation() + ")? ");
    String face = Prompt.inputString("대면(" + study.getFace() + ")? ");
    String introduction = Prompt.inputString("소개글(" + study.getIntroduction() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("스터디 변경을 취소하였습니다.");
      return;
    }

    study.setTitle(title);
    study.setOwner(owner);
    study.setLocation(location);
    study.setFace(face);
    study.setIntroduction(introduction);

    System.out.println("스터디가 수정되었습니다.");
  }

  //  public void delete() {
  //    System.out.println("[회원 삭제]");
  //    int no = Prompt.inputInt("번호? ");
  //
  //    Study study = findByNo(no);
  //
  //    if (study == null) {
  //      System.out.println("해당 번호의 회원이 없습니다.");
  //      return;
  //    }
  //
  //    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
  //    if (input.equalsIgnoreCase("n") || input.length() == 0) {
  //      System.out.println("회원 삭제를 취소하였습니다.");
  //      return;
  //    }
  //
  //    studyList.remove(study);
  //
  //    System.out.println("회원을 삭제하였습니다.");
  //  }
  //
  private Study findByNo(int no) {
    Study[] arr = studyList.toArray(new Study[0]);
    for (Object obj : arr) {
      Study study = (Study) obj;
      if (study.getNo() == no) {
        return study;
      }
    }
    return null;
  }
}