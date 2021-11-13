package com.ogong.pms.web.askBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;

@Controller
public class AskBoardPerAddCotroller {

  @Autowired SqlSessionFactory sqlSeesionFactory;
  @Autowired AskBoardDao askBoardDao;
  //@Autowired MemberDao memberDao;

  @PostMapping("/askboard/peradd")
  public void add(AskBoard askBoard, HttpSession session, HttpServletRequest req) throws Exception {

    int no = Integer.parseInt(req.getParameter("password"));
    askBoard.setAskMemberWriter((Member) session.getAttribute("loginUser"));
    askBoard.setAskTempPW(no);
    askBoardDao.insertPer(askBoard);
    sqlSeesionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 등록");
    mv.addObject("contentUrl", "askBoard/AskBoardPerAdd.jsp");
    mv.setViewName("template1");
    //mv.setViewName("redirect:permylist");

  }
}
