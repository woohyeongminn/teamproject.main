package com.ogong.pms.servlet.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/adminNotice/add")
public class AdminNoticeAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  NoticeDao noticeDao;
  AdminDao adminDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    noticeDao = (NoticeDao) 웹애플리케이션공용저장소.getAttribute("noticeDao");
    adminDao = (AdminDao) 웹애플리케이션공용저장소.getAttribute("adminDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Admin loginAdmin = (Admin) request.getSession().getAttribute("loginAdmin");
      Admin admin = adminDao.findByAdminNo(loginAdmin.getMasterNo());

      if (admin == null) {
        request.getRequestDispatcher("/admin/NoticeAdd.jsp").forward(request, response);
      }

      AdminNotice adminNotice = new AdminNotice();

      adminNotice.setAdminNotiTitle(request.getParameter("title"));
      adminNotice.setAdminNotiContent(request.getParameter("content"));
      adminNotice.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

      Part photoPart = request.getPart("filepath");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(getServletContext().getRealPath("/upload/notice") + "/" + filename);
        // 상속받은 서블릿 중에서 getServletContext를 리턴받는 애가 있음
        // 걔를 통해서 getRealPath(업로드 경로)를 알아내서 저장
        adminNotice.setAdminNotiFile(filename); // 실제 저장한 파일명을 데이터 베이스에 저장하도록

        Thumbnails.of(getServletContext().getRealPath("/upload/notice") + "/" + filename)
        .size(20, 20)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_20x20";
          }
        });
      }

      noticeDao.insert(adminNotice);
      noticeDao.insertFilepath(adminNotice);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list"); 
      request.setAttribute("contentUrl", "/admin/NoticeAdd.jsp");
      // request.getRequestDispatcher("/admin/NoticeAdd.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
