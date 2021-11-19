package com.ogong.pms.web.admin;

import java.util.Collection;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.vo.AskBoard;

@Controller
public class AdminAskBoardController {

  @Autowired AskBoardDao askBoardDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/admin/askboard/list")
  public ModelAndView list() throws Exception {

    Collection<AskBoard> adminAskBoardList = askBoardDao.findAll();

    if (adminAskBoardList == null) {
      throw new Exception("문의 게시글이 존재하지 않습니다.");
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 목록");
    mv.addObject("adminAskBoardList", adminAskBoardList);
    mv.addObject("contentUrl", "admin/AdminAskBoardList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/admin/askboard/detail")
  public ModelAndView detail(int askNo) throws Exception {
    AskBoard adminAskBoard = askBoardDao.findByNo(askNo);

    if (adminAskBoard == null) {
      throw new Exception("문의게시글 상세 오류!");
    }
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 상세");
    mv.addObject("adminAskBoard", adminAskBoard);
    mv.addObject("contentUrl", "admin/AdminAskBoardDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/admin/askboard/delete")
  public ModelAndView delete(int askNo) throws Exception {

    askBoardDao.deletereply(askNo);
    askBoardDao.delete(askNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView(); 

    mv.setViewName("redirect:list");
    return mv;
  }
}







