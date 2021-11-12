package com.ogong.pms.web.study.bookMark;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/bookmark/list")
public class StudyBookMarkListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  StudyDao studyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      Member member = memberDao.findByNo(loginUser.getPerNo());

      Collection<Study> studyList = studyDao.findByMyBookmark(member.getPerNo());

      // request.setAttribute("member", member);
      request.setAttribute("studyList", studyList);
      request.setAttribute("pageTitle", "북마크 목록");
      request.setAttribute("contentUrl", "/study/bookMark/StudyBookMarkList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
