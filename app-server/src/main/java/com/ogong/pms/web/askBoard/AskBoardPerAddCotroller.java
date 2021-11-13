package com.ogong.pms.web.askBoard;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.AskBoard;

@Controller
public class AskBoardPerAddCotroller {

  @Autowired SqlSessionFactory sqlSeesionFactory;
  @Autowired AskBoardDao askBoardDao;
  @Autowired MemberDao memberDao;

  @PostMapping("/askboard/peradd")
  public void add(AskBoard askBoard) throws Exception {

    askBoardDao.insertPer(askBoard);
    sqlSeesionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 등록");
    mv.addObject("contentUrl", "askBoard/AskBoardPerAdd.jsp");
    mv.setViewName("template1");

  }
}
