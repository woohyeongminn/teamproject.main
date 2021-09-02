package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.NoticeBoard;
import com.ogong.util.Prompt;

public class ManagerHandler {

  List<NoticeBoard> noticeList;
  AdminHandler adminHandler;

  public ManagerHandler(List<NoticeBoard> noticeList, AdminHandler adminHandler) {
    this.noticeList = noticeList;
    this.adminHandler = adminHandler;

    NoticeBoard noticeboard = new NoticeBoard();
    noticeboard.setNotiNo(1);
    noticeboard.setNotiTitle("회원 가입을 환영합니다!");
    noticeboard.setNotiContent("열공! 열공!");
    noticeboard.setNotiWriter("관리자");
    noticeboard.setNotiRegisteredDate(new Date(System.currentTimeMillis()));

    noticeList.add(noticeboard);
  }

  public void add() {
    System.out.println();
    System.out.println("▶ 공지 등록");

    NoticeBoard noticeboard = new NoticeBoard();

    noticeboard.setNotiNo(Prompt.inputInt("번호? "));
    noticeboard.setNotiTitle(Prompt.inputString("제목? "));
    noticeboard.setNotiContent(Prompt.inputString("내용? "));
    noticeboard.setNotiWriter(Prompt.inputString("작성자? "));
    noticeboard.setNotiRegisteredDate(new Date(System.currentTimeMillis()));

    noticeList.add(noticeboard);
  }

  public void update() {
    System.out.println();
    System.out.println("▶ 공지 변경");
    int notiNo = Prompt.inputInt("번호? ");

    NoticeBoard noticeboard = findByNotiNo(notiNo);

    if (noticeboard == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    String noticeTitle = Prompt.inputString(String.format("제목(%s)? ", noticeboard.getNotiTitle()));
    String noticeContent = Prompt.inputString(String.format("내용(%s)? ", noticeboard.getNotiContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("변경이 취소되었습니다.");
      return;
    }

    noticeboard.setNotiTitle(noticeTitle);
    noticeboard.setNotiContent(noticeContent);
    System.out.println("공지가 변경되었습니다.");
  }

  public void delete() {
    System.out.println("▶ 공지 삭제");
    int notiNo = Prompt.inputInt("번호? ");

    NoticeBoard noticeboard = findByNotiNo(notiNo);

    if (noticeboard == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("삭제가 취소되었습니다.");
      return;
    }

    noticeList.remove(noticeboard);

    System.out.println("공지가 삭제되었습니다.");
  }

  public NoticeBoard findByNotiNo(int notiNo) {
    for (NoticeBoard noticeBoard : noticeList) {
      if (noticeBoard.getNotiNo() == notiNo) {
        return noticeBoard;
      }
    }
    return null;
  }
}
