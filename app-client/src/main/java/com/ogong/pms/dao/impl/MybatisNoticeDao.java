package com.ogong.pms.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.domain.AdminNotice;

public class MybatisNoticeDao implements NoticeDao {

  SqlSession sqlSession;

  public MybatisNoticeDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  // ---------- [ 공지사항 ] -----------------------------------------------------

  @Override
  public void insert(AdminNotice adminNotice) throws Exception {
    sqlSession.insert("NoticeMapper.insert", adminNotice);

    if (adminNotice.getAdminNotiFile() != null) {
      HashMap<String,Object> params = new HashMap<>();
      params.put("adminNotiNo", adminNotice.getAdminNotiNo());
      params.put("adminNotiFile", adminNotice.getAdminNotiFile());

      sqlSession.insert("NoticeMapper.insertnoitcefile", params);
    }
  }

  @Override
  public void updateTitle(AdminNotice notice) throws Exception {
    sqlSession.update("NoticeMapper.updateTitle", notice);
    sqlSession.commit();
  }

  @Override
  public void updateContent(AdminNotice notice) throws Exception {
    sqlSession.update("NoticeMapper.updateContent", notice);
    sqlSession.commit();
  }

  // -- 파일 변경 --
  //  @Override
  //  public void updateFilepath(AdminNotice notice) throws Exception {
  //    sqlSession.update("NoticeMapper.updateFilepath", notice);
  //    sqlSession.commit();
  //  }

  // -- 파일 추가 // 첨부파일 업데이트용 (AdminNoticeUpdate) --
  @Override
  public void insertFilepath(AdminNotice notice) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("adminNotiNo", notice.getAdminNotiNo());
    params.put("adminNotiFile", notice.getAdminNotiFile());

    sqlSession.insert("NoticeMapper.insertFilepath", params);
  }

  @Override
  public void delete(int noticeNo) throws Exception {
    sqlSession.delete("NoticeMapper.deletenoticefile", noticeNo);
    sqlSession.delete("NoticeMapper.delete", noticeNo);
    sqlSession.commit();
  }

  @Override
  public void deletefile(int noticeNo) throws Exception {
    sqlSession.delete("NoticeMapper.deletenoticefile", noticeNo);
    sqlSession.commit();
  }

  @Override
  public List<AdminNotice> findAll() throws Exception {
    return sqlSession.selectList("NoticeMapper.findAll");
  }

  @Override
  public AdminNotice findByNoticeNo(int noticeNo) throws Exception {
    return sqlSession.selectOne("NoticeMapper.findByNoticeNo", noticeNo);
  }
}

// 확인용
/*
select
 n.notice_no,n.title,n.content,n.create_dt,nf.notice_file_no,nf.filepath 
from notice n left
 outer join notice_file nf on n.notice_no=nf.notice_no 
order by n.notice_no asc
 */
