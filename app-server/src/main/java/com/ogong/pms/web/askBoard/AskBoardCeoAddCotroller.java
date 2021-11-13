package com.ogong.pms.web.askBoard;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.AskBoard;

// 수정 필요
@Controller
public class AskBoardCeoAddCotroller {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired AskBoardDao askBoardDao;
  @Autowired CeoMemberDao ceoMemberDao;

  @PostMapping("/askboard/ceoadd")
  public ModelAndView add(AskBoard askBoard) throws Exception {

    askBoardDao.insertCeo(askBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 등록");
    mv.addObject("contentUrl", "askBoard/AskBoardCeoAdd.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
