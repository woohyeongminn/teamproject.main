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

@WebServlet("/mystudy/ownerList")
public class MyStudyOwnerListController extends HttpServlet {
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

      List<Study> ownerStudyList = new ArrayList<>();

      for (Study study : studyList) {
        if (study.getOwner().getPerNo() == member.getPerNo()) {
          ownerStudyList.add(study);
        }
      }

      request.setAttribute("member", member);
      request.setAttribute("ownerStudyList", ownerStudyList);
      request.getRequestDispatcher("/myStudy/MyStudyOwnerList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}




//List<Study> studyList = studyDao.findAll();
////List<Guilder> guilderList = studyDao.findByGuilderMyAll(member.getPerNo());
//
//System.out.println(" ************** 내 스터디 ************** \n");
//
////조장일때
//int ownerCount = 0;
//System.out.println(" | 👤 조장 | ");
//for (Study study : studyList) {
//  if (study.getOwner().getPerNo() == member.getPerNo()) {
//    System.out.printf(" (%s) [%s]\n", study.getStudyNo(), study.getStudyTitle());
//    System.out.println();
//    ownerCount++;
//  }
//}
//
//if(ownerCount == 0) {
//  System.out.println("  >> 조장으로 참여 중인 스터디가 없습니다.\n");
//  System.out.println();
//}