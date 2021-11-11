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

@WebServlet("/study/update")
public class MyStudyUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  // MemberDao memberDao;
  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    // memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      // Member member = memberDao.findByNo(loginUser.getPerNo());

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      study.setStudyTitle(request.getParameter("studytitle"));
      study.setNumberOfPeple(Integer.parseInt(request.getParameter("numberofpeple")));
      study.setFaceNo(Integer.parseInt(request.getParameter("faceno")));
      study.setIntroduction(request.getParameter("introduction"));

      studyDao.updateStudyTitle(study);
      studyDao.updateNumberOfPeple(study);
      studyDao.updateFaceNo(study);
      studyDao.updateIntroduction(study);
      sqlSession.commit();

      // request.setAttribute("member", member);
      request.setAttribute("study", study);
      response.sendRedirect("detail?studyno=" + study.getStudyNo());

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
