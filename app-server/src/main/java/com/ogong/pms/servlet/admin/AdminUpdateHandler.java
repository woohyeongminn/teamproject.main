package com.ogong.pms.servlet.admin;

import org.apache.ibatis.session.SqlSession;
import com.ogong.menu.Menu;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.domain.Admin;
import com.ogong.pms.handler.AuthAdminLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AdminUpdateHandler implements Command {

  AdminDao adminDao;
  SqlSession sqlSession;

  public AdminUpdateHandler(AdminDao adminDao, SqlSession sqlSession) {
    this.adminDao = adminDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 프로필 수정하기");
    System.out.println();

    int adminNo = (int) request.getAttribute("inputNo");

    Admin admin = adminDao.findByAdminNo(adminNo);

    System.out.println("1. 닉네임");
    System.out.println("2. 이메일");
    System.out.println("3. 비밀번호");
    System.out.println();

    int selectNo = Prompt.inputInt(" 수정하고 싶은 정보를 선택해 주세요. > ");

    String adminNickname = admin.getMasterNickname();
    String adminEmail = admin.getMasterNickname();
    String adminPassword = admin.getMasterPassword();

    switch (selectNo) {
      case 1:
        adminNickname = Prompt.inputString(" 닉네임 : ");
        break;
      case 2:
        adminEmail = Prompt.inputString(" 이메일 : ");
        break;
      case 3:
        adminPassword =  Prompt.inputString(" 비밀번호 : ");
        break;
      default:
        System.out.println(" >> 올바른 번호를 입력해 주세요.");
        return;
    }

    System.out.println();
    String input = Prompt.inputString(" 정말 변경하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 취소되었습니다.");
      return;
    } 

    if (selectNo == 1) {
      admin.setMasterNickname(adminNickname);
      adminDao.updateNickname(admin);
      sqlSession.commit();
    } else if (selectNo == 2) {
      admin.setMasterEmail(adminEmail);
      adminDao.updateEmail(admin);
      sqlSession.commit();
    } else if (selectNo == 3) {
      admin.setMasterPassword(adminPassword);
      adminDao.updatePassword(admin);
      sqlSession.commit();

      AuthAdminLoginHandler.loginAdmin = null;
      AuthAdminLoginHandler.accessLevel = Menu.LOGOUT;
      System.out.printf("\n >> '%s'님의 정보가 변경되었습니다.\n", admin.getMasterNickname());
      System.out.println(" >> 다시 로그인 해 주세요.");
      return;

    }

    System.out.printf("\n >> '%s'님의 정보가 변경되었습니다.", admin.getMasterNickname());
    System.out.println();
  }

}
