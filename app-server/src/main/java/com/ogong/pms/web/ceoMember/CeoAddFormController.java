package com.ogong.pms.web.ceoMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CeoMemberDao;

@Controller
public class CeoAddFormController{

  @Autowired CeoMemberDao ceoMemberDao;

  // 기업 회원가입
  @GetMapping("/ceomember/addform")
  public ModelAndView ceoAddForm() {

    ModelAndView mv = new ModelAndView();

    // 중복체크 하려고?
    //Collection<CeoMember> ceoMemberList  = ceoMemberDao.findAll();
    //request.setAttribute("ceoMemberList", ceoMemberList);

    mv.addObject("pageTitle", "기업 회원가입");
    mv.addObject("contentUrl", "ceoMember/CeoMemberAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }
}