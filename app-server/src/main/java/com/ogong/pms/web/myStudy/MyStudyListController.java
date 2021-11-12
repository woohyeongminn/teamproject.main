package com.ogong.pms.web.myStudy;

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
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/mystudy/list")
public class MyStudyListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      //      int perNo = Integer.parseInt(request.getParameter("perNo"));
      //      Member member = memberDao.findByNo(perNo);

      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      List<Study> studyList = studyDao.findAll();

      // 조장
      List<Study> ownerStudyList = new ArrayList<>();

      for (Study study : studyList) {
        if (study.getOwner().getPerNo() == loginUser.getPerNo()) {
          ownerStudyList.add(study);
        }
      }

      // 구성원
      List<Study> guilderMembers = new ArrayList<>();
      //      System.out.println(" | 👥 구성원 | ");
      for (int i = 0; i < studyList.size(); i++) {
        List<Member> guilders = studyDao.findByGuildersAll(studyList.get(i).getStudyNo());
        studyList.get(i).setMembers(guilders);
        for (Member mem : studyList.get(i).getMembers())
          if (mem.getPerNo() == loginUser.getPerNo()) {
            if (studyList.get(i).getOwner().getPerNo()!=loginUser.getPerNo()) {
              guilderMembers.add(studyList.get(i));
            }
          }
      }

      // 대기중
      List<Study> waitingStudyList = new ArrayList<>();
      for (int i = 0; i < studyList.size(); i++) {
        List<Member> waiting = studyDao.findByWaitingGuilderAll(studyList.get(i).getStudyNo());
        studyList.get(i).setWatingMember(waiting);

        for (Member mem : studyList.get(i).getWatingMember()) {
          if (loginUser.getPerNo() == mem.getPerNo()) {
            waitingStudyList.add(studyList.get(i));
          }
        }
      }

      //request.setAttribute("member", member);
      request.setAttribute("ownerStudyList", ownerStudyList);
      request.setAttribute("guilderMembers", guilderMembers);
      request.setAttribute("waitingStudyList", waitingStudyList);
      request.getRequestDispatcher("/myStudy/MyStudyList.jsp").forward(request, response);

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