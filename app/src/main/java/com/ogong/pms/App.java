package com.ogong.pms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.ogong.menu.Menu;
import com.ogong.menu.MenuGroup;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.AdminInfoHandler;
import com.ogong.pms.handler.AdminNoticeAddHandler;
import com.ogong.pms.handler.AdminNoticeDeleteHandler;
import com.ogong.pms.handler.AdminNoticeDetailHandler;
import com.ogong.pms.handler.AdminNoticeListHandler;
import com.ogong.pms.handler.AdminNoticeUpdateHandler;
import com.ogong.pms.handler.AdminUpdateHandler;
import com.ogong.pms.handler.AskBoardAddHandler;
import com.ogong.pms.handler.AskBoardDeleteHandler;
import com.ogong.pms.handler.AskBoardDetailHandler;
import com.ogong.pms.handler.AskBoardListHandler;
import com.ogong.pms.handler.AskBoardUpdateHandler;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.AuthAdminLogoutHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLogoutHandler;
import com.ogong.pms.handler.CafeAddHandler;
import com.ogong.pms.handler.CafeDeleteHandler;
import com.ogong.pms.handler.CafeDetailHandler;
import com.ogong.pms.handler.CafeListHandler;
import com.ogong.pms.handler.CafeReservationListHandler;
import com.ogong.pms.handler.CafeSearchHandler;
import com.ogong.pms.handler.CafeUpdateHandler;
import com.ogong.pms.handler.CalenderAddHandler;
import com.ogong.pms.handler.CalenderDeleteHandler;
import com.ogong.pms.handler.CalenderDetailHandler;
import com.ogong.pms.handler.CalenderListHandler;
import com.ogong.pms.handler.CalenderUpdateHandler;
import com.ogong.pms.handler.CeoMemberHandler;      // 아직 하기전
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.FreeBoardAddHandler;
import com.ogong.pms.handler.FreeBoardDeleteHandler;
import com.ogong.pms.handler.FreeBoardDetailHandler;
import com.ogong.pms.handler.FreeBoardListHandler;
import com.ogong.pms.handler.FreeBoardUpdateHandler;
import com.ogong.pms.handler.MemberAddHandler;
import com.ogong.pms.handler.MemberDeleteHandler;
import com.ogong.pms.handler.MemberDetailHandler;
import com.ogong.pms.handler.MemberListHandler;
import com.ogong.pms.handler.MemberUpdateHandler;
import com.ogong.pms.handler.MyStudyHandler;       // 아직 하기전
import com.ogong.pms.handler.PromptPerMember;
import com.ogong.pms.handler.StudyHandler;      // 아직 하기전
import com.ogong.pms.handler.ToDoAddHandler;
import com.ogong.pms.handler.ToDoDeleteHandler;
import com.ogong.pms.handler.ToDoDetailHandler;
import com.ogong.pms.handler.ToDoListHandler;
import com.ogong.pms.handler.ToDoUpdateHandler;
import com.ogong.util.Prompt;

public class App {
  List<Study> studyList = new LinkedList<>();
  List<Member> memberList = new LinkedList<>();
  List<AdminNotice> adminNoticeList = new ArrayList<>();
  List<AskBoard> askBoardList = new ArrayList<>();
  List<Cafe> cafeList = new ArrayList<>();
  List<CafeReview> cafeReview = new ArrayList<>();
  List<CafeReservation> reserList = new ArrayList<>();
  List<ToDo> toDoList = new ArrayList<>();
  List<FreeBoard> freeBoardList = new ArrayList<>();
  List<Calender> calenderList = new ArrayList<>();
  List<Admin> adminList = new ArrayList<>();
  List<CeoMember> ceoMemberList = new ArrayList<>();

  // 해시맵 추가(0904)
  HashMap<String, Command> commandMap = new HashMap<>();

  PromptPerMember promptPerMember = new PromptPerMember(memberList); 

  AuthAdminLoginHandler authAdminLoginHandler = new AuthAdminLoginHandler(adminList);
  AuthAdminLogoutHandler authAdminLogoutHandler = new AuthAdminLogoutHandler();
  AuthPerMemberLoginHandler authPerMemberLoginHandler =
      new AuthPerMemberLoginHandler(promptPerMember);
  AuthPerMemberLogoutHandler authPerMemberLogoutHandler = new AuthPerMemberLogoutHandler();

