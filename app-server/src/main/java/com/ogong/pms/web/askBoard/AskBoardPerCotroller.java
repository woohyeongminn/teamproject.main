package com.ogong.pms.web.askBoard;

import java.util.Collection;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;

@Controller
public class AskBoardPerCotroller {

  @Autowired  AskBoardDao askBoardDao;
  @Autowired  SqlSessionFactory sqlSessionFactory;

  @GetMapping("/askboard/permylist")
  public ModelAndView list() throws Exception {

    Collection<AskBoard> myAskBoardList = askBoardDao.findAll();

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 목록");
    mv.addObject("myAskBoardList", myAskBoardList);
    mv.addObject("contentUrl", "askBoard/AskBoardPerMyList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/askboard/permydetail")
  public ModelAndView detail(int askNo) throws Exception {

    AskBoard myAskBoard = askBoardDao.findByNo(askNo);
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 상세");
    mv.addObject("myAskBoard", myAskBoard);
    mv.addObject("contentUrl", "askBoard/AskBoardPerMyDetail.jsp");
    mv.setViewName("template1");

    return mv;
  } 

  @GetMapping("/askboard/perdelete")
  public ModelAndView delete(int askNo) throws Exception { 

    askBoardDao.deletereply(askNo);
    askBoardDao.delete(askNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:permylist");

    return mv;
  }
}







