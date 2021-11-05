package com.ogong.pms.servlet.myStudy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

@WebServlet("/mystudy/guilderList")
public class MyStudyGuilderListController extends HttpServlet {
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
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      int perNo = Integer.parseInt(request.getParameter("perNo"));
      Member member = memberDao.findByNo(perNo);

      List<Study> studyList = studyDao.findAll();

      List<Study> guilderMembers = new ArrayList<>();
      //      System.out.println(" | 👥 구성원 | ");
      for (int i = 0; i < studyList.size(); i++) {
        List<Member> guilders = studyDao.findByGuildersAll(studyList.get(i).getStudyNo());
        studyList.get(i).setMembers(guilders);
        for (Member mem : studyList.get(i).getMembers())
          if (mem.getPerNo() == member.getPerNo()) {
            if (studyList.get(i).getOwner().getPerNo()!=member.getPerNo()) {
              guilderMembers.add(studyList.get(i));
            }
          }
      }

      request.setAttribute("member", member);
      request.setAttribute("guilderMembers", guilderMembers);
      request.getRequestDispatcher("/myStudy/MyStudyGuilderList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}