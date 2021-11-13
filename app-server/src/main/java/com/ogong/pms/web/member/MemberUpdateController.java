package com.ogong.pms.web.member;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class MemberUpdateController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;


  @PostMapping("/member/update")
  protected ModelAndView update(Member member, Part photoFile/*, HttpServletResponse res*/) throws Exception {

    Member oldMember = memberDao.findByNo(member.getPerNo());
    if (oldMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    member.setPerPhoto(oldMember.getPerPhoto());
    member.setPerRegisteredDate(oldMember.getPerRegisteredDate());

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPerPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });

      member.setPerPhoto(filename);
    }

    memberDao.updateName(member);
    memberDao.updateNickname(member);
    memberDao.updateEmail(member);
    memberDao.updatePassword(member);
    memberDao.updatePhoto(member);
    memberDao.updateTel(member);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    //mv.setViewName("redirect:detail?no="+ member.getPerNo());
    //res.sendRedirect("detail?no"+member.getPerNo());
    return mv;
  }
}


