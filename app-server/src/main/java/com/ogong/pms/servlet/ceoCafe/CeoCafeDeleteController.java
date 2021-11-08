package com.ogong.pms.servlet.ceoCafe;

import static com.ogong.pms.domain.Cafe.DELETE;
import java.io.IOException;
import java.time.LocalTime;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;

@WebServlet("/ceomember/cafe/delete")
public class CeoCafeDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CeoMemberDao ceoMemberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int cafeNo = Integer.parseInt(request.getParameter("cafeno"));
      Cafe cafe = cafeDao.findByCafeNo(cafeNo);

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

      cafeDao.updateCafe(cafe);
      cafeDao.deleteCafe(cafe.getNo());
      sqlSession.commit();

      response.sendRedirect("detail");

      // 로그인했을때 화면으로 돌아가야하는데
      // 돌아갈 곳이 없다,,,,,,
      //response.sendRedirect("detail?no="+ ceoMember.getCeoNo());

      //response.sendRedirect("detail?no="+ cafe.getCeoMember().getCeoNo());

      //response.sendRedirect("login?email="+ceoMember.getCeoEmail()+"&password="+ ceoMember.getCeoPassword());


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
