package com.ogong.pms.web.myStudy.todo;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.ToDo;

@Controller
public class ToDoAddFormController {

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;

  @GetMapping("/mystudy/todo/addform")
  public ModelAndView todoAddForm(HttpSession session, int studyno) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    List<ToDo> todoList = toDoDao.findAll(studyno);

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", member);
    mv.addObject("studyno", studyno);
    mv.addObject("todoList", todoList);
    mv.addObject("pageTitle", "📋 To-Do List 등록");
    mv.addObject("contentUrl", "myStudy/todo/ToDoAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
