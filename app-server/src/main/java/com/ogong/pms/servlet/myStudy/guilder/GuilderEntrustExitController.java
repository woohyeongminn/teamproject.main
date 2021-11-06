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
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/mystudy/guilder/entrustexit")
public class GuilderEntrustExitController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  // 조장 권한 위임
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      int guilderMemberNo = Integer.parseInt(request.getParameter("guilderMemberNo"));
      int studyNo = Integer.parseInt(request.getParameter("studyNo"));

      Study myStudy = studyDao.findByNo(studyNo);
      //    myStudy.setMembers(guilder);
      //    List<Member> guilders = myStudy.getMembers();


      // 조장 권한 넘겨주고 본인은 스터디에서 탈퇴
      studyDao.updateOwner(myStudy.getStudyNo(), guilderMemberNo);
      studyDao.updateGuilderExpulsion(myStudy.getStudyNo(), loginUser.getPerNo());

      sqlSession.commit();

      response.sendRedirect("../list#tab1");    //구성원목록 아니고 스터디 목록으로 돌아감 

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
