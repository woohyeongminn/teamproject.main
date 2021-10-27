package com.ogong.pms.servlet.study.bookMark;

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

@WebServlet("/study/bookMarkList")
public class StudyBookMarkListHandler extends GenericServlet {
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
    out.println("  <title>🌟 내 스크랩</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>▶ 내 북마크 - 스터디 목록</h1>");
    // out.println("<a href='form'>북마크 추가</a><br>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>북마크</th>");
    out.println("    <th>제목</th>");
    out.println("    <th>분야</th>");
    out.println("    <th>인원수</th>");
    out.println("    <th>최대 인원수</th>");
    out.println("    <th>조장</th>");
    out.println("    <th>대면/비대면</th>");
    out.println("  <tr>");
    out.println("</thread>");
    out.println("<tbody>");
    try {
      Collection<Study> studyList = studyDao.findAll();

      for (Study study : studyList) {
        out.printf("<tr>"
            + "<td>%d</td>"
            + "<td>%d</td>"
            + " <td><a href='detail?no=%1$d'>%s</a></td>"
            + " <td>%s</td>"
            + " <td>%d</td>"
            + " <td>%d</td>"
            + " <td>%s</td>"
            + " <td>%s</td>"
            + "</tr>", 
            study.getStudyNo(), 
            study.getCountBookMember(), 
            study.getStudyTitle(), 
            study.getSubjectName(), 
            study.getCountMember(),
            study.getNumberOfPeple(),
            study.getOwner().getPerNickname(),
            study.getFaceName());
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

//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    System.out.println();
//    System.out.println("▶ 내 북마크 - 스터디 목록");
//    System.out.println();
//
//    Member member = AuthPerMemberLoginHandler.getLoginUser();
//    if (member == null ) {
//      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
//      return;
//    }
//
//    List<Study> studyList = studyDao.findByMyBookmark(member.getPerNo());
//
//    int count = 0;
//
//    for (Study study : studyList) {
//      // 북마크 있는 경우
//
//      if (study.getStudyTitle().contains("탈퇴")) {
//        System.out.printf(" (%d)\n 스터디명 : %s\n",
//            study.getStudyNo(),
//            study.getStudyTitle());
//        System.out.println();
//      }
//      //      List<Member> bookMem = studyDao.findByBookmarkAll(study.getStudyNo());
//      //      study.setBookMarkMember(bookMem);
//      System.out.printf(" (%d) 🌟%d \n [%s] | 분야 : %s | 인원수 : %s/%s명 | 조장 : %s | 대면/비대면 : %s\n",
//          study.getStudyNo(),
//          study.getCountBookMember(),
//          study.getStudyTitle(),
//          study.getSubjectName(),
//          study.getCountMember(),
//          study.getNumberOfPeple(),
//          study.getOwner().getPerNickname(),
//          study.getFaceName());
//      System.out.println();
//      count++;
//    }
//
//    if (count == 0) {
//      System.out.println(" >> 북마크한 스터디가 없습니다.");
//      return;
//    }
//
//    System.out.println("----------------------");
//    System.out.println("1. 상세");
//    System.out.println("0. 이전");
//
//    while (true) {
//      int selectNo = Prompt.inputInt("선택> ");
//      switch (selectNo) {
//        case 1:request.getRequestDispatcher("/study/bookMarkDetail").forward(request); return;
//        case 0: return;
//        default : 
//          System.out.println(" >> 번호를 다시 선택해 주세요.\n");
//      }
//    }
//  }
