package com.ogong.pms.dao;

import java.util.Collection;
import java.util.Map;
import com.ogong.pms.domain.AskBoard;

public interface AskBoardDao {

  void insertPer(AskBoard askBoard) throws Exception;
  void insertCeo(AskBoard askBoard) throws Exception;
  void update(AskBoard askBoard) throws Exception;
  void updateViewCount(int askNo) throws Exception;
  void delete(int no) throws Exception;
  AskBoard findByNo(int no) throws Exception;

  //문의글 전체 목록(비회원도 조회 가능)
  Collection<AskBoard> findAll(Map<String,Object> params) throws Exception;
  int count() throws Exception;

  //개인회원 문의게시글 목록(로그인 유저)
  Collection<AskBoard> findPerMyAll(Map<String,Object> params) throws Exception;
  int countByPerNo(int perNo) throws Exception;

  //사장회원 문의게시글 목록(로그인 유저)
  Collection<AskBoard> findCeoMyAll(Map<String,Object> params) throws Exception;
  int countByCeoNo(int ceoNo) throws Exception;

  // 답변 전용
  void insertreply(AskBoard askBoard) throws Exception;
  void deletereply(int no) throws Exception;

}
