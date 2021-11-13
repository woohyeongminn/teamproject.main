package com.ogong.pms.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;

@Controller
public class MemberDetailController {

  @Autowired  MemberDao memberDao;

  @GetMapping("/member/detail")
  public ModelAndView detail() {

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "📜 마이페이지");
    mv.addObject("contentUrl", "member/PerMemberDetail.jsp");
    mv.setViewName("template1");
    return mv;

  }
}
