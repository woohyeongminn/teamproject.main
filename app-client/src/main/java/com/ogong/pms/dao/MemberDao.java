package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.Member;

public interface MemberDao {

  void insert(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  Member findByNo(int no) throws Exception;
  Member findByNickName(String inputNick) throws Exception;
  Member findByEmail(String inputEmail) throws Exception;
  Member findByEmailAndPassword(String inputEmail, String inputPassword) throws Exception;
  void updateName(Member member) throws Exception;
  void updateNickname(Member member) throws Exception;
  void updatePhoto(Member member) throws Exception;
  void updateTel(Member member) throws Exception;
  void updateEmail(Member member) throws Exception;
  void updatePassword(Member member) throws Exception;
  void updateActive(Member member) throws Exception;
  // void delete(int no) throws Exception;




  //  void selectOnByNickname(Member member) throws Exception;
  //  int indexNo(int no) throws Exception;
  //  List<Member> findByKeyword(String keyword) throws Exception;

}
