package com.ogong.pms.web.ceoMember;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class CeoUpdateController {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;

  // 기업회원 개인정보 수정은 이름,이메일,비밀번호만 가능
  @PostMapping("/ceomember/update")
  public ModelAndView ceoUpdate(CeoMember ceoMember, Part ceoPhoto) throws Exception {

    System.out.println(ceoMember);

    CeoMember oldCeoMember = ceoMemberDao.findByNo(ceoMember.getCeoNo());

    if (oldCeoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    // 사진
    //    if (ceoPhoto.getSize() > 0) {
    if (ceoPhoto != null) {
      String filename = UUID.randomUUID().toString();
      ceoPhoto.write(sc.getRealPath("/upload/ceoMember") + "/" + filename);
      ceoMember.setCeoPhoto(filename);

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(40, 40)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_40x40";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/ceoMember") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      ceoMember.setCeoPhoto(filename);
      ceoMember.setCeoRegisteredDate(oldCeoMember.getCeoRegisteredDate());

    } else {
      // 기존 정보로 
      ceoMember.setCeoPhoto(oldCeoMember.getCeoPhoto());
      ceoMember.setCeoRegisteredDate(oldCeoMember.getCeoRegisteredDate());
    }

    //      ceoMember.setCeoName(request.getParameter("name"));
    //      ceoMember.setCeoNickname(request.getParameter("nickname"));
    //      ceoMember.setCeoTel(request.getParameter("tel"));
    //      ceoMember.setCeoEmail(request.getParameter("email"));
    //      ceoMember.setCeoPassword(request.getParameter("password"));

    ceoMemberDao.updateCeoMember(ceoMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;

  }
}