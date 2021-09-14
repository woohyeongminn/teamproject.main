package com.ogong.pms.handler;

import com.ogong.menu.Menu;

public abstract class AbstractLoginHandler implements Command {

  //  static int accessLevel = Menu.PER_LOGOUT | Menu.CEO_LOGOUT | Menu.ADMIN_LOGOUT;
  static int accessLevel = Menu.LOGOUT;
}
