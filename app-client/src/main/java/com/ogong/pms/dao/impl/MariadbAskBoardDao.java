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
import com.ogong.pms.domain.Reply;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;

public class MariadbAskBoardDao implements AskBoardDao {

  Connection con;

  public MariadbAskBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(AskBoard askBoard) throws Exception {
    if (AuthPerMemberLoginHandler.getLoginUser() != null) {
      try (PreparedStatement stmt =
          con.prepareStatement(
              "insert into ask_board(title,content,view_cnt,member_no,use_secret)"
                  + " values("
                  + "?,"
                  + "?,"
                  + "?,"
                  + "(select"
                  + " member_no"
                  + " from"
                  + " per_member pm"
                  + " where"
                  + " pm.per_member_no = ?),"
                  + "?)")) {

        stmt.setString(1, askBoard.getAskTitle());
        stmt.setString(2, askBoard.getAskContent());
        stmt.setInt(3, askBoard.getAskVeiwCount());
        stmt.setInt(4, askBoard.getAskMemberWriter().getPerNo());
        stmt.setInt(5, askBoard.getAskStatus());

        if (stmt.executeUpdate() == 0) {
          throw new Exception("문의게시판 데이터 등록 실패!");
        }
      }

    } else if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {
      try (PreparedStatement stmt2 =
          con.prepareStatement(
              "insert into ask_board(title,content,view_cnt,member_no,use_secret)"
                  + " values("
                  + "?,"
                  + "?,"
                  + "?,"
                  + "(select"
                  + " member_no"
                  + " from"
                  + " ceo_member cm"
                  + " where"
                  + " cm.ceo_member_no = ?),"
                  + "?)")) {

        stmt2.setString(1, askBoard.getAskTitle());
        stmt2.setString(2, askBoard.getAskContent());
        stmt2.setInt(3, askBoard.getAskVeiwCount());
        stmt2.setInt(4, askBoard.getAskCeoWriter().getCeoNo());
        stmt2.setInt(5, askBoard.getAskStatus());

        if (stmt2.executeUpdate() == 0) {
          throw new Exception("문의게시판 데이터 등록 실패!");
        }
      }
    }
  }

