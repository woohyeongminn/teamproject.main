package com.ogong.pms.servlet.ceoCafe;

import static com.ogong.pms.domain.Cafe.DELETE;
import java.time.LocalTime;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CeoCafeDeleteHandler implements Command {

  CafeDao cafeDao;
  SqlSession sqlSession;

  public CeoCafeDeleteHandler (CafeDao cafeDao, SqlSession sqlSession) {
    this.cafeDao = cafeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 삭제");
    System.out.println();

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 장소 삭제를 취소하였습니다.");
      return;
    }

    Cafe cafe = cafeDao.findByCafeNo((int) request.getAttribute("cafeNo"));

    cafe.setName("");
    cafe.setMainImg("");
    cafe.setInfo("");
    cafe.setLocation("");
    cafe.setPhone("");
    cafe.setOpenTime(LocalTime.of(00, 00));
    cafe.setCloseTime(LocalTime.of(00, 00));
    cafe.setHoliday("");
    cafe.setBookable(0);
    cafe.setTimePrice(0);
    cafe.setCafeStatus(DELETE);

    cafeDao.deleteCafe(cafe.getNo());
    sqlSession.commit();

    System.out.println(" >> 삭제가 완료되었습니다.");
  }


}