  AdminUpdateHandler adminUpdateHandler = new AdminUpdateHandler(adminList);
  AdminInfoHandler adminInfoHandler = new AdminInfoHandler(adminList, adminUpdateHandler);

  MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
  MemberListHandler memberListHandler = new MemberListHandler(memberList);
  MemberDetailHandler memberDetailHandler = new MemberDetailHandler(memberList);
  MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberList);
  MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(memberList, promptPerMember);

  CafeAddHandler cafeAddHandler = new CafeAddHandler(cafeList, cafeReview, reserList);
  CafeListHandler cafeListHandler = new CafeListHandler(cafeList, cafeReview, reserList);
  CafeDetailHandler cafeDetailHandler = new CafeDetailHandler(cafeList, cafeReview, reserList);
  CafeUpdateHandler cafeUpdateHandler = new CafeUpdateHandler(cafeList, cafeReview, reserList);
  CafeDeleteHandler cafeDeleteHandler = new CafeDeleteHandler(cafeList, cafeReview, reserList);
  CafeSearchHandler cafeSearchHandler = new CafeSearchHandler(cafeList, cafeReview, reserList);
  CafeReservationListHandler cafeReservationListHandler = 
      new CafeReservationListHandler(cafeList, cafeReview, reserList);

  AdminNoticeAddHandler adminNoticeAddHandler = new AdminNoticeAddHandler(adminNoticeList);
  AdminNoticeListHandler adminNoticeListHandler = new AdminNoticeListHandler(adminNoticeList);
  AdminNoticeUpdateHandler adminNoticeUpdateHandler = new AdminNoticeUpdateHandler(adminNoticeList);
  AdminNoticeDetailHandler adminNoticeDetailHandler = new AdminNoticeDetailHandler(adminNoticeList);
  AdminNoticeDeleteHandler adminNoticeDeleteHandler = new AdminNoticeDeleteHandler(adminNoticeList);

  AskBoardAddHandler askBoardAddHandler = new AskBoardAddHandler(askBoardList, memberList);
  AskBoardListHandler askBoardListHandler = new AskBoardListHandler(askBoardList, memberList);
  AskBoardUpdateHandler askBoardUpdateHandler = new AskBoardUpdateHandler(askBoardList, memberList);
  AskBoardDetailHandler askBoardDetailHandler = new AskBoardDetailHandler(askBoardList, memberList);
  AskBoardDeleteHandler askBoardDeleteHandler = new AskBoardDeleteHandler(askBoardList, memberList);

  ToDoAddHandler toDoAddHandler = new ToDoAddHandler(toDoList);
  ToDoListHandler toDoListHandler = new ToDoListHandler(toDoList);
  ToDoUpdateHandler toDoUpdateHandler = new ToDoUpdateHandler(toDoList);
  ToDoDetailHandler toDoDetailHandler = new ToDoDetailHandler(toDoList);
  ToDoDeleteHandler toDoDeleteHandler = new ToDoDeleteHandler(toDoList);

  FreeBoardAddHandler freeBoardAddHandler = new FreeBoardAddHandler(freeBoardList);
  FreeBoardListHandler freeBoardListHandler = new FreeBoardListHandler(freeBoardList);
  FreeBoardDetailHandler freeBoardDetailHandler = new FreeBoardDetailHandler(freeBoardList);
  FreeBoardUpdateHandler freeBoardUpdateHandler = new FreeBoardUpdateHandler(freeBoardList);
  FreeBoardDeleteHandler freeBoardDeleteHandler = new FreeBoardDeleteHandler(freeBoardList);

  CalenderAddHandler calenderAddHandler = new CalenderAddHandler(calenderList);
  CalenderListHandler calenderListHandler = new CalenderListHandler(calenderList);
  CalenderDetailHandler calenderDetailHandler = new CalenderDetailHandler(calenderList);
  CalenderUpdateHandler calenderUpdateHandler = new CalenderUpdateHandler(calenderList);
  CalenderDeleteHandler calenderDeleteHandler = new CalenderDeleteHandler(calenderList);

  CeoMemberHandler ceoMemberHandler = new CeoMemberHandler(ceoMemberList);
  StudyHandler studyHandler = new StudyHandler(studyList, promptPerMember);
  MyStudyHandler myStudyHandler = new MyStudyHandler(studyList, studyHandler);

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      this(title, ENABLE_ALL, menuId);
    }

    public MenuItem(String title, int enableState, String menuId) {
      super(title, enableState);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      command.execute();
    }

  }

  public static void main(String[] args) {
    App app = new App(); 
    app.welcomeservice();
  }

  public App() {

  }

  void welcomeservice() {
    welcome().execute();
    service();
  }

  void service() {
    createMenu().execute();
    Prompt.close();
  }

  static Menu welcome() {
    ////////////////////////////////////////////////////////////////
    System.out.println("\u2728" + "오늘의 공부" + "\u2728");
    System.out.println("\u0020\u2229__\u2229\u0020\u00a0\u00a0\u00a0\u0020\u2229__\u2229\u0020");
    System.out.println("\u0028\u0020\u0027\u03c0\u0027\u0029\u00a0\u00a0\u00a0\u0028\u0027\u03c0\u0027\u00a0\u0029");
    System.out.println("\u0028\u0020\u2283\u2755\u2282\u0029\u00a0\u00a0\u00a0\u0028\u2283\u2755\u2282\u00a0\u0029");
    ////////////////////////////////////////////////////////////////
    MenuGroup welcomeMenuGroup = new MenuGroup("발표를 시작하겠습니다");
    welcomeMenuGroup.setPrevMenuTitle("시작");
    return welcomeMenuGroup;
  }

  Menu createMenu() {
    //---------------------------------------------------
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(createAdminMenu());
    mainMenuGroup.add(createUserMenu());
    //    mainMenuGroup.add(createCeoMenu());

    //---------------------------------------------------
    return mainMenuGroup;
  }

  // 관리자
  Menu createAdminMenu() {
    MenuGroup adminMenuGroup = new MenuGroup("관리자");
    //-------------------------------------------------------------
    //로그인o, 로그아웃o
    adminMenuGroup.add(new Menu("로그인", Menu.ENABLE_ADMINLOGOUT) {
      @Override
      public void execute() {
        authAdminLoginHandler.execute();
      }});

    // 제일 하단으로 내리기
    adminMenuGroup.add(new Menu("로그아웃", Menu.ENABLE_ADMINLOGIN) {
      @Override
      public void execute() {
        authAdminLogoutHandler.execute();
      }});

    //--------------------------------------------------------------- 
    // 관리자 프로필o
    MenuGroup adminpageMenu = new MenuGroup("마이 페이지", Menu.ENABLE_ADMINLOGIN); 
    adminMenuGroup.add(adminpageMenu);

    adminpageMenu.add(new Menu("관리자 정보") {
      @Override
      public void execute() {
        adminInfoHandler.execute();
        selectMyPage();
        return;
      }
    });

    //--------------------------------------------------------------- 

    // 관리자 회원 관리
    // 목록안에서 상세보기 기능x 삭제x (수정>보류)x
    MenuGroup adminUserMenu = new MenuGroup("회원 관리", Menu.ENABLE_ADMINLOGIN); 
    adminMenuGroup.add(adminUserMenu);

    adminUserMenu.add(new Menu("개인 회원 조회") {
      @Override
      public void execute() {
        memberListHandler.execute();
        selectUserModifyPage();
        return;
      }
    });

    // 목록안에서 상세보기x,삭제x,(수정>보류)x,가게승인x
    adminUserMenu.add(new Menu("사장 회원 조회") {
      @Override
      public void execute() {
        ceoMemberHandler.list();
        return;
      }
    });
    //--------------------------------------------------------------- 

    //--------------------------------------------------------------- 

    // 스터디 관리 
    // 회원이 등록한 스터디 삭제기능x, 목록x 상세보기x 
    MenuGroup adminStudyMenu = new MenuGroup("스터디 관리");
    adminMenuGroup.add(adminStudyMenu);
    //---------------------------------------------------------------

    // 장소 후기 관리 
    // 회원이 쓴 후기 삭제기능x, 목록x 상세보기x 
    MenuGroup adminReviewMenu = new MenuGroup("장소 후기 관리");
    adminMenuGroup.add(adminReviewMenu);
    //---------------------------------------------------------------

    // 관리자 고객센터 관리
    MenuGroup csMenu = new MenuGroup("고객센터 관리");
    adminMenuGroup.add(csMenu);

    // 관리자 고객센터 관리 - 공지사항
    MenuGroup adminNoticeMenu = new MenuGroup("공지사항"); 
    csMenu.add(adminNoticeMenu);

    adminNoticeMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        adminNoticeAddHandler.execute();
        return;
      }});
    adminNoticeMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        adminNoticeListHandler.execute(); 
      }});
    adminNoticeMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        adminNoticeDetailHandler.execute(); 
      }});
    adminNoticeMenu.add(new Menu("수정") {
      @Override
      public void execute() {
        adminNoticeUpdateHandler.execute(); 
      }});
    adminNoticeMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        adminNoticeDeleteHandler.execute(); 
      }});

    //---------------------------------------------------


    // 관리자 고객센터 - 문의사항
    MenuGroup adminaskMenu = new MenuGroup("문의사항");
    csMenu.add(adminaskMenu);

    // 댓글 기능x, 회원이 쓴 문의글 삭제기능x 
    adminaskMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        askBoardListHandler.execute(); 
      }});
    adminaskMenu.add(new Menu("상세보기") { // 댓글 > add 필요 + 추가 예정
      @Override
      public void execute() {
        askBoardDetailHandler.execute(); 
      }});
    adminaskMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        askBoardDeleteHandler.execute(); 
      }});

    return adminMenuGroup;
  }

  // 회원
  Menu createUserMenu() {
    MenuGroup userMenuGroup = new MenuGroup("개인"); 
    userMenuGroup.add(new Menu("회원가입", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        memberAddHandler.execute();
      }});

    //-------------------------------------------------------------
    //로그인o, SNS로그인o, ID/PW 찾기o, 회원가입o, 로그아웃o
    MenuGroup loginMenu = new MenuGroup("로그인", Menu.ENABLE_LOGOUT); 
    userMenuGroup.add(loginMenu);
    loginMenu.add(new Menu("로그인", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        authPerMemberLoginHandler.execute();
      }});
    loginMenu.add(new Menu("NAVER로 시작하기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        authPerMemberLoginHandler.naverLogin();
      }});
    loginMenu.add(new Menu("KAKAO로 시작하기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        authPerMemberLoginHandler.kakaoLogin();
      }});
    loginMenu.add(new Menu("GOOGLE로 시작하기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        authPerMemberLoginHandler.googleLogin();
      }});
    loginMenu.add(new Menu("ID/PW 찾기", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        promptPerMember.selectFindEmailPw();
      }});
    loginMenu.add(new Menu("회원 가입", Menu.ENABLE_LOGOUT) {
      @Override
      public void execute() {
        memberAddHandler.execute();
      }});
    userMenuGroup.add(new Menu("로그아웃", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        authPerMemberLogoutHandler.execute();
      }});
    //--------------------------------------------------------------- 

    //-------------------------------------------------------------- 
    MenuGroup mypageMenu = new MenuGroup("마이 페이지", Menu.ENABLE_LOGIN); 
    userMenuGroup.add(mypageMenu);
    // 리턴위치 지정(탈퇴시 메인으로 돌아가기)
    // 마이페이지 - 개인정보 - (수정o 탈퇴o 문의내역x) 
    // 마이페이지 - 예약내역 - (내역보기x 취소하기x 후기보기x) 
    mypageMenu.add(new Menu("개인 정보") {
      @Override
      public void execute() {
        memberDetailHandler.execute();
        selectMyPage();
        return;
      }});
    mypageMenu.add(new Menu("예약 내역") {
      @Override
      public void execute() {
        cafeReservationListHandler.execute();
      }});
    //--------------------------------------------------------------

    //-------------------------------------------------------------- 
    MenuGroup allStudyMenu = new MenuGroup("모든 스터디"); 
    userMenuGroup.add(allStudyMenu);
    // 등록o 목록보기o 상세보기△(참여x) 검색x 필터x(switch문 활용) 

    allStudyMenu.add(new Menu("등록", Menu.ENABLE_LOGIN) {
      @Override
      public void execute() {
        studyHandler.add();
      }});

    // 나중구현: 목록 안에서 상세 보기를 해야함 
    allStudyMenu.add(new Menu("목록 보기") {
      @Override
      public void execute() {
        studyHandler.list();
      }});
    // 나중구현: 상세 보기 안에서 참여 신청 해야함
    allStudyMenu.add(new Menu("상세 보기") {
      @Override
      public void execute() {
        studyHandler.detail();
      }});
    //--------------------------------------------------------------

    //--------------------------------------------------------------
    MenuGroup myStudyMenu = new MenuGroup("내 스터디"); 
    userMenuGroup.add(myStudyMenu);
    // 캘린더, 투두, 자유 게시판, 구성원, 화상채팅

    myStudyMenu.add(new Menu("개인 정보") {
      @Override
      public void execute() {
        memberDetailHandler.execute();
        selectMyPage();
        return;
      }});
    myStudyMenu.add(new Menu("참여 목록") {
      @Override
      public void execute() {
        myStudyHandler.list();
      }});

    // 내 스터디 하위 메뉴 1 - 구성원
    // 내 스터디 하위 메뉴 2 - 캘린더
    MenuGroup calenderMenu = new MenuGroup("캘린더");
    myStudyMenu.add(calenderMenu);

    calenderMenu.add(new Menu("일정 등록") {
      @Override
      public void execute() {
        calenderAddHandler.execute(); 
      }});
    calenderMenu.add(new Menu("일정 목록") {
      @Override
      public void execute() {
        calenderListHandler.execute(); 
      }});
    calenderMenu.add(new Menu("일정 상세보기") {
      @Override
      public void execute() {
        calenderDetailHandler.execute(); 
      }});
    calenderMenu.add(new Menu("일정 변경") {
      @Override
      public void execute() {
        calenderUpdateHandler.execute(); 
      }});
    calenderMenu.add(new Menu("일정 삭제") {
      @Override
      public void execute() {
        calenderDeleteHandler.execute(); 
      }});

    // 내 스터디 하위 메뉴 3 - 투두리스트
    MenuGroup todoMenu = new MenuGroup("To-Do List");
    myStudyMenu.add(todoMenu);

    todoMenu.add(new Menu("To-Do List 등록") {
      @Override
      public void execute() {
        toDoAddHandler.execute(); 
      }});
    todoMenu.add(new Menu("To-Do List 목록") {
      @Override
      public void execute() {
        toDoListHandler.execute(); 
      }});
    todoMenu.add(new Menu("To-Do List 상세보기") {
      @Override
      public void execute() {
        toDoDetailHandler.execute(); 
      }});
    todoMenu.add(new Menu("To-Do List 변경") {
      @Override
      public void execute() {
        toDoUpdateHandler.execute(); 
      }});
    todoMenu.add(new Menu("To-Do List 삭제") {
      @Override
      public void execute() {
        toDoDeleteHandler.execute(); 
      }});

    // 내 스터디 하위 메뉴 4 - 자유게시판
    // 댓글 기능x
    MenuGroup freeBoardMenu = new MenuGroup("자유게시판");
    myStudyMenu.add(freeBoardMenu);

    freeBoardMenu.add(new Menu("자유게시판 게시글 작성") {
      @Override
      public void execute() {
        freeBoardAddHandler.execute(); 
      }});
    freeBoardMenu.add(new Menu("자유게시판 게시글 목록") {
      @Override
      public void execute() {
        freeBoardListHandler.execute(); 
      }});
    freeBoardMenu.add(new Menu("자유게시판 게시글 상세보기") {
      @Override
      public void execute() {
        freeBoardDetailHandler.execute(); 
      }});
    freeBoardMenu.add(new Menu("자유게시판 게시글 수정") {
      @Override
      public void execute() {
        freeBoardUpdateHandler.execute(); 
      }});
    freeBoardMenu.add(new Menu("자유게시판 게시글 삭제") {
      @Override
      public void execute() {
        freeBoardDeleteHandler.execute(); 
      }});

    // 내 스터디 하위 메뉴 5 - 화상미팅
    // 내 스터디 하위 메뉴 6 - 탈퇴





    //-------------------------------------------------------------- 
    // 리뷰, 예약(by Song)
    MenuGroup cafeMenu = new MenuGroup("스터디 장소"); 
    userMenuGroup.add(cafeMenu);

    cafeMenu.add(new Menu("장소 등록/기업 권한") {
      @Override
      public void execute() {
        cafeAddHandler.execute();
      }});
    cafeMenu.add(new Menu("장소 목록") {
      @Override
      public void execute() {
        cafeListHandler.execute();
      }});
    cafeMenu.add(new Menu("장소 검색") {
      @Override
      public void execute() {
        cafeSearchHandler.execute();
      }});
    cafeMenu.add(new Menu("장소 상세보기") {
      @Override
      public void execute() {
        cafeDetailHandler.execute();
      }});
    cafeMenu.add(new Menu("장소 정보 변경하기") {
      @Override
      public void execute() {
        cafeUpdateHandler.execute();
      }});
    cafeMenu.add(new Menu("장소 삭제하기") {
      @Override
      public void execute() {
        cafeDeleteHandler.execute();
      }});
    cafeMenu.add(new Menu("장소 예약 내역 보기") {
      @Override
      public void execute() {
        cafeReservationListHandler.execute();
      }});
    //-------------------------------------------------------------- 

    // 회원 고객센터
    MenuGroup csMenu = new MenuGroup("고객센터");
    userMenuGroup.add(csMenu);

    // 관리자 고객센터 관리 - 공지사항
    MenuGroup NoticeMenu = new MenuGroup("공지사항"); 
    csMenu.add(NoticeMenu);

    NoticeMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        adminNoticeListHandler.execute(); 
      }});
    NoticeMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        adminNoticeDetailHandler.execute(); 
      }});
    //---------------------------------------------------


    //---------------------------------------------------
    MenuGroup askMenu = new MenuGroup("문의사항");
    csMenu.add(askMenu);

    askMenu.add(new Menu("등록") {
      @Override
      public void execute() {
        askBoardAddHandler.execute(); 
      }});
    askMenu.add(new Menu("목록") {
      @Override
      public void execute() {
        askBoardListHandler.execute(); 
      }});
    askMenu.add(new Menu("상세보기") {
      @Override
      public void execute() {
        askBoardDetailHandler.execute(); 
      }});
    askMenu.add(new Menu("변경") {
      @Override
      public void execute() {
        askBoardUpdateHandler.execute(); 
      }});
    askMenu.add(new Menu("삭제") {
      @Override
      public void execute() {
        askBoardDeleteHandler.execute(); 
      }});

    return userMenuGroup;
  }

  // 기업
  Menu createCeoMenu() {
    return null;
  }

  // 관리자용 문의사항
  private void selectMyPage() {

    if (authPerMemberLoginHandler.getLoginUser() != null) {
      System.out.println();
      System.out.println("1. 수정하기");
      System.out.println("2. 문의 내역");
      System.out.println("3. 탈퇴하기");
      System.out.println("4. 뒤로가기");

      int selectNo = Prompt.inputInt("선택> ");
      switch (selectNo) {
        case 1: memberUpdateHandler.execute(); break;
        case 2: askBoardListHandler.execute(); break;
        case 3: memberDeleteHandler.execute(); return;
        default : return;
      }
    }
    return;
  }


  // 관리자가 회원 조회 들어가면 권한이 없어서 하단 명령 처리 안 됨 --------------
  private void selectUserModifyPage() {
    System.out.println();
    System.out.println("1. 상세보기");
    System.out.println("2. 수정하기");
    System.out.println("3. 삭제하기");
    System.out.println("4. 뒤로가기");

    int selectAdminNo = Prompt.inputInt("선택> ");
    switch (selectAdminNo) {
      case 1: memberDetailHandler.execute(); break;
      case 2: memberUpdateHandler.execute(); break;
      case 3: memberDeleteHandler.execute(); break;
      default : return;
    }
  }
  // ---------------------------------------------















  //    //---------------------------------------------------
  //    MenuGroup studyMenu = new MenuGroup("모든 스터디");
  //    mainMenuGroup.add(studyMenu);
  //    studyMenu.add(new Menu("내 스터디 변경하기/마이페이지 권한") {
  //      @Override
  //      public void execute() {
  //        newStudyHandler.update(); 
  //      }});
  //    studyMenu.add(new Menu("내 스터디 삭제하기//마이페이지 권한") {
  //      @Override
  //      public void execute() {
  //        newStudyHandler.delete(); 
  //      }});

}