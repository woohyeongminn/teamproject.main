package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.domain.AdminNotice;

public abstract class AbstractAdminNoticeHandler implements Command {

  List<AdminNotice> adminNoticeList;
  List<Admin> adminList;

  public AbstractAdminNoticeHandler(List<AdminNotice> adminNoticeList, List<Admin> adminList) {
    this.adminNoticeList = adminNoticeList;
    this.adminList = adminList;

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

