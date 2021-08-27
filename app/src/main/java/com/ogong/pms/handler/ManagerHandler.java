package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Manager;
import com.ogong.util.Prompt;

public class ManagerHandler {

  List<Manager> managerList;

  public ManagerHandler(List<Manager> managerList) {
    this.managerList = managerList;
  }

  public void add() {
    System.out.println("[공지사항]");

    Manager manager = new Manager();

    manager.setHostNo(Prompt.inputInt("번호? "));
    manager.setHostTitle(Prompt.inputString("제목? "));
    manager.setHostContent(Prompt.inputString("내용? "));
    manager.setHostWriter(Prompt.inputString("작성자? "));
    manager.setHostRegisteredDate(new Date(System.currentTimeMillis()));

    managerList.add(manager);
  }

  public void update() {
    System.out.println("[공지사항 변경]");
    int hostNo = Prompt.inputInt("번호? ");

    Manager manager = findByHostNo(hostNo);

    if (manager == null) {
      System.out.println("해당 번호의 공지글이 없습니다.");
      return;
    }

    String managerTitle = Prompt.inputString(String.format("제목(%s)? ", manager.getHostTitle()));
    String managerContent = Prompt.inputString(String.format("내용(%s)? ", manager.getHostContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("문의글 변경을 취소하였습니다.");
      return;
    }

    manager.setHostTitle(managerTitle);
    manager.setHostContent(managerContent);
    System.out.println("공지글을 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[공지사항 삭제]");
    int hostNo = Prompt.inputInt("번호? ");

    Manager manager = findByHostNo(hostNo);

    if (manager == null) {
      System.out.println("해당 번호의 공지글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("문의글 삭제를 취소하였습니다.");
      return;
    }

    managerList.remove(manager);

    System.out.println("문의글을 삭제하였습니다.");
  }

  public Manager findByHostNo(int hostNo) {
    Manager[] arr = managerList.toArray(new Manager[0]);
    for (Manager manager : arr) {
      if (manager.getHostNo() == hostNo) {
        return manager;
      }
    }
    return null;
  }
}
