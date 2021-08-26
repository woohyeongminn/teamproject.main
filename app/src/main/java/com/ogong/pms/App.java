package com.ogong.pms;

import java.util.LinkedList;
import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
<<<<<<< HEAD
=======
import com.ogong.pms.handler.MemberHandler;
>>>>>>> f29bc346370916e55c27e9b587e8593ba6745c96
import com.ogong.pms.handler.NewStudyHandler;
import com.ogong.util.Prompt;

public class App {

  List<Study> studyList = new LinkedList<>();
  NewStudyHandler newStudyHandler = new NewStudyHandler(studyList);

  List<Member> memberList = new LinkedList<>();
  MemberHandler memberHandler = new MemberHandler(memberList);


  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  Menu createMenu() {
    //--------------------------------------------------------------------
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    MenuGroup studyMenu = new MenuGroup("[모든 스터디]");
    mainMenuGroup.add(studyMenu);

    studyMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        newStudyHandler.add(); 
      }});
    studyMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        newStudyHandler.list(); 
      }});
    studyMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        newStudyHandler.detail(); 
      }});
    studyMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        newStudyHandler.update(); 
      }});

    //    studyMenu.add(new Menu("삭제") {
    //      public void execute() {
    //        newStudyHandler.delete(); 
    //      }});


<<<<<<< HEAD
=======
    //--------------------------------------------------------------------
    MenuGroup memberMenu = new MenuGroup("[개인회원]");
    mainMenuGroup.add(memberMenu);

    memberMenu.add(new Menu("등록하기") {
      @Override
      public void execute() {
        memberHandler.add(); 
      }});
    memberMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        memberHandler.list(); 
      }});
    memberMenu.add(new Menu("내 가입정보") {
      @Override
      public void execute() {
        memberHandler.detail(); 
      }});
    memberMenu.add(new Menu("개인정보 수정하기") {
      @Override
      public void execute() {
        memberHandler.update(); 
      }});
    memberMenu.add(new Menu("회원 탈퇴하기") {
      @Override
      public void execute() {
        memberHandler.delete(); 
      }});


>>>>>>> f29bc346370916e55c27e9b587e8593ba6745c96
    return mainMenuGroup;
  }

}