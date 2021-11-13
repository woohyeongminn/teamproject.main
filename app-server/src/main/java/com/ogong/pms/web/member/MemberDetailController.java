package com.ogong.pms.web.member;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@Controller
public class MemberDetailController {

  @Autowired  MemberDao memberDao;

  @GetMapping("/member/detail")
  public ModelAndView detail(HttpSession session) throws Exception {

    Member loginPer = (Member) session.getAttribute("loginUser");

    if (loginPer == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    Member perMember = memberDao.findByNo(loginPer.getPerNo());

    if (perMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("perMember", perMember);
    mv.addObject("pageTitle", "📜 마이페이지");
    mv.addObject("contentUrl", "member/PerMemberDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }
}
