package com.ogong.pms.servlet.myStudy;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/study/exit")
public class MyStudyExitController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      Member member = memberDao.findByNo(loginUser.getPerNo());

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      // 내가 조장일 때
      if (study.getOwner().getPerNo() == member.getPerNo() && study.getCountMember() == 0) {
        studyDao.updateStatusDelete(study);
        sqlSession.commit();
        // System.out.println(" >> 스터디가 삭제 되었습니다.");
      }

      studyDao.deleteGuilder(study.getStudyNo(), member.getPerNo());
      // System.out.println(" >> 탈퇴되었습니다.");

      // request.setAttribute("member", member);
      request.setAttribute("study", study);
      request.setAttribute("pageTitle", "내 스터디 탈퇴");
      request.setAttribute("contentUrl", "/myStudy/MyStudyExit.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

/*
 * int count = 0; for (Member m : myStudy.getMembers()) { if (m.getPerNo() ==
 * AuthPerMemberLoginHandler.getLoginUser().getPerNo()) { count++; } }
 * 
 * if (count != 0) { String input = Prompt.inputString(" 정말 탈퇴하시겠습니까?(네 / 아니오)"); if
 * (!input.equals("네")) { System.out.println(" >> 탈퇴를 취소하였습니다."); return; }
 * studyDao.deleteGuilder(myStudy.getStudyNo(), member.getPerNo());
 * System.out.println(" >> 탈퇴되었습니다."); } else if (myStudy.getOwner().getPerNo() == member.getPerNo()
 * && myStudy.getMembers().size() == 0) {
 * 
 * String input = Prompt.inputString(" 정말 탈퇴하시겠습니까?(네 / 아니오)"); if (!input.equals("네")) {
 * System.out.println(" >> 탈퇴를 취소하였습니다."); return; }
 * 
 * studyDao.delete(myStudy.getStudyNo(), member.getPerNo());
 * 
 * System.out.println(" >> 스터디가 삭제 되었습니다."); } }
 */
