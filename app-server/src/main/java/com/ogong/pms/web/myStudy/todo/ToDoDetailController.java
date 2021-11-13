package com.ogong.pms.web.myStudy.todo;

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
public class ToDoDetailController {

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;

  @GetMapping("/mystudy/todo/detail")
  public ModelAndView todoDetail(int perno, int studyno, int todono) throws Exception {

    Member member = memberDao.findByNo(perno);

    Study myStudy = studyDao.findByNo(studyno);

    ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todono);

    ModelAndView mv = new ModelAndView();
    mv.addObject("todo", todo);
    mv.addObject("member", member);
    mv.addObject("study", myStudy);
    mv.addObject("pageTitle", "📋 내 스터디 상세");
    mv.addObject("contentUrl", "myStudy/todo/ToDoDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }
}