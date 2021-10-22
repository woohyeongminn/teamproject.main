package com.ogong.pms.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;

public class MybatisAskBoardDao implements AskBoardDao {

  SqlSession sqlSession;

  public MybatisAskBoardDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(AskBoard askBoard) throws Exception {
    //      sqlSession.insert("AskBoardMapper.insert", askBoard);
  }

  @Override
  public void insertreply(AskBoard askBoard) throws Exception {
    //    try (PreparedStatement stmt =
    //        con.prepareStatement("insert into ask_board_reply(reply_title, reply_content, ask_board_no)"
    //            + " values(?,?,?)")) {
    //      stmt.setString(1, askBoard.getReply().getReplyTitle());
    //      stmt.setString(2, askBoard.getReply().getReplyContent());
    //      stmt.setInt(3, askBoard.getAskNo());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("문의게시판 답변 데이터 등록 실패!");
    //      }
    //    }
  }

  @Override
  public void update(AskBoard askBoard) throws Exception {
    //    try (PreparedStatement stmt =
    //        con.prepareStatement("update ask_board set"
    //            + " title=?,"
    //            + " content=?"
    //            + " where ask_board_no=?")) {
    //
    //      stmt.setString(1, askBoard.getAskTitle());
    //      stmt.setString(2, askBoard.getAskContent());
    //      stmt.setInt(3, askBoard.getAskNo());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("문의게시판 데이터 수정 실패!");
    //      }
    //    }
  }

  @Override
  public void updateViewCount(AskBoard askBoard) throws Exception {
    //    try (PreparedStatement stmt =
    //        con.prepareStatement("update ask_board set"
    //            + " view_cnt=?"
    //            + " where ask_board_no=?")) {
    //
    //      stmt.setInt(1, askBoard.getAskVeiwCount());
    //      stmt.setInt(2, askBoard.getAskNo());
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("문의게시판 조회수 데이터 수정 실패!");
    //      }
    //    }
  }

  @Override
  public void delete(int no) throws Exception {
    //    try (PreparedStatement stmt = con.prepareStatement(
    //        "delete from ask_board where ask_board_no=?");
    //        PreparedStatement stmt2 = con.prepareStatement(
    //            "delete from ask_board_reply where ask_board_no=?")) {
    //
    //      // 문의게시판 답변 먼저 삭제
    //      stmt2.setInt(1, no);
    //      stmt2.executeUpdate();
    //
    //      // 문의게시판 삭제
    //      stmt.setInt(1, no);
    //
    //      if (stmt.executeUpdate() == 0) {
    //        throw new Exception("공지게시판 데이터 삭제 실패!");
    //      }
    //    }
  }

  @Override
  public List<AskBoard> findAll() throws Exception {
    return sqlSession.selectList("AskBoardMapper.findAll");
  }

  @Override
  public AskBoard findByNo(int no) throws Exception {
    return sqlSession.selectOne("AskBoardMapper.findByNo", no);

    //      if (rs.getString("r_title") != null) {
    //        Reply reply = new Reply();
    //        reply.setReplyTitle(rs.getString("r_title"));
    //        reply.setReplyContent(rs.getString("r_content"));
    //        reply.setReplyRegisteredDate(rs.getDate("r_dt"));
    //        askBoard.setReply(reply);
    //      }
    //      
    //      Member member = new Member();
    //      CeoMember ceoMember = new CeoMember();
    //
    //      if (rs.getInt("member_status") == Member.PER) {
    //        member.setPerNo(rs.getInt("per_no"));
    //        member.setPerNickname(rs.getString("nickname"));
    //        member.setPerPassword(rs.getString("writer_pw"));
    //
    //        askBoard.setAskMemberWriter(member);
    //      }
    //      else if (rs.getInt("member_status") == Member.CEO) {
    //        ceoMember.setCeoNo(rs.getInt("ceo_no"));
    //        ceoMember.setCeoNickname(rs.getString("nickname"));
    //        ceoMember.setCeoPassword(rs.getString("writer_pw"));
    //
    //        askBoard.setAskCeoWriter(ceoMember);
    //      }
    //      return askBoard;
    //    }
  }
}
