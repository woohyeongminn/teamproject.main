package com.ogong.pms.dao;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.CafeRoom;

public interface CafeRoomDao {

  //-----------------------CafeRoom--------------------------------------

  List<CafeRoom> getCafeRoomList() throws Exception;
  List<CafeRoom> findCafeRoomListByCafe(int cafeNo) throws Exception;
  List<CafeRoom> findCafeRoomListByMember(int cafeNo) throws Exception;
  CafeRoom findByRoomNo(int roomNo) throws Exception;
  void insertCafeRoom(CafeRoom cafeRoom) throws Exception;
  void updateCafeRoom(CafeRoom cafeRoom) throws Exception;
  void updateCafeRoomStatus(int roomNo) throws Exception;
  void deleteCafeRoom(int roomNo) throws Exception; // 안씀

  // 스터디룸 이미지
  void insertCafeRoomImage(HashMap<String,Object> params) throws Exception;
  void deleteCafeRoomImage(HashMap<String,Object> params) throws Exception;

}
