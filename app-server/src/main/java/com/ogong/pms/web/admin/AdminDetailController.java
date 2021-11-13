package com.ogong.pms.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDetailController {

  @GetMapping("/admin/detail")
  public ModelAndView detail() {

    //      Admin adminpro = adminDao.findByAdminNo(no);
    //
    //      if (adminpro == null) {
    //        throw new Exception(" >> 다시 선택해 주세요.");
    //      }
    //
    //      request.setAttribute("adminpro", adminpro);


    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "🙂 마이페이지");
    mv.addObject("contentUrl", "admin/AdminDetail.jsp");
    mv.setViewName("template1");
    return mv;

  }
}
