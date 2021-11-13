package com.ogong.pms.web.askBoard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AskBoardPerAddFormCotroller {

  @GetMapping("/askboard/peraddform")
  public ModelAndView addFrom() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "💬문의글 등록");
    mv.addObject("contentUrl", "askBoard/AskBoardPerAddForm.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
