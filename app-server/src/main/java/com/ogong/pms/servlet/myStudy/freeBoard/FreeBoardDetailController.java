package com.ogong.pms.servlet.myStudy.freeBoard;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;

@WebServlet("/mystudy/freeboarddetail")
public class FreeBoardDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  FreeBoardDao freeBoardDao;
  StudyDao studyDao;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int studyNo = Integer.parseInt(request.getParameter("studyNo"));
      int perNo = Integer.parseInt(request.getParameter("perNo"));
      int freeNo = Integer.parseInt(request.getParameter("freeNo"));

      Member member = memberDao.findByNo(perNo);
      FreeBoard freeBoard = freeBoardDao.findByNo(freeNo, studyNo);

      //    if (freeBoard == null) {
      //      System.out.println(" >> 해당 번호의 게시글이 없습니다.\n");
      //      request.getRequestDispatcher("/myStudy/freeBoardList").forward(request);
      //      return;
      //    }

      request.setAttribute("freeBoard", freeBoard);
      request.setAttribute("member", member);
      request.getRequestDispatcher(
          "/myStudy/freeBoard/FreeBoardDetail.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

