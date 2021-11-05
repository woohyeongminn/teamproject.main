package com.ogong.pms.handler.myStudy.freeBoard;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.FreeBoardFile;
import com.ogong.util.Prompt;

public class PromptFreeBoard {

  FreeBoardDao freeBoardDao;
  CommentDao commentDao;
  SqlSession sqlSession;

  public PromptFreeBoard(CommentDao commentDao, FreeBoardDao freeBoardDao, SqlSession sqlSession) {
    this.freeBoardDao = freeBoardDao;
    this.commentDao = commentDao;
    this.sqlSession = sqlSession;
  }

  public void printComments(FreeBoard freeboard) throws Exception {
    System.out.println();
    System.out.println("============= 댓글 =============");


    List<Comment> commentList = commentDao.findAll(freeboard.getFreeBoardNo());

    for (Comment comment : commentList) {
      System.out.printf(" (%d) | 내용 : %s | 작성자 : %s | 등록일 : %s\n",
          comment.getCommentNo(),
          comment.getCommentText(),
          comment.getCommentWiter().getPerNickname(),
          comment.getCommentRegisteredDate());
    }

    if (commentList.isEmpty()) {
      System.out.println(" >> 등록된 댓글이 없습니다.");
    }
  }

  public void addFile(FreeBoard freeBoard) throws Exception {
    List<FreeBoardFile> fileList = new ArrayList<>();
    while (true) {
      FreeBoardFile fileName = new FreeBoardFile();
      System.out.println();
      String inputFileName = Prompt.inputString(" 첨부파일 (다음:Enter) : ");

      if (inputFileName.equals("")) {
        break;
      }
      fileName.setAtcFileName(inputFileName);
      fileList.add(fileName);
    }

    if (fileList.isEmpty()) {
      System.out.println(" >> 등록할 첨부파일이 없습니다.");
      return;
    }

    freeBoard.setFreeBoardFile(fileList);

    String input = Prompt.inputString("\n 첨부파일을 추가하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 첨부파일 추가를 취소하였습니다.");
      return;
    }

    if (!freeBoard.getFreeBoardFile().isEmpty()) {
      for (FreeBoardFile file : freeBoard.getFreeBoardFile()) {
        freeBoardDao.insertFile(file, freeBoard.getFreeBoardNo());
      }
      sqlSession.commit();
    }
  }

  public void deleteFile(FreeBoard freeBoard) throws Exception {

    if (freeBoard.getFreeBoardFile().isEmpty()) {
      System.out.println(" >> 삭제할 파일이 없습니다.");
      return;
    }

    System.out.printf("\n 첨부파일 : %d개" , freeBoard.getCountFile());

    for (int i = 0; i < freeBoard.getFreeBoardFile().size(); i++) {
      System.out.println();
      String input = Prompt.inputString(" 첨부파일(" + freeBoard.getFreeBoardFile().get(i).getAtcFileName() + ") / 삭제(D), 다음(Enter)> : "); 

      if (input.equalsIgnoreCase("")) {
        continue;
      }

      if (input.equalsIgnoreCase("d")) {
        String input2 = Prompt.inputString("\n 정말 삭제하시겠습니까? (네 / 아니오) ");

        if (!input2.equalsIgnoreCase("네")) {
          System.out.println(" >> 변경을 취소되었습니다.");
          continue;
        }

        int fileNo = freeBoard.getFreeBoardFile().get(i).getAtcFileNo();
        freeBoardDao.deleteFileOne(fileNo, freeBoard.getFreeBoardNo());
        System.out.printf(" 첨부파일(%s)이 삭제되었습니다.\n" , freeBoard.getFreeBoardFile().get(i).getAtcFileName());

      } else {
        System.out.println(" >> 명령어가 올바르지 않습니다.");
      } 
    }
  }
}