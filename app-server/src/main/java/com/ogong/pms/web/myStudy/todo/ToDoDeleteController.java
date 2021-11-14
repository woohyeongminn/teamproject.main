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
public class ToDoDeleteController {

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/mystudy/todo/delete")
  public ModelAndView todoDelete(HttpSession session, int studyno, int todono) throws Exception {


    Member member = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByNo(studyno);

    ToDo todo = toDoDao.findByNo(myStudy.getStudyNo(), todono);

    toDoDao.delete(todo.getTodoNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?perno="+member.getPerNo()+"&studyno="+myStudy.getStudyNo());
    return mv;
  }
}
