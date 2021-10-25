package com.ogong.pms.handler.ceoCafe;

import static com.ogong.pms.domain.Cafe.WAIT;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.domain.Address;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeImage;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.AddressSearchApi;
import com.ogong.util.Prompt;

public class CeoCafeAddHandler implements Command {

  CafeDao cafeDao;
  SqlSession sqlSession;

  public CeoCafeAddHandler (CafeDao cafeDao, SqlSession sqlSession) {
    this.cafeDao = cafeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 장소 등록");
    System.out.println();

    CeoMember ceoMember = AuthCeoMemberLoginHandler.getLoginCeoMember();

    Cafe cafe = new Cafe();

    cafe.setCeoMember(ceoMember);
    cafe.setName(Prompt.inputString(" 상호명 : "));

    ArrayList<CafeImage> fileNames = new ArrayList<>();
    while(true) {
      String fileName = Prompt.inputString(" 사진 (완료:빈 문자열) : ");
      if (fileName.length() == 0) {
        break;
      }
      fileNames.add(new CafeImage(fileName));
    }

    cafe.setInfo(Prompt.inputString(" 소개글 : "));

    AddressSearchApi api = new AddressSearchApi();
    Address address = api.searchAddress();
    String addressString = address.getLnmAdres();
    System.out.println(" 기본 주소 : " + addressString);
    cafe.setLocation(addressString + " " + Prompt.inputString(" 상세 주소 : "));

    cafe.setPhone(Prompt.inputString(" 전화번호 : "));
    cafe.setOpenTime(LocalTime.parse(Prompt.inputString(" 오픈시간 (예시 > 09:00) : ")));
    cafe.setCloseTime(LocalTime.parse(Prompt.inputString(" 마감시간 (예시 > 21:00) : ")));
    //    cafe.setHoliday(Prompt.inputString(" 휴무일 : "));

    ArrayList<Date> holidays = new ArrayList<>();
    if (Prompt.inputString(" 휴무일을 입력하시겠습니까? (네 / 아니오) ").equals("네")) {
      while(true) {
        try {
          Date holiday = Prompt.inputDate(" 휴무일 (예시 > 2021-12-1, 완료: 빈 문자열) : ");
          holidays.add(holiday);
        } catch (IllegalArgumentException e) {
          break;
        }
      }
    }

    //    cafe.setBookable(Prompt.inputInt(" 예약가능인원 : "));
    //    cafe.setTimePrice(Prompt.inputInt(" 시간당금액 : "));
    cafe.setCafeStatus(WAIT); // 1 : 승인대기

    System.out.println();
    String input = Prompt.inputString(" 등록하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 등록이 취소되었습니다.");
      return;
    }

    try {
      cafeDao.insertCafe(cafe);

      if (!fileNames.isEmpty()) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("fileNames", fileNames);
        params.put("cafeNo", cafe.getNo());

        cafeDao.insertCafeImage(params);
      }

      if (!holidays.isEmpty()) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("holidays", holidays);
        params.put("cafeNo", cafe.getNo());

        cafeDao.insertCafeHolidays(params);
      }
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
    }

    System.out.println(" >> 카페 등록 완료!");
  }

}
