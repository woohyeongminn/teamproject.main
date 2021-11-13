package com.ogong.pms.web.ceoMember;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

@Controller
public class CeoUpdateFormController  {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired ServletContext sc;

  @PostMapping("/ceomember/updateform")
  public ModelAndView ceoUpdateForm(CeoMember ceoMember) throws Exception {
    //    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");
    //    CeoMember CeoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());
    //
    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("ceoMember", ceoMember);
    mv.addObject("pageTitle", "🙂 마이페이지 - 프로필 수정");
    mv.addObject("contentUrl", "ceoMember/CeoMemberUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;
  }
}







