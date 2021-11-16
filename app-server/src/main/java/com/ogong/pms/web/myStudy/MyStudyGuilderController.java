package com.ogong.pms.web.myStudy;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class MyStudyGuilderController {

  @Autowired
  MemberDao memberDao;
  @Autowired
  StudyDao studyDao;

  @RequestMapping("/mystudy/guilderList")
  protected ModelAndView guilderList(HttpSession session) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    Member member = memberDao.findByNo(loginUser.getPerNo());

    List<Study> studyList = studyDao.findAll();
    List<Study> guilderMembers = new ArrayList<>();

    for (int i = 0; i < studyList.size(); i++) {
      List<Member> guilders = studyDao.findByGuildersAll(studyList.get(i).getStudyNo());
      studyList.get(i).setMembers(guilders);

      for (Member mem : studyList.get(i).getMembers()) {
        if (mem.getPerNo() == member.getPerNo()) {
          if (studyList.get(i).getOwner().getPerNo() != member.getPerNo()) {
            guilderMembers.add(studyList.get(i));
          }
        }
      }
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("member", member);
    mv.addObject("guilderMembers", guilderMembers);
    mv.addObject("pageTitle", "구성원 스터디");
    mv.setViewName("myStudy/MyStudyGuilderList.jsp");

    return mv;
  }

  @RequestMapping("/mystudy/guilderDetail")
  public ModelAndView guilderDetail(HttpSession session, int studyNo) throws Exception {
    Member loginUser = (Member) session.getAttribute("loginUser");
    Member member = memberDao.findByNo(loginUser.getPerNo());
    Study myStudy = studyDao.findByMyNo(studyNo, member.getPerNo());

    List<Member> guilders = studyDao.findByGuildersAll(myStudy.getStudyNo());
    myStudy.setMembers(guilders);

    ModelAndView mv = new ModelAndView();

    mv.addObject("member", member);
    mv.addObject("study", myStudy);

    return mv;
  }
}
