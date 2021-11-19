package com.ogong.pms.web.admin;

import java.util.Collection;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.vo.CeoMember;

@Controller
public class AdminCeoMemberController {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  // 기업회원 목록
  @GetMapping("/admin/ceomember/list")
  public ModelAndView ceoMemberList() throws Exception {

    Collection<CeoMember> ceoMemberList  = ceoMemberDao.findAll();

    ModelAndView mv = new ModelAndView();

    mv.addObject("ceoMemberList", ceoMemberList);
    mv.addObject("pageTitle", "🏢 기업 회원");
    mv.addObject("contentUrl", "admin/AdminCeoMemberList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  //기업회원 상세
  @GetMapping("/admin/ceomember/detail")
  public ModelAndView ceoMemberDetail(int ceono) throws Exception {

    CeoMember ceoMember = ceoMemberDao.findByNo(ceono);

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    ModelAndView mv = new ModelAndView();

    mv.addObject("ceoMember", ceoMember);

    mv.addObject("pageTitle", " 🏢 기업 회원");
    mv.addObject("contentUrl", "admin/AdminCeoMemberDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  // 기업회원 탈퇴시키기
  @GetMapping("/admin/ceomember/delete")
  public ModelAndView ceoMemberDelete(int ceono) throws Exception {

    CeoMember ceoMember = ceoMemberDao.findByNo(ceono);

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    ceoMember.setCeoName("Deleted Member("+ ceoMember.getCeoName() +")");
    ceoMember.setCeoNickname("Deleted Member("+ ceoMember.getCeoNickname() +")");
    ceoMember.setCeoBossName("Deleted Member("+ ceoMember.getCeoBossName() +")");
    ceoMember.setCeoEmail("Deleted Email");
    ceoMember.setCeoPassword("Deleted Password");
    ceoMember.setCeoPhoto("Deleted Photo");
    ceoMember.setCeoLicenseNo("Deleted LicenseNo");
    ceoMember.setCeoStatus(CeoMember.CEO);
    ceoMember.setActive(CeoMember.OUTUSER);

    ceoMemberDao.updateActive(ceoMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

}
