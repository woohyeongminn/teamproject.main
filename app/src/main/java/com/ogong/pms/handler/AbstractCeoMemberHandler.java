package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.CeoMember;

public abstract class AbstractCeoMemberHandler implements Command {

  List<CeoMember> ceoMemberList;

  static CeoMember loginCeoMember;
  public static CeoMember getLoginCeoMember() {
    return loginCeoMember;
  }

  public AbstractCeoMemberHandler(List<CeoMember> ceoMemberList) {
    this.ceoMemberList = ceoMemberList;
  }
}







