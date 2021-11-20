package com.ogong.pms.web.admin;

import java.util.Collection;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;

@Controller
public class AdminAskBoardController {

  @Autowired AskBoardDao askBoardDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @RequestMapping("/admin/askboard/list")
  public ModelAndView list(@RequestParam(defaultValue = "1") int pageNo, 
      @RequestParam(defaultValue = "10") int pageSize) throws Exception {
    ModelAndView mv = new ModelAndView();

    int count = askBoardDao.count();

    if (pageSize < 5 || pageSize > 10) {
      pageSize = 10;
    }

    int totalPage = count / pageSize + ((count % pageSize) > 0 ? 1 : 0);

    if (pageNo < 1 || pageNo > totalPage) {
      pageNo = 1;
    }

    HashMap<String,Object> params = new HashMap<>();
    params.put("offset", pageSize * (pageNo - 1));
    params.put("length", pageSize);    

    Collection<AskBoard> adminAskBoardList = askBoardDao.findAll(params);

    mv.addObject("pageTitle", "💬문의글 목록");
    mv.addObject("totalPage", totalPage);
    mv.addObject("pageNo", pageNo);
    mv.addObject("pageSize", pageSize);
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







