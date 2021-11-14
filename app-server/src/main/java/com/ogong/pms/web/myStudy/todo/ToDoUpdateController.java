package com.ogong.pms.web.myStudy.todo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@Controller
public class ToDoUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @PostMapping("/mystudy/todo/update")
  public ModelAndView todoUpdate(HttpSession session, int studyno, ToDo todo, String progress_no) throws Exception {

    //    Member member = (Member) session.getAttribute("loginUser");

    Study myStudy = studyDao.findByNo(studyno);

    ToDo oldtodo = toDoDao.findByNo(myStudy.getStudyNo(), todo.getTodoNo());

    if (oldtodo == null) {
      throw new Exception("다시 선택하세요.");
    }

    int status = Integer.parseInt(progress_no);

    todo.setTodoStatus(status);

    toDoDao.update(todo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?studyno="+myStudy.getStudyNo());
    return mv;
  }
}
