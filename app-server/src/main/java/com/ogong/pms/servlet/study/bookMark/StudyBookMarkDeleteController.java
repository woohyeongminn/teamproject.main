package com.ogong.pms.servlet.study.bookMark;

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

@WebServlet("/member/bookmark/delete")
public class StudyBookMarkDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
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
      int studyNo = Integer.parseInt(request.getParameter("no"));
      Member member = memberDao.findByNo(studyNo);

      Study myStudy = studyDao.findByBookmark(studyNo, member.getPerNo());

      for (int i = 0; i < myStudy.getBookMarkMember().size(); i++) {
        if (member.getPerNo() == myStudy.getBookMarkMember().get(i).getPerNo()) {
          myStudy.getBookMarkMember().remove(i);
          break;
        }
      }

      studyDao.deleteBookmark(myStudy.getStudyNo(), member.getPerNo());
      sqlSession.commit();

    } catch (Exception e) {
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
