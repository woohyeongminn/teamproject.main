package com.ogong.pms.web.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/admin/study/delete")
public class AdminStudyDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      if (study == null) {
        throw new Exception(" >> 해당 번호의 스터디가 존재 하지않습니다.");
      }

      List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
      study.setWatingMember(waitingGuilder);

      List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
      study.setMembers(guilders);

      // studyDao.deleteAllGuilder(study.getStudyNo());
      // studyDao.deleteAllBookmark(study.getStudyNo());
      studyDao.updateStatusDelete(study);
      sqlSession.commit();

      request.setAttribute("study", study);
      response.sendRedirect("list");

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
