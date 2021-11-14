package com.ogong.pms.web.myStudy.guilder;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class GuilderController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;

  // 스터디 구성원 목록
  @GetMapping("/mystudy/guilder/list")
  public ModelAndView list(HttpSession session, int studyNo) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    Study myStudy = studyDao.findByMyNo(studyNo, loginUser.getPerNo());

    ModelAndView mv = new ModelAndView();

    List<Member> guilders = studyDao.findByGuildersAll(myStudy.getStudyNo());    
    if (!guilders.isEmpty()) {
      myStudy.setMembers(guilders);
      mv.addObject("guildersList", guilders);
    }

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(myStudy.getStudyNo());
    if (!waitingGuilder.isEmpty()) {
      myStudy.setWatingMember(waitingGuilder);
      mv.addObject("waitingGuilderList", waitingGuilder);
    }

    mv.addObject("study", myStudy);
    mv.addObject("contentUrl", "myStudy/guilder/GuilderList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  //조장 권한 위임(탈퇴)
  @PostMapping("/mystudy/guilder/entrustexit")
  public ModelAndView entrustexit(
      int guilderMemberNo, 
      int studyNo, 
      HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    Study myStudy = studyDao.findByNo(studyNo);

    // 조장 권한 넘겨주고 본인은 스터디에서 탈퇴
    studyDao.updateOwner(myStudy.getStudyNo(), guilderMemberNo);
    sqlSessionFactory.openSession().commit();

    studyDao.updateGuilderExpulsion(myStudy.getStudyNo(), loginUser.getPerNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:../list#tab1");

    return mv;
  }

  //조장 권한 위임(탈퇴x)
  @PostMapping("/mystudy/guilder/entrust")
  public ModelAndView entrust(
      int guilderMemberNo, 
      int studyNo, 
      HttpSession session) throws Exception {

    Study myStudy = studyDao.findByNo(studyNo);

    // 조장 권한 넘겨주고 본인은 구성원으로 돌아가기
    studyDao.updateOwner(myStudy.getStudyNo(), guilderMemberNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?&studyNo="+studyNo+"#tab1");

    return mv;
  }

  //구성원 탈퇴시키기
  @PostMapping("/mystudy/guilder/delete")
  public ModelAndView delete(
      int guilderMemberNo, 
      int studyNo, 
      HttpSession session) throws Exception {

    Study myStudy = studyDao.findByNo(studyNo);

    studyDao.updateGuilderExpulsion(myStudy.getStudyNo(), guilderMemberNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?&studyNo="+studyNo+"#tab1");

    return mv;
  }

}
