package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Calender;

public abstract class AbstractCalenderHandler implements Command {

  List<Calender> calenderList;

  public AbstractCalenderHandler( List<Calender> calenderList) {
    this.calenderList = calenderList;
  }

  //------------------------------------------------------------------------------------------------

  protected Calender findByDay(int day) {
    for (Calender calender : calenderList) {
      if (calender.getDay() == day) {
        return calender;
      }
    }
    return null;
  }
}
