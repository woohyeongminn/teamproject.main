package com.ogong.pms.handler.member;

import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;
import com.ogong.util.RandomPw;
import com.ogong.util.SendMail;

public class CeoFindIdPwHandler implements Command {

  RandomPw randomPw;
  CeoMemberDao ceoMemberDao;

  public CeoFindIdPwHandler(RandomPw randomPw, CeoMemberDao ceoMemberDao) {
    this.randomPw = randomPw;
    this.ceoMemberDao = ceoMemberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("1. 이메일 찾기");
    System.out.println("2. 비밀번호 찾기");

    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : wantCeoEmail(); break;

      case 2 : 
        System.out.println();
        while (true) {
          String inputName =  Prompt.inputString(" 이름 : ");

          if (inputName.equals("")) {
            return;
          }

          CeoMember ceoMember = ceoMemberDao.findByName(inputName);

          if (ceoMember != null) {
            String inputTel =  Prompt.inputString(" 전화번호 : ");

            if (!ceoMember.getCeoTel().equals(inputTel)) {
              System.out.println(" >> 회원 이름과 전화번호가 일치하지 않습니다. (종료:엔터)\n");
              continue;
            }

            wantCeoPw(ceoMember.getCeoName());
            break;
          }

          System.out.println(" >> 해당 이름이 존재하지 않습니다. (종료:엔터)\n");
          continue;
        }

      default : return;
    }
  }

  public void wantCeoEmail() throws Exception {
    System.out.println();
    System.out.println("▶ 이메일 찾기\n");

    while (true) {
      String inputName =  Prompt.inputString(" 이름 : ");

      if (inputName.equals("")) {
        return;
      }

      CeoMember ceoMember = ceoMemberDao.findByName(inputName);

      if (ceoMember != null) {
        String inputTel =  Prompt.inputString(" 전화번호 : ");

        if (!ceoMember.getCeoTel().equals(inputTel)) {
          System.out.println(" >> 회원 이름과 전화번호가 일치하지 않습니다. (종료:엔터)\n");
          continue;
        }

        System.out.println();
        System.out.printf(" '%s님'의 이메일 >> ", ceoMember.getCeoName());
        System.out.println(ceoMember.getCeoEmail());

      } else {
        System.out.println(" >> 해당 이름이 존재하지 않습니다. (종료:엔터)\n");
        continue;
      }

      String input = Prompt.inputString(" 비밀번호 찾기로 넘어가시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 찾기를 종료합니다.");
        return;
      } 
      wantCeoPw(ceoMember.getCeoName());
      return;
    }
  }

  public void wantCeoPw(String ceoName) throws Exception {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급\n");

    while (true) {
      CeoMember ceoMember = ceoMemberDao.findByName(ceoName);

      if (ceoMember != null) {
        String inputEmail =  Prompt.inputString(" 이메일 : ");

        // 여기가 안먹힘
        if (!ceoMember.getCeoEmail().equals(inputEmail)) {
          System.out.println(" >> 본인의 이메일을 입력하세요. (종료:엔터)\n");
          continue;
        }
        SendMail sendMail = new SendMail();

        String pw = randomPw.randomPw();
        ceoMember.setCeoPassword(pw);
        System.out.println(" >> 처리 중입니다. 잠시만 기다려 주세요.");
        sendMail.sendMail(inputEmail, pw);
        System.out.println();
        System.out.printf(" '%s님'의 임시 비밀번호가 메일로 전송되었습니다.\n", ceoMember.getCeoNickname());
        System.out.println(" >> 로그인 후 비밀번호를 변경해 주세요.");
        ceoMemberDao.updatePassword(ceoMember);
        return;

      } else {
        System.out.println(" >> 해당 이름이 존재하지 않습니다. (종료:엔터)\n");
        continue;
      }
    }
    //}
  }
}
