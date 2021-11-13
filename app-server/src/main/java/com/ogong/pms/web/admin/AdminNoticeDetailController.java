package com.ogong.pms.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@Controller
public class AdminNoticeDetailController {

  @Autowired NoticeDao noticeDao;

  @GetMapping("/adminNotice/detail")
  public ModelAndView noticeDetail(int no) throws Exception {

    AdminNotice adminNotice = noticeDao.findByNoticeNo(no);

    if (adminNotice == null) {
      throw new Exception(" >> 해당 번호의 공지글이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("adminNotice", adminNotice);
    mv.addObject("pageTitle", "🔔 공지게시글 상세");
    mv.addObject("contentUrl", "admin/NoticeDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }
}

