package com.ogong.pms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.Join;
import com.ogong.pms.domain.Login;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.NoticeBoard;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AskBoardHandler;
import com.ogong.pms.handler.CafeHandler;
import com.ogong.pms.handler.JoinHandler;
import com.ogong.pms.handler.LoginHandler;
import com.ogong.pms.handler.MemberHandler;
import com.ogong.pms.handler.NewStudyHandler;
import com.ogong.pms.handler.NoticeBoardHandler;
import com.ogong.util.Prompt;

public class App {
  List<Study> studyList = new LinkedList<>();
  NewStudyHandler newStudyHandler = new NewStudyHandler(studyList);

  List<Member> memberList = new LinkedList<>();
  MemberHandler memberHandler = new MemberHandler(memberList);

  List<NoticeBoard> noticeBoardList = new ArrayList<>();
  NoticeBoardHandler noticeboardHandler = new NoticeBoardHandler(noticeBoardList);

  List<AskBoard> askBoardList = new ArrayList<>();
  AskBoardHandler askBoardHandler = new AskBoardHandler(askBoardList);

  List<Cafe> cafeList = new ArrayList<>();
  CafeHandler cafeHandler = new CafeHandler(cafeList, memberList);

  //  <기존코드>
  //  List<Login> loginList = new ArrayList<>();
  //  LoginHandler loginHandler = new LoginHandler(loginList, memberHandler);

  List<Join> joinList = new ArrayList<>();
  JoinHandler joinHandler = new JoinHandler(joinList);

  //<2021-08-26 : 추가된 코드(woo)>
  List<Login> loginList = new ArrayList<>();
  LoginHandler loginHandler = new LoginHandler(loginList, joinHandler);

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }
  void service() {
    createMenu().execute();
    Prompt.close();
  }
  Menu createMenu() {
    System.out.println("\u0020\u002f\u0029\u002f\u0029\u0020");
    System.out.print("\u0028\u0020\u0027\u03c0\u0027\u0029");
    System.out.println(" < 오늘의 공부");
    System.out.println("\u0028\u0020\u003e\u004f\u003c\u0029");

    //--------------------------------------------------------------------

    ////////////////////////////////////////////////////////
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");
    ////////////////////////////////////////////////////////

    //--------------------------------------------------------------------
    MenuGroup joinMenu = new MenuGroup("[회원가입]");
    mainMenuGroup.add(joinMenu);

    joinMenu.add(new Menu("개인 회원 가입") {
      @Override
      public void execute() {
        joinHandler.add(); 
      }});
    joinMenu.add(new Menu("기업 회원 가입") {
      @Override
      public void execute() {
        joinHandler.add(); 
      }});

    //--------------------------------------------------------------------
    MenuGroup loginMenu = new MenuGroup("[로그인]");
    mainMenuGroup.add(loginMenu);

    loginMenu.add(new Menu("로그인") {
      @Override
      public void execute() {
        loginHandler.addLoginPage(); 
      }});

    //--------------------------------------------------------------------
    MenuGroup memberMenu = new MenuGroup("[마이페이지]");
    mainMenuGroup.add(memberMenu);


    memberMenu.add(new Menu("내 가입 정보") {
      @Override
      public void execute() {
        memberHandler.list(); 
      }});
    memberMenu.add(new Menu("내 가입 정보 상세") {
      @Override
      public void execute() {
        memberHandler.detail(); 
      }});
    memberMenu.add(new Menu("개인 정보 수정하기") {
      @Override
      public void execute() {
        memberHandler.update(); 
      }});
    memberMenu.add(new Menu("회원 탈퇴하기") {
      @Override
      public void execute() {
        memberHandler.delete(); 
      }});

    //--------------------------------------------------------------------
    MenuGroup studyMenu = new MenuGroup("[모든 스터디]");
    mainMenuGroup.add(studyMenu);

    studyMenu.add(new Menu("내 스터디 등록하기") {
      @Override
      public void execute() {
        newStudyHandler.add(); 
      }});
    studyMenu.add(new Menu("스터디 목록보기") {
      @Override
      public void execute() {
        newStudyHandler.list(); 
      }});
    studyMenu.add(new Menu("스터디 상세보기") {
      @Override
      public void execute() {
        newStudyHandler.detail(); 
      }});
    //    studyMenu.add(new Menu("내 스터디 변경하기") {
    //      @Override
    //      public void execute() {
    //        newStudyHandler.update(); 
    //      }});
    //    studyMenu.add(new Menu("내 스터디 삭제하기") {
    //      public void execute() {
    //        newStudyHandler.delete(); 
    //      }});

    //--------------------------------------------------------------------
    MenuGroup noticeMenu = new MenuGroup("[공지사항]");
    mainMenuGroup.add(noticeMenu);

    noticeMenu.add(new Menu("공지사항") {
      public void execute() {
        noticeboardHandler.add(); 
      }});
    noticeMenu.add(new Menu("공지사항 목록") {
      public void execute() {
        noticeboardHandler.list(); 
      }});
    noticeMenu.add(new Menu("공지사항 상세보기") {
      public void execute() {
        noticeboardHandler.detail(); 
      }});

    //--------------------------------------------------------------------
    MenuGroup askMenu = new MenuGroup("[문의게시판]");
    mainMenuGroup.add(askMenu);

    askMenu.add(new Menu("문의사항 등록") {
      public void execute() {
        askBoardHandler.add(); 
      }});
    askMenu.add(new Menu("문의사항 목록") {
      public void execute() {
        askBoardHandler.list(); 
      }});
    askMenu.add(new Menu("문의사항 상세보기") {
      public void execute() {
        askBoardHandler.detail(); 
      }});
    askMenu.add(new Menu("문의사항 변경") {
      public void execute() {
        askBoardHandler.update(); 
      }});
    askMenu.add(new Menu("문의사항 삭제") {
      public void execute() {
        askBoardHandler.delete(); 
      }});

    //--------------------------------------------------------------------
    MenuGroup cafeMenu = new MenuGroup("[장소 예약]");
    mainMenuGroup.add(cafeMenu);

    cafeMenu.add(new Menu("장소 등록") {
      public void execute() {
        cafeHandler.add();
      }
    });
    cafeMenu.add(new Menu("장소 목록") {
      public void execute() {
        cafeHandler.list();
      }
    });
    cafeMenu.add(new Menu("장소 검색") {
      public void execute() {
        cafeHandler.find();
      }
    });
    cafeMenu.add(new Menu("장소 상세보기") {
      public void execute() {
        cafeHandler.detail();
      }
    });
    return mainMenuGroup;
  }
}


