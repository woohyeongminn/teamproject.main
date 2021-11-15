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
public class AskBoardAllCotroller {

  @Autowired AskBoardDao askBoardDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/askboard/alllist")
  public ModelAndView list() throws Exception {

    ModelAndView mv = new ModelAndView();

    Collection<AskBoard> askBoardList = askBoardDao.findAll();

    mv.addObject("pageTitle", "💬문의글 목록");
    mv.addObject("askBoardList", askBoardList);
    mv.addObject("contentUrl", "askBoard/AskBoardAllList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/askboard/alldetail")
  public ModelAndView detail(int askNo) throws Exception {

    AskBoard askBoard = askBoardDao.findByNo(askNo);

    if (askBoard == null) {
      throw new Exception("문의게시글 상세 오류!");
    }

    int i = askBoard.getAskVeiwCount() + 1;
    askBoard.setAskVeiwCount(i);

    askBoardDao.updateViewCount(askBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 상세");
    mv.addObject("askBoard", askBoard);
    mv.addObject("contentUrl", "askBoard/AskBoardAllDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
