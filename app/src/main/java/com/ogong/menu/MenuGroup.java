package com.ogong.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.util.Prompt;


public class MenuGroup extends Menu {

  static Stack<Menu> breadCrumb = new Stack<>();

  Menu[] childs = new Menu[100];
  int size;
  boolean disablePrevMenu;
  String prevMenuTitle = "이전 메뉴";
  static public boolean successLogin = false;

  // 이전으로 이동시키는 메뉴를 표현하기 위해 만든 클래스
  private static class PrevMenu extends Menu {
    public PrevMenu() {
      super("");
    }
    @Override
    public void execute() {
    }
  }
  static PrevMenu prevMenu = new PrevMenu();

  public MenuGroup(String title) {
    super(title);
  }

  public MenuGroup(String title, boolean disablePrevMenu) {
    super(title);
    this.disablePrevMenu = disablePrevMenu;
  }

  public MenuGroup(String title, int no) {
    super(title);
    this.enableState = no;
  }

  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }

  public void add(Menu child) {
    if (this.size == this.childs.length) {
      return; 
    }
    this.childs[this.size++] = child; 
  }

  public Menu remove(Menu child) {
    int index = indexOf(child);
    if (index == -1) {
      return null;
    }
    for (int i = index + 1; i < this.size; i++) {
      this.childs[i - 1] = this.childs[i];
    }
    childs[--this.size] = null;
    return child;
  }

  public int indexOf(Menu child) {
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i] == child) {
        return i;
      }
    }
    return -1;
  }

  // 배열에 들어 있는 Menu 객체를 찾는다.
  public Menu getMenu(String title) { 
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i].title.equals(title)) {
        return this.childs[i];
      }
    }
    return null;
  }

  @Override 
  public void execute() {
    breadCrumb.push(this);

    while (true) {
      if (successLogin) {
        successLogin = false;
        breadCrumb.pop();
        return;
      }

      printBreadCrumbMenuTitle();
      List<Menu> menuList = getMenuList();
      printMenuList(menuList);

      try {
        Menu menu = selectMenu(menuList);
        if (menu == null) {
          System.out.println("무효한 메뉴 번호입니다.");
          continue;
        }
        if (menu instanceof PrevMenu) {
          breadCrumb.pop();
          return;
        }

        menu.execute();

      } catch (Exception e) {
        System.out.println("-----------------------------------------");
        System.out.printf ("오류 발생: %s\n", e.getClass().getName());
        e.printStackTrace();   // 왜 에러 떳는지 알려준다.
        System.out.println("-----------------------------------------");
      }
    }
  }


  private String getBreadCrumb() {
    String path = "";

    for (int i = 0; i < breadCrumb.size(); i++) {
      if (path.length() > 0) {
        path += " / ";
      }
      Menu menu = breadCrumb.get(i); 
      path += menu.title;
    }

    // 메인, 관리자 메뉴는 안보이게 (로그아웃 전에 메인, 관리자를 못가게해야함)
    if (AuthPerMemberLoginHandler.getLoginUser() != null) {
      path += " - " + AuthPerMemberLoginHandler.getLoginUser().getPerNickname();
    }

    return path;
  }

  // 출력될 메뉴 목록 준비
  // 왜?
  // - 메뉴 출력 속도를 빠르게 하기 위함.
  // - 메뉴를 출력할 때 출력할 메뉴와 출력하지 말아야 할 메뉴를 구분하는
  //   시간을 줄이기 위함.
  private List<Menu> getMenuList() {
    ArrayList<Menu> menuList = new ArrayList<>();
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i].enableState == Menu.ENABLE_LOGOUT &&
          AuthPerMemberLoginHandler.getLoginUser() == null) {
        menuList.add(this.childs[i]);

      } else if (this.childs[i].enableState == Menu.ENABLE_LOGIN
          && AuthPerMemberLoginHandler.getLoginUser() != null) {
        menuList.add(this.childs[i]);

      } else if (this.childs[i].enableState == Menu.ENABLE_ADMINLOGOUT
          && AuthAdminLoginHandler.getLoginAdmin() == null) {
        menuList.add(this.childs[i]);

      } else if (this.childs[i].enableState == Menu.ENABLE_ADMINLOGIN
          && AuthAdminLoginHandler.getLoginAdmin() != null) {
        menuList.add(this.childs[i]);

      } else if (this.childs[i].enableState == Menu.ENABLE_ALL) {
        menuList.add(this.childs[i]);
      }
    }
    return menuList;
  }

  private void printBreadCrumbMenuTitle() {
    System.out.printf("\n[%s]\n", getBreadCrumb());
  }

  private void printMenuList(List<Menu> menuList) {
    int i = 1;
    for (Menu menu : menuList) {
      System.out.printf("%d. %-20s\n", i++ , menu.title);
    }

    if (!disablePrevMenu) {
      System.out.printf("0. %s\n", this.prevMenuTitle);
    }
  }

  private Menu selectMenu(List<Menu> menuList)  {
    int menuNo = Prompt.inputInt("선택> ");

    if (menuNo < 0 || menuNo > menuList.size()) {
      return null;
    }

    if (menuNo == 0 && !disablePrevMenu) {
      return prevMenu;   // 호출한 쪽에 '이전 메뉴' 선택을 알리기 위해
    } 

    return menuList.get(menuNo - 1);
  }

}



