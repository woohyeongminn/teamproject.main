package com.ogong.pms.servlet.study.bookMark;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/study/bookmark/add")
public class StudyBookMarkAddController extends HttpServlet {
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
      int no = (int) request.getAttribute("no");
      Member member = memberDao.findByNo(no);

      Study study = studyDao.findByNo(no);

      List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
      study.setWatingMember(waitingGuilder);

      List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
      study.setMembers(guilders);

      List<Member> bookmark = studyDao.findByBookmarkAll(study.getStudyNo());
      study.setBookMarkMember(bookmark);

      studyDao.insertBookmark(study.getStudyNo(), member.getPerNo());
      sqlSession.commit();

    } catch (Exception e) {
      sqlSession.rollback();
      request.setAttribute("error", e);

      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
}
