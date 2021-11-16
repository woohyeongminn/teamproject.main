package com.ogong.pms.web.myStudy;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@Controller
public class MyStudyDetailController {

  @Autowired StudyDao studyDao;
  @Autowired ToDoDao toDoDao;

  @GetMapping("/mystudy/detail")
  public ModelAndView mystudyDetail(HttpSession session, int studyNo) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByMyNo(studyNo, loginUser.getPerNo());
    List<ToDo> todoList = toDoDao.findAll(myStudy.getStudyNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", loginUser);
    mv.addObject("study", myStudy);
    mv.addObject("todoList", todoList);
    mv.addObject("pageTitle", "🗃 내 스터디 상세");
    mv.addObject("contentUrl", "myStudy/MyStudyDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
