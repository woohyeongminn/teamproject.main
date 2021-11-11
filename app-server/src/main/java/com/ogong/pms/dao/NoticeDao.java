package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.AdminNotice;

public interface NoticeDao {

  // ---------- [ 공지사항 ] -----------------------------------------------------

  void insert(AdminNotice adminNotice) throws Exception;
  void insertFilepath(AdminNotice adminNotice) throws Exception;
  void updateTitle(AdminNotice notice) throws Exception;
  void updateContent(AdminNotice notice) throws Exception;
  void updateFilepath(AdminNotice notice) throws Exception;
  void delete(int noticeNo) throws Exception;
  void deletenoticefile(int noticeNo) throws Exception;
  List<AdminNotice> findAll() throws Exception;
  AdminNotice findByNoticeNo(int noticeNo) throws Exception;

}

