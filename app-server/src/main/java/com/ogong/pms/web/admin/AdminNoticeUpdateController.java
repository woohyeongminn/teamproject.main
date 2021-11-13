package com.ogong.pms.web.admin;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class AdminNoticeUpdateController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired NoticeDao noticeDao;
  @Autowired ServletContext sc;

  @PostMapping("/adminNotice/update")
  public ModelAndView noticeUpdate(AdminNotice adminNotice, Part filepath) throws Exception {

    AdminNotice notice = noticeDao.findByNoticeNo(adminNotice.getAdminNotiNo());

    if (notice == null) {
      throw new Exception(" >> 해당 번호의 공지글을 찾을 수 없습니다.");
    } 

    adminNotice.setAdminNotiFile(notice.getAdminNotiFile());
    adminNotice.setAdminNotiRegisteredDate(notice.getAdminNotiRegisteredDate());

    if (filepath.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      filepath.write(sc.getRealPath("/upload/notice") + "/" + filename);
      notice.setAdminNotiFile(filename); // 실제 저장한 파일명을 데이터 베이스에 저장하도록

      Thumbnails.of(sc.getRealPath("/upload/notice") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/notice") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });

      adminNotice.setAdminNotiFile(filename);
    }

    noticeDao.updateTitle(adminNotice);
    noticeDao.updateContent(adminNotice);
    noticeDao.deletenoticefile(notice.getAdminNotiNo());
    noticeDao.insertFilepath(adminNotice);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;

  }
}