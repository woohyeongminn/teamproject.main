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

  //  public void add() {
  //    System.out.println("[공지사항]");
  //
  //    NoticeBoard notice = new NoticeBoard();
  //
  //    notice.setNotiNo(Prompt.inputInt("번호? "));
  //    notice.setNotiTitle(Prompt.inputString("제목? "));
  //    notice.setNotiContent(Prompt.inputString("내용? "));
  //    notice.setNotiWriter(Prompt.inputString("작성자? "));
  //    notice.setNotiRegisteredDate(new Date(System.currentTimeMillis()));
  //
  //    noticeBoardList.add(notice);
  //  }

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

  //    public void update() {
  //      System.out.println("[공지사항 변경]");
  //      int notiNo = Prompt.inputInt("번호? ");
  //  
  //      NoticeBoard notice = findByNo(notiNo);
  //  
  //      if (notice == null) {
  //        System.out.println("해당 번호의 게시글이 없습니다.");
  //        return;
  //      }
  //  
  //      String notiTitle = Prompt.inputString(String.format("제목(%s)? ", notice.getNotiTitle()));
  //      String notiContent = Prompt.inputString(String.format("내용(%s)? ", notice.getNotiContent()));
  //  
  //      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
  //      if (input.equalsIgnoreCase("n") || input.length() == 0) {
  //        System.out.println("게시글 변경을 취소하였습니다.");
  //        return;
  //      }
  //  
  //      notice.setNotiTitle(notiTitle);
  //      notice.setNotiContent(notiContent);
  //      System.out.println("게시글을 변경하였습니다.");
  //    }
  //  
  //    public void delete() {
  //      System.out.println("[공지사항 삭제]");
  //      int notiNo = Prompt.inputInt("번호? ");
  //  
  //      NoticeBoard notice = findByNo(notiNo);
  //  
  //      if (notice == null) {
  //        System.out.println("해당 번호의 게시글이 없습니다.");
  //        return;
  //      }
  //  
  //      String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
  //      if (input.equalsIgnoreCase("n") || input.length() == 0) {
  //        System.out.println("게시글 삭제를 취소하였습니다.");
  //        return;
  //      }
  //  
  //      noticeBoardList.remove(notice);
  //  
  //      System.out.println("게시글을 삭제하였습니다.");
  //    }

  //  public void findByHostNo() {
  //    System.out.println("[공지사항]");
  //    int hostNo = Prompt.inputInt("번호: ");
  //
  //    Manager manager = managerHandler.findByHostNo(hostNo);
  //
  //    if (manager == null) {
  //      System.out.println("해당 번호의 공지글이 없습니다.");
  //      return;
  //    }
  //  }
}







