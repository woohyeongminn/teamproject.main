package com.ogong.pms.servlet;

import javax.servlet.http.HttpServlet;
import com.ogong.menu.Menu;

public abstract class AbstractLoginHandler extends HttpServlet {

  public static int accessLevel = Menu.LOGOUT;

  public static int getUserAccessLevel() {
    return accessLevel;
  }
}
