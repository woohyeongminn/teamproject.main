//package com.ogong.pms.servlet.admin;
//
//import static com.ogong.pms.domain.Cafe.DELETE;
//import static com.ogong.pms.domain.Cafe.WAIT;
//import java.util.List;
//import com.ogong.pms.dao.CafeDao;
//import com.ogong.pms.domain.Cafe;
//import com.ogong.pms.handler.Command;
//import com.ogong.pms.handler.CommandRequest;
//import com.ogong.util.Prompt;
//
//public class AdminCafeControlHandler implements Command {
//
//  CafeDao cafeDao;
//
//  public AdminCafeControlHandler (CafeDao cafeDao) {
//    this.cafeDao = cafeDao;
//  }
//
//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    System.out.println();
//    System.out.println("▶ 장소 목록");
//
//    List<Cafe> cafeList = cafeDao.getCafeList();
//
//    if (cafeList.isEmpty()) {
//      System.out.println(" >> 등록된 장소가 없습니다.");
//      return;
//    }
//
//    for(Cafe cafe : cafeList) {
//      if (cafe.getCafeStatus() == DELETE) {
//        System.out.printf(" \n (%s) | 삭제된 장소입니다. \n", cafe.getNo());
//        continue;
//      }
//      System.out.printf(" \n (%s) | 이름 : %s | 주소 : %s | 운영시간 : %s ~ %s", 
//          cafe.getNo(), 
//          cafe.getName(), 
//          cafe.getLocation(), 
//          cafe.getOpenTime(),
//          cafe.getCloseTime());
//      if (cafe.getCafeStatus() == WAIT) {
//        System.out.printf(" * 승인 대기 중인 카페입니다.");
//      }
//      System.out.println();
//    }
//
//    selectCafeDetailMenu(request);
//  }
//
//  private void selectCafeDetailMenu(CommandRequest request) throws Exception {
//    System.out.println("\n----------------------");
//    System.out.println("1. 상세");
//    System.out.println("2. 승인");
//    System.out.println("0. 이전");
//    int input = Prompt.inputInt("선택> ");
//    switch (input) {
//      case 1: request.getRequestDispatcher("/cafe/controlDetail").forward(request); return;
//      case 2: request.getRequestDispatcher("/cafe/controlApproval").forward(request); return;
//      case 0: return;
//      default :
//        System.out.println(" >> 번호를 다시 선택해 주세요.");
//    }
//  }
//}
