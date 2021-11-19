package com.ogong.pms.web.ceoMember;

import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.util.RandomPw;
import com.ogong.pms.util.SendMail;
import com.ogong.pms.vo.CeoMember;

@Controller
public class CeoMemberFindIdPwController  {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;


  @GetMapping("/ceomember/findemailform")
  public ModelAndView findEmailForm() throws Exception {

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "내 정보 찾기");
    mv.addObject("contentUrl", "ceoMember/FindEmailForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @RequestMapping("/ceomember/findemail")
  public ModelAndView findEmail(String[] tel, String ceoName) throws Exception {
    ModelAndView mv = new ModelAndView();

    String ceoTel = tel[0] + "-" + tel[1] + "-" + tel[2];
    CeoMember ceoMember = ceoMemberDao.findEmailByNameAndTel(ceoName, ceoTel);

    if (ceoMember != null) {
      String findemail = 
          ceoMember.getCeoEmail().replace(
              ceoMember.getCeoEmail().substring(
                  ceoMember.getCeoEmail().lastIndexOf("@"),ceoMember.getCeoEmail().length()),
              "*****");

      System.out.println(ceoMember);
      mv.addObject("pageTitle", "🔎이메일 찾기완료");
      mv.addObject("findemail", findemail);
      mv.addObject("ceoMember", ceoMember);
      mv.addObject("contentUrl", "ceoMember/FindEmail.jsp");
      mv.setViewName("template1");

    } else {
      mv.addObject("pageTitle", "⚠정보 오류");
      mv.addObject("refresh", "2;url=findemailform");
      mv.addObject("contentUrl", "ceoMember/InputFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  } 

  @GetMapping("/ceomember/getpwbyemailform")
  public ModelAndView getPwByEmailForm() throws Exception {

    ModelAndView mv = new ModelAndView();

    mv.addObject("pageTitle", "내 정보 찾기");
    mv.addObject("contentUrl", "ceoMember/GetPwByEmailForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/ceomember/getpwbyemail")
  public ModelAndView getPwByEmail(CeoMember ceoMember, String site) throws Exception {
    ModelAndView mv = new ModelAndView();
    SendMail sendMail = new SendMail();

    String ceoName = ceoMember.getCeoName();
    String ceoEmail = ceoMember.getCeoEmail() + "@" + site;

    CeoMember findCeoMember = ceoMemberDao.findByNameAndEmail(ceoName, ceoEmail);

    RandomPw randomPw = new RandomPw();

    if (findCeoMember != null) {
      String pw = randomPw.randomPw();      
      findCeoMember.setCeoPassword(pw);

      sendMail.sendMail(ceoEmail, pw);
      ceoMemberDao.updatePassword(findCeoMember);
      sqlSessionFactory.openSession().commit();

      mv.addObject("refresh", "2;url=form");
      mv.addObject("pageTitle", "📧임시 비밀번호 발급");
      mv.addObject("ceoMember", findCeoMember);
      mv.addObject("contentUrl", "ceoMember/GetPwByEmail.jsp");
      mv.setViewName("template1");

    } else {
      mv.addObject("pageTitle", "⚠정보 오류");
      mv.addObject("refresh", "2;url=getpwbyemailform");
      mv.addObject("contentUrl", "ceoMember/InputFail.jsp");
      mv.setViewName("template1");
    }
    return mv;
  }
}