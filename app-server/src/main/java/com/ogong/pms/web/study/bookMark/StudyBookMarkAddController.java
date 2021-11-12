package com.ogong.pms.web.study.bookMark;

import java.io.IOException;
import java.util.List;
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

@WebServlet("/bookmark/add")
public class StudyBookMarkAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init() {
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

      List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
      study.setWatingMember(waitingGuilder);

      List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
      study.setMembers(guilders);

      List<Member> bookmark = studyDao.findByBookmarkAll(study.getStudyNo());
      study.setBookMarkMember(bookmark);

      studyDao.insertBookmark(study.getStudyNo(), member.getPerNo());
      sqlSession.commit();

      // request.setAttribute("member", member);
      request.setAttribute("study", study);
      response.sendRedirect("list");
      // request.getRequestDispatcher("/study/bookMark/StudyBookMarkAdd.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
