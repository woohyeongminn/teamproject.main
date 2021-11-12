package com.ogong.pms.web.myStudy.guilder;

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

@WebServlet("/mystudy/guilder/delete")
public class GuilderDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  // 구성원 탈퇴시키기
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int guilderMemberNo = Integer.parseInt(request.getParameter("guilderMemberNo"));
      int studyNo = Integer.parseInt(request.getParameter("studyNo"));

      Study myStudy = studyDao.findByNo(studyNo);


      studyDao.updateGuilderExpulsion(myStudy.getStudyNo(), guilderMemberNo);
      sqlSession.commit();

      response.sendRedirect("list?&studyNo="+studyNo+"#tab1");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}