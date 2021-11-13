package com.ogong.pms.web.ceoMember;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
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

//@MultipartConfig(maxFileSize = 1024 * 1024 * 10)

@Controller
public class CeoAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired ServletContext sc;

  // 기업 개인
  @PostMapping("/ceomember/add")
  public ModelAndView coeAdd(CeoMember ceoMember, Part ceoPhoto, String[] tel, String site) throws Exception {

    String ceoTel = tel[0] + "-" + tel[1] + "-" + tel[2];
    ceoMember.setCeoTel(ceoTel);

    ceoMember.setCeoEmail(ceoMember.getCeoEmail() +'@'+ site);

    ceoMember.setCeoStatus(CeoMember.CEO);

    if (ceoPhoto.getSize() > 0) {
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
    }

    ceoMemberDao.insert(ceoMember);
    ceoMemberDao.insertCeo(ceoMember);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("Refresh", "1;url=list");
    mv.addObject("pageTitle", "기업 회원가입");
    mv.addObject("contentUrl", "ceoMember/CeoMemberAdd.jsp");
    mv.setViewName("template1");
    return mv;
  }
}