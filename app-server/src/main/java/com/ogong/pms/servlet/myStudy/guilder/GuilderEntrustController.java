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
import com.ogong.pms.domain.Study;

@WebServlet("/mystudy/guilder/entrust")
public class GuilderEntrustController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  // 조장 권한 위임
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int perNo = Integer.parseInt(request.getParameter("perNo"));

      int guilderMemberNo = Integer.parseInt(request.getParameter("guilderMemberNo"));

      int studyNo = Integer.parseInt(request.getParameter("studyNo"));


      Study myStudy = studyDao.findByNo(studyNo);
      //    List<Member> guilder = studyDao.findByGuildersAll(myStudy.getStudyNo());
      //    myStudy.setMembers(guilder);
      //    List<Member> guilders = myStudy.getMembers();

      // 조장 권한 넘겨주고 본인은 구성원으로 돌아가기

      studyDao.updateOwner(myStudy.getStudyNo(), guilderMemberNo);
      sqlSession.commit();


      response.sendRedirect("list?&studyNo="+studyNo+"&perNo="+perNo+"#tab1");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
