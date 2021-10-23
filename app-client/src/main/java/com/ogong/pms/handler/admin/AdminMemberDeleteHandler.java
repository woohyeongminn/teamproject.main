package com.ogong.pms.handler.admin;

import java.util.List;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminMemberDeleteHandler implements Command {

  MemberDao memberDao;
  StudyDao studyDao;

  public AdminMemberDeleteHandler(MemberDao memberDao, StudyDao studyDao) {
    this.memberDao = memberDao;
    this.studyDao = studyDao;
  }

  // 개인
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 회원 삭제");

    int memberNo = (int) request.getAttribute("memberNo");

    Member member = memberDao.findByNo(memberNo);

    if (member.getPerNickname() != AuthAdminLoginHandler.getLoginAdmin().getMasterNickname()) {

      String input = Prompt.inputString(" 정말 탈퇴시키겠습니까? (네 /아니오) ");

      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 회원 삭제를 취소하였습니다.");
        request.getRequestDispatcher("/adminMember/list").forward(request);
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

        System.out.println(" >> 회원이 삭제되었습니다.");
        request.getRequestDispatcher("/adminMember/list").forward(request);
        return;
      }
    }
  }
}



// ------------------- 스터디에 반영 --------------------
//      requestAgent.request("study.selectList", null);
//
//      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//        System.out.println(" >> 스터디가 없습니다.");
//        return;
//      }
//
//      Collection<Study> studyList = requestAgent.getObjects(Study.class);
//      List<Study> s = new ArrayList<>(studyList);
//
//      for (int i = s.size() -1; i >= 0; i--) {
//        if (s.get(i).getOwner().getPerNo() == user.getPerNo()) {
//
//          if (user.getPerNickname().contains("탈퇴")) {
//            s.get(i).setStudyTitle(" >> 탈퇴된 회원의 스터디입니다.");
//          }
//
//          //          HashMap<String,String> studyParams = new HashMap<>();
//          //          studyParams.put("studyNo", String.valueOf(s.get(i)));
//
//          requestAgent.request("study.update", s.get(i));
//
//          if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//            System.out.println(" >> 스터디 삭제가 실패되었습니다.");
//            return;
//          }
//
//        } else {
//          for (Member m : s.get(i).getMembers()) {
//            if(m.getPerNo() == user.getPerNo()) {
//              s.get(i).getMembers().remove(m);
//
//              requestAgent.request("study.update", s.get(i));
//
//              if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
//                System.out.println(" >> 구성원 탈퇴 실패.");
//                return;
//              }
//              break;
//            }
//          }
//        }
//      }