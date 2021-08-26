package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.NoticeBoard;
import com.ogong.util.Prompt;

public class NoticeBoardHandler {

  List<NoticeBoard> noticeBoardList;

  public NoticeBoardHandler(List<NoticeBoard> noticeBoardList) {
    this.noticeBoardList = noticeBoardList;
  }

  public void add() {
    System.out.println("[공지사항]");

    NoticeBoard notice = new NoticeBoard();

    notice.setNo(Prompt.inputInt("번호? "));
    notice.setTitle(Prompt.inputString("제목? "));
    notice.setContent(Prompt.inputString("내용? "));
    notice.setWriter(Prompt.inputString("작성자? "));
    notice.setRegisteredDate(new Date(System.currentTimeMillis()));

    noticeBoardList.add(notice);
  }

  public void list() {
    System.out.println("[공지사항 목록]");

    NoticeBoard[] notices = new NoticeBoard[noticeBoardList.size()];

    noticeBoardList.toArray(notices);

    for (NoticeBoard notice : notices) {
      System.out.printf("%d, %s, %s, %s\n", 
          notice.getNo(), 
          notice.getTitle(), 
          notice.getWriter(),
          notice.getRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[공지사항 상세보기]");
    int no = Prompt.inputInt("번호? ");

    NoticeBoard notice = findByNo(no);

    if (notice == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", notice.getTitle());
    System.out.printf("내용: %s\n", notice.getContent());
    System.out.printf("작성자: %s\n", notice.getWriter());
    System.out.printf("등록일: %s\n", notice.getRegisteredDate());
  }

  //  public void update() {
  //    System.out.println("[공지사항 변경]");
  //    int no = Prompt.inputInt("번호? ");
  //
  //    NoticeBoard notice = findByNo(no);
  //
  //    if (notice == null) {
  //      System.out.println("해당 번호의 게시글이 없습니다.");
  //      return;
  //    }
  //
  //    String title = Prompt.inputString(String.format("제목(%s)? ", notice.getTitle()));
  //    String content = Prompt.inputString(String.format("내용(%s)? ", notice.getContent()));
  //
  //    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
  //    if (input.equalsIgnoreCase("n") || input.length() == 0) {
  //      System.out.println("게시글 변경을 취소하였습니다.");
  //      return;
  //    }
  //
  //    notice.setTitle(title);
  //    notice.setContent(content);
  //    System.out.println("게시글을 변경하였습니다.");
  //  }
  //
  //  public void delete() {
  //    System.out.println("[공지사항 삭제]");
  //    int no = Prompt.inputInt("번호? ");
  //
  //    NoticeBoard notice = findByNo(no);
  //
  //    if (notice == null) {
  //      System.out.println("해당 번호의 게시글이 없습니다.");
  //      return;
  //    }
  //
  //    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
  //    if (input.equalsIgnoreCase("n") || input.length() == 0) {
  //      System.out.println("게시글 삭제를 취소하였습니다.");
  //      return;
  //    }
  //
  //    noticeBoardList.remove(notice);
  //
  //    System.out.println("게시글을 삭제하였습니다.");
  //  }

  private NoticeBoard findByNo(int no) {
    NoticeBoard[] arr = noticeBoardList.toArray(new NoticeBoard[0]);
    for (NoticeBoard notice : arr) {
      if (notice.getNo() == no) {
        return notice;
      }
    }
    return null;
  }
}







