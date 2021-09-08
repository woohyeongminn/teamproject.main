package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;

public class PromptCeoMember {

  List<CeoMember> ceoMemberList;

  public PromptCeoMember(List<CeoMember> ceoMemberList) {
    this.ceoMemberList = ceoMemberList;
  }

  //닉네임을 통해 기업을 찾아서 리턴한다.
  public CeoMember getCeoByNick(String inputNick) {
    for (CeoMember ceoMember : ceoMemberList) {
      if (inputNick.equals(ceoMember.getCeoName())) {
        return ceoMember;
      }
    }
    return null;
  }

  //이메일을 통해 기업을 찾아서 리턴한다.
  public CeoMember getCeoByEmail(String inputEmail) {
    for (CeoMember ceoMember : ceoMemberList) {
      if (ceoMember.getCeoEmail().equals(inputEmail)) {
        return ceoMember;
      }
    }
    return null;
  }

  //비밀번호를 통해 기업을 찾아서 리턴한다.
  public CeoMember getCeoByPW(String inputPW) {
    for (CeoMember ceoMember : ceoMemberList) {
      if (inputPW.equals(ceoMember.getCeoPassword())) {
        return ceoMember;
      }
    }
    return null;
  }
}
