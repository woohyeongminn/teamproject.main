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
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;

@Controller
public class ToDoAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired StudyDao studyDao;
  @Autowired MemberDao memberDao;
  @Autowired ToDoDao toDoDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @PostMapping("/mystudy/todo/add")
  public ModelAndView todoAdd(ToDo todo, HttpSession session, int studyno) throws Exception {

    Member loginUser = (Member) session.getAttribute("loginUser");

    if (loginUser == null) {
      throw new Exception("로그인 한 회원이 없습니다.");
    }

    Member member = memberDao.findByNo(loginUser.getPerNo());

    Study myStudy = studyDao.findByNo(studyno);

    if (myStudy.getStudyTitle() == null) {
      throw new Exception(" >> 가입된 스터디가 없습니다.");
    }

    toDoDao.insert(todo);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?studyno="+myStudy.getStudyNo()+member.getPerNo());
    return mv;

  }
}
