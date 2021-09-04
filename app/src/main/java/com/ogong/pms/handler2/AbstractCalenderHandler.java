package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Calender;

public abstract class AbstractCalenderHandler implements Command {

  List<Calender> calenderList;

  public AbstractCalenderHandler( List<Calender> calenderList) {
    this.calenderList = calenderList;
  }

  //------------------------------------------------------------------------------------------------

  protected Calender findByDay(int day) {
    Calender[] arr = calenderList.toArray(new Calender[0]);
    for (Object obj : arr) {
      Calender calender = (Calender) obj;
      if (calender.getDay() == day) {
        return calender;
      }
    }
    return null;
  }
}
