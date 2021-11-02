package com.ogong.pms.servlet.study;

import java.io.IOException;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@WebServlet("/study/list/end")
public class StudyEndListController extends GenericServlet {
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
    System.out.println();
    System.out.println("▶ 스터디 목록 - 완료");
    System.out.println();

    try {
      List<Study> studyList = studyDao.findAllEnd();

      if (studyList == null) {
        throw new Exception("스터디 목록이 없습니다.");
      }

      request.setAttribute("perno", Integer.parseInt(request.getParameter("perno")));
      request.setAttribute("studyList", studyList);
      request.getRequestDispatcher("/study/StudyEndList.jsp").forward(request, response);

      // for (Study study : studyList) {
      //
      // if (study.getStudyTitle().contains("탈퇴")) {
      // System.out.printf(" (%d)\n 스터디명 : %s\n", study.getStudyNo(), study.getStudyTitle());
      // System.out.println();
      // }
      //
      // else {
      // System.out.printf(
      // " (%d) 🌟%d \n [%s] | %s | 조장 : %s | 분야 : %s | 지역 : %s | 인원수 : %s/%s명\n",
      // study.getStudyNo(),
      // study.getCountBookMember(),
      // study.getStudyTitle(),
      // study.getFaceName(),
      // study.getOwner().getPerNickname(),
      // study.getSubjectName(),
      // study.getArea(),
      // study.getCountMember(),
      // study.getNumberOfPeple()
      // );
      // }
      // }

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