  @Override
  public void insertreply(AskBoard askBoard) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("insert into ask_board_reply(reply_title, reply_content, ask_board_no)"
            + " values(?,?,?)")) {
      stmt.setString(1, askBoard.getReply().getReplyTitle());
      stmt.setString(2, askBoard.getReply().getReplyContent());
      stmt.setInt(3, askBoard.getAskNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("문의게시판 답변 데이터 등록 실패!");
      }
    }
  }

  @Override
  public void update(AskBoard askBoard) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("update ask_board set"
            + " title=?,"
            + " content=?"
            + " where ask_board_no=?")) {

      stmt.setString(1, askBoard.getAskTitle());
      stmt.setString(2, askBoard.getAskContent());
      stmt.setInt(3, askBoard.getAskNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("문의게시판 데이터 수정 실패!");
      }
    }
  }

  @Override
  public void updateViewCount(AskBoard askBoard) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("update ask_board set"
            + " view_cnt=?"
            + " where ask_board_no=?")) {

      stmt.setInt(1, askBoard.getAskVeiwCount());
      stmt.setInt(2, askBoard.getAskNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("문의게시판 조회수 데이터 수정 실패!");
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from ask_board where ask_board_no=?");
        PreparedStatement stmt2 = con.prepareStatement(
            "delete from ask_board_reply where ask_board_no=?")) {

      // 문의게시판 답변 먼저 삭제
      stmt2.setInt(1, no);
      stmt2.executeUpdate();

      // 문의게시판 삭제
      stmt.setInt(1, no);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("공지게시판 데이터 삭제 실패!");
      }
    }
  }


  @Override
  public List<AskBoard> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " ab.ask_board_no,"
            + " ab.title,"
            + " ab.content,"
            + " m.member_no,"
            + " pm.per_member_no per_no,"
            + " cm.ceo_member_no ceo_no,"
            + " m.nickname,"
            + " m.status member_status,"
            + " ab.use_secret ask_status,"
            + " ab.create_dt,"
            + " ab.view_cnt,"
            + " abr.reply_title r_title,"
            + " abr.reply_content r_content,"
            + " abr.reply_dt r_dt"
            + " from"
            + " ask_board ab"
            + " left outer join member m on m.member_no=ab.member_no"
            + " left outer join per_member pm on pm.member_no=m.member_no"
            + " left outer join ceo_member cm on cm.member_no=m.member_no"
            + " left outer join ask_board_reply abr on abr.ask_board_no=ab.ask_board_no"
            + " order by ab.ask_board_no asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<AskBoard> list = new ArrayList<>();

      while (rs.next()) {
        AskBoard askBoard = new AskBoard();
        askBoard.setAskNo(rs.getInt("ask_board_no"));
        askBoard.setAskTitle(rs.getString("title"));
        askBoard.setAskContent(rs.getString("content"));
        askBoard.setAskRegisteredDate(rs.getDate("create_dt"));
        askBoard.setAskVeiwCount(rs.getInt("view_cnt"));
        askBoard.setAskStatus(rs.getInt("ask_status"));

        if (rs.getString("r_title") != null) {
          Reply reply = new Reply();
          reply.setReplyTitle(rs.getString("r_title"));
          reply.setReplyContent(rs.getString("r_content"));
          reply.setReplyRegisteredDate(rs.getDate("r_dt"));
          askBoard.setReply(reply);
        }

        Member member = new Member();
        CeoMember ceoMember = new CeoMember();

        if (rs.getInt("member_status") == 1) {
          member.setPerNo(rs.getInt("per_no"));
          member.setPerNickname(rs.getString("nickname"));

          askBoard.setAskMemberWriter(member);
          list.add(askBoard);
        }
        else if (rs.getInt("member_status") == 2) {
          ceoMember.setCeoNo(rs.getInt("ceo_no"));
          ceoMember.setCeoNickname(rs.getString("nickname"));

          askBoard.setAskCeoWriter(ceoMember);
          list.add(askBoard);
        }
      }
      return list;
    }
  }

  @Override
  public AskBoard findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " ab.ask_board_no,"
            + " ab.title,"
            + " ab.content,"
            + " m.member_no,"
            + " pm.per_member_no per_no,"
            + " cm.ceo_member_no ceo_no,"
            + " m.nickname,"
            + " m.status member_status,"
            + " ab.use_secret ask_status,"
            + " ab.create_dt,"
            + " ab.view_cnt,"
            + " abr.reply_title r_title,"
            + " abr.reply_content r_content,"
            + " abr.reply_dt r_dt"
            + " from"
            + " ask_board ab"
            + " left outer join member m on m.member_no=ab.member_no"
            + " left outer join per_member pm on pm.member_no=m.member_no"
            + " left outer join ceo_member cm on cm.member_no=m.member_no"
            + " left outer join ask_board_reply abr on abr.ask_board_no=ab.ask_board_no"
            + " where ab.ask_board_no=" + no
            + " order by ab.ask_board_no asc");
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      AskBoard askBoard = new AskBoard();
      askBoard.setAskNo(rs.getInt("ask_board_no"));
      askBoard.setAskTitle(rs.getString("title"));
      askBoard.setAskContent(rs.getString("content"));
      askBoard.setAskRegisteredDate(rs.getDate("create_dt"));
      askBoard.setAskVeiwCount(rs.getInt("view_cnt"));
      askBoard.setAskStatus(rs.getInt("ask_status"));

      if (rs.getString("r_title") != null) {
        Reply reply = new Reply();
        reply.setReplyTitle(rs.getString("r_title"));
        reply.setReplyContent(rs.getString("r_content"));
        reply.setReplyRegisteredDate(rs.getDate("r_dt"));
        askBoard.setReply(reply);
      }

      Member member = new Member();
      CeoMember ceoMember = new CeoMember();

      if (rs.getInt("member_status") == 1) {
        member.setPerNo(rs.getInt("per_no"));
        member.setPerNickname(rs.getString("nickname"));

        askBoard.setAskMemberWriter(member);
      }
      else if (rs.getInt("member_status") == 2) {
        ceoMember.setCeoNo(rs.getInt("ceo_no"));
        ceoMember.setCeoNickname(rs.getString("nickname"));

        askBoard.setAskCeoWriter(ceoMember);
      }
      return askBoard;
    }
  }


  @Override
  public AskBoard findByPerAskBoard(int askNo, int perMemberNo) throws Exception {
    return null;
  }

  @Override
  public AskBoard findByCeoAskBoard(int askNo, int ceoMemberNo) throws Exception {

    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " ab.ask_board_no,"
            + " ab.title,"
            + " ab.content,"
            + " cm.ceo_member_no ceo_no,"
            + " m.nickname,"
            + " m.status member_status,"
            + " ab.use_secret ask_status,"
            + " ab.create_dt,"
            + " ab.view_cnt,"
            + " abr.reply_title r_title,"
            + " abr.reply_content r_content,"
            + " abr.reply_dt r_dt"
            + " from"
            + " ask_board ab"
            + " left outer join member m on m.member_no=ab.member_no"
            + " left outer join ceo_member cm on cm.member_no=m.member_no"
            + " left outer join ask_board_reply abr on abr.ask_board_no=ab.ask_board_no"
            + " where ab.ask_board_no=" + askNo
            + " order by ab.ask_board_no asc");
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      AskBoard askBoard = new AskBoard();
      askBoard.setAskNo(rs.getInt("ask_board_no"));
      askBoard.setAskTitle(rs.getString("title"));
      askBoard.setAskContent(rs.getString("content"));
      askBoard.setAskRegisteredDate(rs.getDate("create_dt"));
      askBoard.setAskVeiwCount(rs.getInt("view_cnt"));
      askBoard.setAskStatus(rs.getInt("ask_status"));

      if (rs.getString("r_title") != null) {
        Reply reply = new Reply();
        reply.setReplyTitle(rs.getString("r_title"));
        reply.setReplyContent(rs.getString("r_content"));
        reply.setReplyRegisteredDate(rs.getDate("r_dt"));
        askBoard.setReply(reply);
      }

      Member member = new Member();
      CeoMember ceoMember = new CeoMember();

      if (rs.getInt("member_status") == 1) {
        member.setPerNo(rs.getInt("per_no"));
        member.setPerNickname(rs.getString("nickname"));

        askBoard.setAskMemberWriter(member);
      }
      else if (rs.getInt("member_status") == 2) {
        ceoMember.setCeoNo(rs.getInt("ceo_no"));
        ceoMember.setCeoNickname(rs.getString("nickname"));

        askBoard.setAskCeoWriter(ceoMember);
      }
      return askBoard;
    }

  }

}
