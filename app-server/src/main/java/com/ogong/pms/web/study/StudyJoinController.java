package com.ogong.pms.web.study;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class StudyJoinController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/join")
  public ModelAndView join(int studyno, HttpSession session) throws Exception {
    Study study = studyDao.findByNo(studyno);

    // if (study.getStatus() == 2) {
    // throw new Exception(" >> 완료된 스터디 입니다.");
    // }

    List<Member> waitingGuilder = studyDao.findByWaitingGuilderAll(study.getStudyNo());
    study.setWatingMember(waitingGuilder);

    List<Member> guilders = studyDao.findByGuildersAll(study.getStudyNo());
    study.setMembers(guilders);

    // for (Member guilder : study.getMembers()) {
    // if (guilder.getPerNo() == member.getPerNo()) {
    // throw new Exception(" >> 이미 참여 중인 스터디입니다.");
    // }
    // }

    // for (Member memberWating : study.getWatingMember()) {
    // if (memberWating.getPerNo() == member.getPerNo()) {
    // throw new Exception(" >> 이미 승인 대기 중인 스터디입니다.");
    // }
    // }

    // if (study.getMembers().size() == study.getNumberOfPeple()) {
    // throw new Exception(" >> 참여 가능 인원수를 초과하였습니다.");
    // }

    study.getWatingMember().add((Member) session.getAttribute("loginUser"));
    studyDao.insertGuilder(study.getStudyNo(),
        ((Member) session.getAttribute("loginUser")).getPerNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.addObject("study", study);
    // mv.addObject("contentUrl", "study/StudyJoin.jsp");
    mv.setViewName("redirect:list");
    return mv;

    // System.out.println(" >> 참여 신청이 완료되었습니다.\n 승인이 완료될 때까지 기다려 주세요.");
  }
}
