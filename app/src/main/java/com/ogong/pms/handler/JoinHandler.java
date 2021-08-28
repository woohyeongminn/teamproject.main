package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Join;
import com.ogong.util.Prompt;

public class JoinHandler {

  List<Join> joinList;


  public JoinHandler(List<Join> joinList) {
    this.joinList = joinList;
  }

  public void add() {
    System.out.println("[개인 회원가입]");

    Join join = new Join();

    join.setJoinNickname(Prompt.inputString("닉네임? "));
    join.setJoinEmail(Prompt.inputString("이메일? "));
    join.setJoinPassword(Prompt.inputString("비밀번호? "));
    join.setJoinPhoto(Prompt.inputString("사진? "));

    joinList.add(join);
  }

  //<2021-08-26 : 추가된 코드(woo)>
  public Join findByEmail(String joinEmail) {
    Join[] arr = joinList.toArray(new Join[0]);
    for (Object obj : arr) {
      Join join = (Join) obj;
      if (join.getJoinEmail().equals(joinEmail)) {
        return join;
      }
    }
    return null;
  }


}
