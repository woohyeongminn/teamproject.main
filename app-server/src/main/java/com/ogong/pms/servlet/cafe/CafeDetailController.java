package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/cafe/detail")
public class CafeDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;
  CafeRoomDao cafeRoomDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      Cafe cafe = cafeDao.findByCafeNoMember(Integer.parseInt(request.getParameter("no")));
      cafe.setInfo(cafe.getInfo().replace("\n", "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));

      HashMap<String,Object> params = new HashMap<>();
      params.put("cafeNo", cafe.getNo());
      cafe.setHoliday(cafeDao.getCafeHoliday(params));

      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());
      List<CafeRoom> roomList = cafeRoomDao.findCafeRoomListByCafe(cafe.getNo());

      cafeDao.updateViewCount(cafe.getNo());
      sqlSession.commit();

      request.setAttribute("cafe", cafe);
      request.setAttribute("reviewList", reviewList);
      request.setAttribute("roomList", roomList);
      request.setAttribute("roomListSize", roomList.size());
      request.getRequestDispatcher("/cafe/CafeDetail.jsp").forward(request, response);
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}