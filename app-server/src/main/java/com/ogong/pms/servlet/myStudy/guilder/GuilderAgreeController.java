package com.ogong.pms.servlet.myStudy.guilder;

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

@WebServlet("/mystudy/guilder/agree")
public class GuilderAgreeController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int loginNo = Integer.parseInt(request.getParameter("loginNo"));
      int watingMemberNo = Integer.parseInt(request.getParameter("watingMemberNo"));
      int studyNo = Integer.parseInt(request.getParameter("studyNo"));

      studyDao.updateGuilder(studyNo, watingMemberNo);
      sqlSession.commit();

      //      request.getRequestDispatcher("/myStudy/guilder/GuilderList.jsp").forward(request, response);

      // 길더리스트로 가야함
      response.sendRedirect("list?perNo="+loginNo+"&studyNo="+studyNo);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
