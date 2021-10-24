package com.ogong.pms.handler.member;

import java.sql.Date;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoAddHandler implements Command {

  CeoMemberDao ceoMemberDao;
  SqlSession sqlSession;

  public CeoAddHandler(CeoMemberDao ceoMemberDao, SqlSession sqlSession) {
    this.ceoMemberDao = ceoMemberDao;
    this.sqlSession = sqlSession;
  }

  // 기업 개인
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 기업 회원가입");
    System.out.println();

    List<CeoMember> ceoMemberList = ceoMemberDao.findAll();
    CeoMember ceoMember = new CeoMember();

    // 이름
    String inputName = Prompt.inputString(" 이름 : ");
    ceoMember.setCeoName(inputName);

    // 닉네임
    String inputNewNick;
    inputNewNick = Prompt.inputString(" 닉네임 : ");
    for (CeoMember c : ceoMemberList) {
      if (inputNewNick.equals(c.getCeoNickname())) {
        System.out.println(" >> 이미 사용 중인 닉네임입니다.");
        return;
      }
    }
    ceoMember.setCeoNickname(inputNewNick);

    // 사진
    ceoMember.setCeoPhoto(Prompt.inputString(" 사진 : "));

    // 전화번호
    String inputTel = Prompt.inputString(" 전화번호 : ");
    ceoMember.setCeoTel(inputTel);

    // 대표자명
    ceoMember.setCeoBossName(Prompt.inputString(" 대표자명 : "));

    // 사업자 등록번호
    String inputLicenseNo;
    while (true) {
      inputLicenseNo = Prompt.inputString(" 사업자 등록번호 : ");
      if (inputLicenseNo.length() != 10) {
        System.out.println(" >> 10자리 숫자를 입력해주세요.\n");
        continue;
      }
      break;
    }
    ceoMember.setCeoLicenseNo(inputLicenseNo);

    // 이메일
    String inputNewEmail;
    while (true) {
      inputNewEmail = Prompt.inputString(" 이메일 : ");
      if (!inputNewEmail.contains("@") ||
          !inputNewEmail.contains(".com") ||
          inputNewEmail.length() < 6) {
        System.out.println(" >> 정확한 이메일 양식으로 입력해주세요.\n");
        continue;
      }
      break;
    }
    ceoMember.setCeoEmail(inputNewEmail);

    // 비밀번호
    String inputNewPW;
    while (true) {
      inputNewPW = Prompt.inputString(" 비밀번호 : ");
      if (inputNewPW.length() < 8 || (!inputNewPW.contains("!") && !inputNewPW.contains("@")
          && !inputNewPW.contains("#") && !inputNewPW.contains("$")
          && !inputNewPW.contains("^") && !inputNewPW.contains("%")
          && !inputNewPW.contains("&") && !inputNewPW.contains("*"))) {
        System.out.println(" >> 8자 이상 특수문자를 포함시켜 주세요.\n");
        continue;
      }
      break;
    }
    ceoMember.setCeoPassword(inputNewPW);

    while (true) {
      String pw =  Prompt.inputString(" 비밀번호 확인 : ");
      if (!pw.equals(ceoMember.getCeoPassword())) {
        System.out.println(" >> 확인 실패!\n");
        continue;
      } else {
        System.out.println(" >> 확인 완료!\n");
      }
      break;
    }

    // 가입일
    ceoMember.setCeoRegisteredDate(new Date(System.currentTimeMillis()));

    // 회원 상태
    ceoMember.setCeoStatus(CeoMember.CEO);

    // 고유번호 +1
    //    CeoMember lastCeoMember = null;
    //    if (!arrayCeoMember.isEmpty()) {
    //      lastCeoMember = arrayCeoMember.get(arrayCeoMember.size() - 1);
    //      ceoMember.setCeoNo(lastCeoMember.getCeoNo() +1);
    //    } else {
    //      ceoMember.setCeoNo(1);
    //    }
    //    ceoMember.setCeoStatus(CeoMember.INUSER);

    ceoMemberDao.insert(ceoMember);
    sqlSession.commit();
    System.out.println(" >> 회원가입이 완료되었습니다.");
  }
}