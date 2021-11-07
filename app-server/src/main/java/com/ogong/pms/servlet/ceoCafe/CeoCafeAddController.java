package com.ogong.pms.servlet.ceoCafe;

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
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/cafe/add")
public class CeoCafeAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;
  CafeDao cafeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      Cafe cafe = new Cafe();

      cafe.setName(request.getParameter("name"));
      cafe.setCeoMember(ceoMember); 
      cafe.setInfo(request.getParameter("info"));
      cafe.setLocation(request.getParameter("location"));
      cafe.setPhone(request.getParameter("tel"));
      cafe.setOpenTime(LocalTime.parse(request.getParameter("openTime")));
      cafe.setCloseTime(LocalTime.parse(request.getParameter("closeTime")));
      cafe.setHoliday(request.getParameter("holiday"));
      cafe.setCafeStatus(Integer.parseInt(request.getParameter("cafeStatus")));
      //cafe.setCafeImgs(request.getParameter("filename[]"));

      System.out.println(cafe);

      cafeDao.insertCafe(cafe);
      sqlSession.commit();

      //      List<CafeImage> imageList = cafe.getCafeImgs();
      //
      //      if (!imageList.isEmpty()) {
      //        CafeImage cafeImage = new CafeImage();
      //        cafeImage.setName(request.getParameter("filename[]"));
      //        cafeImage.setCafeNo(cafe.getNo());
      //
      //        ArrayList<CafeImage> cafeImageList = new ArrayList<>();
      //        cafeImageList.add(cafeImage);
      //
      //        HashMap<String,Object> params = new HashMap<>(); 
      //        params.put("fileNames", cafeImageList);
      //        params.put("cafeNo", cafe.getNo());
      //
      //        cafeDao.insertCafeImage(params);
      //        sqlSession.commit();
      //      }

      response.sendRedirect("detail");

      //sqlSession.rollback();

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
