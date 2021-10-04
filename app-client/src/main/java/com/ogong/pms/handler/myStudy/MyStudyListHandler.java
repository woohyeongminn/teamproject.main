package com.ogong.pms.handler.myStudy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Member;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;

public class MyStudyListHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println();
    System.out.println("▶ 내 스터디 목록");
    System.out.println();

    if (AuthPerMemberLoginHandler.getLoginUser() == null ) {
      System.out.println(" >> 로그인 한 회원만 조회 가능합니다.");
      return;
    }

    // 로그인 멤버 데이터 요청
    int no = AuthPerMemberLoginHandler.getLoginUser().getPerNo();

    HashMap<String,String> params = new HashMap<>();
    params.put("memberNo", String.valueOf(no));

    requestAgent.request("member.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    Member member = requestAgent.getObject(Member.class);

    // 스터디 데이터 요청
    requestAgent.request("study.selectList", member);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(" >> 스터디 목록 조회 실패!");
      return;
    }

    Collection<Study> studyCollection = requestAgent.getObjects(Study.class);
    List<Study> myStudyList = new ArrayList<>(studyCollection);

    //----------------------------------------------------------------------------------------------
    // test용으로 setPerMyStudy에 new로 생성해서
    // 값은 안 넣었지만 null값이 있으므로 사이즈로 비교해야 한다.
    int joinCount = 0;

    System.out.println(" << 참여 중 >> \n");
    for (int i = 0; i < myStudyList.size(); i++) {
      if (myStudyList.get(i).getMemberNames().contains(member.getPerNickname()) ||
          myStudyList.get(i).getOwner().getPerNickname().equals(member.getPerNickname())) {
        System.out.printf(" (%s)\n [%s]\n", myStudyList.get(i).getStudyNo(),
            myStudyList.get(i).getStudyTitle());
        System.out.println();
        joinCount++;
      }
    }

    if(joinCount == 0) {
      System.out.println(" >> 가입한 스터디가 없습니다.\n");
    }

    int waitCount = 0;
    System.out.println(" << 승인 대기 중>> \n"); 
    for (int i = 0; i < myStudyList.size(); i++) {
      if (myStudyList.get(i).getWatingMemberNames().contains(member.getPerNickname())) {
        System.out.printf(" (%s)\n [%s]\n", myStudyList.get(i).getStudyNo(),
            myStudyList.get(i).getStudyTitle());
        System.out.println();
        waitCount++;
      }
    }

    if (waitCount == 0) {
      System.out.println(" >> 승인 대기 중인 스터디가 없습니다.\n");
    }
  }
}