package com.ogong.pms.dao;

import java.util.List;
import com.ogong.pms.domain.Member;

public interface MemberDao {

  void insert(Member member) throws Exception;
  void selectList(Member member) throws Exception;
  void selectOneByEmailPassword(Member member) throws Exception;
  void selectOneByEmail(Member member) throws Exception;
  void selectOneByPassword(Member member) throws Exception;
  void selectOne(Member member) throws Exception;
  void update(Member member) throws Exception;
  void delete(Member member) throws Exception;
  void selectOnByNickname(Member member) throws Exception;
  Member findByNo(int no) throws Exception;
  int indexNo(int no) throws Exception;
  List<Member> findAll() throws Exception;
  List<Member> findByKeyword(String keyword) throws Exception;

}
