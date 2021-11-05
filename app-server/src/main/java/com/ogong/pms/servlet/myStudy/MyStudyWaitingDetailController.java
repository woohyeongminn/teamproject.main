package com.ogong.pms.servlet.myStudy;

import java.io.IOException;
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

@WebServlet("/mystudy/waitingdetail")
public class MyStudyWaitingDetailController extends HttpServlet {
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

      int perNo = Integer.parseInt(request.getParameter("perNo"));
      int studyNo = Integer.parseInt(request.getParameter("studyNo"));
      Member member = memberDao.findByNo(perNo);
      Study waitingStudy = studyDao.findByMyNo(studyNo, perNo);

      request.setAttribute("member", member);
      request.setAttribute("waitingStudy", waitingStudy);
      request.getRequestDispatcher("/myStudy/MyStudyWaitingDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
