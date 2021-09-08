package com.ogong.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.util.Prompt;

public class CeoAddHandler extends AbstractCeoHandler {

  int ceoMemberNo = 3;

  public CeoAddHandler(List<CeoMember> ceoMemberList) {
    super(ceoMemberList);

    CeoMember ceoMember = new CeoMember();

    ceoMember.setCeoNo(1);
    ceoMember.setCeoEmail("study1");
    ceoMember.setCeoBossName("내가사장");
    ceoMember.setCeoPassword("1111");
    ceoMember.setCeoPhoto("a.jpg");
    ceoMember.setCeoLicenseNo("123-45-12345");
    ceoMember.setCeoBossName("김사장");
    ceoMember.setCeoregisteredDate(Date.valueOf("2020-12-10"));
    ceoMember.setCafes(new ArrayList<>());
    ceoMemberList.add(ceoMember);

    ceoMember = new CeoMember();

    ceoMember.setCeoNo(2);
    ceoMember.setCeoEmail("study2");
    ceoMember.setCeoBossName("오호라스터디");
    ceoMember.setCeoPassword("1111");
    ceoMember.setCeoPhoto("bbb.jpg");
    ceoMember.setCeoLicenseNo("123-45-67812");
    ceoMember.setCeoBossName("박사장");
    ceoMember.setCeoregisteredDate(Date.valueOf("2021-7-21"));
    ceoMember.setCafes(new ArrayList<>());
    ceoMemberList.add(ceoMember);

    ceoMember = new CeoMember();

    ceoMember.setCeoNo(3);
    ceoMember.setCeoEmail("study3");
    ceoMember.setCeoBossName("엄스네공부방");
    ceoMember.setCeoPassword("1111");
    ceoMember.setCeoPhoto("ccc.jpg");
    ceoMember.setCeoLicenseNo("123-45-48751");
    ceoMember.setCeoBossName("엄사장");
    ceoMember.setCeoregisteredDate(Date.valueOf("2021-5-1"));
    ceoMember.setCafes(new ArrayList<>());
    ceoMemberList.add(ceoMember);
  }

  // 개인
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 기업 회원가입");
    System.out.println();

    CeoMember ceoMember = new CeoMember();

    ceoMember.setCeoNo(ceoMemberNo++);
    ceoMember.setCeoEmail(Prompt.inputString(" 이메일 : "));
    ceoMember.setCeoBossName(Prompt.inputString(" 이 름 : "));
    ceoMember.setCeoPassword(Prompt.inputString(" 비밀번호 : "));
    while (true) {
      String pw =  Prompt.inputString(" 비밀번호 확인 : ");
      if (!pw.equals(ceoMember.getCeoPassword())) {
        System.out.println("  > 확인 실패!\n");
        continue;
      } else {
        System.out.println("  > 확인 완료!\n");
      }
      break;
    }
    ceoMember.setCeoLicenseNo(Prompt.inputString(" 사업자 등록번호 : "));
    ceoMember.setCeoBossName(Prompt.inputString(" 대표자명 : "));
    ceoMember.setCeoPhoto(Prompt.inputString(" 사진 : "));
    ceoMember.setCeoregisteredDate(new Date(System.currentTimeMillis()));
    ceoMember.setCafes(new ArrayList<>());

    ceoMemberList.add(ceoMember);

    System.out.println("가입이 완료되었습니다.");
  }
}







