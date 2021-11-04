package com.ogong.pms.servlet.ceoMember;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@WebServlet("/ceomember/add")
public class CeoAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CeoMemberDao ceoMemberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    ceoMemberDao = (CeoMemberDao) 웹애플리케이션공용저장소.getAttribute("ceoMemberDao");
  }

  // 기업 개인
  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //List<CeoMember> ceoMemberList = ceoMemberDao.findAll();
    CeoMember ceoMember = new CeoMember();

    // 이름
    ceoMember.setCeoName(request.getParameter("name"));

    // 닉네임
    ceoMember.setCeoNickname(request.getParameter("nickname"));

    // 사진
    ceoMember.setCeoPhoto(request.getParameter("photo"));

    // 전화번호
    int tel1 = Integer.parseInt(request.getParameter("tel1"));
    int tel2 = Integer.parseInt(request.getParameter("tel2"));
    int tel3 = Integer.parseInt(request.getParameter("tel3"));

    String tel = tel1 + "-" + tel2 + "-" + tel3;

    ceoMember.setCeoTel(tel);

    // 대표자명
    ceoMember.setCeoBossName(request.getParameter("bossname"));

    // 사업자 등록번호
    ceoMember.setCeoLicenseNo(request.getParameter("licenseno"));

    // 이메일
    String email = request.getParameter("email");
    String site = request.getParameter("site");

    ceoMember.setCeoEmail(email +'@'+ site);

    // 비밀번호
    ceoMember.setCeoPassword(request.getParameter("passwordcheck"));

    // 가입일
    ceoMember.setCeoRegisteredDate(new Date(System.currentTimeMillis()));

    // 회원 상태
    ceoMember.setCeoStatus(CeoMember.CEO);

    try {
      ceoMemberDao.insert(ceoMember);
      ceoMemberDao.insertCeo(ceoMember);
      sqlSession.commit();
      //response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("/ceoMember/CeoMemberAdd.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}