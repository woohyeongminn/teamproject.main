package com.ogong.pms.web.myStudy;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class MyStudyController {

  @Autowired StudyDao studyDao;

  @GetMapping("/mystudy/list")
  public ModelAndView mystudyList(HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    List<Study> studyList = studyDao.findAll();

    // 조장
    List<Study> ownerStudyList = new ArrayList<>();

    for (Study study : studyList) {
      if (study.getOwner().getPerNo() == loginUser.getPerNo()) {
        ownerStudyList.add(study);
      }
    }

    // 구성원
    List<Study> guilderMembers = new ArrayList<>();
    for (int i = 0; i < studyList.size(); i++) {
      List<Member> guilders = studyDao.findByGuildersAll(studyList.get(i).getStudyNo());
      studyList.get(i).setMembers(guilders);
      for (Member mem : studyList.get(i).getMembers())
        if (mem.getPerNo() == loginUser.getPerNo()) {
          if (studyList.get(i).getOwner().getPerNo()!=loginUser.getPerNo()) {
            guilderMembers.add(studyList.get(i));
          }
        }
    }

    // 대기중
    List<Study> waitingStudyList = new ArrayList<>();
    for (int i = 0; i < studyList.size(); i++) {
      List<Member> waiting = studyDao.findByWaitingGuilderAll(studyList.get(i).getStudyNo());
      studyList.get(i).setWatingMember(waiting);

      for (Member mem : studyList.get(i).getWatingMember()) {
        if (loginUser.getPerNo() == mem.getPerNo()) {
          waitingStudyList.add(studyList.get(i));
        }
      }
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("ownerStudyList", ownerStudyList);
    mv.addObject("guilderMembers", guilderMembers);
    mv.addObject("waitingStudyList", waitingStudyList);
    mv.addObject("pageTitle", "🗃 내 스터디 목록");
    mv.addObject("contentUrl", "myStudy/MyStudyList.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
