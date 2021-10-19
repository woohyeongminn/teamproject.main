package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.domain.Member;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;

public class MariadbAskBoardDao implements AskBoardDao {

  Connection con;

  public MariadbAskBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(AskBoard askBoard) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into ask_board(title,content,view_cnt,member_no,use_secret,reply_dt)"
            + "values(?,?,?,?,?,?)")) {

      stmt.setString(1, askBoard.getAskTitle());
      stmt.setString(2, askBoard.getAskContent());
      stmt.setInt(3, askBoard.getAskVeiwCount());
      if (AuthPerMemberLoginHandler.getLoginUser() != null) {
        stmt.setInt(4, askBoard.getAskMemberWriter().getPerNo());
      } else if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {
        stmt.setInt(4, askBoard.getAskCeoWriter().getCeoNo());
      }
      stmt.setInt(5, askBoard.getAskStatus());
      stmt.setDate(6, null);
      if (stmt.executeUpdate() == 0) {
        throw new Exception("문의게시판 데이터 등록 실패!");
      }
    }
  }

  @Override
  public void insertreply(AskBoard askBoard) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into ask_board(reply_title, reply_content)"
            + "values(?,?)")) {
      stmt.setString(1, askBoard.getReply().getReplyTitle());
      stmt.setString(2, askBoard.getReply().getReplyContent());
      if (stmt.executeUpdate() == 0) {
        throw new Exception("문의게시판 답변 데이터 등록 실패!");
      }
    }
  }

  @Override
  public void update(AskBoard askBoard) throws Exception {
  }

  @Override
  public void delete(int no) throws Exception {
  }

  @Override
  public List<AskBoard> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " ab.ask_board_no,"
            + " ab.title,"
            + " m.member_no,"
            + " m.nickname,"
            + " m.status member_status,"
            + " ab.use_secret ask_status,"
            + " ab.create_dt,"
            + " ab.view_cnt"
            + " from"
            + " ask_board ab"
            + " left outer join member m on m.member_no=ab.member_no"
            + " order by ab.ask_board_no asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<AskBoard> list = new ArrayList<>();

      while (rs.next()) {
        AskBoard askBoard = new AskBoard();
        askBoard.setAskNo(rs.getInt("ask_board_no"));
        askBoard.setAskTitle(rs.getString("title"));
        askBoard.setAskRegisteredDate(rs.getDate("create_dt"));
        askBoard.setAskVeiwCount(rs.getInt("view_cnt"));
        askBoard.setAskStatus(rs.getInt("ask_status"));

        Member member = new Member();
        CeoMember ceoMember = new CeoMember();

        if (rs.getInt("member_status") == 1) {
          member.setPerNo(rs.getInt("member_no"));
          member.setPerNickname(rs.getString("nickname"));

          askBoard.setAskMemberWriter(member);
          list.add(askBoard);
        }
        else if (rs.getInt("member_status") == 2) {
          ceoMember.setCeoNo(rs.getInt("member_no"));
          ceoMember.setCeoNickname(rs.getString("nickname"));

          askBoard.setAskCeoWriter(ceoMember);
          list.add(askBoard);
        }
      }
      return list;
    }
  }

  /*
select
 ab.ask_board_no,
 ab.title,
 m.member_no,
 m.nickname,
 m.status,
 ab.create_dt,
 ab.view_cnt
 from
 ask_board ab
 left outer join member m on m.member_no=ab.member_no
 order by ab.ask_board_no desc
   */


  @Override
  public AskBoard findByNo(int no) throws Exception {
    return null;
  }

  @Override
  public AskBoard findByPerAskBoard(int askNo, int perMemberNo) throws Exception {
    return null;
  }

  @Override
  public AskBoard findByCeoAskBoard(int askNo, int ceoMemberNo) throws Exception {
    return null;
  }

}
