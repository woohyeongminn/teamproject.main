package com.ogong.pms.web.askBoard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AskBoardCeoAddFormCotroller {

  @GetMapping("/askboard/ceoaddform")
  public ModelAndView addForm() {

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 등록");
    mv.addObject("contentUrl", "askBoard/AskBoardCeoAddForm.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
