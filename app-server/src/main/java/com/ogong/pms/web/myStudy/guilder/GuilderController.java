package com.ogong.pms.web.myStudy.guilder;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CalendarDao;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class GuilderController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired CommentDao commentDao;
  @Autowired FreeBoardDao freeBoardDao;
  @Autowired ToDoDao toDoDao;
  @Autowired CalendarDao calendarDao;

  // 스터디 구성원 목록(참여 중인 구성원)
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

    //    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(myStudy.getStudyNo());
    //    if (!waitingGuilder.isEmpty()) {
    //      myStudy.setWaitingMember(waitingGuilder);
    //      mv.addObject("waitingGuilderList", waitingGuilder);
    //    }

    Integer guilderStatus = studyDao.findGuilderStatusByNo(studyNo, loginUser.getPerNo());

    if (guilderStatus == 1) {
      mv.addObject("status","waiting");
    }

    mv.addObject("study", myStudy);
    mv.addObject("member", loginUser);
    mv.addObject("contentUrl", "myStudy/guilder/GuilderList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  //스터디 구성원 목록
  @GetMapping("/mystudy/guilder/waitinglist")
  public ModelAndView waitinglist(HttpSession session, int studyNo) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    Study myStudy = studyDao.findByMyNo(studyNo, loginUser.getPerNo());

    ModelAndView mv = new ModelAndView();

    //   List<Member> guilders = studyDao.findByGuildersAll(myStudy.getStudyNo());    
    //   if (!guilders.isEmpty()) {
    //     myStudy.setMembers(guilders);
    //     mv.addObject("guildersList", guilders);
    //   }

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(myStudy.getStudyNo());
    if (!waitingGuilder.isEmpty()) {
      myStudy.setWaitingMember(waitingGuilder);
      mv.addObject("waitingGuilderList", waitingGuilder);
    }

    Integer guilderStatus = studyDao.findGuilderStatusByNo(studyNo, loginUser.getPerNo());

    if (guilderStatus == 1) {
      mv.addObject("status","waiting");
    }

    mv.addObject("study", myStudy);
    mv.addObject("contentUrl", "myStudy/guilder/WaitingGuilderList.jsp");
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

    commentDao.deleteByMemberNo(loginUser.getPerNo());

    List<FreeBoard> freeBoardList = freeBoardDao.findAllByMemberNo(loginUser.getPerNo());
    for (FreeBoard freeBoard : freeBoardList) {
      freeBoardDao.deleteFile(freeBoard.getFreeBoardNo());
    }

    freeBoardDao.deleteByMemberNo(loginUser.getPerNo());
    toDoDao.deleteByMemberNo(loginUser.getPerNo());
    calendarDao.deleteByMemberNo(loginUser.getPerNo());
    studyDao.deleteGuilder(myStudy.getStudyNo(), loginUser.getPerNo());
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

    commentDao.deleteByMemberNo(guilderMemberNo);

    List<FreeBoard> freeBoardList = freeBoardDao.findAllByMemberNo(guilderMemberNo);
    for (FreeBoard freeBoard : freeBoardList) {
      freeBoardDao.deleteFile(freeBoard.getFreeBoardNo());
    }

    freeBoardDao.deleteByMemberNo(guilderMemberNo);
    toDoDao.deleteByMemberNo(guilderMemberNo);
    calendarDao.deleteByMemberNo(guilderMemberNo);
    Study myStudy = studyDao.findByNo(studyNo);

    studyDao.deleteGuilder(myStudy.getStudyNo(), guilderMemberNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?&studyNo="+studyNo+"#tab1");

    return mv;
  }

  // 대기중인 사람 승인
  @PostMapping("/mystudy/guilder/agree")
  public ModelAndView agree(
      int watingMemberNo, 
      int studyNo, 
      HttpSession session) throws Exception {

    studyDao.updateGuilder(studyNo, watingMemberNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?&studyNo="+studyNo+"#tab2");

    return mv;
  }

  // 대기중인 사람 거절
  @PostMapping("/mystudy/guilder/disagree")
  public ModelAndView disagree(
      int watingMemberNo, 
      int studyNo, 
      HttpSession session) throws Exception {

    studyDao.deleteGuilder(studyNo, watingMemberNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?&studyNo="+studyNo+"#tab2");

    return mv;
  }

}
