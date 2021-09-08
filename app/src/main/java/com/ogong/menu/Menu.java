package com.ogong.menu;

// MenuItem과 MenuGroup을 같은 타입으로 묶기 위해 정의한 클래스이다.
// 이 클래스 자체는 직접 인스턴스를 만들어 사용하기 위함이 아니라
// 상속해주는 용도이기 때문에 추상 클래스로 정의한다.
public abstract class Menu {

  public static final int ENABLE_ALL = 0; // 모든 경우
  public static final int ENABLE_LOGOUT = 1; //로그아웃인 경우
  public static final int ENABLE_LOGIN = 2;  // 로그인인 경우
  public static final int ENABLE_ADMINLOGOUT = 3; //로그아웃인 경우
  public static final int ENABLE_ADMINLOGIN = 4;  // 관리자 로그인인 경우
  public static final int ENABLE_CEOLOGOUT = 5;  // 기업회원 로그아웃인 경우
  public static final int ENABLE_CEOLOGIN = 6;  // 기업회원 로그인인 경우


  String title;

  int enableState;
  // 메뉴 이름없이 인스턴스를 생성할 수 없도록 
  // 기본 생성자를 정의하지 않는다.
  // 대신 인스턴스를 만들 때 반드시 메뉴 이름을 입력하도록 강요하기 위해
  // 다음과 같이 String 을 파라미터로 받는 생성자를 정의한다.
  public Menu(String title) {
    this.title = title;
  }

  public Menu(String title, int enableState) {
    this(title);        // 메뉴이름 설정은 기존 생성자를 통해 처리한다. 
    this.enableState = enableState;
  }

  // 서브 클래스에서 해야할 일을 정의한다.
  // 단, 서브 클래스에서 반드시 재정의 할 메서드이기 때문에 
  // 구체적으로 구현하지 않는다.
  public abstract void execute();
}
