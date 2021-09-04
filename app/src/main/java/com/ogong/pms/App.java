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
    commandMap.put("/calender/add", new CalenderAddHandler(calenderList));
    commandMap.put("/calender/list", new CalenderListHandler(calenderList));
    commandMap.put("/calender/detail", new CalenderDetailHandler(calenderList));
    commandMap.put("/calender/update", new CalenderUpdateHandler(calenderList));
    commandMap.put("/calender/detail", new CalenderDetailHandler(calenderList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList, promptPerMember));

    commandMap.put("/freeBoard/add", new FreeBoardAddHandler(freeBoardList));
    commandMap.put("/freeBoard/list", new FreeBoardListHandler(freeBoardList));
    commandMap.put("/freeBoard/detail", new FreeBoardDetailHandler(freeBoardList));
    commandMap.put("/freeBoard/update", new FreeBoardUpdateHandler(freeBoardList));
    commandMap.put("/freeBoard/delete", new FreeBoardDeleteHandler(freeBoardList));

    commandMap.put("/toDo/add", new ToDoAddHandler(toDoList));
    commandMap.put("/toDo/list", new ToDoListHandler(toDoList));
    commandMap.put("/toDo/detail", new ToDoUpdateHandler(toDoList));
    commandMap.put("/toDo/update", new ToDoDetailHandler(toDoList));
    commandMap.put("/toDo/delete", new ToDoDeleteHandler(toDoList));

    commandMap.put("/askBoard/add",  new AskBoardAddHandler(askBoardList, memberList));
    commandMap.put("/askBoard/list", new AskBoardListHandler(askBoardList, memberList));
    commandMap.put("/askBoard/detail", new AskBoardUpdateHandler(askBoardList, memberList));
    commandMap.put("/askBoard/update", new AskBoardDetailHandler(askBoardList, memberList));
    commandMap.put("/askBoard/delete", new AskBoardDeleteHandler(askBoardList, memberList));

    commandMap.put("/cafe/add", new CafeAddHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/list", new CafeListHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/detail", new CafeDetailHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/update", new CafeUpdateHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/delete", new CafeDeleteHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/search", new CafeSearchHandler(cafeList, cafeReview, reserList));
    commandMap.put("/cafe/reservationsList", new CafeReservationListHandler(cafeList, cafeReview, reserList));

    commandMap.put("/adminNotice/add", new AdminNoticeAddHandler(adminNoticeList));
    commandMap.put("/adminNotice/list", new AdminNoticeListHandler(adminNoticeList));
    commandMap.put("/adminNotice/update", new AdminNoticeUpdateHandler(adminNoticeList));
    commandMap.put("/adminNotice/datail", new AdminNoticeDetailHandler(adminNoticeList));
    commandMap.put("/adminNotice/delete", new AdminNoticeDeleteHandler(adminNoticeList));

    commandMap.put("/admin/login", new AuthAdminLoginHandler(adminList));
    commandMap.put("/admin/logout", new AuthAdminLogoutHandler());
    commandMap.put("/member/login", new AuthPerMemberLoginHandler(promptPerMember));
    commandMap.put("/member/logout", new AuthPerMemberLogoutHandler());

    commandMap.put("/admin/info", new AdminInfoHandler(adminList));
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
    mainMenuGroup.add(createMemberMenu());
    //    mainMenuGroup.add(createCeoMenu());

    //---------------------------------------------------
    return mainMenuGroup;
  }

  // 관리자
  Menu createAdminMenu() {
    MenuGroup adminMenuGroup = new MenuGroup("관리자");

    adminMenuGroup.add(new MenuItem("로그인", Menu.ENABLE_ADMINLOGOUT, "/admin/login"));
    adminMenuGroup.add(new MenuItem("로그아웃", Menu.ENABLE_ADMINLOGIN, "/admin/logout"));

    adminMenuGroup.add(createAdminProMenu());

    return adminMenuGroup;
  }

  private Menu createAdminProMenu() {
    MenuGroup adminPageMenu = new MenuGroup("마이 페이지", Menu.ENABLE_ADMINLOGIN); 

    adminPageMenu.add(new MenuItem("관리자 정보", "/admin/info"));

    return adminPageMenu;
  }

  // ------------------------------------------------------------
  // 개인 회원 메인
  Menu createMemberMenu() {
    MenuGroup userMenuGroup = new MenuGroup("개인"); 
    userMenuGroup.add(new MenuItem("회원가입", Menu.ENABLE_LOGOUT, "/member/add"));

    //송
    userMenuGroup.add(createCafeMenu());

    return userMenuGroup;
  }

  private Menu createMemberLoginoutMenu() {




    return ; // 수정하세용
  }

  //송
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

}