package com.ogong.pms.web.admin;

import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.domain.Admin;

@Controller
public class AdminController {

  @Autowired AdminDao adminDao;
  @Autowired ServletContext sc;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/admin/detail")
  public ModelAndView detail() {

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "🙂 마이페이지");
    mv.addObject("contentUrl", "admin/AdminDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/admin/updateForm")
  public ModelAndView updateForm() {

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "🙂 마이페이지");
    mv.addObject("contentUrl", "admin/AdminUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/admin/update")
  public ModelAndView update(Admin admin) throws Exception {

    Admin adminInfo = adminDao.findByAdminNo(admin.getMasterNo());

    if (adminInfo == null) {
      throw new Exception(" >> 다시 선택해 주세요.");
    } 

    adminDao.updateAdmin(admin);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:logout");
    return mv;
  }
}
