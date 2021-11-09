package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.Comment;

public interface CommentDao {
  List<Comment> findAll(int boardNo) throws Exception;
  void insert(@Param("studyNo")int studyNo, @Param("boardNo")int BoardNo, @Param("comment")Comment comment) throws Exception;
  void updateContent(Comment comment) throws Exception;
  void delete(int commentNo) throws Exception;
  Comment findByNo(int commentNo) throws Exception;
}
