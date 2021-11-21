package com.ogong.pms.web.askBoard;

import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Reply;

@Controller
public class ReplyController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired AskBoardDao askBoardDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/askboard/replyaddform")
  public ModelAndView addForm(int askNo) throws Exception {

    AskBoard askBoard = askBoardDao.findByNo(askNo);

    if (askBoard.getReply() != null) {
      throw new Exception(" >> 이미 등록된 답변이 있습니다.");
    }

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "📖문의글 등록");
    mv.addObject("askBoard", askBoard);
    mv.addObject("contentUrl", "askBoard/ReplyAddForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  @PostMapping("/askboard/replyadd")
  public ModelAndView add(Reply reply ,int askNo) throws Exception {

    AskBoard askBoard = askBoardDao.findByNo(askNo);

    if (askBoard.getReply() != null) {
      throw new Exception(" >> 이미 등록된 답변이 있습니다.");
    }

    askBoard.setReply(reply);

    askBoardDao.insertreply(askBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../admin/askboard/list");
    //    mv.addObject("pageTitle", "📖답변 등록");
    //    mv.addObject("contentUrl", "askBoard/ReplyAdd.jsp");
    //    mv.setViewName("template1");
    return mv;
  }
}
