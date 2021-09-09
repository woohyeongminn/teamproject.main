//package com.ogong.pms.handler;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import com.ogong.pms.domain.Comment;
//import com.ogong.pms.domain.FreeBoard;
//import com.ogong.pms.domain.Member;
//import com.ogong.pms.domain.Study;
//import com.ogong.util.Prompt;
//
//public class FreeBoardAddHandler extends AbstractFreeBoardHandler {
//
//  int freeBoardNo;
//  List<Member> memberList;
//
//  public FreeBoardAddHandler(List<FreeBoard> freeBoardList, List<Member> memberList,
//      List<Comment> commentList, HashMap<String, Command> commandMap) {
//    super(freeBoardList, commentList, commandMap);
//    this.memberList = memberList;
//
//    FreeBoard test = new FreeBoard();
//    test.setFreeBoardNo(freeBoardNo++);
//    test.setFreeBoardTitle("게시글1");
//    test.setFreeBoardContent("5월 10일에 만나요");
//    test.setFreeBoardAtcFile("지도");
//    test.setFreeBoardWriter(memberList.get(0));
//    test.setFreeBoardViewcount(test.getFreeBoardViewcount());
//    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
//    test.setComment(new ArrayList<>());
//    freeBoardList.add(test);
//
//    test = new FreeBoard();
//    test.setFreeBoardNo(freeBoardNo++);
//    test.setFreeBoardTitle("게시글2");
//    test.setFreeBoardContent("아주아주 잘하고 있습니다");
//    test.setFreeBoardAtcFile("jpg");
//    test.setFreeBoardWriter(memberList.get(1));
//    test.setFreeBoardViewcount(test.getFreeBoardViewcount());
//    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
//    test.setComment(new ArrayList<>());
//    freeBoardList.add(test);
//
//    test = new FreeBoard();
//    test.setFreeBoardNo(freeBoardNo++);
//    test.setFreeBoardTitle("게시글3");
//    test.setFreeBoardContent("159p 이상합니다");
//    test.setFreeBoardAtcFile("문제집");
//    test.setFreeBoardWriter(memberList.get(2));
//    test.setFreeBoardViewcount(test.getFreeBoardViewcount());
//    test.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));
//    test.setComment(new ArrayList<>());
//    freeBoardList.add(test);
//  }
//
//  @Override
//  public void execute() {
//   
//}
//
