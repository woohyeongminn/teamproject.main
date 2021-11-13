package com.ogong.pms.web.admin;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AdminDao;

@Controller
public class AdminUpdateFormController {

  @Autowired AdminDao adminDao;
  @Autowired ServletContext sc;

  @GetMapping("/admin/updateForm")
  public ModelAndView updateForm() {

    //Admin admin = adminDao.findByAdminNo(no);

    ModelAndView mv = new ModelAndView();
    //    mv.addObject("admin", admin);
    mv.addObject("pageTitle", "🙂 마이페이지");
    mv.addObject("contentUrl", "admin/AdminUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
