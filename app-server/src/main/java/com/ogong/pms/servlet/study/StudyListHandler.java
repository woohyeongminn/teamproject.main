package com.ogong.pms.servlet.study;

import java.io.IOException;
import java.io.PrintWriter;
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
public class StudyListHandler extends GenericServlet {
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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>📖 스터디 찾기</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>목록</h1>");
    // out.println("<a href='form'>스터디 등록</a><br>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>북마크</th>");
    out.println("    <th>제목</th>");
    out.println("    <th>대면/비대면</th>");
    out.println("    <th>조장</th>");
    out.println("    <th>분야</th>");
    out.println("    <th>지역</th>");
    out.println("    <th>인원수</th>");
    out.println("    <th>최대 인원수</th>");
    out.println("  <tr>");
    out.println("</thread>");
    out.println("<tbody>");

    try {
      Collection<Study> studyList = studyDao.findAll();

      for (Study study : studyList) {
        out.printf(
            "<tr>" + "<td>%d</td>" + " <td>%d</td>" + " <td><a href='detail?no=%1$d'>%s</a></td>"
                + " <td>%s</td>" + " <td>%s</td>" + " <td>%s</td>" + " <td>%s</td>" + " <td>%d</td>"
                + " <td>%d</td>" + "</tr>",
                study.getStudyNo(), study.getCountBookMember(), study.getStudyTitle(),
                study.getFaceName(), study.getOwner().getPerNickname(), study.getSubjectName(),
                study.getArea(), study.getCountMember(), study.getNumberOfPeple());
      }

    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}

// @Override
// public void execute(CommandRequest request) throws Exception {
// System.out.println();
// System.out.println("▶ 스터디 목록");
// System.out.println();
//
// List<Study> studyList = studyDao.findAll();
//
// if (studyList.isEmpty()) {
// System.out.println(" >> 스터디 목록이 없습니다.");
// return;
// }
//
// for (Study study : studyList) {
//
// if (study.getStudyTitle().contains("탈퇴")) {
// System.out.printf(" (%d)\n 스터디명 : %s\n", study.getStudyNo(), study.getStudyTitle());
// System.out.println();
// }
//
// else {
// //List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
// //List<Member> bookMem = studyDao.findByBookmarkAll(study.getStudyNo());
// //study.setMembers(guilders);
// //study.setBookMarkMember(bookMem);
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
// System.out.println();
// }
// }
// }
