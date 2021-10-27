package com.ogong.pms.servlet;

import com.ogong.menu.Menu;

public abstract class AbstractLoginHandler {

  public static int accessLevel = Menu.LOGOUT;

  public static int getUserAccessLevel() {
    return accessLevel;
  }
}
