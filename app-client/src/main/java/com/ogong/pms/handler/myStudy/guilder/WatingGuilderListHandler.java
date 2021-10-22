//package com.ogong.pms.handler.myStudy.guilder;
//
//import java.util.List;
//import com.ogong.pms.dao.StudyDao;
//import com.ogong.pms.domain.Member;
//import com.ogong.pms.domain.Study;
//import com.ogong.pms.handler.AuthPerMemberLoginHandler;
//import com.ogong.pms.handler.Command;
//import com.ogong.pms.handler.CommandRequest;
//import com.ogong.util.Prompt;
//
//public class WatingGuilderListHandler implements Command {
//
//  StudyDao studyDao;
//  
//
//  public WatingGuilderListHandler(StudyDao studyDao) {
//    this.studyDao = studyDao;
//  }
//
//  // 스터디 구성원 목록
//  @Override
//  public void execute(CommandRequest request) throws Exception {
//    System.out.println();
//    System.out.println("▶ 승인 대기 목록");
//    System.out.println();
//
//    int inputNo = (int) request.getAttribute("inputNo");
//
//    Study myStudy = studyDao.findByNo(inputNo);
//
//    if (!myStudy.getWatingMember().isEmpty()) {
//
//      for (Member m : myStudy.getWatingMember()) {
//        System.out.println(m.getPerNickname());
//      }
//      System.out.println("\n----------------------");
//      System.out.println(" [ 승인 / 거절 ] ");
//      System.out.println();
//      System.out.println("1. 승인");
//      System.out.println("2. 거절");
//      System.out.println("0. 취소");
//      System.out.println();
//
//      int inputGuilerNo = Prompt.inputInt("선택> ");
//      switch (inputGuilerNo) {
//        case 1: agreeStudyMember(myStudy); return;
//        case 2: disagreeStudyMember(myStudy); return;
//        case 0: return;
//        default: return;
//      }
//    }
//  }
//
//  // 승인
//  private void agreeStudyMember(Study myStudy) throws Exception {
//
//    Member member = AuthPerMemberLoginHandler.getLoginUser();
//
//    List<Member> waitingMembers = myStudy.getWatingMember();
//
//    if (myStudy.getMembers().size()+1 >= myStudy.getNumberOfPeple()) {
//      System.out.println("인원수가 가득차 승인할 수 없습니다.");
//      return;
//    }
//
//    if (member != null && 
//        (myStudy.getOwner().getPerNo() == member.getPerNo())) {
//      System.out.println();
//
//      if (!myStudy.getWatingMember().isEmpty()) {
//
//        String input = Prompt.inputString(" >> 대기 중인 회원 중 승인할 닉네임을 입력하세요 : ");
//
//        for (Member watingMember : waitingMembers) {    
//          if (watingMember.getPerNickname().equals(input)) {
//            studyGuilderDao.updateGuilder(myStudy.getStudyNo(), watingMember.getPerNo());
//            System.out.printf(
//                " >> '%s님'이 구성원으로 승인되었습니다.\n", watingMember.getPerNickname());
//            return;
//          }
//        } 
//        System.out.println(" >> 닉네임을 다시 입력하세요.");
//      }
//    }
//  }
//
//  // 거절
//  private void disagreeStudyMember(Study myStudy) throws Exception {
//
//    Member member = AuthPerMemberLoginHandler.getLoginUser();
//
//    List<Member> waitingMembers = myStudy.getWatingMember();
//
//    if (member != null && (myStudy.getOwner().getPerNo() == member.getPerNo())) {
//      System.out.println();
//      if (!myStudy.getWatingMember().isEmpty()) {
//        String input = Prompt.inputString(" >> 대기 중인 회원 중 거절할 닉네임을 입력하세요 : ");
//
//        for (Member watingMember : waitingMembers) {  
//
//          if (!watingMember.getPerNickname().equals(input)) {
//            System.out.println(" >> 닉네임을 다시 입력하세요.");
//          }
//
//          if (watingMember.getPerNickname().equals(input)) {
//            studyDao.deleteGuilder(myStudy.getStudyNo(), watingMember.getPerNo());
//            System.out.printf(" >> '%s님'의 구성원 신청이 거절되었습니다.\n", watingMember.getPerNickname());
//            break;
//          }
//        }
//        return;
//      }
//    }
//  }
//}
