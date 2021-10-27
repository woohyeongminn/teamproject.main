package com.ogong.pms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.FreeBoardFile;

public interface FreeBoardDao {
  List<FreeBoard> findAll(int studyNo) throws Exception;
  FreeBoard findByNo(@Param("freeBoardNo") int boardNo, @Param("studyNo") int studyNo) throws Exception;

  void insert(FreeBoard freeBoard) throws Exception;
  void insertFile(@Param("fileName") FreeBoardFile fileName, @Param("freeBoardNo") int boardNo) throws Exception;

  void update(@Param("freeBoard") FreeBoard freeBoard, @Param("studyNo") int studyNo) throws Exception;
  //void updateFile(@Param("fileName") FreeBoardFile fileName, @Param("freeBoardNo") int boardNo) throws Exception;
  void updateTitle(@Param("freeBoard")FreeBoard freeBoard) throws Exception;
  void updateContent(@Param("freeBoard")FreeBoard freeBoard) throws Exception;

  void updateViewCount(@Param("freeBoard") FreeBoard freeBoard, @Param("studyNo") int studyNo) throws Exception;

  void delete(@Param("freeBoardNo") int freeBoardNo, @Param("studyNo") int studyNo) throws Exception;

  void deleteFile(int freeBoardNo) throws Exception;    /*게시글 첨부파일 전체 삭제*/
  void deleteFileOne(@Param("fileNo")int fileNo, @Param("freeBoardNo")int freeBoardNo) throws Exception;    /*게시글 첨부파일 하나씩 삭제*/

  void deleteComment(int freeBoardNo) throws Exception;

}
