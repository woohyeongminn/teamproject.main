package com.ogong.pms.servlet.study;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@WebServlet("/study/list")
public class StudyListController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      /* [GR] Search Ver.1 */
      /*
       * String field_ = request.getParameter("f"); String query_ = request.getParameter("q");
       * 
       * String field = "title"; if (field_ != null && !field_.equals("")) field = field_;
       * 
       * String query = ""; if (query_ != null && !query_.equals("")) query = query_;
       * 
       * NoticeService service = new NoticeService(); List<Notice> list=
       * service.getNoticeList(field, query ,1);
       * 
       * request.setAttribute("list", list);
       * request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response); }
       */

      Collection<Study> studyList = studyDao.findAll();

      if (studyList.isEmpty()) {
        throw new Exception("스터디 목록이 없습니다.");
      }

      request.setAttribute("perno", Integer.parseInt(request.getParameter("perno")));
      request.setAttribute("studyList", studyList);
      request.getRequestDispatcher("/study/StudyList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
