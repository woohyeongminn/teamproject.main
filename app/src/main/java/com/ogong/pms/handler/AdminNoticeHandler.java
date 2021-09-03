package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;
import com.ogong.util.Prompt;

public class AdminNoticeHandler {

  List<AdminNotice> adminNoticeList;
  List<Admin> adminList;

  public AdminNoticeHandler(List<AdminNotice> adminNoticeList, List<Admin> adminList) {
    this.adminNoticeList = adminNoticeList;
    this.adminList = adminList;

    AdminNotice adminWriteList = new AdminNotice();
    adminWriteList.setAdminNotiNo(1);
    adminWriteList.setAdminNotiTitle("오늘의 공부 회원 가입을 환영합니다!");
    adminWriteList.setAdminNotiContent("오늘의 공부와 함께 목표를 향해 으쌰으쌰!");
    adminWriteList.setAdminNotiWriter(adminList.get(0));
    adminWriteList.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    adminNoticeList.add(adminWriteList);

    adminWriteList = new AdminNotice();
    adminWriteList.setAdminNotiNo(2);
    adminWriteList.setAdminNotiTitle("오늘의 공부 홈페이지 이용 방법");
    adminWriteList.setAdminNotiContent("내용 업데이트 예정입니다.");
    adminWriteList.setAdminNotiWriter(adminList.get(0));
    adminWriteList.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    adminNoticeList.add(adminWriteList);
  }

  public void add() {
    System.out.println();
    System.out.println("▶ 공지 등록");
    System.out.println();

    AdminNotice adminWriteList = new AdminNotice();

    adminWriteList.setAdminNotiNo(Prompt.inputInt("번호 : "));
    adminWriteList.setAdminNotiTitle(Prompt.inputString("제목 : "));
    adminWriteList.setAdminNotiContent(Prompt.inputString("내용 : "));
    adminWriteList.setAdminNotiWriter(AdminHandler.getLoginAdmin());
    adminWriteList.setAdminNotiRegisteredDate(new Date(System.currentTimeMillis()));

    adminNoticeList.add(adminWriteList);
  }

  public void list() {
    System.out.println();
    System.out.println("▶ 공지 목록");
    System.out.println();

    for (AdminNotice adminWriteList : adminNoticeList) {
      System.out.printf("(%d)\n 제목 : %s\n 작성자 : %s\n 등록일 : %s\n", 
          adminWriteList.getAdminNotiNo(), 
          adminWriteList.getAdminNotiTitle(),
          adminWriteList.getAdminNotiWriter().getMasterNickname(),
          adminWriteList.getAdminNotiRegisteredDate());
      System.out.println();
    }      
  }

  public void detail() {
    System.out.println();
    System.out.println("▶ 공지 상세");
    int adminnotiNo = Prompt.inputInt("번호 : ");
    System.out.println();

    AdminNotice adminWriteList = findByNotiNo(adminnotiNo);

    if (adminWriteList == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    System.out.printf(">> %s\n", adminWriteList.getAdminNotiTitle());
    System.out.printf(">> %s\n", adminWriteList.getAdminNotiContent());
    System.out.printf(">> %s\n", adminWriteList.getAdminNotiWriter().getMasterNickname());
    System.out.printf(">> %s\n", adminWriteList.getAdminNotiRegisteredDate());
  }

  public void update() {
    System.out.println();
    System.out.println("▶ 공지 변경");
    int adminnotiNo = Prompt.inputInt("번호 : ");

    AdminNotice adminWriteList = findByNotiNo(adminnotiNo);

    if (adminWriteList == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    String adminNoticeTitle = Prompt.inputString(
        String.format("제목(%s) : ", adminWriteList.getAdminNotiTitle()));
    String adminNoticeContent = Prompt.inputString(
        String.format("내용(%s) : ", adminWriteList.getAdminNotiContent()));

    String inputnotice = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (inputnotice.equalsIgnoreCase("아니오") || inputnotice.length() == 0) {
      System.out.println("변경이 취소되었습니다.");
      return;
    }

    adminWriteList.setAdminNotiTitle(adminNoticeTitle);
    adminWriteList.setAdminNotiContent(adminNoticeContent);
    System.out.println("공지가 변경되었습니다.");
  }

  public void delete() {
    System.out.println();
    System.out.println("▶ 공지 삭제");
    int adminnotiNo = Prompt.inputInt("번호 : ");

    AdminNotice adminWriteList = findByNotiNo(adminnotiNo);

    if (adminWriteList == null) {
      System.out.println("공지를 다시 선택하세요.");
      return;
    }

    String inputnotice = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (inputnotice.equalsIgnoreCase("아니오") || inputnotice.length() == 0) {
      System.out.println("삭제가 취소되었습니다.");
      return;
    }

    adminNoticeList.remove(adminWriteList);

    System.out.println("공지가 삭제되었습니다.");
  }

  public AdminNotice findByNotiNo(int adminnotiNo) {
    for (AdminNotice adminNotice : adminNoticeList) {
      if (adminNotice.getAdminNotiNo() == adminnotiNo) {
        return adminNotice;
      }
    }
    return null;
  }
}
