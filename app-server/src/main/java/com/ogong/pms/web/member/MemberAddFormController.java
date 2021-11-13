package com.ogong.pms.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;

@Controller
public class MemberAddFormController {

  @Autowired MemberDao memberDao;

  @GetMapping("/member/addform")
  public ModelAndView addForm() {
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "✏회원 가입");
    mv.addObject("contentUrl", "member/PerMemberAddForm.jsp");
    mv.setViewName("template1");
    return mv;

  }
}


