package com.ogong.pms.servlet.member;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

public class MemberDeleteHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }
  // 개인
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      String email = request.getParameter("email");
      String password = request.getParameter("password");

      Member perMember = memberDao.findByEmailAndPassword(email, password);

      if (perMember == null) {
        throw new Exception("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
      }
    } catch (Exception e) {

    }



    // 조장일때
    for (Study study : studyList) {
      if (study.getOwner().getPerNo() == member.getPerNo()) {
        System.out.println("\n >> 스터디 삭제 후 탈퇴 가능합니다.");
        return;
      }
    }

    member.setPerNickname("Deleted Member("+ member.getPerNickname() +")");
    member.setPerName("Deleted Name");
    member.setPerPhoto("Deleted Photo");
    member.setPerTel("Deleted Tel");
    member.setPerEmail("Deleted Email");
    member.setPerPassword("Deleted Password");
    member.setPerStatus(Member.PER);
    member.setActive(Member.OUTUSER);

    memberDao.updateActive(member);
    sqlSession.commit();
    //AuthPerMemberLoginHandler.loginUser = null;
    //AuthPerMemberLoginHandler.accessLevel = Menu.LOGOUT;

    System.out.println();
    System.out.println(" >> 회원 탈퇴를 완료하였습니다.");
    return;

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
