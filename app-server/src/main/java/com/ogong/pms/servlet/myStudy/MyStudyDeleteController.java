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
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@WebServlet("/study/delete")
public class MyStudyDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // MemberDao memberDao;
  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    // memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // int perNo = Integer.parseInt(request.getParameter("perno"));
      // Member member = memberDao.findByNo(perNo);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      // if (study.getOwner().getPerNo() != member.getPerNo()) {
      // throw new Exception(" >> 삭제 권한이 없습니다.");
      //
      // } else {
      // if (study.getCountMember() > 0) {
      // throw new Exception(" >> 구성원이 있는 스터디는 삭제할 수 없습니다.");
      //
      // } else if (study.getWatingCountMember() > 0) {
      // System.out.println(" >> 승인 대기 중인 구성원이 없어야 스터디 삭제가 가능합니다.");
      //
      // studyDao.updateGuilderExpulsionAll(study.getStudyNo());
      // System.out.println(" >> 구성원을 모두 삭제하였습니다.\n");
      // }
      // }

      studyDao.updateStatusDelete(study);
      sqlSession.commit();

      // request.setAttribute("member", member);
      request.setAttribute("study", study);
      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
