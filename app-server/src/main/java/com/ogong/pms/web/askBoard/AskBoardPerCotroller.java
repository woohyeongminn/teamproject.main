package com.ogong.pms.web.askBoard;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;

@Controller
public class AskBoardPerCotroller {

  @Autowired  AskBoardDao askBoardDao;
  @Autowired  SqlSessionFactory sqlSessionFactory;

  @GetMapping("/askboard/peraddform")
  public ModelAndView addFrom() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "💬문의글 등록");
    mv.addObject("contentUrl", "askBoard/AskBoardPerAddForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/askboard/peradd")
  public ModelAndView add(HttpSession session, HttpServletRequest request, AskBoard askBoard) throws Exception {

    if (askBoard.getAskStatus() == 2) {
      askBoard.setAskTempPW(Integer.parseInt(request.getParameter("password")));
    }

    askBoard.setAskMemberWriter((Member) session.getAttribute("loginUser"));
    askBoardDao.insertPer(askBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:permylist");

    return mv;

  }

  @RequestMapping("/askboard/permylist")
  public ModelAndView list(HttpSession session) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");
    Collection<AskBoard> perMyAskBoardList = askBoardDao.findPerMyAll(loginUser.getPerNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 목록");
    mv.addObject("perMyAskBoardList", perMyAskBoardList);
    mv.addObject("contentUrl", "askBoard/AskBoardPerMyList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @GetMapping("/askboard/permydetail")
  public ModelAndView detail(int askNo) throws Exception {

    AskBoard myAskBoard = askBoardDao.findByNo(askNo);
    ModelAndView mv = new ModelAndView();

    int i = myAskBoard.getAskVeiwCount() + 1;
    myAskBoard.setAskVeiwCount(i);

    askBoardDao.updateViewCount(myAskBoard);
    sqlSessionFactory.openSession().commit();

    mv.addObject("pageTitle", "💬문의글 상세");
    mv.addObject("myAskBoard", myAskBoard);
    mv.addObject("contentUrl", "askBoard/AskBoardPerMyDetail.jsp");
    mv.setViewName("template1");

    return mv;
  } 

  @GetMapping("/askboard/perupdateform")
  public ModelAndView updateForm(int askNo) throws Exception {

    AskBoard perAskBoard = askBoardDao.findByNo(askNo);
    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "💬문의글 수정");
    mv.addObject("perAskBoard", perAskBoard);
    mv.addObject("contentUrl", "askBoard/AskBoardPerUpdateForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/askboard/perupdate")
  public ModelAndView update(AskBoard askBoard) throws Exception {

    AskBoard prevAskBoard = askBoardDao.findByNo(askBoard.getAskNo());
    if (prevAskBoard == null) {
      throw new Exception("해당 번호의 문의글이 없습니다.");
    }
    askBoard.setAskRegisteredDate(prevAskBoard.getAskRegisteredDate());
    askBoardDao.update(askBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:permylist");

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







