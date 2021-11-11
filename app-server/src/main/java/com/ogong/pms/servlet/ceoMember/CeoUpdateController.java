package com.ogong.pms.servlet.ceoMember;

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
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/ceomember/update")
public class CeoUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;
  SqlSession sqlSession;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  // 기업회원 개인정보 수정은 이름,이메일,비밀번호만 가능
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      CeoMember loginCeo = (CeoMember) request.getSession().getAttribute("loginCeoUser");
      CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

      if (ceoMember == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      // 사진
      Part photoPart = request.getPart("photo");
      //      if (photoPart.getSize() > 0) {
      if (photoPart != null) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(getServletContext().getRealPath("/upload/ceoMember") + "/" + filename);
        ceoMember.setCeoPhoto(filename);

        Thumbnails.of(getServletContext().getRealPath("/upload/ceoMember") + "/" + filename)
        .size(40, 40)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_40x40";
          }
        });

        Thumbnails.of(getServletContext().getRealPath("/upload/ceoMember") + "/" + filename)
        .size(80, 80)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_80x80";
          }
        });

      }

      ceoMember.setCeoName(request.getParameter("name"));
      ceoMember.setCeoNickname(request.getParameter("nickname"));
      ceoMember.setCeoTel(request.getParameter("tel"));
      ceoMember.setCeoEmail(request.getParameter("email"));
      ceoMember.setCeoPassword(request.getParameter("password"));

      //      ceoMemberDao.updateName(ceoMember);
      //      ceoMemberDao.updateNickName(ceoMember);
      //      ceoMemberDao.updateTel(ceoMember);
      //      ceoMemberDao.updateEmail(ceoMember);
      //      ceoMemberDao.updatePassword(ceoMember);
      //      ceoMemberDao.updatePhoto(ceoMember);

      ceoMemberDao.updateCeoMember(ceoMember);
      sqlSession.commit();

      response.sendRedirect("detail");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}