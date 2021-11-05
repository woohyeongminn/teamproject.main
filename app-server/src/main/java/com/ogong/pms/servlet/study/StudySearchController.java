package com.ogong.pms.servlet.study;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@WebServlet("/study/search")
public class StudySearchController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // System.out.println("▶ 스터디 검색");

      // System.out.println(" 지역 / 분야 / 스터디명으로 검색할 수 있습니다.");
      // String input = Prompt.inputString(" 검색어 : ");

      // int count = 0;

      List<Study> studyList = studyDao.findByKeyword(input);

      if (studyList.isEmpty()) {
        throw new Exception("해당 검색어의 스터디가 존재하지 않습니다.");
      }

      for (Study searchStudy : studyList) {
        System.out.printf(" \n (%s)\n", searchStudy.getStudyNo());
        System.out.printf(" [%s]\n", searchStudy.getStudyTitle());
        System.out.printf(" >> 조장 : %s\n", searchStudy.getOwner().getPerNickname());
        System.out.printf(" >> 분야 : %s\n", searchStudy.getSubjectName());
        System.out.printf(" >> 지역 : %s\n", searchStudy.getArea());
        System.out.printf(" >> 인원수 : %s/%s명\n", searchStudy.getCountMember(),
            searchStudy.getNumberOfPeple());
        System.out.printf(" >> 대면 : %s\n", searchStudy.getFaceName());
        System.out.printf(" >> 소개글 : %s\n", searchStudy.getIntroduction());
        // count++;
      }

      // if (count == 0) {
      // throw new Exception(" >> 검색어를 다시 입력해 주세요.");
      // }

      request.setAttribute("studyList", studyList);
      request.getRequestDispatcher("/study/StudySearch.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
