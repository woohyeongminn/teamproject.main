package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.Member;

public interface MemberDao {

  void insert(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  Member findByNo(int no) throws Exception;
  //Member findByName(String inputName) throws Exception;
  //Member findByEmail(String inputEmail) throws Exception;
  //Member findByTel(String inputTel) throws Exception;

  Member findEmailByNameAndTel(@Param("name") String name, @Param("tel") String tel) throws Exception;
  Member findByNameAndEmail(@Param("name") String name, @Param("email") String email) throws Exception;

  Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password) throws Exception;
  void updateName(Member member) throws Exception;
  void updateNickname(Member member) throws Exception;
  void updatePhoto(Member member) throws Exception;
  void updateTel(Member member) throws Exception;
  void updateEmail(Member member) throws Exception;
  void updatePassword(Member member) throws Exception;
  void updateActive(Member member) throws Exception;

  String idOverlap(String id) throws Exception;  
  String nickOverlap(String nick) throws Exception;  
  // void delete(int no) throws Exception;
  //  void selectOnByNickname(Member member) throws Exception;
  //  int indexNo(int no) throws Exception;
  //  List<Member> findByKeyword(String keyword) throws Exception;

}
