package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Manager;
import com.ogong.pms.domain.NoticeBoard;
import com.ogong.util.Prompt;

public class NoticeBoardHandler {

  List<NoticeBoard> noticeBoardList;
  List<Manager> manaerList;
  ManagerHandler managerHandler;

  public NoticeBoardHandler(List<NoticeBoard> noticeBoardList, List<Manager> manaerList, ManagerHandler managerHandler) {
    this.noticeBoardList = noticeBoardList;
    this.manaerList = manaerList;
    this.managerHandler = managerHandler;
  }

  public void list() {
    System.out.println("[공지사항 목록]");

    Manager[] managers = manaerList.toArray(new Manager[0]);

    for (Manager manager : managers) {
      System.out.printf("%d, %s, %s, %s\n", 
          manager.getHostNo(), 
          manager.getHostTitle(), 
          manager.getHostWriter(),
          manager.getHostRegisteredDate());
    }
  }

  public void detail() {
    System.out.println("[공지사항 상세보기]");
    int inputHostNo = Prompt.inputInt("번호? ");

    Manager manager = managerHandler.findByHostNo(inputHostNo);

    if (manager == null) {
      System.out.println("해당 번호의 공지글이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", manager.getHostTitle());
    System.out.printf("내용: %s\n", manager.getHostContent());
    System.out.printf("작성자: %s\n", manager.getHostWriter());
    System.out.printf("등록일: %s\n", manager.getHostRegisteredDate());
  }

  public void findByHostNo() {
    System.out.println("[공지사항]");
    int hostNo = Prompt.inputInt("번호: ");

    Manager manager = managerHandler.findByHostNo(hostNo);

    if (manager == null) {
      System.out.println("해당 번호의 공지글이 없습니다.");
      return;
    }
  }
}







