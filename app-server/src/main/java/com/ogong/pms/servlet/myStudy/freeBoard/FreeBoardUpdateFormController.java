package com.ogong.pms.servlet.myStudy.freeBoard;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;

@WebServlet("/mystudy/freeboardupdateform")
public class FreeBoardUpdateFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  FreeBoardDao freeBoardDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    freeBoardDao = (FreeBoardDao) 웹애플리케이션공용저장소.getAttribute("freeBoardDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int studyNo = Integer.parseInt(request.getParameter("studyNo"));
      int freeNo = Integer.parseInt(request.getParameter("freeNo"));
      //int perNo = Integer.parseInt(request.getParameter("perNo"));

      FreeBoard freeBoard = freeBoardDao.findByNo(freeNo, studyNo);


      //request.setAttribute("perNo", perNo);
      request.setAttribute("studyNo", studyNo);
      request.setAttribute("freeNo", freeNo);
      request.setAttribute("freeBoard", freeBoard);

      request.getRequestDispatcher("/myStudy/freeBoard/FreeBoardUpdate.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}