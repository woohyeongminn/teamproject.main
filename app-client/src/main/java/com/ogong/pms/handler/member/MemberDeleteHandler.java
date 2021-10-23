package com.ogong.pms.handler.member;

import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class MemberDeleteHandler implements Command {

  MemberDao memberDao;
  StudyDao studyDao;

  public MemberDeleteHandler(MemberDao memberDao, StudyDao studyDao) {
    this.memberDao = memberDao;
    this.studyDao = studyDao;
  }

  // 개인
  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println();
    System.out.println("▶ 회원 탈퇴");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null) {
      System.out.println(" >> 로그인 하세요.");
      return;
    }

    int no;
    try {
      no = (int) request.getAttribute("memberNo");
    } catch (NullPointerException e) {
      no = AuthPerMemberLoginHandler.getLoginUser().getPerNo();
    }

    Member member = memberDao.findByNo(no);

    System.out.println(" << 이메일 확인 >>");
    String inputEmail = Prompt.inputString(" 이메일을 입력하세요 : ");

    if (!(inputEmail.equals(member.getPerEmail()))) {
      System.out.println();
      System.out.println(" >> 이메일이 일치하지 않습니다.");
      return;
    }

    System.out.println();
    System.out.println(" << 비밀번호 확인 >>");
    String inputPassword = Prompt.inputString(" 비밀번호를 입력하세요 : ");

    Member perMember = memberDao.findByEmailAndPassword(inputEmail, inputPassword);

    if (perMember == null) {
      System.out.println();
      System.out.println(" >> 비밀번호가 일치하지 않습니다.");
      return;
    }

    System.out.println();
    String input = Prompt.inputString(" 정말 탈퇴하시겠습니까? (네 /아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 회원 탈퇴를 취소하였습니다.");
      return;
    }

    if (input.equals("네")) {
      List<Study> studyList = studyDao.findAll();

      // 조장일때
      for (Study study : studyList) {
        if (study.getOwner().getPerNo() == member.getPerNo()) {
          System.out.println(" >> 스터디 삭제 후 탈퇴 가능합니다.");
          return;
        }
      }

      // 구성원일때 스터디에서 자동으로 탈퇴
      for (int i = 0; i < studyList.size(); i++) {
        for (Member mem : studyList.get(i).getMembers()) {
          if (mem.getPerNo() == member.getPerNo()) {
            studyDao.deleteGuilder(studyList.get(i).getStudyNo(), mem.getPerNo());
          }
        }
      }

      member.setPerNickname("탈퇴된 회원: ( " + member.getPerNickname() + " )");
      member.setPerName("Deleted Name");
      member.setPerPhoto("Deleted Photo");
      member.setPerTel("Deleted Tel");
      member.setPerEmail("Deleted Email");
      member.setPerPassword("Deleted Password");
      member.setPerStatus(Member.PER);
      member.setActive(Member.OUTUSER);

      memberDao.updateActive(member);
      AuthPerMemberLoginHandler.loginUser = null;
      AuthPerMemberLoginHandler.accessLevel = Menu.LOGOUT;

      System.out.println();
      System.out.println(" >> 회원 탈퇴를 완료하였습니다.");
      return;
    }
  }
}



//------------------- 스터디에 반영 --------------------
//requestAgent.request("study.selectList", null);
//
// if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
// System.out.println(" >> 해당 스터디가 없습니다.");
// return;
// }
//
// Collection<Study> studyList = requestAgent.getObjects(Study.class);
// List<Study> s = new ArrayList<>(studyList);

// for (int i = s.size() -1; i >= 0; i--) {
// if (s.get(i).getOwner().getPerNo() == member.getPerNo()) {
// HashMap<String,String> studyParams = new HashMap<>();
// studyParams.put("studyNo", String.valueOf(s.get(i).getStudyNo()));
//
// requestAgent.request("study.delete", studyParams);
//
// if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
// System.out.println(" >> 스터디 삭제가 실패되었습니다.");
// return;
// }
// } else {
// for (Member m : s.get(i).getMembers()) {
// if(m.getPerNo() == member.getPerNo()) {
// s.get(i).getMembers().remove(m);
//
// requestAgent.request("study.update", s.get(i));
//
// if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
// System.out.println(" >> 구성원 탈퇴 실패!");
// return;
// }
// break;
// }
// }
// }
// }
