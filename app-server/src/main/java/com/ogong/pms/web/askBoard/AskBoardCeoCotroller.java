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
public class AskBoardCeoCotroller {

  @Autowired  AskBoardDao askBoardDao;
  @Autowired  SqlSessionFactory sqlSessionFactory;

  @GetMapping("/askboard/ceomylist")
  public ModelAndView list() throws Exception {

    Collection<AskBoard> myAskBoardList = askBoardDao.findAll();

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 목록");
    mv.addObject("myAskBoardList", myAskBoardList);
    mv.addObject("contentUrl", "askBoard/AskBoardCeoMyList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/askboard/ceomydetail")
  public ModelAndView detail(int askNo) throws Exception {
    AskBoard myAskBoard = askBoardDao.findByNo(askNo);

    if (myAskBoard == null) {
      throw new Exception("문의게시글 상세 오류!");
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 상세");
    mv.addObject("myAskBoard", myAskBoard);
    mv.addObject("contentUrl", "askBoard/AskBoardCeoMyDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/askboard/ceodelete")
  public ModelAndView delete(int askNo) throws Exception { 

    askBoardDao.deletereply(askNo);
    askBoardDao.delete(askNo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:ceomylist");

    return mv;
  }
}







