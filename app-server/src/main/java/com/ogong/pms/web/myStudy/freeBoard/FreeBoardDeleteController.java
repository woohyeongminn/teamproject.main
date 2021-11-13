package com.ogong.pms.web.myStudy.freeBoard;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.FreeBoard;

@Controller
public class FreeBoardDeleteController {

  @Autowired
  SqlSessionFactory sqlSessionFactory;
  @Autowired
  FreeBoardDao freeBoardDao;

  @GetMapping("/freeboard/delete")
  public ModelAndView delete(int freeboardno, int studyno) throws Exception {
    // int studyNo = Integer.parseInt(request.getParameter("studyno"));
    // int freeBoardNo = Integer.parseInt(request.getParameter("freeboardno"));

    FreeBoard freeBoard = freeBoardDao.findByNo(freeboardno, studyno);

    if (freeBoard == null) {
      throw new Exception("해당 번호의 자유 게시판이 없습니다.");
    }

    if (!freeBoard.getFreeBoardFile().isEmpty()) {
      throw new Exception("게시글의 첨부파일이 모두 삭제됩니다.");
    }

    freeBoardDao.deleteComment(freeboardno);
    freeBoardDao.deleteFile(freeboardno);
    freeBoardDao.delete(freeboardno, studyno);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();

    mv.setViewName("redirect:list?studyno=" + studyno);

    return mv;
  }
}
