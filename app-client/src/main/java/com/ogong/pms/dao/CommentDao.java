package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.Comment;

public interface CommentDao {
  List<Comment> findAll(int boardNo) throws Exception;
  void insert(@Param("studyNo")int studyNo, @Param("boardNo")int BoardNo, @Param("comment")Comment comment) throws Exception;
  void update(@Param("commentNo")int commentNo, @Param("content")String content) throws Exception;
}
