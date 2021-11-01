package com.ogong.pms.servlet.ceoCafe;

import static com.ogong.pms.domain.Cafe.DELETE;
import java.io.IOException;
import java.time.LocalTime;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;

@WebServlet("/ceomember/cafe/delete")
public class CeoCafeDeleteController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Cafe cafe = cafeDao.findByCafeNo(no);

      if (cafe == null) {
        throw new Exception("등록된 카페가 없습니다.");
      }

      cafe.setName("");
      cafe.setMainImg("");
      cafe.setInfo("");
      cafe.setLocation("");
      cafe.setPhone("");
      cafe.setOpenTime(LocalTime.of(00, 00));
      cafe.setCloseTime(LocalTime.of(00, 00));
      cafe.setHoliday("");
      cafe.setBookable(0);
      cafe.setTimePrice(0);
      cafe.setCafeStatus(DELETE);

      cafeDao.deleteCafe(cafe.getNo());
      sqlSession.commit();

      // ceomember/detail

      int ceoNo = cafe.getCeoMember().getCeoNo();
      response.sendRedirect("detail?no="+ ceoNo);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
