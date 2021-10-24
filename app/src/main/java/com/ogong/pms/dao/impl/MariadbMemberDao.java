//package com.ogong.pms.dao.impl;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import com.ogong.pms.dao.MemberDao;
//import com.ogong.pms.domain.Member;
//
//public class MariadbMemberDao implements MemberDao {
//
//  Connection con;
//
//  public MariadbMemberDao(Connection con) {
//    this.con = con;
//  }
//
//  @Override
//  public void insert(Member member) throws Exception {
//    try (PreparedStatement stmt = con.prepareStatement(
//        "insert into member(name,nickname,email,password,tel,photo,status) values(?,?,?,password(?),?,?,?)",
//        Statement.RETURN_GENERATED_KEYS)) {
//
//      stmt.setString(1, member.getPerName());
//      stmt.setString(2, member.getPerNickname());
//      stmt.setString(3, member.getPerEmail());
//      stmt.setString(4, member.getPerPassword());
//      stmt.setString(5, member.getPerTel());
//      stmt.setString(6, member.getPerPhoto());
//      stmt.setInt(7, member.getPerStatus());
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 데이터 저장 실패!");
//      }
//
//      // 회원가입할때 입력된 회원의 PK 값 꺼내기
//      int perMemberNo = 0;
//      try (ResultSet pkRS = stmt.getGeneratedKeys()) {
//        if (pkRS.next()) {
//          perMemberNo = pkRS.getInt("member_no");
//        }
//      }
//
//      // per_member 테이블에 추가하기
//      try (PreparedStatement stmt2 =
//          con.prepareStatement("insert into per_member(member_no) values(?)")) {
//
//        //stmt2.setInt(1, member.getPerNo()); // per_member_no : 개인회원 번호
//        stmt2.setInt(1, perMemberNo); // member_no : 전체 회원에서 번호
//        stmt2.executeUpdate();
//      }
//    }
//  }
//
//  @Override
//  public List<Member> findAll() throws Exception {
//    try (
//        PreparedStatement stmt = con.prepareStatement("select"
//            + " pm.per_member_no per_no,m.name,m.nickname,m.email,m.tel,m.photo,m.created_dt,m.active,m.status"
//            + " from member m" + " join per_member pm on m.member_no=pm.member_no"
//            + " order by name asc");
//
//        ResultSet rs = stmt.executeQuery()) {
//
//      ArrayList<Member> list = new ArrayList<>();
//
//      while (rs.next()) {
//        Member member = new Member();
//        member.setPerNo(rs.getInt("per_no"));
//        member.setPerName(rs.getString("name"));
//        member.setPerNickname(rs.getString("nickname"));
//        member.setPerEmail(rs.getString("email"));
//        member.setPerTel(rs.getString("tel"));
//        member.setPerPhoto(rs.getString("photo"));
//        member.setPerRegisteredDate(rs.getDate("created_dt"));
//        member.setActive(rs.getInt("active"));
//        member.setPerStatus(rs.getInt("status"));
//
//        list.add(member);
//      }
//
//      return list;
//    }
//  }
//
//  @Override
//  public Member findByNo(int no) throws Exception {
//    try (
//        PreparedStatement stmt = con.prepareStatement("select"
//            + " pm.per_member_no per_no,m.name,m.nickname,m.email,m.tel,m.photo,m.created_dt,m.active,m.status"
//            + " from member m" 
//            + " join per_member pm on pm.member_no=m.member_no" + " where pm.per_member_no=" + no);
//
//        ResultSet rs = stmt.executeQuery()) {
//
//      if (!rs.next()) {
//        return null;
//      }
//
//      Member member = new Member();
//      member.setPerNo(rs.getInt("per_no"));
//      member.setPerName(rs.getString("name"));
//      member.setPerNickname(rs.getString("nickname"));
//      member.setPerEmail(rs.getString("email"));
//      member.setPerTel(rs.getString("tel"));
//      member.setPerPhoto(rs.getString("photo"));
//      member.setPerRegisteredDate(rs.getDate("created_dt"));
//      member.setActive(rs.getInt("active"));
//      member.setPerStatus(rs.getInt("status"));
//
//      return member;
//    }
//  }
//
//  @Override
//  public Member findByNickName(String inputNick) throws Exception {
//    try (PreparedStatement stmt = con.prepareStatement(
//        "select pm.per_member_no,m.name,m.nickname,m.email,m.tel,m.photo,m.created_dt,m.active,m.status"
//            + " from member m" + " join per_member pm on m.member_no=pm.member_no"
//            + " where nickname=?")) {
//
//      stmt.setString(1, inputNick);
//
//      try (ResultSet rs = stmt.executeQuery()) {
//        if (!rs.next()) {
//          return null;
//        }
//
//        Member member = new Member();
//        member.setPerNo(rs.getInt("member_no"));
//        member.setPerName(rs.getString("name"));
//        member.setPerNickname(rs.getString("nickname"));
//        member.setPerEmail(rs.getString("email"));
//        member.setPerTel(rs.getString("tel"));
//        member.setPerPhoto(rs.getString("photo"));
//        member.setPerRegisteredDate(rs.getDate("created_dt"));
//        member.setActive(rs.getInt("active"));
//        member.setPerStatus(rs.getInt("status"));
//        return member;
//      }
//    }
//  }
//
//  @Override
//  public Member findByEmail(String inputEmail) throws Exception {
//    try (PreparedStatement stmt = con.prepareStatement(
//        "select pm.per_member_no per_no,m.name,m.nickname,m.email,m.tel,m.photo,m.created_dt,m.active,m.status"
//            + " from member m" + " join per_member pm on m.member_no=pm.member_no"
//            + " where email=?")) {
//
//      stmt.setString(1, inputEmail);
//
//      try (ResultSet rs = stmt.executeQuery()) {
//        if (!rs.next()) {
//          return null;
//        }
//
//        Member member = new Member();
//        member.setPerNo(rs.getInt("per_no"));
//        member.setPerName(rs.getString("name"));
//        member.setPerNickname(rs.getString("nickname"));
//        member.setPerEmail(rs.getString("email"));
//        member.setPerTel(rs.getString("tel"));
//        member.setPerPhoto(rs.getString("photo"));
//        member.setPerRegisteredDate(rs.getDate("created_dt"));
//        member.setActive(rs.getInt("active"));
//        member.setPerStatus(rs.getInt("status"));
//        return member;
//      }
//    }
//  }
//
//  @Override
//  public Member findByEmailAndPassword(String inputEmail, String inputPassword, int inputActive) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("select pm.per_member_no per_no, m.nickname, m.email from member m"
//            + " join per_member pm on m.member_no=pm.member_no"
//            + " where email=? and password=password(?)")) {
//
//      stmt.setString(1, inputEmail);
//      stmt.setString(2, inputPassword);
//
//      try (ResultSet rs = stmt.executeQuery()) {
//        if (!rs.next()) {
//          return null;
//        }
//
//        Member member = new Member();
//        member.setPerNo(rs.getInt("per_no"));
//        member.setPerNickname(rs.getString("nickname"));
//        member.setPerEmail(rs.getString("email"));
//
//        return member;
//      }
//    }
//  }
//
//  @Override
//  public void updateName(Member member) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("update member set" + " name=?" + " where member_no="
//            + "(select member_no from per_member where per_member_no=?)")) {
//
//      stmt.setString(1, member.getPerName());
//      stmt.setInt(2, member.getPerNo());
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 이름 데이터 변경 실패!");
//      }
//    }
//  }
//
//  @Override
//  public void updateNickname(Member member) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("update member set" + " nickname=?" + " where member_no="
//            + "(select member_no from per_member where per_member_no=?)")) {
//
//      stmt.setString(1, member.getPerNickname());
//      stmt.setInt(2, member.getPerNo());
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 닉네임 데이터 변경 실패!");
//      }
//    }
//  }
//
//  @Override
//  public void updatePhoto(Member member) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("update member set" + " photo=?" + " where member_no="
//            + "(select member_no from per_member where per_member_no=?)")) {
//
//      stmt.setString(1, member.getPerPhoto());
//      stmt.setInt(2, member.getPerNo());
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 사진 데이터 변경 실패!");
//      }
//    }
//  }
//
//  @Override
//  public void updateTel(Member member) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("update member set" + " tel=?" + " where member_no="
//            + "(select member_no from per_member where per_member_no=?)")) {
//
//      stmt.setString(1, member.getPerTel());
//      stmt.setInt(2, member.getPerNo());
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 전화번호 데이터 변경 실패!");
//      }
//    }
//  }
//
//  @Override
//  public void updateEmail(Member member) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("update member set" + " email=?" + " where member_no="
//            + "(select member_no from per_member where per_member_no=?)")) {
//
//      stmt.setString(1, member.getPerEmail());
//      stmt.setInt(2, member.getPerNo());
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 이메일 데이터 변경 실패!");
//      }
//    }
//  }
//
//  @Override
//  public void updatePassword(Member member) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("update member set" 
//            + " password=password(?)" 
//            + " where member_no="
//            + " (select member_no from per_member where per_member_no=?)")) {
//
//      stmt.setString(1, member.getPerPassword());
//      stmt.setInt(2, member.getPerNo());
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 비밀번호 데이터 변경 실패!");
//      }
//    }
//  }
//
//  @Override
//  public void updateActive(Member member) throws Exception {
//    try (PreparedStatement stmt =
//        con.prepareStatement("update member set" + " active=2" + " where member_no="
//            + " (select member_no from member where member_no=" + member.getPerNo() + ")")) {
//
//      if (stmt.executeUpdate() == 0) {
//        throw new Exception("회원 탈퇴 실패!");
//      }
//    }
//  }
//}
//
//// @Override
//// public void delete(int no) throws Exception {
//// try (PreparedStatement stmt1 = con.prepareStatement(
//// "delete from per_member where member_no=?")) {
//// try (PreparedStatement stmt2 = con.prepareStatement(
//// "delete from member where member_no=?")) {
////
//// stmt1.setInt(1, no);
//// stmt2.setInt(2, no);
////
//// if (stmt1.executeUpdate() == 0 && stmt2.executeUpdate() == 0) {
//// throw new Exception("회원 데이터 삭제 실패!");
//// }
//// }
//// }
//// }
