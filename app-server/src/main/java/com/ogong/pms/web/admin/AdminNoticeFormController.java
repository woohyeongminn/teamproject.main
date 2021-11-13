package com.ogong.pms.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminNoticeFormController {

  @GetMapping("/adminNotice/form")
  public ModelAndView noticeForm() {

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "🔔 공지게시글 등록");
    mv.addObject("contentUrl", "admin/NoticeAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  } 
}

