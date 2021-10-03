package com.ogong.pms.table;

import java.util.ArrayList;
import com.ogong.pms.domain.Study;
import com.ogong.server.DataProcessor;
import com.ogong.server.Request;
import com.ogong.server.Response;

// 역할 
// - 회원 데이터를 저장하고 조회하는 일을 한다.
public class StudyTable extends JsonDataTable<Study> implements DataProcessor {

  public StudyTable() {
    super("study.json", Study.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "study.insert" : insert(request, response); break;
      case "study.selectOne" : selectOne(request, response); break;
      case "study.selectList" : selectList(request, response); break;
      case "study.delete" : delete(request, response); break;
      case "study.selectByKeyword" : selectByKeyword(request, response); break;
      //      case "member.selectOneByEmail" : selectOneByEmail(request, response); break;
      //      case "member.selectOneByPassword" : selectOneByPassword(request, response); break;
      //      case "member.update" : update(request, response); break;
      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Study study = request.getObject(Study.class);
    list.add(study);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("studyNo"));
    Study study = findByStudyNo(no);

    if (study != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(study);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 스터디를 찾을 수 없습니다.");
    }
  }

  private void selectByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Study> searchResult = new ArrayList<>();
    for (Study study : list) {
      if (!study.getArea().contains(keyword) &&
          !study.getSubject().contains(keyword) &&
          !study.getStudyTitle().contains(keyword)) {
        continue;
      }
      searchResult.add(study);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("studyNo"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 스터디를 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private int indexOf(int studyNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getStudyNo() == studyNo) {
        return i;
      }
    }
    return -1;
  }

  public Study findByStudyNo(int studyinputNo) {
    for (Study study : list) {
      if (study.getStudyNo() == studyinputNo) {
        return study;
      }
    }
    return null;
  }

  // 내 스터디에서만 번호 찾기
  //  public Study findByMyStudyNo(int inputNo) {
  //
  //    Member member = AuthPerMemberLoginHandler.getLoginUser();
  //    
  //    
  //    
  //    for (Study study : list) {
  //      if (study.getStudyNo() == inputNo) {
  //        if (study.getMemberNames().contains(member.getPerNickname()) ||
  //            study.getOwner().getPerNickname().equals(member.getPerNickname())) {
  //          return study;
  //        }
  //      }
  //    }
  //    return null;
  //  }

  //  private void selectOneByEmailPassword(Request request, Response response) throws Exception {
  //    String email = request.getParameter("email");
  //    String password = request.getParameter("password");
  //
  //    Member member = null;
  //    for (Member m : list) {
  //      if (m.getPerEmail().equals(email) && m.getPerPassword().equals(password)) {
  //        member = m;
  //        break;
  //      }
  //    }
  //
  //    if (member != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(member);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 이메일과 암호를 가진 회원을 찾을 수 없습니다.");
  //    }
  //  }
  //
  //  private void selectOneByEmail(Request request, Response response) throws Exception {
  //    String email = request.getParameter("email");
  //
  //    Member member = null;
  //    for (Member m : list) {
  //      if (m.getPerEmail().equals(email)) {
  //        member = m;
  //        break;
  //      }
  //    }
  //
  //    if (member != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(member);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 이메일을 가진 회원을 찾을 수 없습니다.");
  //    }
  //  }
  //
  //  private void selectOneByPassword(Request request, Response response) throws Exception {
  //    String password = request.getParameter("password");
  //
  //    Member member = null;
  //    for (Member m : list) {
  //      if (m.getPerPassword().equals(password)) {
  //        member = m;
  //        break;
  //      }
  //    }
  //
  //    if (member != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(member);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("비밀번호가 일치하지 않습니다.");
  //    }
  //  }
  //
  //  private void selectOne(Request request, Response response) throws Exception {
  //    int no = Integer.parseInt(request.getParameter("memberNo"));
  //    Member m = findByNo(no);
  //
  //    if (m != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(m);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
  //    }
  //  }
  //
  //  private void update(Request request, Response response) throws Exception {
  //    Member member = request.getObject(Member.class);
  //
  //    int index = indexOf(member.getPerNo());
  //    if (index == -1) {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
  //      return;
  //    }
  //
  //    list.set(index, member);
  //    response.setStatus(Response.SUCCESS);
  //  }
  //
  //  private void delete(Request request, Response response) throws Exception {
  //    int no = Integer.parseInt(request.getParameter("memberNo"));
  //    int index = indexOf(no);
  //
  //    if (index == -1) {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
  //      return;
  //    }
  //
  //    list.remove(index);
  //    response.setStatus(Response.SUCCESS);
  //  }

  //  private Member findByNo(int no) {
  //    for (Member m : list) {
  //      if (m.getPerNo() == no) {
  //        return m;
  //      }
  //    }
  //    return null;
  //  }
  //
  //  private int indexOf(int memberNo) {
  //    for (int i = 0; i < list.size(); i++) {
  //      if (list.get(i).getPerNo() == memberNo) {
  //        return i;
  //      }
  //    }
  //    return -1;
  //  }

}
