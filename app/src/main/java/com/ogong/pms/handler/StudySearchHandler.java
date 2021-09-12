package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Study;

public class StudySearchHandler extends AbstractStudyHandler{

  public StudySearchHandler(List<Study> studyList) {
    super(studyList);
  }

  @Override
  public void execute() {
    search();
  }

}
