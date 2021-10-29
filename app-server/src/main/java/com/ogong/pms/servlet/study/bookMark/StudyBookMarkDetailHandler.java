package com.ogong.pms.servlet.study.bookMark;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@WebServlet("/study/bookMarkDetail")
public class StudyBookMarkDetailHandler extends GenericServlet {
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
    out.println("  <title>🌟 내 북마크</title>");
    out.println("  <style>");
    out.println("  label {");
    out.println("    margin-right: 5px;");
    out.println("    text-align: right;");
    // out.println(" display: inline-block;");
    out.println("    width: 60px;");
    out.println("  }");
    out.println("  </style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>▶ 내 북마크 상세</h1>");

    int no = Integer.parseInt(request.getParameter("no"));

    try {
      Study study = studyDao.findByNo(no);

      if (study == null) {
        out.println("해당 번호의 북마크가 없습니다.");

      } else {
        out.println("<form action='update'>");
        out.printf(
            "<label for='f-studyNo'>번호</label> <input id='f-studyNo' type='text' name='studyNo' value='%d' readonly><br>\n",
            study.getStudyNo());
        out.printf(
            "<label for='f-countBookMember'>북마크</label> <input id='f-countBookMember' type='text' name='countBookMember' value='%d' readonly><br>\n",
            study.getCountBookMember());
        out.printf(
            "<label for='f-studyTitle'>제목</label> <input id='f-studyTitle' type='text' name='studyTitle' value='%s'><br>\n",
            study.getStudyTitle());
        out.printf(
            "<label for='f-owner'>조장</label> <input id='f-owner' type='text' name='owner' value='%s'><br>\n",
            study.getOwner().getPerNickname());
        out.printf(
            "<label for='f-subjectName'>분야</label> <input id='f-subjectName' type='text' name='subjectName' value='%s'><br>\n",
            study.getSubjectName());
        out.printf(
            "<label for='f-area'>지역</label> <input id='f-area' type='text' name='area' value='%s'><br>\n",
            study.getArea());
        out.printf(
            "<label for='f-countMember'>인원수</label> <input id='f-countMember' type='text' name='countMember' value='%d' readonly><br>\n",
            study.getCountBookMember());
        out.printf(
            "<label for='f-numberOfPeple'>최대 인원수</label> <input id='f-numberOfPeple' type='text' name='numberOfPeple' value='%d' readonly><br>\n",
            study.getCountBookMember());
        out.printf(
            "<label for='f-faceName'>대면/비대면</label> <input id='f-faceName' type='text' name='faceName' value='%s'><br>\n",
            study.getFaceName());
        out.printf(
            "<label for='f-introduction'>소개글</label> <input id='f-introduction' type='text' name='introduction' value='%s'><br>\n",
            study.getIntroduction());
        out.println();

        // out.println("<button>변경</button>");
        // out.printf(" <a href='delete?no=%d'>[삭제]</a>", study.getStudyNo());
        out.println(" <a href='list'>[목록]</a><br>");
        out.println("</form>");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
}

// @Override
// public void execute(CommandRequest request) throws Exception {
// System.out.println();
// System.out.println("▶ 내 북마크 - 스터디 상세\n");
//
// Member member = AuthPerMemberLoginHandler.getLoginUser();
//
// int inputNo;
// inputNo = Prompt.inputInt(" 번호 : ");
//
// Study study = studyDao.findByBookmark(inputNo, member.getPerNo());
//
// if (study == null) {
// System.out.println(" >> 내가 북마크한 스터디 번호를 입력하세요.");
// return;
// }
//
// System.out.printf(" \n (%s) 🌟%d\n", study.getStudyNo(), study.getCountBookMember());
// System.out.printf(" [%s]\n", study.getStudyTitle());
// System.out.printf(" >> 조장 : %s\n", study.getOwner().getPerNickname());
// System.out.printf(" >> 분야 : %s\n", study.getSubjectName());
// System.out.printf(" >> 지역 : %s\n", study.getArea());
// System.out.printf(" >> 인원수 : %s/%s명\n",
// study.getMembers().size() + 1, study.getNumberOfPeple());
// System.out.printf(" >> 대면/비대면 : %s\n", study.getFaceName());
// System.out.printf(" >> 소개글 : %s\n", study.getIntroduction());
//
// request.setAttribute("inputNo", study.getStudyNo());
//
// System.out.println("\n----------------------");
// System.out.println("1. 참여 신청하기");
// System.out.println("2. 북마크 삭제");
// System.out.println("0. 이전");
//
// while (true) {
// int selectNo = Prompt.inputInt("선택> ");
// switch (selectNo) {
// case 1: request.getRequestDispatcher("/study/join").forward(request); return;
// case 2: request.getRequestDispatcher("/study/bookMarkDelete").forward(request); return;
// case 0: return;
// default :
// System.out.println(" >> 번호를 다시 선택해 주세요.\n");
// }
// }
// }
