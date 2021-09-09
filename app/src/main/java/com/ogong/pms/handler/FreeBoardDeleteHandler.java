//package com.ogong.pms.handler;
//
//import java.util.HashMap;
//import java.util.List;
//import com.ogong.pms.domain.Comment;
//import com.ogong.pms.domain.FreeBoard;
//import com.ogong.util.Prompt;
//
//public class FreeBoardDeleteHandler extends AbstractFreeBoardHandler {
//
//  public FreeBoardDeleteHandler(List<FreeBoard> freeBoardList,
//      List<Comment> commentList, HashMap<String, Command> commandMap) {
//    super(freeBoardList, commentList, commandMap);
//  }
//
//  @Override
//  public void execute() {
//    System.out.println();
//    System.out.println("▶ 게시글 삭제");
//
//    int inputTitle = Prompt.inputInt("번호 : ");
//    System.out.println();
//
//    FreeBoard free = findByNo(inputTitle);
//
//    if (free == null) {
//      System.out.println("해당 번호의 게시글이 없습니다.");
//      selectPage();
//      return;
//    }
//
//    if (free.getFreeBoardWriter().getPerNo() != AuthPerMemberLoginHandler.getLoginUser().getPerNo()) {
//      System.out.println("삭제 권한이 없습니다.");
//      selectPage();
//      return;
//    }
//
//    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
//    if (!input.equalsIgnoreCase("네")) {
//      System.out.println("게시글 삭제를 취소하였습니다.");
//      selectPage();
//      return;
//    }
//
//    freeBoardList.remove(free);
//
//    System.out.println("게시글이 삭제되었습니다.");
//    selectPage();
//  }
//}
//
