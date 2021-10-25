package com.ogong.pms.handler.member;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;
import com.ogong.util.RandomPw;
import com.ogong.util.SendMail;

public class MemberFindIdPwHandler implements Command {

  RandomPw randomPw;
  MemberDao memberDao;
  SqlSession sqlSession;

  public MemberFindIdPwHandler(RandomPw randomPw, MemberDao memberDao, SqlSession sqlSession) {
    this.randomPw = randomPw;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("1. 이메일 찾기");
    System.out.println("2. 비밀번호 찾기");
    int selectNo = Prompt.inputInt("선택> ");

    switch (selectNo) {
      case 1:
        wantPerEmail();
        break;

      case 2:
        wantByPerPw();
        break;

      default:
        return;
    }
  }

  public void wantPerEmail() throws Exception {
    System.out.println();
    System.out.println("▶ 이메일 찾기\n");

    while (true) {
      String inputName = Prompt.inputString(" 이름 : ");

      if (inputName.equals("")) {
        return;
      }

      Member member = memberDao.findByName(inputName);

      if (member != null) {
        String inputTel = Prompt.inputString(" 전화번호 : ");
        if (member.getPerTel().equals(inputTel)) {
          System.out.println();
          System.out.printf(" '%s님'의 이메일 >> ", member.getPerName());
          System.out.println(member.getPerEmail());
        } else {
          System.out.println(" >> 회원 이름과 전화번호가 일치하지 않습니다. (종료: Enter)\n");
          continue;
        }

      } else {
        System.out.println(" >> 해당 이름이 존재하지 않습니다. (종료: Enter)\n");
        continue;
      }

      String input = Prompt.inputString(" 비밀번호 찾기로 넘어가시겠습니까? (네 / 아니오) ");
      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 찾기를 종료합니다.");
        return;
      }
      wantByPerPw();
      break;
    }
  }

  // --------------------------------------------------------------------------------------------

  public void wantByPerPw() throws Exception {
    System.out.println();
    System.out.println("▶ 임시 비밀번호 발급");

    SendMail sendMail = new SendMail();

    while (true) {
      System.out.println();
      System.out.println("1. 이메일");
      System.out.println("2. 전화번호");
      System.out.println("0. 이전");
      int selectNo = Prompt.inputInt("선택> ");

      switch (selectNo) {
        case 1:
          System.out.println();
          String inputName = Prompt.inputString(" 이름 : ");
          Member memberName = memberDao.findByName(inputName);
          String inputEmail = Prompt.inputString(" 이메일 : ");
          Member memberEmail = memberDao.findByEmail(inputEmail);

          if (memberName != null && memberEmail != null) {
            String pw = randomPw.randomPw();

            memberEmail.setPerPassword(pw);
            System.out.println(" >> 처리 중입니다. 잠시만 기다려 주세요.\n");

            sendMail.sendMail(inputEmail, pw);
            System.out.printf(" '%s님'의 임시 비밀번호가 메일로 전송되었습니다.\n", memberEmail.getPerNickname());
            System.out.println(" >> 로그인 후 비밀번호를 변경해 주세요.");

            memberDao.updatePassword(memberEmail);
            sqlSession.commit();
            return;

          } else {
            System.out.println(" >> 해당 이메일이 존재하지 않습니다.");
            continue;
          }

        case 2:
          System.out.println();
          inputName = Prompt.inputString(" 이름 : ");
          memberName = memberDao.findByName(inputName);
          String inputTel = Prompt.inputString(" 전화번호 : ");
          Member memberTel = memberDao.findByTel(inputTel);
          // SMS API 적용 후 삭제
          inputEmail = Prompt.inputString(" 이메일 : ");
          memberEmail = memberDao.findByEmail(inputEmail);

          if (memberName != null && memberTel != null && memberEmail != null) {
            String pw = randomPw.randomPw();

            memberTel.setPerPassword(pw);
            System.out.println(" >> 처리 중입니다. 잠시만 기다려 주세요.");
            System.out.println();

            // SMS API 적용 후 변경
            sendMail.sendMail(inputEmail, pw);
            System.out.printf(" '%s님'의 임시 비밀번호가 메일로 전송되었습니다.\n", memberTel.getPerNickname());
            System.out.println(" >> 로그인 후 비밀번호를 변경해 주세요.");

            memberDao.updatePassword(memberTel);
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

  // public void sendPerPw(String input) throws Exception {}
}
