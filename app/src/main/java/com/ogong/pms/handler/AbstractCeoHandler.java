package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public abstract class AbstractCeoHandler implements Command {

  List<CeoMember> ceoMemberList;

  public AbstractCeoHandler(List<CeoMember> ceoMemberList) {
    this.ceoMemberList = ceoMemberList;
  }

  public void detail() {
    System.out.println();
    System.out.println("▶ 기업회원 상세");
    int inputceoEmail = Prompt.inputInt(" 번호 : ");
    System.out.println();

    CeoMember ceoMember = findByCeoMemberNo(inputceoEmail);

    if (ceoMember == null) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    System.out.printf(" [%s]\n", ceoMember.getCeoBossName());
    System.out.printf(" >> 점포명 : %s\n", ceoMember.getCeoEmail());
    System.out.printf(" >> 이메일 : %s\n", ceoMember.getCeoEmail());
    //System.out.printf(" >> 점포주소 : %s\n", ceoMember.getCeoStoreDetailAddress());
    //System.out.printf(" >> 사진 : %s\n", ceoMember.getCeoStoreName());
    System.out.printf(" >> 가입일 : %s\n", ceoMember.getCeoregisteredDate());
  }

  public void update() {
    System.out.println();
    System.out.println("▶ 기업회원 변경"); 
    int inputceoEmail = Prompt.inputInt(" 번호 : ");
    System.out.println();

    CeoMember ceoMember = findByCeoMemberNo(inputceoEmail);

    if (ceoMember == null) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    String ceoBossName = Prompt.inputString(" 대표자명(" + ceoMember.getCeoBossName()  + ") : ");
    String ceoEmail = Prompt.inputString(" 이메일(" + ceoMember.getCeoEmail() + ") : ");
    String ceoPassword = Prompt.inputString(" 비밀번호? ");
    //String ceoStoreName = Prompt.inputString(" 점포명(" + ceoMember.getCeoStoreName() + ") : ");
    //String ceoStoreDetailAddress = Prompt.inputString("점포주소(" + ceoMember.getCeoStoreDetailAddress() + ") : ");

    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 기업 회원 변경을 취소하였습니다.");
      return;
    }

    ceoMember.setCeoBossName(ceoBossName);
    ceoMember.setCeoEmail(ceoEmail);
    //ceoMember.setCeoPassword(ceoStoreName);
    //ceoMember.setCeoPhoto(ceoStoreDetailAddress);

    System.out.println(" >> 기업 회원 정보를 변경하였습니다.");
  }

  public void delete() {
    System.out.println();
    System.out.println("▶ 기업회원 탈퇴");
    int inputceoEmail = Prompt.inputInt(" 번호 : ");
    System.out.println();

    CeoMember ceoMember = findByCeoMemberNo(inputceoEmail);

    if (ceoMember == null) {
      System.out.println(" >> 해당 번호의 기업 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString(" 정말 탈퇴하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 기업 회원 탈퇴를 취소하였습니다.");
      return;
    }

    ceoMemberList.remove(ceoMember);

    System.out.println(" >> 기업 회원이 탈퇴되었습니다.");
  }


  public CeoMember findByEmail(String ceoEmail) {
    for (CeoMember ceoMember : ceoMemberList) {
      if (ceoMember.getCeoEmail().equals(ceoEmail)) {
        return ceoMember;
      }
    }
    return null;
  }



  public CeoMember findByCeoMemberNo(int inputceoNo) {
    for (CeoMember ceo : ceoMemberList) {
      if (inputceoNo == ceo.getCeoNo()) {
        return ceo;
      }
    }
    return null;
  }


  //-------------prompt는 login에서 사용------------------------------------------------
  public boolean exist(String ceoEmail) {
    CeoMember[] list = ceoMemberList.toArray(new CeoMember[0]);
    for (CeoMember ceoMember : list) {
      if (ceoMember.getCeoEmail().equals(ceoEmail)) {
        return true;
      }
    }
    return false;
  }

  public String promptCeoEmail(String ceoEmail) {
    while (true) {
      String ceoEmailBox = Prompt.inputString(ceoEmail);
      if (this.exist(ceoEmailBox)) {
        return ceoEmailBox;
      } else if (ceoEmailBox.length() == 0) {
        return null;
      }
      System.out.println(" >> 등록된 기업 회원이 아닙니다.");
    }
  }

  public String promptMemberPassword(String ceoPassWord) {
    while (true) {
      String ceoPasswordBox = Prompt.inputString(ceoPassWord);
      if (this.exist(ceoPasswordBox)) {
        return ceoPasswordBox;
      } else if (ceoPasswordBox.length() == 0) {
        return null;
      }
      System.out.println(" >> 등록된 기업 회원이 아닙니다.");
    }
  }




  //-------------카페------------------------------------------------

  protected static String getCafeStatusLabel(int status) {
    switch (status) {
      case 0: return "승인대기";
      case 1: return "운영중";
      case 2: return "운영중단";
      case 3: return "삭제";
      default: return "오류";
    }
  }

  protected static int promptCafeStatus(int status) {

    System.out.printf(" 상태(%s) : \n", getCafeStatusLabel(status));
    int input = 0;
    do {
      System.out.println(" 1: 운영중");
      System.out.println(" 2: 운영중단");
      input = Prompt.inputInt(" > ");

      if (input != 1 && input != 2) {
        System.out.println(" >> 번호를 잘못 선택하셨습니다.\n    다시 입력해 주세요.\n");
      }
    } while (input != 1 && input != 2);

    return input;
  }
}







