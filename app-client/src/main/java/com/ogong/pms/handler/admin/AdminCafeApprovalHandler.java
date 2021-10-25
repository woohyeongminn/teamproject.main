package com.ogong.pms.handler.admin;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminCafeApprovalHandler implements Command {

  CafeDao cafeDao;
  SqlSession sqlSession;

  public AdminCafeApprovalHandler (CafeDao cafeDao, SqlSession sqlSession) {
    this.cafeDao = cafeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 승인");

    while (true) {
      System.out.println();
      int inputCafeNo = 0;
      try {
        inputCafeNo = Prompt.inputInt(" 장소 번호 : ");
      } catch (NumberFormatException e) {
        System.out.println(" >> 숫자만 입력해 주세요.");
        continue;
      }

      Cafe cafe = cafeDao.findByCafeNo(inputCafeNo);

      if (cafe == null) {
        System.out.println(" >> 번호를 다시 선택하세요.");
        request.getRequestDispatcher("/cafe/control").forward(request);
        return;
      } else if (cafe.getCafeStatus() != Cafe.WAIT) {
        System.out.println(" >> 승인 대기 중인 카페가 아닙니다.\n    번호를 다시 선택하세요.");
        request.getRequestDispatcher("/cafe/control").forward(request);
        return;
      } else if (cafe.getCafeStatus() == Cafe.WAIT) {
        String input = Prompt.inputString(" 정말 승인하시겠습니까? (네 / 아니오) ");
        System.out.println();
        if (!input.equalsIgnoreCase("네")) {
          System.out.println(" >> 장소 승인을 취소하였습니다.");
          return;
        }

        cafeDao.updateCafeStatusToGENERAL(cafe.getNo());
        sqlSession.commit();

        System.out.printf(" >> '%s'를 승인하였습니다.\n", cafe.getName());
        return;
      }
    }
  }
}
