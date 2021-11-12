package com.ogong.pms.web.study;

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
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@WebServlet("/study/join")
public class StudyJoinController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MemberDao memberDao;
  StudyDao studyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      Member member = memberDao.findByNo(loginUser.getPerNo());

      int studyNo = Integer.parseInt(request.getParameter("studyno"));
      Study study = studyDao.findByNo(studyNo);

      // if (study.getStatus() == 2) {
      // throw new Exception(" >> 완료된 스터디 입니다.");
      // }

      List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
      study.setWatingMember(waitingGuilder);

      List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
      study.setMembers(guilders);

      // for (Member guilder : study.getMembers()) {
      // if (guilder.getPerNo() == member.getPerNo()) {
      // throw new Exception(" >> 이미 참여 중인 스터디입니다.");
      // }
      // }

      // for (Member memberWating : study.getWatingMember()) {
      // if (memberWating.getPerNo() == member.getPerNo()) {
      // throw new Exception(" >> 이미 승인 대기 중인 스터디입니다.");
      // }
      // }

      // if (study.getMembers().size() == study.getNumberOfPeple()) {
      // throw new Exception(" >> 참여 가능 인원수를 초과하였습니다.");
      // }

      study.getWatingMember().add(member);

      studyDao.insertGuilder(study.getStudyNo(), member.getPerNo());
      sqlSession.commit();

      // request.setAttribute("member", member);
      request.setAttribute("study", study);
      response.sendRedirect("list");
      // request.getRequestDispatcher("/study/StudyJoin.jsp").forward(request, response);

      // System.out.println(" >> 참여 신청이 완료되었습니다.\n 승인이 완료될 때까지 기다려 주세요.");

    } catch (Exception e) {
      e.printStackTrace();
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
