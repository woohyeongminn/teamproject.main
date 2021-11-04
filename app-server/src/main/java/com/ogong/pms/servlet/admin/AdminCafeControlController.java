package com.ogong.pms.servlet.admin;

import java.io.IOException;
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
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReview;

@WebServlet("/admin/cafeControl")
public class AdminCafeControlController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  // updateForm형식
  CafeDao cafeDao;
  CafeReviewDao cafeReviewDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeReviewDao = (CafeReviewDao) 웹애플리케이션공용저장소.getAttribute("cafeReviewDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      Cafe cafe = cafeDao.findByCafeNo(Integer.parseInt(request.getParameter("no")));

      if (cafe == null) {
        System.out.println(" >> 해당 번호의 장소가 존재하지 않습니다.");
        return;
      }

      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());

      HashMap<String,Object> params = new HashMap<>();
      params.put("cafeNo", cafe.getNo());
      cafe.setHoliday(cafeDao.getCafeHoliday(params));

      cafeDao.updateViewCount(cafe.getNo());
      sqlSession.commit();

      request.setAttribute("cafe", cafe);
      request.setAttribute("reviewList", reviewList);

      request.getRequestDispatcher("/admin/AdminCafeApprovalForm.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
//    
//    System.out.println();
//    System.out.println("▶ 장소 목록");
//
//    List<Cafe> cafeList = cafeDao.getCafeList();
//
//    if (cafeList.isEmpty()) {
//      System.out.println(" >> 등록된 장소가 없습니다.");
//      return;
//    }
//
//    for(Cafe cafe : cafeList) {
//      if (cafe.getCafeStatus() == DELETE) {
//        System.out.printf(" \n (%s) | 삭제된 장소입니다. \n", cafe.getNo());
//        continue;
//      }
//      System.out.printf(" \n (%s) | 이름 : %s | 주소 : %s | 운영시간 : %s ~ %s", 
//          cafe.getNo(), 
//          cafe.getName(), 
//          cafe.getLocation(), 
//          cafe.getOpenTime(),
//          cafe.getCloseTime());
//      if (cafe.getCafeStatus() == WAIT) {
//        System.out.printf(" * 승인 대기 중인 카페입니다.");
//      }
//      System.out.println();
//    }
//
//    selectCafeDetailMenu(request);
//  }
//
//  private void selectCafeDetailMenu(CommandRequest request) throws Exception {
//    System.out.println("\n----------------------");
//    System.out.println("1. 상세");
//    System.out.println("2. 승인");
//    System.out.println("0. 이전");
//    int input = Prompt.inputInt("선택> ");
//    switch (input) {
//      case 1: request.getRequestDispatcher("/cafe/controlDetail").forward(request); return;
//      case 2: request.getRequestDispatcher("/cafe/controlApproval").forward(request); return;
//      case 0: return;
//      default :
//        System.out.println(" >> 번호를 다시 선택해 주세요.");
//    }
//  }
//}
