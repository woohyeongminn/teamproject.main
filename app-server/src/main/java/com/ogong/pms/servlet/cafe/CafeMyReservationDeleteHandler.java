package com.ogong.pms.servlet.cafe;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.domain.CafeReservation;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class CafeMyReservationDeleteHandler implements Command {

  CafeReservationDao cafeReservationDao;
  SqlSession sqlSession;

  public CafeMyReservationDeleteHandler (CafeReservationDao cafeReservationDao, SqlSession sqlSession) {
    this.cafeReservationDao = cafeReservationDao;
    this.sqlSession = sqlSession;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();

    CafeReservation myReservation = (CafeReservation) request.getAttribute("myReservation");

    if (myReservation == null) {
      System.out.println(" >> 예약 번호를 잘못 선택하셨습니다.");
      return;
    }

    Date today = new Date(System.currentTimeMillis());
    Date reserDate = myReservation.getUseDate();

    if (reserDate.toLocalDate().compareTo(today.toLocalDate()) > 0) {

      String input = Prompt.inputString(" 정말 예약 취소 하시겠습니까? (네 / 아니오) ");

      if (!input.equalsIgnoreCase("네")) {
        System.out.println(" >> 예약 취소를 취소합니다.");
        return;
      }

      cafeReservationDao.deleteReservation(myReservation.getReservationNo(), 3);
      sqlSession.commit();

      System.out.println(" >> 예약이 취소되었습니다.");

    } else if (reserDate.toLocalDate().compareTo(today.toLocalDate()) == 0) {
      System.out.println(" >> 당일 예약은 취소 불가능합니다.");
      return;
    } else {
      System.out.println(" >> 지난 예약은 선택할 수 없습니다.");
      return;
    }
  }
}
