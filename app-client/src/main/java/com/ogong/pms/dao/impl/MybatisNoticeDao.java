package com.ogong.pms.dao.impl;

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
    //    try (PreparedStatement stmt =
    //        con.prepareStatement("insert into notice(title,content,create_dt) values(?,?,?)",
    //            Statement.RETURN_GENERATED_KEYS)) {
    //
    //      stmt.setString(1, adminNotice.getAdminNotiTitle());
    //      stmt.setString(2, adminNotice.getAdminNotiContent());
    //      stmt.setDate(3, adminNotice.getAdminNotiRegisteredDate());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("공지사항 데이터 입력 실패!");
    //      }
    //
    //      // 공지사항 PK 값 꺼내기
    //      int noticeNo = 0;
    //      try (ResultSet pkRS = stmt.getGeneratedKeys()) {
    //        if (pkRS.next()) {
    //          noticeNo = pkRS.getInt("adminNotice_no");
    //        }
    //      }
    //
    //      if (adminNotice.getAdminNotiFile() != null) {
    //        // notice_file 테이블에 추가하기
    //        try (PreparedStatement stmt2 = con.prepareStatement(
    //            "insert into notice_file(notice_no,filepath) values(?,?)")) {
    //          stmt2.setInt(1, noticeNo); // 위에서 PK로 꺼낸 공지사항 번호
    //          stmt2.setString(2, adminNotice.getAdminNotiFile()); // 첨부파일명
    //          stmt2.executeUpdate();
    //        }
    //      }
    //    }
  }

  @Override
  public void updateTitle(AdminNotice notice) throws Exception {
    //    try (PreparedStatement stmt =
    //        con.prepareStatement("update notice set" + " title=?" + " where notice_no=?")) {
    //
    //      stmt.setString(1, notice.getAdminNotiTitle());
    //      stmt.setInt(2, notice.getAdminNotiNo());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("공지게시판 제목 데이터 변경 실패!");
    //      }
    //    }
  }

  @Override
  public void updateContent(AdminNotice notice) throws Exception {
    //    try (PreparedStatement stmt =
    //        con.prepareStatement("update notice set" + " content=?" + " where notice_no=?")) {
    //
    //      stmt.setString(1, notice.getAdminNotiContent());
    //      stmt.setInt(2, notice.getAdminNotiNo());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("공지게시판 내용 데이터 변경 실패!");
    //      }
    //    }
  }

  @Override
  public void updateFilepath(AdminNotice notice) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "update notice_file set"
    //            + " filepath=?" + " where notice_file_no=?")) {
    //
    //      stmt.setString(1, notice.getAdminNotiFile());
    //      stmt.setInt(2, notice.getAdminNotiFileNo());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("공지게시판 파일 데이터 변경 실패!");
    //      }
    //    }
  }

  // -- 첨부파일 업데이트용 --
  @Override
  public void insertFilepath(AdminNotice notice) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "insert into notice_file(notice_no,filepath) values(?,?)")) {
    //
    //      stmt.setInt(1, notice.getAdminNotiNo()); // 공지게시판 번호
    //      stmt.setString(2, notice.getAdminNotiFile());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("공지게시판 파일 데이터 첨부 실패!");
    //      }
    //    }
  }

  @Override
  public void delete(int noticeNo) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "delete from notice where notice_no=?");
    //        PreparedStatement stmt2 = con.prepareStatement(
    //            "delete from notice_file where notice_no=?")) {
    //
    //      // 첨부파일 먼저 삭제
    //      stmt2.setInt(1, noticeNo);
    //      stmt2.executeUpdate();
    //
    //      // 공지게시판 삭제
    //      stmt.setInt(1, noticeNo);
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("공지게시판 데이터 삭제 실패!");
    //      }
    //    }
  }

  @Override
  public List<AdminNotice> findAll() throws Exception {
    return sqlSession.selectList("NoticeMapper.findAll");
  }

  @Override
  public AdminNotice findByNoticeNo(int noticeNo) throws Exception {
    return null;
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "select"
    //            + " n.notice_no,"
    //            + " n.title,"
    //            + " n.content,"
    //            + " n.create_dt,"
    //            + " nf.notice_file_no,"
    //            + " nf.filepath"
    //            + " from notice n"
    //            + " left outer join notice_file nf on n.notice_no=nf.notice_no"
    //            + " where n.notice_no=" + noticeNo
    //            + " order by n.notice_no asc");
    //        ResultSet rs = stmt.executeQuery()) {
    //
    //      if (!rs.next()) {
    //        return null;
    //      }
    //
    //      AdminNotice adminNotice = new AdminNotice();
    //      adminNotice.setAdminNotiNo(rs.getInt("notice_no"));
    //      adminNotice.setAdminNotiTitle(rs.getString("title"));
    //      adminNotice.setAdminNotiContent(rs.getString("content"));
    //      adminNotice.setAdminNotiRegisteredDate(rs.getDate("create_dt"));
    //      adminNotice.setAdminNotiFileNo(rs.getInt("notice_file_no"));
    //      adminNotice.setAdminNotiFile(rs.getString("filepath"));
    //
    //      return adminNotice;
    //    }
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
