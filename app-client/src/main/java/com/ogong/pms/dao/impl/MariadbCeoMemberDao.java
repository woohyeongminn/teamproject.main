package com.ogong.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.CeoMember;

public class MariadbCeoMemberDao implements CeoMemberDao {

  Connection con;

  public MariadbCeoMemberDao(Connection con){
    this.con = con;
  }

  @Override
  public void insert(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into member(name,nickname,email,password,tel,photo,status) values(?,?,?,password(?),?,?,?)",
        Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, ceoMember.getCeoName());
      stmt.setString(2, ceoMember.getCeoNickname());
      stmt.setString(3, ceoMember.getCeoEmail());
      stmt.setString(4, ceoMember.getCeoPassword());
      stmt.setString(5, ceoMember.getCeoTel());
      stmt.setString(6, ceoMember.getCeoPhoto());
      stmt.setInt(7, ceoMember.getCeoStatus());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("사장 회원 데이터 저장 실패!");
      }

      // 회원가입할때 입력된 회원의 PK 값 꺼내기
      int ceoMemberNo = 0;
      try (ResultSet pkRS = stmt.getGeneratedKeys()) {
        if (pkRS.next()) {
          ceoMemberNo = pkRS.getInt("member_no");
        }
      }

      // ceo_member 테이블에 추가하기
      try (PreparedStatement stmt2 =
          con.prepareStatement(
              "insert into ceo_member(ceo_member_no,member_no,bossname,license_no) values(?,?,?,?)")) {

        stmt2.setInt(1, ceoMember.getCeoNo()); // ceo_member_no : 사장회원 번호
        stmt2.setInt(2, ceoMemberNo); // member_no : 전체 회원에서 번호
        stmt2.setString(3, ceoMember.getCeoBossName());
        stmt2.setString(4, ceoMember.getCeoLicenseNo());
        stmt2.executeUpdate();
      }
    }
  }

  @Override
  public List<CeoMember> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " m.member_no,"
            + "m.name,"
            + "m.nickname,"
            + "m.email,"
            + "m.tel,"
            + "m.photo,"
            + "m.created_dt,"
            + "m.active,"
            + "m.status,"
            + "cm.ceo_member_no ceo_no,"
            + "cm.bossname ceo_bossname,"
            + "cm.license_no ceo_license_no"
            + " from member m"
            + " join ceo_member cm on m.member_no=cm.member_no"
            + " order by name asc");

        ResultSet rs = stmt.executeQuery()) {

      ArrayList<CeoMember> list = new ArrayList<>();

      while (rs.next()) {
        CeoMember ceoMember = new CeoMember();
        ceoMember.setCeoNo(rs.getInt("ceo_no"));
        ceoMember.setCeoName(rs.getString("name"));
        ceoMember.setCeoNickname(rs.getString("nickname"));
        ceoMember.setCeoEmail(rs.getString("email"));
        ceoMember.setCeoTel(rs.getString("tel"));
        ceoMember.setCeoPhoto(rs.getString("photo"));
        ceoMember.setCeoRegisteredDate(rs.getDate("created_dt"));
        ceoMember.setCeoStatus(rs.getInt("status"));
        ceoMember.setActive(rs.getInt("active"));
        ceoMember.setCeoBossName(rs.getString("ceo_bossname"));
        ceoMember.setCeoLicenseNo(rs.getString("ceo_license_no"));

        list.add(ceoMember);
      }

      return list;
    }
  }

  @Override
  public CeoMember findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " m.member_no,"
            + "cm.ceo_member_no ceo_no,"
            + "m.name,"
            + "m.nickname,"
            + "m.email,"
            + "m.password,"
            + "m.tel,"
            + "m.photo,"
            + "m.created_dt,"
            + "m.active,"
            + "m.status,"
            + "cm.bossname ceo_bossname,"
            + "cm.license_no ceo_license_no"
            + " from member m"
            + " join ceo_member cm on m.member_no=cm.member_no"
            + " where cm.ceo_member_no=" + no);
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      CeoMember ceoMember = new CeoMember();      
      ceoMember.setCeoNo(rs.getInt("ceo_no"));
      ceoMember.setCeoName(rs.getString("name"));
      ceoMember.setCeoNickname(rs.getString("nickname"));
      ceoMember.setCeoEmail(rs.getString("email"));
      ceoMember.setCeoPassword(rs.getString("password"));
      ceoMember.setCeoTel(rs.getString("tel"));
      ceoMember.setCeoPhoto(rs.getString("photo"));
      ceoMember.setCeoRegisteredDate(rs.getDate("created_dt"));
      ceoMember.setActive(rs.getInt("active"));
      ceoMember.setCeoStatus(rs.getInt("status"));
      ceoMember.setCeoBossName(rs.getString("ceo_bossname"));
      ceoMember.setCeoLicenseNo(rs.getString("ceo_license_no"));

      return ceoMember;
    }
  }

  // 아이디 찾기
  @Override
  public CeoMember findByName(String inputName) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " cm.ceo_member_no ceo_no,"
            + "m.name,"
            + "m.email"
            + " from member m"
            + " join ceo_member cm on m.member_no=cm.member_no" 
            + " where m.name=?")) {

      stmt.setString(1, inputName);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        CeoMember ceoMember = new CeoMember();
        ceoMember.setCeoNo(rs.getInt("ceo_no"));
        ceoMember.setCeoName(rs.getString("name"));
        ceoMember.setCeoEmail(rs.getString("email"));

        return ceoMember;
      }
    }
  }

  // 비밀번호 찾기
  @Override
  public CeoMember findByEmail(String inputEmail) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " cm.ceo_member_no ceo_no,"
            + "m.email,"
            + "m.nickname"
            + " from member m"
            + " join ceo_member cm on m.member_no=cm.member_no" 
            + " where m.email=?")) {

      stmt.setString(1, inputEmail);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        CeoMember ceoMember = new CeoMember();
        ceoMember.setCeoNo(rs.getInt("ceo_no"));
        ceoMember.setCeoEmail(rs.getString("email"));
        ceoMember.setCeoNickname(rs.getString("nickname"));

        return ceoMember;
      }
    }
  }

  @Override
  public CeoMember findByEmailAndPassword(String inputEmail, String inputPassword) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select"
            + " cm.ceo_member_no ceo_no,"
            + "m.name,"
            + "m.email"
            + " from member m"
            + " join ceo_member cm on m.member_no=cm.member_no" 
            + " where email=? and password=password(?)")) {

      // 일반문장은 try 안에 선언할 수 없다.
      stmt.setString(1, inputEmail);
      stmt.setString(2, inputPassword);

      // ResultSet을 자동 close 하기위해 try 안에 try를 선언한다.
      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }

        CeoMember ceoMember = new CeoMember();
        ceoMember.setCeoNo(rs.getInt("ceo_no"));
        ceoMember.setCeoName(rs.getString("name"));
        ceoMember.setCeoEmail(rs.getString("email"));

        return ceoMember;
      }
    }
  }

  // 개인정보 수정 - 이름
  @Override
  public void updateName(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " name = ?"
            + " where member_no ="   
            + " (select member_no from ceo_member where ceo_member_no=" + ceoMember.getCeoNo() + ")")) {

      stmt.setString(1, ceoMember.getCeoName());

      if (stmt.executeUpdate() == 0) {
        throw new Exception(" 사장 회원 닉네임 데이터 변경 실패!");
      }
    }
  }

  //개인정보 수정 - 닉네임
  @Override
  public void updateNickName(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " nickname = ?"
            + " where member_no ="
            + " (select member_no from ceo_member where ceo_member_no=" + ceoMember.getCeoNo() + ")")) {

      stmt.setString(1, ceoMember.getCeoNickname());

      if (stmt.executeUpdate() == 0) {
        throw new Exception(" 사장 회원 닉네임 데이터 변경 실패!");
      }
    }
  }

  //개인정보 수정 - 사진
  @Override
  public void updatePhoto(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " photo = ?"
            + " where member_no ="
            + " (select member_no from ceo_member where ceo_member_no=" + ceoMember.getCeoNo() + ")")) {

      stmt.setString(1, ceoMember.getCeoPhoto());

      if (stmt.executeUpdate() == 0) {
        throw new Exception(" 사장 회원 사진 데이터 변경 실패!");
      }
    }
  }

  //개인정보 수정 - 전화번호
  @Override
  public void updateTel(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " tel = ?"
            + " where member_no ="
            + " (select member_no from ceo_member where ceo_member_no=" + ceoMember.getCeoNo() + ")")) {

      stmt.setString(1, ceoMember.getCeoTel());

      if (stmt.executeUpdate() == 0) {
        throw new Exception(" 사장 회원 전화번호 데이터 변경 실패!");
      }
    }
  }

  //개인정보 수정 - 이메일
  @Override
  public void updateEmail(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " email = ?"
            + " where member_no ="
            + " (select member_no from ceo_member where ceo_member_no=" + ceoMember.getCeoNo() + ")")) {

      stmt.setString(1, ceoMember.getCeoEmail());

      if (stmt.executeUpdate() == 0) {
        throw new Exception(" 사장 회원 이메일 데이터 변경 실패!");
      }
    }
  }

  //개인정보 수정 - 비밀번호
  @Override
  public void updatePassword(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " password = password(?)"
            + " where member_no ="
            + " (select member_no from ceo_member where ceo_member_no=" + ceoMember.getCeoNo() + ")")) {

      stmt.setString(1, ceoMember.getCeoPassword());

      if (stmt.executeUpdate() == 0) {
        throw new Exception(" 사장 회원 비밀번호 데이터 변경 실패!");
      }
    }
  }

  // 탈퇴
  @Override
  public void updateActive(CeoMember ceoMember) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update member set"
            + " active = 2"
            + " where member_no ="
            + " (select member_no from ceo_member where ceo_member_no=" + ceoMember.getCeoNo() + ")")) {

      if (stmt.executeUpdate() == 0) {
        throw new Exception(" 사장 회원 탈퇴 실패!");
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {

  }
}