package com.ogong.pms.web.member;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;

@Controller
public class MemberAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @PostMapping("/member/add")
  protected ModelAndView add(Member member) throws Exception {

    //    if (photoFile.getSize() > 0) {
    //      String filename = UUID.randomUUID().toString();
    //      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
    //      member.setPhoto(filename);
    //
    //      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
    //      .size(20, 20)
    //      .outputFormat("jpg")
    //      .crop(Positions.CENTER)
    //      .toFiles(new Rename() {
    //        @Override
    //        public String apply(String name, ThumbnailParameter param) {
    //          return name + "_20x20";
    //        }
    //      });
    //
    //      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
    //      .size(100, 100)
    //      .outputFormat("jpg")
    //      .crop(Positions.CENTER)
    //      .toFiles(new Rename() {
    //        @Override
    //        public String apply(String name, ThumbnailParameter param) {
    //          return name + "_100x100";
    //        }
    //      });
    //    }

    memberDao.insert(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=form");
    mv.addObject("pageTitle", "👋환영 합니다!");
    mv.addObject("contentUrl", "member/PerMemberAdd.jsp");
    mv.setViewName("template1");
    return mv;

  }
}


