package com.ogong.pms.web.myStudy.todo;

import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
public class ToDoUpdateFormController {

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/mystudy/todo/updateform")
  public ModelAndView todoUpdateForm(HttpSession session, int studyno, int todono) throws Exception {

    Member member = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByNo(studyno);

    ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todono);

    ModelAndView mv = new ModelAndView();
    mv.addObject("todo", todo);
    mv.addObject("member", member);
    mv.addObject("study", myStudy);
    mv.addObject("pageTitle", "📋 To-Do List 변경");
    mv.addObject("contentUrl", "myStudy/todo/ToDoUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
