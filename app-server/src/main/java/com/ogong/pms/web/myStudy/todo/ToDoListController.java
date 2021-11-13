package com.ogong.pms.web.myStudy.todo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@Controller
public class ToDoListController {

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;

  @GetMapping("/mystudy/todo/list")
  public ModelAndView todoList(int perno, int studyno) throws Exception {

    Member member = memberDao.findByNo(perno);

    Study myStudy = studyDao.findByNo(studyno);
    List<ToDo> todoList = toDoDao.findAll(myStudy.getStudyNo());

    List<ToDo> countProgressing = new ArrayList<>();
    for (ToDo todo : todoList) {
      if (todo.getTodoStatus() == ToDo.PROGRESSING) {
        todo.setTodocomplete(getStatusToDo(todo.getTodoStatus()));
        countProgressing.add(todo);

      } else if (todo.getTodoStatus() == ToDo.COMPLETE) {
        todo.setTodocomplete(getStatusToDo(todo.getTodoStatus()));
        countProgressing.add(todo);
      }
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", member);
    mv.addObject("study", myStudy);
    mv.addObject("countProgressing", countProgressing);
    mv.addObject("pageTitle", "📋 To-Do List 목록");
    mv.addObject("contentUrl", "myStudy/todo/ToDoList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  // 상태 꺼내기
  private String getStatusToDo(int todoStatus) {
    switch (todoStatus) {
      case 1:
        return "○";
      case 2:
        return "✔";
      default:
        return null;
    }
  }
}