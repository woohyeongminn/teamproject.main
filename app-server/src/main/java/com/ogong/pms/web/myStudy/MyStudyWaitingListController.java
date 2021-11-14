package com.ogong.pms.web.myStudy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/mystudy/waitinglist")
public class MyStudyWaitingListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {

      int perNo = Integer.parseInt(request.getParameter("perNo"));
      Member member = memberDao.findByNo(perNo);
      List<Study> studyList = studyDao.findAll();

      List<Study> waitingStudyList = new ArrayList<>();
      for (int i = 0; i < studyList.size(); i++) {
        List<Member> waiting = studyDao.findByWaitingGuilderAll(studyList.get(i).getStudyNo());
        studyList.get(i).setWatingMember(waiting);

        for (Member mem : studyList.get(i).getWatingMember()) {
          if (member.getPerNo() == mem.getPerNo()) {
            waitingStudyList.add(studyList.get(i));
          }
        }
      }

      request.setAttribute("member", member);
      request.setAttribute("waitingStudyList", waitingStudyList);
      request.getRequestDispatcher("/myStudy/MyStudyWaitingList.jsp").forward(request, response);


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}