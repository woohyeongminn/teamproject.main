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
import com.ogong.pms.domain.Comment;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.domain.ToDo;
import com.ogong.pms.handler.AdminInfoHandler;
import com.ogong.pms.handler.AdminMemberDeleteHandler;
import com.ogong.pms.handler.AdminNoticeAddHandler;
import com.ogong.pms.handler.AdminNoticeDeleteHandler;
import com.ogong.pms.handler.AdminNoticeDetailHandler;
import com.ogong.pms.handler.AdminNoticeListHandler;
import com.ogong.pms.handler.AdminNoticeUpdateHandler;
import com.ogong.pms.handler.AdminStudyDeleteHandler;
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
import com.ogong.pms.handler.CalenderDetailHandler;
import com.ogong.pms.handler.CalenderListHandler;
import com.ogong.pms.handler.CalenderUpdateHandler;
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
import com.ogong.pms.handler.MyStudyDeleteHandler;
import com.ogong.pms.handler.MyStudyDetailHandler;
import com.ogong.pms.handler.MyStudyListHandler;
import com.ogong.pms.handler.PromptPerMember;
import com.ogong.pms.handler.StudyAddHandler;
import com.ogong.pms.handler.StudyListHandler;
import com.ogong.pms.handler.StudyUpdateHandler;
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
  List<Comment> commentList = new ArrayList<>();

  // 해시맵 추가(0904)
  HashMap<String, Command> commandMap = new HashMap<>();

  PromptPerMember promptPerMember = new PromptPerMember(memberList); 

  //CeoMemberHandler ceoMemberHandler = new CeoMemberHandler(ceoMemberList);
  //MyStudyHandler myStudyHandler = new MyStudyHandler(studyList, studyHandler);

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
    commandMap.put("/calender/add", new CalenderAddHandler(calenderList));
    commandMap.put("/calender/list", new CalenderListHandler(calenderList));
    commandMap.put("/calender/detail", new CalenderDetailHandler(calenderList));
    commandMap.put("/calender/update", new CalenderUpdateHandler(calenderList));
    commandMap.put("/calender/detail", new CalenderDetailHandler(calenderList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList, commandMap));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList, promptPerMember));
    commandMap.put("/adminmember/delete", new AdminMemberDeleteHandler(memberList, promptPerMember));

    commandMap.put("/freeBoard/add", new FreeBoardAddHandler(freeBoardList, memberList, studyList, commentList));
    commandMap.put("/freeBoard/list", new FreeBoardListHandler(freeBoardList, memberList, commentList, commandMap));
    commandMap.put("/freeBoard/detail", new FreeBoardDetailHandler(freeBoardList, memberList, commentList));
    commandMap.put("/freeBoard/update", new FreeBoardUpdateHandler(freeBoardList, memberList, commentList));
    commandMap.put("/freeBoard/delete", new FreeBoardDeleteHandler(freeBoardList, memberList, commentList));

    commandMap.put("/toDo/add", new ToDoAddHandler(toDoList));
    commandMap.put("/toDo/list", new ToDoListHandler(toDoList));
    commandMap.put("/toDo/detail", new ToDoUpdateHandler(toDoList));
    commandMap.put("/toDo/update", new ToDoDetailHandler(toDoList));
    commandMap.put("/toDo/delete", new ToDoDeleteHandler(toDoList));

    commandMap.put("/askBoard/add",  new AskBoardAddHandler(askBoardList, memberList));
    commandMap.put("/askBoard/list", new AskBoardListHandler(askBoardList, memberList));
    commandMap.put("/askBoard/detail", new AskBoardDetailHandler(askBoardList, memberList));
    commandMap.put("/askBoard/update", new AskBoardUpdateHandler(askBoardList, memberList));
    commandMap.put("/askBoard/delete", new AskBoardDeleteHandler(askBoardList, memberList));

    commandMap.put("/cafe/add", new CafeAddHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/list", new CafeListHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/detail", new CafeDetailHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/update", new CafeUpdateHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/delete", new CafeDeleteHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/search", new CafeSearchHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/reservationList", new CafeReservationListHandler(cafeList, cafeReview, reserList));

    commandMap.put("/adminNotice/add", new AdminNoticeAddHandler(adminNoticeList));
    commandMap.put("/adminNotice/list", new AdminNoticeListHandler(adminNoticeList));
    commandMap.put("/adminNotice/update", new AdminNoticeUpdateHandler(adminNoticeList));
    commandMap.put("/adminNotice/detail", new AdminNoticeDetailHandler(adminNoticeList));
    commandMap.put("/adminNotice/delete", new AdminNoticeDeleteHandler(adminNoticeList));

    commandMap.put("/admin/login", new AuthAdminLoginHandler(adminList));
    commandMap.put("/admin/logout", new AuthAdminLogoutHandler());
    commandMap.put("/member/login", new AuthPerMemberLoginHandler(promptPerMember));
    commandMap.put("/member/logout", new AuthPerMemberLogoutHandler());

    commandMap.put("/admin/info", new AdminInfoHandler(adminList));

    commandMap.put("/study/add", new StudyAddHandler(studyList, promptPerMember));
    commandMap.put("/study/list", new StudyListHandler(studyList));
    commandMap.put("/study/update", new StudyUpdateHandler(studyList));
    commandMap.put("/study/delete", new AdminStudyDeleteHandler(studyList));

    commandMap.put("/myStudy/delete", new MyStudyDeleteHandler(studyList));
    commandMap.put("/myStudy/list", new MyStudyListHandler(studyList, commandMap));
    commandMap.put("/myStudy/detail", new MyStudyDetailHandler(studyList, commandMap));
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


  //--------------------------------------------------------
  Menu createMenu() {

    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(createAdminMenu());
    mainMenuGroup.add(createMemberMenu());
    //mainMenuGroup.add(createCeoMenu());


    return mainMenuGroup;
  }

  // -----------------------------------------------------------------------------------------------
  // 관리자 메인
  Menu createAdminMenu() {
    MenuGroup adminMenuGroup = new MenuGroup("관리자");

    adminMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_ADMINLOGOUT, "/admin/login"));
    adminMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_ADMINLOGIN, "/admin/logout"));

    adminMenuGroup.add(createAdminProMenu());       // 마이 페이지
    adminMenuGroup.add(createControlMemberMenu());  // 회원 관리
    adminMenuGroup.add(createControlStudyMenu());   // 스터디 관리
    adminMenuGroup.add(createControlReviewMenu());  // 장소 후기 관리
    adminMenuGroup.add(createAdminCSMenu());        // 고객센터 관리

    return adminMenuGroup;
  }

  // 관리자 하위 메뉴1 - 마이페이지
  private Menu createAdminProMenu() {
    MenuGroup adminPageMenu = new MenuGroup("마이 페이지", Menu.ENABLE_ADMINLOGIN); 

    adminPageMenu.add(new MenuItem("관리자 정보", "/admin/info"));

    return adminPageMenu;
  }

  // 관리자 하위 메뉴2 - 회원 관리
  private Menu createControlMemberMenu() {
    MenuGroup adminUserMenu = new MenuGroup("회원 관리", Menu.ENABLE_ADMINLOGIN); 

    adminUserMenu.add(new MenuItem("개인 회원 조회", "/member/list"));    //개인 회원 조회
    adminUserMenu.add(new MenuItem("사장 회원 조회", "ceo 구현 전"));

    return adminUserMenu;
  }

  // 관리자 하위 메뉴3 - 스터디 관리
  private Menu createControlStudyMenu() {
    MenuGroup adminStudyMenu = new MenuGroup("스터디 관리"); 

    adminStudyMenu.add(new MenuItem("목록","/study/list"));
    adminStudyMenu.add(new MenuItem("삭제","/study/delete"));
    return adminStudyMenu;
  }

  // 관리자 하위 메뉴4 - 장소 후기 관리
  private Menu createControlReviewMenu() {
    MenuGroup adminStudyMenu = new MenuGroup("장소 후기 관리"); 

    return adminStudyMenu;
  }

  //관리자 하위 메뉴5 - 고객센터 관리
  private Menu createAdminCSMenu() {
    MenuGroup csMenu = new MenuGroup("고객센터 관리");
    csMenu.add(createAdminNoticeMenu());
    csMenu.add(createAdminAskMenu());

    return csMenu;
  }

  // 5-1
  private Menu createAdminNoticeMenu() {
    MenuGroup adminNoticeMenu = new MenuGroup("공지사항"); 
    adminNoticeMenu.add(new MenuItem("등록", "/adminNotice/add"));
    adminNoticeMenu.add(new MenuItem("목록", "/adminNotice/list"));
    adminNoticeMenu.add(new MenuItem("상세보기", "/adminNotice/detail"));
    adminNoticeMenu.add(new MenuItem("수정", "/adminNotice/update"));
    adminNoticeMenu.add(new MenuItem("삭제", "/adminNotice/delete"));

    return adminNoticeMenu;
  }

  // 5-2
  private Menu createAdminAskMenu() {
    MenuGroup adminaskMenu = new MenuGroup("문의사항");
    adminaskMenu.add(new MenuItem("목록", "/askBoard/list"));
    adminaskMenu.add(new MenuItem("상세보기", "/askBoard/detail"));
    adminaskMenu.add(new MenuItem("삭제", "/askBoard/delete"));

    return adminaskMenu;
  }

  // -----------------------------------------------------------------------------------------------
  // 개인 회원 메인
  Menu createMemberMenu() {
    MenuGroup userMenuGroup = new MenuGroup("개인"); 
    userMenuGroup.add(new MenuItem("회원가입", Menu.ENABLE_LOGOUT, "/member/add"));
    userMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_LOGIN, "/member/logout"));

    userMenuGroup.add(createLoginMenu());
    userMenuGroup.add(createStudyMenu());
    userMenuGroup.add(createMystudyMenu());
    userMenuGroup.add(createCafeMenu());
    return userMenuGroup;
  }

  //개인 하위 메뉴1 - 로그인
  private Menu createLoginMenu() {
    MenuGroup loginMenu = new MenuGroup("로그인", Menu.ENABLE_LOGOUT); 

    loginMenu.add(new MenuItem("로그인", Menu.ENABLE_LOGOUT, "/member/login"));
    loginMenu.add(new MenuItem("NAVER로 시작하기", Menu.ENABLE_LOGOUT, "해시맵 호출 안 함"));
    loginMenu.add(new MenuItem("KAKAO로 시작하기", Menu.ENABLE_LOGOUT, "해시맵 호출 안 함"));
    loginMenu.add(new MenuItem("GOOGLE로 시작하기", Menu.ENABLE_LOGOUT, "해시맵 호출 안 함"));
    loginMenu.add(new MenuItem("ID/PW 찾기", Menu.ENABLE_LOGOUT, "해시맵 호출 안 함"));
    loginMenu.add(new MenuItem("회원가입", Menu.ENABLE_LOGOUT, "/member/add"));

    //  loginMenu.add(new Menu("NAVER로 시작하기", Menu.ENABLE_LOGOUT) {
    //    authPerMemberLoginHandler.naverLogin();

    //  loginMenu.add(new Menu("KAKAO로 시작하기", Menu.ENABLE_LOGOUT) {
    //    authPerMemberLoginHandler.kakaoLogin();

    //  loginMenu.add(new Menu("GOOGLE로 시작하기", Menu.ENABLE_LOGOUT) {
    //    authPerMemberLoginHandler.googleLogin();

    //  loginMenu.add(new Menu("ID/PW 찾기", Menu.ENABLE_LOGOUT) {
    //    promptPerMember.selectFindEmailPw();

    return loginMenu;
  }

  //개인 하위 메뉴1 - 모든스터디
  private Menu createStudyMenu() {
    MenuGroup allStudyMenu = new MenuGroup("모든 스터디"); 
    // 등록o 목록보기o 상세보기△(참여x) 검색x 필터x(switch문 활용) 

    allStudyMenu.add(new MenuItem("등록","/study/add"));
    allStudyMenu.add(new MenuItem("목록","/study/list"));
    allStudyMenu.add(new MenuItem("변경","/study/update"));

    return allStudyMenu; 
  }

  //개인 하위 메뉴2 - 내 스터디
  private Menu createMystudyMenu() {
    MenuGroup myStudyMenu = new MenuGroup("내 스터디"); 

    // 캘린더, 투두, 자유 게시판, 구성원, 화상채팅
    //상세?구성원?
    //    myStudyMenu.add(new Menu("개인 정보") {
    //      @Override
    //      public void execute() {
    //        memberDetailHandler.execute();
    //        selectMyPage();
    //        return;
    //      }});
    //    myStudyMenu.add(new Menu("참여 목록") {
    //      @Override
    //      public void execute() {
    //        myStudyHandler.list();
    //      }});
    // 내 스터디 하위 메뉴 1 - 구성원
    // 내 스터디 하위 메뉴 5 - 화상미팅
    // 내 스터디 하위 메뉴 6 - 탈퇴
    myStudyMenu.add(new MenuItem("내 스터디 목록", "/myStudy/list"));
    //myStudyMenu.add(new MenuItem("내 스터디 삭제","/myStudy/delete"));    //내스터디목록 하위로 들어가야함
    //
    myStudyMenu.add(createCalenderMenu());
    //    myStudyMenu.add(createToDoMenu());
    //    myStudyMenu.add(createFreeBoardMenu());
    return myStudyMenu;
  }

  // 2-1
  private Menu createCalenderMenu() {
    MenuGroup calenderMenu = new MenuGroup("캘린더");

    calenderMenu.add(new MenuItem("일정 등록", "/calender/add"));
    calenderMenu.add(new MenuItem("일정 목록", "/calender/list"));
    //    calenderMenu.add(new MenuItem("일정 상세보기", "/calender/detail"));
    //    calenderMenu.add(new MenuItem("일정 변경", "/calender/update"));
    //    calenderMenu.add(new MenuItem("일정 삭제", "/calender/delete"));

    return calenderMenu;
  }
  //
  //  // 2-2
  //  private Menu createToDoMenu() {
  //    MenuGroup toDoMenu = new MenuGroup("To-Do List");
  //    toDoMenu.add(new MenuItem("등록" , "/todo/add"));
  //    toDoMenu.add(new MenuItem("목록" , "/todo/list"));
  //    toDoMenu.add(new MenuItem("상세" , "/todo/detail"));
  //    toDoMenu.add(new MenuItem("수정" , "/todo/update"));
  //    toDoMenu.add(new MenuItem("삭제" , "/todo/delete"));
  //    return toDoMenu;
  //  }
  //
  //  // 2-3 
  //  private Menu createFreeBoardMenu() {
  //    MenuGroup freeBoardMenu = new MenuGroup("자유게시판");
  //    freeBoardMenu.add(new MenuItem("게시글 등록" , "/freeBoard/add"));
  //    freeBoardMenu.add(new MenuItem("게시글 목록" , "/freeBoard/list"));
  //    freeBoardMenu.add(new MenuItem("게시글 상세" , "/freeBoard/detail"));
  //    freeBoardMenu.add(new MenuItem("게시글 수정" , "/freeBoard/update"));
  //    freeBoardMenu.add(new MenuItem("게시글 삭제" , "/freeBoard/delete"));
  //    return freeBoardMenu;
  //  }

  //개인 하위 메뉴3 - 스터디 장소
  private Menu createCafeMenu() {
    MenuGroup cafeMenu = new MenuGroup("스터디 장소"); 

    cafeMenu.add(new MenuItem("장소 등록/기업 권한", "/cafe/add"));
    cafeMenu.add(new MenuItem("장소 목록", "/cafe/list"));
    cafeMenu.add(new MenuItem("장소 검색", "/cafe/search"));
    cafeMenu.add(new MenuItem("장소 상세보기", "/cafe/detail"));
    cafeMenu.add(new MenuItem("장소 정보 변경하기", "/cafe/update"));
    cafeMenu.add(new MenuItem("장소 삭제하기", "/cafe/delete"));
    cafeMenu.add(new MenuItem("장소 예약 내역 보기", "/cafe/reservationList"));

    return cafeMenu;
  }

  //개인 하위 메뉴4 - 고객센터
  private Menu createCSMenu() {
    MenuGroup memberCSMenu = new MenuGroup("고객센터 관리");
    memberCSMenu.add(createNoticeMenu());
    memberCSMenu.add(createAskBoardMenu());

    return memberCSMenu;
  }

  // 4-1
  private Menu createNoticeMenu() {
    MenuGroup noticeMenu = new MenuGroup("공지사항"); 

    noticeMenu.add(new MenuItem("목록", "/adminNotice/list"));
    noticeMenu.add(new MenuItem("상세보기", "/adminNotice/detail"));

    return noticeMenu;
  }

  // 4-2
  private Menu createAskBoardMenu() {
    MenuGroup askBoardMenu = new MenuGroup("문의사항");

    askBoardMenu.add(new MenuItem("등록", "/askBoard/add"));
    askBoardMenu.add(new MenuItem("목록", "/askBoard/list"));
    askBoardMenu.add(new MenuItem("상세보기", "/askBoard/detail"));
    askBoardMenu.add(new MenuItem("변경", "/askBoard/update"));
    askBoardMenu.add(new MenuItem("삭제", "/askBoard/delete"));

    return askBoardMenu;
  }


  //개인 하위 메뉴5 - 모든 스터디(구현 전)

  //개인 하위 메뉴6 - 마이페이지(구현 전)

}

