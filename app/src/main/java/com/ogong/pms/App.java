package com.ogong.pms;

import java.util.LinkedList;
import java.util.List;
import com.ogong.handler.NewStudyHandler;
import com.ogong.menu.Menu;
import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class App {

  List<Study> studyList = new LinkedList<>();
  NewStudyHandler newStudyHandler = new NewStudyHandler(studyList);

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {

    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    MenuGroup studyMenu = new MenuGroup("[모든 스터디]");
    mainMenuGroup.add(studyMenu);

    studyMenu.add(new Menu("등록") {
      public void execute() {
        newStudyHandler.add(); 
      }});
    studyMenu.add(new Menu("목록") {
      public void execute() {
        newStudyHandler.list(); 
      }});
    studyMenu.add(new Menu("상세보기") {
      public void execute() {
        newStudyHandler.detail(); 
      }});
    studyMenu.add(new Menu("변경") {
      public void execute() {
        newStudyHandler.update(); 
      }});

    //    studyMenu.add(new Menu("삭제") {
    //      public void execute() {
    //        newStudyHandler.delete(); 
    //      }});

    return mainMenuGroup;
  }

}