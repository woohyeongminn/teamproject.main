package com.ogong.pms.web.myStudy.freeBoard;

import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class FreeBoardAddController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;
  @Autowired
  ServletContext sc;

  @PostMapping("/mystudy/freeboard/add")
  public ModelAndView add(FreeBoard freeBoard, Part photoFile, HttpSession session)
      throws Exception {
    // FreeBoard freeBoard = new FreeBoard();

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
    }

    freeBoard.setStudyNo(((Study) session.getAttribute("studyno")).getStudyNo());
    freeBoard.setFreeBoardWriter((Member) session.getAttribute("loginUser"));
    // freeBoard.setFreeBoardTitle(request.getParameter("title"));
    // freeBoard.setFreeBoardContent(request.getParameter("content"));
    System.out.println(freeBoard);

    freeBoardDao.insert(freeBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?studyno=" + freeBoard.getStudyNo());

    return mv;
  }
}
