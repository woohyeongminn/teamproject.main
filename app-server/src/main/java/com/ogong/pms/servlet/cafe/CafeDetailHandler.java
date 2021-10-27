package com.ogong.pms.servlet.cafe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CafeRoom;

@WebServlet("/cafe/detail")
public class CafeDetailHandler extends HttpServlet {
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
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>스터디카페 상세</title>");
    out.println("<style>");
    out.println("label {");
    out.println("  display: inline-block;");
    out.println("  margin-right: 5px;");
    out.println("  width: 130px;");
    out.println("}");
    out.println("#aside {");
    out.println("   width: 120px;");
    out.println("   height: 171px;");
    out.println("   float: left;");
    out.println("   background-color: lightsteelblue;");
    out.println("   display: table;");
    out.println("}");
    out.println("#content {");
    out.println("   margin-left: 130px;");
    out.println("}");
    out.println("#c-image {");
    out.println("  display: table-cell;");
    out.println("  vertical-align: middle;");
    out.println("  text-align: center;");
    out.println("}");
    out.println("#c-grade {");
    out.println("   margin-left: 41px;");
    out.println("   vertical-align: 4px;");
    out.println("}");
    out.println("#c-review {");
    out.println("  width: 427px;");
    out.println("  background-color: whitesmoke;");
    out.println("  height: 80px;");
    out.println("  margin-bottom: 10px;");
    out.println("}");
    out.println("#c-review-content {");
    out.println("}");
    out.println("</style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>스터디카페 상세</h1>");

    try {

      Cafe cafe = cafeDao.findByCafeNoMember(Integer.parseInt(request.getParameter("no")));

      if (cafe == null) {
        out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
        return;
      }

      HashMap<String,Object> params = new HashMap<>();
      params.put("cafeNo", cafe.getNo());
      cafe.setHoliday(cafeDao.getCafeHoliday(params));

      out.printf("<input id='c-no' type='hidden' value='%d'><br>\n", cafe.getNo());
      out.printf("<h2>[%s]</h2><br>\n", cafe.getName());
      out.printf("<div id='aside'><span id='c-image'>대표이미지</span></div>"/* , cafe.getCafeImageNames() */);
      out.printf("<div id='content'><label for='f-info'>소개글</label> %s<br>\n", cafe.getInfo());
      out.printf("<label for='f-location'>주소</label> %s<br>\n", cafe.getLocation());
      out.printf("<label for='f-tel'>전화번호</label> %s<br>\n", cafe.getPhone());
      out.printf("<label for='f-openTime'>오픈시간</label> %s<br>\n", cafe.getOpenTime());
      out.printf("<label for='f-closeTime'>마감시간</label> %s<br>\n", cafe.getCloseTime());
      out.printf("<label for='f-holiday'>이번주 휴무일</label> %s<br>\n", cafe.getHoliday());
      out.printf("<label for='f-viewCount'>조회수</label> %d<br>\n", cafe.getViewCount());
      out.printf("<label for='f-review'>리뷰평점</label> ★ %.1f(%d)</div><br>\n" , cafe.getAvgReview(), cafe.getCountReview());
      listReview(cafe, out); // 리뷰 목록
      out.println();

      if (cafe.getCafeStatus() == Cafe.STOP) {
        // 카페가 운영중단 상태일때는 예약 메뉴 출력 안하고 상세보기만
        return;
      }

      int roomCount = 0;
      List<CafeRoom> roomList = cafeRoomDao.getCafeRoomList();
      for (CafeRoom cafeRoom : roomList) {
        if (cafeRoom.getCafe().getNo() == cafe.getNo()) {
          roomCount++;
        }
      }
      out.println("<a href='list'>목록</a>");

      if (roomCount != 0) {
        out.println("<button>스터디룸 예약</button>");

        //      int selectNo = Prompt.inputInt(" 선택> ");
        //      switch (selectNo) {
        //        case 1 : 
        //          request.setAttribute("selectReservation", "addReservation");
        //          request.getRequestDispatcher("/cafe/reservation").forward(request); 
        //          return;
        //        case 2 : 
        //          request.setAttribute("selectReservation", "addRoomReservation");
        //          request.getRequestDispatcher("/cafe/reservation").forward(request); 
        //          return;
        //        case 0 : return;
        //        default : 
        //          System.out.println(" >> 번호를 다시 선택해 주세요.");
        //      } 
      } 
      //    else {
      //      System.out.println("----------------------");
      //      System.out.println("1. 일반 예약");
      //      System.out.println("0. 이전");
      //
      //      int selectNo = Prompt.inputInt(" 선택> ");
      //      switch (selectNo) {
      //        case 1 : 
      //          request.setAttribute("selectReservation", "addReservation");
      //          request.getRequestDispatcher("/cafe/reservation").forward(request); 
      //          return;
      //        case 0 : return;
      //        default : 
      //          System.out.println(" >> 번호를 다시 선택해 주세요.");
      //      } 
      //  }   


      request.setAttribute("cafeNo", cafe.getNo());
      cafeDao.updateViewCount(cafe.getNo());
      sqlSession.commit();

    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }

  private void listReview(Cafe cafe, PrintWriter out) throws Exception {
    int i = 1;
    out.println();
    out.println("================= 리뷰 =================<br>");

    List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

    if (reviewList.isEmpty()) {
      out.println("등록된 리뷰가 없습니다.<br><br>");
    } else {
      for (CafeReview review : reviewList) {
        if (review.getReviewStatus() == 2) {
          //System.out.printf(" \n (%s)\n", review.getReviewNo());
          out.printf("<div id='c-review'>삭제 된 리뷰입니다.</div><br>"/* , i++ */);
          continue;
        }
        String nickname = review.getMember().getPerNickname();
        out.printf("<div id='c-review'><span>%s</span> <span id='c-grade'>%s(%d/5)</span> <span id='c-grade'>%s</span> <br><p id='c-review-content'>%s</p><br></div>",
            /* i++, */ nickname
            , CafeHandlerHelper.getReviewGradeStatusLabel(review.getGrade())
            , review.getGrade()
            , review.getRegisteredDate()
            , review.getContent());
      }
    }
  }
}