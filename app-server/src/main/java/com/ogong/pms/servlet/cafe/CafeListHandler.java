package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;

@WebServlet("/cafe/list")
public class CafeListHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>스터디카페목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>스터디카페 목록</h1>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>이름</th>");
    out.println("    <th>주소</th>");
    out.println("    <th>운영시간</th>");
    out.println("    <th>조회수</th>");
    out.println("    <th>리뷰</th>");
    out.println("  <tr>");
    out.println("</thread>");
    out.println("<tbody>");

    try {
      List<Cafe> cafeList = cafeDao.getCafeListByMember();

      if (cafeList == null) {
        System.out.println("등록된 장소가 없습니다.");
        return;
      }

      for(Cafe cafe : cafeList) {
        out.printf("<tr> "
            + " <td>%d</td>"
            + " <td><a href='detail?no=%1$d'>%s</a></td>"
            + " <td>%s</td>"
            + " <td>%s ~ %s</td>"
            + " <td>%d</td>"
            + " <td>★%.1f(%d)</td>"
            + "</tr>", 
            cafe.getNo(), 
            cafe.getName(), 
            cafe.getLocation(), 
            cafe.getOpenTime(),
            cafe.getCloseTime(),
            cafe.getViewCount(),
            cafe.getAvgReview(),
            cafe.getCountReview());
        if (cafe.getCafeStatus() == Cafe.STOP) {
          out.println(" * 운영 중단");
        }
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
    //    selectCafeDetailMenu(request);
  }

  //  private void selectCafeDetailMenu(CommandRequest request) throws Exception {
  //    System.out.println("\n----------------------");
  //    System.out.println("1. 상세");
  //    System.out.println("2. 검색");
  //    System.out.println("0. 이전");
  //    while (true) {
  //      int input = Prompt.inputInt(" 선택> ");
  //      switch (input) {
  //        case 1: request.getRequestDispatcher("/cafe/detail").forward(request); return;
  //        case 2: request.getRequestDispatcher("/cafe/search").forward(request); return;
  //        case 0: return;
  //        default : 
  //          System.out.println(" >> 번호를 다시 선택해 주세요.");
  //      }
  //    }
  //  }
}
