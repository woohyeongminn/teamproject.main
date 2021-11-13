package com.ogong.pms.web.myStudy.freeBoard;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class FreeBoardUpdateController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;
  @Autowired
  ServletContext sc;
  // @Autowired PromptFreeBoard promptFreeBoard;

  @PostMapping("/freeboard/update")
  public ModelAndView update(FreeBoard freeBoard, Part photoFile) throws Exception {
    // int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));

    FreeBoard oldFreeBoard =
        freeBoardDao.findByNo(freeBoard.getFreeBoardNo(), freeBoard.getStudyNo());

    if (oldFreeBoard == null) {
      throw new Exception("해당 번호의 자유 게시판이 없습니다.");
    }

    // freeBoard.setFreeBoardTitle(request.getParameter("title"));
    // freeBoard.setFreeBoardContent(request.getParameter("content"));
    freeBoard.setFreeBoardFile(oldFreeBoard.getFreeBoardFile());

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/freeboard") + "/" + filename);
      freeBoard.setFreeBoardFile(filename);

      Thumbnails.of(sc.getRealPath("/upload/freeboard") + "/" + filename).size(20, 20)
      .outputFormat("jpg").crop(Positions.CENTER).toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/freeboard") + "/" + filename).size(100, 100)
      .outputFormat("jpg").crop(Positions.CENTER).toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });

      freeBoard.setFreeBoardFile(filename);
    }

    freeBoardDao.update(freeBoard, freeBoard.getStudyNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:detail?studyno=" + freeBoard.getStudyNo() + "&freeboardno="
        + freeBoard.getFreeBoardNo());

    return mv;
  }
}
