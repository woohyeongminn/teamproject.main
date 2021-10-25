package com.ogong.pms.handler.ceoCafe;

import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.CafeRoom;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoCafeRoomDeleteHandler implements Command {

  CafeDao cafeDao;
  SqlSession sqlSession;

  public CeoCafeRoomDeleteHandler (CafeDao cafeDao, SqlSession sqlSession) {
    this.cafeDao = cafeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 스터디룸 삭제");
    System.out.println();

    CafeRoom cafeRoom = (CafeRoom) request.getAttribute("cafeRoom");

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 스터디룸 삭제를 취소하였습니다.");
      return;
    }

    try {
      if (!cafeRoom.getRoomImgs().isEmpty()) {
        HashMap<String,Object> deleteParams = new HashMap<>();
        deleteParams.put("fileNames", cafeRoom.getRoomImgs());
        cafeDao.deleteCafeRoomImage(deleteParams);
      }

      cafeDao.deleteCafeRoom(cafeRoom.getRoomNo());
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
    }

    System.out.println(" >> 스터디룸 삭제 완료.");
  }

}
