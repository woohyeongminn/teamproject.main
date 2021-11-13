package com.ogong.pms.web.admin;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

@Controller
public class AdminNoticeDeleteController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired NoticeDao noticeDao;

  @GetMapping("/adminNotice/delete")
  public ModelAndView noticeDelete(int no) throws Exception {

    AdminNotice notice = noticeDao.findByNoticeNo(no);

    if (notice == null) {
      throw new Exception (" >> 해당 번호의 공지글이 없습니다.");
    }

    noticeDao.deletenoticefile(no);
    noticeDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }
}
