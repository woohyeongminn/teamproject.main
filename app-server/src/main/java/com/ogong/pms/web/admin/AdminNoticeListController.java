package com.ogong.pms.web.admin;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@Controller
public class AdminNoticeListController {

  @Autowired NoticeDao noticeDao;

  @GetMapping("/adminNotice/list")
  public ModelAndView noticeList() throws Exception {

    Collection<AdminNotice> adminNoticeList = noticeDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("adminNoticeList", adminNoticeList);
    mv.addObject("pageTitle", "🔔 공지게시글 목록");
    mv.addObject("contentUrl", "admin/NoticeList.jsp");
    mv.setViewName("template1");
    return mv;
  }
}