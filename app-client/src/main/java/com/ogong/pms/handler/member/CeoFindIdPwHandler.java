package com.ogong.pms.handler.member;

import org.apache.ibatis.session.SqlSession;
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
  SqlSession sqlSession;

  public CeoFindIdPwHandler(RandomPw randomPw, CeoMemberDao ceoMemberDao, SqlSession sqlSession) {
    this.randomPw = randomPw;
    this.ceoMemberDao = ceoMemberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("1. 이메일 찾기");
    System.out.println("2. 비밀번호 찾기");

    int selectNo = Prompt.inputInt("선택> ");
    switch (selectNo) {
      case 1 : wantCeoEmail(); break;
      case 2 : wantCeoPw(); break;
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
          System.out.println(" >> 회원 이름과 전화번호가 일치하지 않습니다. (종료 : Enter)\n");
          continue;
        }

        System.out.println();
        System.out.printf(" '%s님'의 이메일 >> ", ceoMember.getCeoName());
        System.out.println(ceoMember.getCeoEmail());

      } else {
        System.out.println(" >> 해당 이름이 존재하지 않습니다. (종료 : Enter)\n");
        continue;
      }

      String input = Prompt.inputString(" 비밀번호 찾기로 넘어가시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 찾기를 종료합니다.");
        return;
      } 
      wantCeoPw();
      return;
    }
  }

  public void wantCeoPw() throws Exception {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급");

    SendMail sendMail = new SendMail();

    while (true) {
      System.out.println();
      System.out.println("1. 이메일로 발급");
      System.out.println("2. 전화번호로 발급");
      System.out.println("0. 이전");
      int selectNo = Prompt.inputInt("선택> ");

      switch (selectNo) {
        case 1:
          System.out.println();

          String inputName = Prompt.inputString(" 이름 : ");
          CeoMember ceoMemberName = ceoMemberDao.findByName(inputName);

          String inputEmail = Prompt.inputString(" 이메일 : ");
          CeoMember ceoMemberEmail = ceoMemberDao.findByEmail(inputEmail);

          if (ceoMemberName != null && ceoMemberEmail != null) {
            String pw = randomPw.randomPw();

            ceoMemberEmail.setCeoPassword(pw);
            System.out.println(" >> 처리 중입니다. 잠시만 기다려 주세요.\n");

            sendMail.sendMail(inputEmail, pw);
            System.out.printf(" '%s님'의 임시 비밀번호가 메일로 전송되었습니다.\n", ceoMemberEmail.getCeoNickname());
            System.out.println(" >> 로그인 후 비밀번호를 변경해 주세요.");

            ceoMemberDao.updatePassword(ceoMemberEmail);
            sqlSession.commit();
            return;

          } else {
            System.out.println(" >> 해당 이메일이 존재하지 않습니다.");
            continue;
          }

        case 2:
          System.out.println();
          inputName = Prompt.inputString(" 이름 : ");
          ceoMemberName = ceoMemberDao.findByName(inputName);
          String inputTel = Prompt.inputString(" 전화번호 : ");
          CeoMember CeoMemberTel = ceoMemberDao.findByTel(inputTel);

          // SMS API 적용 후 삭제
          inputEmail = Prompt.inputString(" 이메일 : ");
          ceoMemberEmail = ceoMemberDao.findByEmail(inputEmail);

          if (ceoMemberName != null && CeoMemberTel != null && ceoMemberEmail != null) {
            String pw = randomPw.randomPw();
            CeoMemberTel.setCeoPassword(pw);

            System.out.println(" >> 처리 중입니다. 잠시만 기다려 주세요.");
            System.out.println();

            // SMS API 적용 후 변경
            sendMail.sendMail(inputEmail, pw);
            System.out.printf(" '%s님'의 임시 비밀번호가 메일로 전송되었습니다.\n", CeoMemberTel.getCeoNickname());
            System.out.println(" >> 로그인 후 비밀번호를 변경해 주세요.");

            ceoMemberDao.updatePassword(CeoMemberTel);
            sqlSession.commit();
            return;

          } else {
            System.out.println(" >> 해당 전화번호가 존재하지 않습니다.");
            continue;
          }

        case 0:
          return;

        default:
          System.out.println(" >> 번호를 다시 선택해 주세요.");
          continue;
      }
    }
  }
}
