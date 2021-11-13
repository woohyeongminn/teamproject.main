package com.ogong.pms.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@Controller
public class AdminNoticeUpdateFormController {

  @Autowired NoticeDao noticeDao;

  @GetMapping("/adminNotice/Updateform")
  public ModelAndView noticeUpdateForm(int no) throws Exception {

    AdminNotice notice = noticeDao.findByNoticeNo(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("notice", notice);
    mv.addObject("pageTitle", "🔔 공지게시글 변경");
    mv.addObject("contentUrl", "admin/NoticeUpdateform.jsp");
    mv.setViewName("template1");
    return mv;

  }
}
