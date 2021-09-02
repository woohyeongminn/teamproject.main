package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.NoticeBoard;
import com.ogong.util.Prompt;

public class NoticeBoardHandler {

  List<NoticeBoard> noticeBoardList;
  ManagerHandler managerHandler;

  public NoticeBoardHandler(List<NoticeBoard> noticeBoardList, ManagerHandler managerHandler) {
    this.noticeBoardList = noticeBoardList;
    this.managerHandler = managerHandler;
  }

  public void list() {
    System.out.println("▶ 공지 목록");

    for (NoticeBoard noticeboard : noticeBoardList) {
      System.out.printf("%d, %s, %s, %s\n", 
          noticeboard.getNotiNo(), 
          noticeboard.getNotiTitle(), 
          noticeboard.getNotiWriter(),
          noticeboard.getNotiRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("▶ 공지 상세");
    int inputNotiNo = Prompt.inputInt("번호? ");

    NoticeBoard noticeboard = managerHandler.findByNotiNo(inputNotiNo);

    if (noticeboard == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    System.out.printf("제목: %s\n", noticeboard.getNotiTitle());
    System.out.printf("내용: %s\n", noticeboard.getNotiContent());
    System.out.printf("작성자: %s\n", noticeboard.getNotiWriter());
    System.out.printf("등록일: %s\n", noticeboard.getNotiRegisteredDate());
  }

  public void findByNotiNo() {
    System.out.println("▶ 공지사항");
    int inputNo = Prompt.inputInt("번호: ");

    NoticeBoard noticeboard = managerHandler.findByNotiNo(inputNo);

    if (noticeboard == null) {
      System.out.println("해당 번호의 공지글이 없습니다.");
      return;
    }
  }
}







