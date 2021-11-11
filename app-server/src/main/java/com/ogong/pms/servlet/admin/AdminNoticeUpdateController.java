package com.ogong.pms.servlet.admin;

import java.io.IOException;
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
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/adminNotice/update")
public class AdminNoticeUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  NoticeDao noticeDao;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    noticeDao = (NoticeDao) 웹애플리케이션공용저장소.getAttribute("noticeDao");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int noticeNo = Integer.parseInt(request.getParameter("no"));
      AdminNotice notice = noticeDao.findByNoticeNo(noticeNo);

      if (notice == null) {
        throw new Exception(" >> 해당 번호의 공지글을 찾을 수 없습니다.");
      } 

      //AdminNotice notice = new AdminNotice();

      notice.setAdminNotiTitle(request.getParameter("title"));
      notice.setAdminNotiContent(request.getParameter("content"));

      Part photoPart = request.getPart("filepath");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(getServletContext().getRealPath("/upload/notice") + "/" + filename);
        // 상속받은 서블릿 중에서 getServletContext를 리턴받는 애가 있음
        // 걔를 통해서 getRealPath(업로드 경로)를 알아내서 저장
        notice.setAdminNotiFile(filename); // 실제 저장한 파일명을 데이터 베이스에 저장하도록

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

      noticeDao.updateTitle(notice);
      noticeDao.updateContent(notice);
      //noticeDao.updateFilepath(notice);
      noticeDao.deletenoticefile(noticeNo);
      noticeDao.insertFilepath(notice);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}