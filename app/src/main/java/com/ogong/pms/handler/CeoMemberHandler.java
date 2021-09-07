//  수정 중.....

package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoMemberHandler {

  int ceoMemberNo;
  List<CeoMember> ceoMemberList;

  public CeoMemberHandler(List ceoMemberList) {
    this.ceoMemberList = ceoMemberList;
  }

  public void add() {
    System.out.println();
    System.out.println("▶ 기업 회원가입");
    System.out.println();

    CeoMember ceoMember = new CeoMember();

    ceoMember.setCeoNo(ceoMemberNo++);
    ceoMember.setCeoEmail(Prompt.inputString(" 이메일 : "));
    ceoMember.setCeoName(Prompt.inputString(" 이름 : "));
    ceoMember.setCeoPassword(Prompt.inputString(" 암호 : "));
    ceoMember.setCeoPassword(Prompt.inputString(" 암호 확인 : "));
    ceoMember.setCeoLicenseNo(Prompt.inputString(" 사업자 등록번호 : "));
    ceoMember.setCeoBossName(Prompt.inputString(" 대표자명 : "));
    ceoMember.setCeoStoreName(Prompt.inputString(" 점포명 : "));
    ceoMember.setCeoStoreDetailAddress(Prompt.inputString(" 점포명 : "));
    ceoMember.setCeoPhoto(Prompt.inputString(" 사진 : "));
    ceoMember.setCeoregisteredDate(new Date(System.currentTimeMillis()));

    ceoMemberList.add(ceoMember);
  }

  public void list() {
    System.out.println();
    System.out.println("▶ 가입 확인");
    System.out.println();

    CeoMember[] list = ceoMemberList.toArray(new CeoMember[0]);

    for (CeoMember ceoMember : list) {
      System.out.println();
      System.out.printf(" (%d)\n 이름 : %s\n 이메일 : %s\n 대표자명 : %s\n 점포명 : %s\n 가입일 : %s\n",
          ceoMember.getCeoNo(),      // 해결필요
          ceoMember.getCeoName(), 
          ceoMember.getCeoEmail(),
          ceoMember.getCeoBossName(),
          ceoMember.getCeoStoreName(),
          ceoMember.getCeoregisteredDate());

      System.out.println();
    }
  }

  public void detail() {
    System.out.println();
    System.out.println("▶ 기업 상세");
    String inputceoEmail = Prompt.inputString("이메일 : ");
    System.out.println();

    CeoMember ceoMember = findByEmail(inputceoEmail);

    if (ceoMember == null) {
      System.out.println("해당 이메일의 기업회원이 없습니다.");
      return;
    }

    System.out.printf(" [%s]\n", ceoMember.getCeoName());
    System.out.printf(" >> 이메일 : %s\n", ceoMember.getCeoEmail());
    System.out.printf(" >> 대표자명 : %s\n", ceoMember.getCeoBossName());
    System.out.printf(" >> 점포명 : %s\n", ceoMember.getCeoEmail());
    System.out.printf(" >> 점포주소 : %s\n", ceoMember.getCeoStoreDetailAddress());
    System.out.printf(" >> 사진 : %s\n", ceoMember.getCeoStoreName());
    System.out.printf(" >> 가입일 : %s\n", ceoMember.getCeoregisteredDate());
  }

  public void update() {
    System.out.println();
    System.out.println("▶ 기업 정보 변경");
    String inputceoEmail = Prompt.inputString("이메일 : ");
    System.out.println();

    CeoMember ceoMember = findByEmail(inputceoEmail);

    if (ceoMember == null) {
      System.out.println("해당 이메일의 기업회원이 없습니다.");
      return;
    }

    String ceoName = Prompt.inputString(" 이름(" + ceoMember.getCeoName()  + ") : ");
    String ceoEmail = Prompt.inputString(" 이메일(" + ceoMember.getCeoEmail() + ") : ");
    String ceoPassword = Prompt.inputString(" 암호? ");
    String ceoStoreName = Prompt.inputString(" 점포명(" + ceoMember.getCeoStoreName() + ") : ");
    String ceoStoreDetailAddress = Prompt.inputString("점포주소(" + ceoMember.getCeoStoreDetailAddress() + ") : ");

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("기업회원 변경을 취소하였습니다.");
      return;
    }

    ceoMember.setCeoName(ceoName);
    ceoMember.setCeoEmail(ceoEmail);
    ceoMember.setCeoPassword(ceoStoreName);
    ceoMember.setCeoPhoto(ceoStoreDetailAddress);

    System.out.println("기업회원 정보를 변경하였습니다.");
  }

  public void delete() {
    System.out.println();
    System.out.println("▶ 회원 탈퇴");
    String inputceoEmail = Prompt.inputString("이메일 : ");
    System.out.println();
    CeoMember ceoMember = findByEmail(inputceoEmail);

    if (ceoMember == null) {
      System.out.println("해당 이메일의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 탈퇴하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;
    }

    ceoMemberList.remove(ceoMember);

    System.out.println("회원이 탈퇴되었습니다.");
  }



  public CeoMember findByEmail(String ceoEmail) {
    CeoMember[] arr = ceoMemberList.toArray(new CeoMember[0]);
    for (Object obj : arr) {
      CeoMember ceoMember = (CeoMember) obj;
      if (ceoMember.getCeoEmail().equals(ceoEmail)) {
        return ceoMember;
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
      System.out.println("등록된 회원이 아닙니다.");
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
      System.out.println("등록된 회원이 아닙니다.");
    }
  }


}







