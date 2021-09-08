package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;

public abstract class AbstractCeoHandler implements Command {

  List<CeoMember> ceoMemberList;

  static CeoMember loginCeoMember;
  public static CeoMember getLoginCeoMember() {
    return loginCeoMember;
  }

  public AbstractCeoHandler(List<CeoMember> ceoMemberList) {
    this.ceoMemberList = ceoMemberList;
  }
}







