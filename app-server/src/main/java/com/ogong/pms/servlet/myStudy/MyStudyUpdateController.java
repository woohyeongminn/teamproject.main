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

  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // int no = Integer.parseInt(request.getParameter("no"));
      // Member member = memberDao.findByNo(no);

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study myStudy = studyDao.findByNo(studyNo);

      myStudy.setStudyTitle(request.getParameter("studyTitle"));
      myStudy.setSubjectNo(Integer.parseInt(request.getParameter("subjectNo")));
      myStudy.setArea(request.getParameter("area"));
      myStudy.setNumberOfPeple(Integer.parseInt(request.getParameter("numberOfPeple")));
      myStudy.setFaceNo(Integer.parseInt(request.getParameter("faceNo")));
      myStudy.setIntroduction(request.getParameter("introduction"));

      studyDao.update(myStudy);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
