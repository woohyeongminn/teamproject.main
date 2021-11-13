package com.ogong.pms.web.myStudy;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class MyStudyDeleteController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/delete")
  public ModelAndView delete(int studyno) throws Exception {
    Study study = studyDao.findByNo(studyno);

    // if (study.getOwner().getPerNo() != member.getPerNo()) {
    // throw new Exception(" >> 삭제 권한이 없습니다.");
    //
    // } else {
    // if (study.getCountMember() > 0) {
    // throw new Exception(" >> 구성원이 있는 스터디는 삭제할 수 없습니다.");
    //
    // } else if (study.getWatingCountMember() > 0) {
    // System.out.println(" >> 승인 대기 중인 구성원이 없어야 스터디 삭제가 가능합니다.");
    //
    // studyDao.updateGuilderExpulsionAll(study.getStudyNo());
    // System.out.println(" >> 구성원을 모두 삭제하였습니다.\n");
    // }
    // }

    studyDao.updateStatusDelete(study);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    mv.setViewName("redirect:list");

    return mv;
  }
}
