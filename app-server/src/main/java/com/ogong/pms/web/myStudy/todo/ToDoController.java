package com.ogong.pms.web.myStudy.todo;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@Controller
public class ToDoController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/mystudy/todo/addform")
  public ModelAndView todoAddForm(HttpSession session, int studyno) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    List<ToDo> todoList = toDoDao.findAll(studyno);

    Study myStudy = studyDao.findByNo(studyno);

    ModelAndView mv = new ModelAndView();
    mv.addObject("member", member);
    mv.addObject("studyno", studyno);
    mv.addObject("study", myStudy);
    mv.addObject("todoList", todoList);
    mv.addObject("pageTitle", myStudy.getStudyTitle() + " - " + "📋 To-Do List 등록");
    mv.addObject("contentUrl", "myStudy/todo/ToDoAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/mystudy/todo/add")
  public ModelAndView todoAdd(ToDo todo, HttpSession session) throws Exception {

    todo.setTodoWriter((Member) session.getAttribute("loginUser"));

    Study myStudy = studyDao.findByNo(todo.getStudyNo());

    if (myStudy.getStudyTitle() == null) {
      throw new Exception(" >> 가입된 스터디가 없습니다.");
    }

    toDoDao.insert(todo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?studyNo="+myStudy.getStudyNo());
    return mv;
  }

  @GetMapping("/mystudy/todo/list")
  public ModelAndView todoList(HttpSession session, int studyno) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");

    ModelAndView mv = new ModelAndView();
    if (loginUser == null) {
      mv.setViewName("redirect:../login");
      return mv;
    }

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

    mv.addObject("member", loginUser);
    mv.addObject("study", myStudy);
    mv.addObject("countProgressing", countProgressing);
    mv.addObject("pageTitle", myStudy.getStudyTitle() + " - " + "📋 To-Do List 목록");
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

  @GetMapping("/mystudy/todo/detail")
  public ModelAndView todoDetail(int perno, int studyno, int todono) throws Exception {

    Member member = memberDao.findByNo(perno);

    Study myStudy = studyDao.findByNo(studyno);

    ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todono);

    ModelAndView mv = new ModelAndView();
    mv.addObject("todo", todo);
    mv.addObject("member", member);
    mv.addObject("study", myStudy);
    mv.addObject("pageTitle", myStudy.getStudyTitle() + " - " + "📋 To-Do List 상세");
    mv.addObject("contentUrl", "myStudy/todo/ToDoDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  //  @GetMapping("/mystudy/todo/updateform")
  //  public ModelAndView todoUpdateForm(HttpSession session, int studyno, int todono) throws Exception {
  //
  //    Member member = (Member) session.getAttribute("loginUser");
  //
  //    Study myStudy = studyDao.findByNo(studyno);
  //
  //    ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todono);
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.addObject("todo", todo);
  //    mv.addObject("member", member);
  //    mv.addObject("study", myStudy);
  //    mv.addObject("pageTitle", "📋 To-Do List 변경");
  //    mv.addObject("contentUrl", "myStudy/todo/ToDoUpdateForm.jsp");
  //    mv.setViewName("template1");
  //    return mv;
  //  }
  //
  //  @PostMapping("/mystudy/todo/update")
  //  public ModelAndView todoUpdate(HttpSession session, int studyno, ToDo todo, String progress_no) throws Exception {
  //
  //    Study myStudy = studyDao.findByNo(studyno);
  //
  //    ToDo oldtodo = toDoDao.findByNo(myStudy.getStudyNo(), todo.getTodoNo());
  //
  //    if (oldtodo == null) {
  //      throw new Exception("다시 선택하세요.");
  //    }
  //
  //    int status = Integer.parseInt(progress_no);
  //
  //    todo.setTodoStatus(status);
  //
  //    toDoDao.update(todo);
  //    sqlSessionFactory.openSession().commit();
  //
  //    ModelAndView mv = new ModelAndView();
  //    mv.setViewName("redirect:list?studyno="+myStudy.getStudyNo());
  //    return mv;
  //  }

  @GetMapping("/mystudy/todo/delete")
  public ModelAndView todoDelete(HttpSession session, int studyno, int todono) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByNo(studyno);

    ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todono);

    toDoDao.delete(todo.getTodoNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../detail?studyNo="+myStudy.getStudyNo());
    // mv.setViewName("redirect:list?perno="+member.getPerNo()+"&studyno="+myStudy.getStudyNo());
    return mv;
  }
}
