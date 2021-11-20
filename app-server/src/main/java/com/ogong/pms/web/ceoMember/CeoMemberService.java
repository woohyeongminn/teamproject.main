package com.ogong.pms.web.ceoMember;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ogong.pms.dao.CeoMemberDao;

@Service
public class CeoMemberService {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;

  public void idOverlap(String id, HttpServletResponse response) throws IOException {

    String email;

    try {
      email = ceoMemberDao.idOverlap(id);

      if (email == null) {                //id가 없어야 true(사용 가능)
        response.getWriter().print("1");

      } else if (email != null) {          //id가 있으면 false(중복으로 사용 불가능)
        response.getWriter().print("0");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void nickOverlap(String nick, HttpServletResponse response) throws IOException {

    String nickname;

    try {
      nickname = ceoMemberDao.nickOverlap(nick);

      if (nickname == null) {                //id가 없어야 true(사용 가능)
        response.getWriter().print("1");

      } else if (nickname != null) {          //id가 있으면 false(중복으로 사용 불가능)
        response.getWriter().print("0");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void licenseOverlap(String license, HttpServletResponse response) throws IOException {

    String licenseNo;

    try {
      licenseNo = ceoMemberDao.licenseOverlap(license);

      if (licenseNo == null) {                //id가 없어야 true(사용 가능)
        response.getWriter().print("1");

      } else if (licenseNo != null) {          //id가 있으면 false(중복으로 사용 불가능)
        response.getWriter().print("0");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
