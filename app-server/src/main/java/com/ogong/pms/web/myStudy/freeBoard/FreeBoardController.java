package com.ogong.pms.web.myStudy.freeBoard;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;

@Controller
public class FreeBoardController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StudyDao studyDao;
  @Autowired FreeBoardDao freeBoardDao;
  @Autowired CommentDao commentDao;
  /* @Autowired ServletContext sc; */

  /* 자유 게시판 등록 폼 */
  @GetMapping("/mystudy/freeboard/form")
  public ModelAndView addform(int studyno, HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();

    Member loginUser = (Member) session.getAttribute("loginUser");

    Integer guilderStatus = studyDao.findGuilderStatusByNo(studyno, loginUser.getPerNo());

    if (guilderStatus == 1) {
      mv.addObject("status","waiting");
    }

    Study study = studyDao.findByMyNo(studyno, loginUser.getPerNo());

    mv.addObject("member", loginUser);
    mv.addObject("studyno", studyno);
    mv.addObject("study", study);
    mv.addObject("pageTitle", study.getStudyTitle() + " - 자유 게시판 등록");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardAddForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  /* 자유 게시판 등록 */
  @PostMapping("/mystudy/freeboard/add")
  public ModelAndView add(FreeBoard freeBoard, Part photoFile, HttpSession session)
      throws Exception {

    /* if (photoFile == null) {
      System.out.println("파일 X");
    }

    else if (photoFile.getSize() > 0) {
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
    } */

    freeBoard.setFreeBoardWriter((Member) session.getAttribute("loginUser"));
    System.out.println(freeBoard);

    freeBoardDao.insert(freeBoard);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?studyno=" + freeBoard.getStudyNo());

    return mv;
  }

  /* 자유 게시판 목록 */
  @GetMapping("/mystudy/freeboard/list")
  public ModelAndView list(int studyno, HttpSession session) throws Exception {
    List<FreeBoard> freeBoardList = freeBoardDao.findAll(studyno);

    ModelAndView mv = new ModelAndView();

    Study study = studyDao.findByNo(studyno);
    Member loginUser = (Member) session.getAttribute("loginUser");

    Integer guilderStatus = studyDao.findGuilderStatusByNo(studyno, loginUser.getPerNo());

    if (guilderStatus == 1) {
      mv.addObject("status","waiting");
    }

    mv.addObject("studyno", studyno);
    mv.addObject("study", study);
    mv.addObject("member", loginUser);
    mv.addObject("freeBoardList", freeBoardList);
    mv.addObject("pageTitle", study.getStudyTitle() + " - 자유 게시판 목록");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardList.jsp");
    mv.setViewName("template1");

    return mv;
  }

  /* 자유 게시판 상세 */
  @GetMapping("/mystudy/freeboard/detail")
  public ModelAndView detail(int freeboardno, int studyno, HttpSession session) throws Exception {
    FreeBoard freeBoard = freeBoardDao.findByNo(freeboardno, studyno);

    if (freeBoard == null) {
      throw new Exception("해당 번호의 자유 게시판이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();

    List<Comment> commentList = commentDao.findAll(freeboardno);

    Member loginUser = (Member) session.getAttribute("loginUser");

    Integer guilderStatus = studyDao.findGuilderStatusByNo(studyno, loginUser.getPerNo());

    if (guilderStatus == 1) {
      mv.addObject("status","waiting");
    }

    Study study = studyDao.findByMyNo(studyno, loginUser.getPerNo());

    mv.addObject("member", loginUser);
    mv.addObject("study", study);
    mv.addObject("freeBoard", freeBoard);
    mv.addObject("commentList", commentList);
    mv.addObject("pageTitle", study.getStudyTitle() + " - 자유 게시판 상세");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardDetail.jsp");
    mv.setViewName("template1");

    return mv;
  }

  /* 자유 게시판 수정 폼 */
  @GetMapping("/mystudy/freeboard/updateform")
  public ModelAndView updateform(int freeboardno, int studyno,HttpSession session) throws Exception {
    FreeBoard freeBoard = freeBoardDao.findByNo(freeboardno, studyno);

    Member loginUser = (Member) session.getAttribute("loginUser");

    Integer guilderStatus = studyDao.findGuilderStatusByNo(studyno, loginUser.getPerNo());
    Study study = studyDao.findByMyNo(studyno, loginUser.getPerNo());

    ModelAndView mv = new ModelAndView();

    if (guilderStatus == 1) {
      mv.addObject("status","waiting");
    }

    mv.addObject("freeBoard", freeBoard);
    mv.addObject("study", study);
    mv.addObject("member", loginUser);
    mv.addObject("pageTitle", study.getStudyTitle() + " - 자유 게시판 수정");
    mv.addObject("contentUrl", "myStudy/freeBoard/FreeBoardUpdateForm.jsp");
    mv.setViewName("template1");

    return mv;
  }

  /* 자유 게시판 수정 */
  @PostMapping("/mystudy/freeboard/update")
  public ModelAndView update(FreeBoard freeBoard, Part photoFile) throws Exception {
    FreeBoard oldFreeBoard =
        freeBoardDao.findByNo(freeBoard.getFreeBoardNo(), freeBoard.getStudyNo());

    if (oldFreeBoard == null) {
      throw new Exception("해당 번호의 자유 게시판이 없습니다.");
    }

    /* freeBoard.setFreeBoardFile(oldFreeBoard.getFreeBoardFile());

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
    } */

    freeBoardDao.update(freeBoard, freeBoard.getStudyNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:detail?studyno=" + freeBoard.getStudyNo() + "&freeboardno="
        + freeBoard.getFreeBoardNo());

    return mv;
  }

  /* 자유 게시판 삭제 */
  @GetMapping("/mystudy/freeboard/delete")
  public ModelAndView delete(int freeboardno, int studyno) throws Exception {
    FreeBoard freeBoard = freeBoardDao.findByNo(freeboardno, studyno);

    if (freeBoard == null) {
      throw new Exception("해당 번호의 자유 게시판이 없습니다.");
    }

    /* if (freeBoard.getFreeBoardFile() == null) {
      System.out.println("파일 X");

    } else if (!freeBoard.getFreeBoardFile().isEmpty()) {
      freeBoardDao.deleteFile(freeboardno);
      throw new Exception("게시글의 첨부파일이 모두 삭제됩니다.");
    } */

    freeBoardDao.deleteComment(freeboardno);
    freeBoardDao.delete(freeboardno, studyno);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?studyno=" + studyno);

    return mv;
  }
}