//  //-------------------------------------------------------------- 
//  MenuGroup mypageMenu = new MenuGroup("마이 페이지", Menu.ENABLE_LOGIN); 
//  userMenuGroup.add(mypageMenu);
//  // 리턴위치 지정(탈퇴시 메인으로 돌아가기)
//  // 마이페이지 - 개인정보 - (수정o 탈퇴o 문의내역x) 
//  // 마이페이지 - 예약내역 - (내역보기x 취소하기x 후기보기x) 
//  mypageMenu.add(new Menu("개인 정보") {
//    @Override
//    public void execute() {
//      memberDetailHandler.execute();
//      selectMyPage();
//      return;
//    }});
//  mypageMenu.add(new Menu("예약 내역") {
//    @Override
//    public void execute() {
//      cafeReservationListHandler.execute();
//    }});
//  //--------------------------------------------------------------


//  //-------------------------------------------------------------- 
//  MenuGroup allStudyMenu = new MenuGroup("모든 스터디"); 
//  userMenuGroup.add(allStudyMenu);
//  // 등록o 목록보기o 상세보기△(참여x) 검색x 필터x(switch문 활용) 
//
//  allStudyMenu.add(new Menu("등록", Menu.ENABLE_LOGIN) {
//    @Override
//    public void execute() {
//      studyHandler.add();
//    }});
//
//  // 나중구현: 목록 안에서 상세 보기를 해야함 
//  allStudyMenu.add(new Menu("목록 보기") {
//    @Override
//    public void execute() {
//      studyHandler.list();
//    }});
//  // 나중구현: 상세 보기 안에서 참여 신청 해야함
//  allStudyMenu.add(new Menu("상세 보기") {
//    @Override
//    public void execute() {
//      studyHandler.detail();
//    }});
//////--------------------------------------------------------------


////--------------------------------------------------------------
//// 기업
//Menu createCeoMenu() {
//  return null;
//}
//

// ---------------------------------------------------------------
//// 관리자용 문의사항 - 권한이 없어서 하단 명령 처리 안 됨 > 구현 중
//   -> 지금 일반 멤버만 수정 삭제가 가능해서
//private void selectMyPage() {
//
//  if (authPerMemberLoginHandler.getLoginUser() != null) {
//    System.out.println();
//    System.out.println("1. 수정하기"); >> 회원 권한
//    System.out.println("2. 문의하기"); >> 회원 권한
//    System.out.println("3. 삭제하기"); >> 관리자
//    System.out.println("4. 댓글달기"); >> 관리자
//    System.out.println("5. 뒤로가기");
//
//    int selectNo = Prompt.inputInt("선택> ");
//    switch (selectNo) {
//      case 1: memberUpdateHandler.execute(); break;
//      case 2: askBoardListHandler.execute(); break;
//      case 3: memberDeleteHandler.execute(); return;
//      default : return;
//    }
//  }
//  return;
//}
////--------------------------------------------------------------

