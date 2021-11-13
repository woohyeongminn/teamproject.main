package com.ogong.pms.web.study;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Study;

@Controller
public class StudySearchController {

  @Autowired
  StudyDao studyDao;

  @GetMapping("/study/search")
  public ModelAndView search(String where, String keyword) throws Exception {
    // String where = (String) session.getAttribute("where");
    // String keyword = (String) session.getAttribute("keyword");

    if (where.equals("1")) {
      where = "ss.name";

    } else if (where.equals("2")) {
      where = "s.name";

    } else if (where.equals("3")) {
      where = "s.area";
    }

    List<Study> studyList = studyDao.findByKeyword(where, keyword);

    /*
     * if (studyList.isEmpty()) { throw new Exception("해당 검색어의 스터디가 존재하지 않습니다."); }
     *
     * for (Study searchStudy : studyList) { System.out.printf(" \n (%s)\n",
     * searchStudy.getStudyNo()); System.out.printf(" [%s]\n", searchStudy.getStudyTitle());
     * System.out.printf(" >> 조장 : %s\n", searchStudy.getOwner().getPerNickname());
     * System.out.printf(" >> 분야 : %s\n", searchStudy.getSubjectName());
     * System.out.printf(" >> 지역 : %s\n", searchStudy.getArea());
     * System.out.printf(" >> 인원수 : %s/%s명\n", searchStudy.getCountMember(),
     * searchStudy.getNumberOfPeple()); System.out.printf(" >> 대면 : %s\n",
     * searchStudy.getFaceName()); System.out.printf(" >> 소개글 : %s\n",
     * searchStudy.getIntroduction()); count++; }
     * 
     * if (count == 0) { throw new Exception(" >> 검색어를 다시 입력해 주세요."); }
     */

    ModelAndView mv = new ModelAndView();

    mv.addObject("studyList", studyList);
    mv.addObject("pageTitle", "스터디 목록");
    mv.addObject("contentUrl", "study/StudyList.jsp");
    mv.setViewName("template1");

    return mv;
  }
}
