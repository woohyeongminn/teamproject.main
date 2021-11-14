package com.ogong.pms.web.myStudy;

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
public class MyStudyGuilderDetailController {

  @Autowired  StudyDao studyDao;
  @Autowired  MemberDao memberDao;

  @RequestMapping("/mystudy/guilderDetail")
  public ModelAndView guilderDetail(HttpSession session, int studyNo) throws Exception{

    Member loginUser = (Member) session.getAttribute("loginUser");
    Member member = memberDao.findByNo(loginUser.getPerNo());

    Study myStudy = studyDao.findByMyNo(studyNo, member.getPerNo());

    List<Member> guilders = studyDao.findByGuildersAll(myStudy.getStudyNo());
    myStudy.setMembers(guilders);

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", member);
    mv.addObject("study", myStudy);
    //mv.setViewName("/myStudy/MyStudyGuilderDetail.jsp");
    return mv;
  }
}