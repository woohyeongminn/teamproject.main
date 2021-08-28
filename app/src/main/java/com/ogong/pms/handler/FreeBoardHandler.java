package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Login;
import com.ogong.util.Prompt;

public class FreeBoardHandler {


  List<Login> loginList;
  List<FreeBoard> freeBoardList;
  FreeBoardHandler freeBoardHandler;


  public FreeBoardHandler(List<Login> loginList, List<FreeBoard> freeBoardList, FreeBoardHandler freeBoardHandler) {
    this.freeBoardList = freeBoardList;
    this.loginList = loginList;
    this.freeBoardHandler = freeBoardHandler;
  }


  public void add() {
    System.out.println("[자유게시판 글쓰기]");

    FreeBoard freeBoard = new FreeBoard();
    //Join join = new Join();

    freeBoard.setFreeBoardTitle(Prompt.inputString("제목? "));
    freeBoard.setFreeBoardWriter(Prompt.inputString("작성자? "));
    freeBoard.setFreeBoardContent(Prompt.inputString("내용? "));
    freeBoard.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));

    freeBoardList.add(freeBoard);
  }

  //freeBoard.setFreeBoardWriter(join.getJoinNickname());
  //------------------------------------------------------------------------------------------------


  public void list() {
    System.out.println("[자유게시판 목록]");

    FreeBoard[] list = freeBoardList.toArray(new FreeBoard[0]);

    for (FreeBoard freeBoard : list) {
      System.out.printf(
          "번호 : %s\n, 제목 : %s\n, 내용 : %s\n, 첨부파일 : %s\n, 작성자 : %s\n, 조회수 : %s\n, 작성일 : %s\n",
          freeBoard.getFreeBoardNo(), 
          freeBoard.getFreeBoardTitle(),
          freeBoard.getFreeBoardContent(),
          freeBoard.getFreeBoardAtcFile(),
          freeBoard.getFreeBoardWriter(),
          freeBoard.getFreeBoardViewcount(),
          freeBoard.getFreeBoardRegisteredDate()
          );
    }
  }

  //------------------------------------------------------------------------------------------------
  public void detail() {
    System.out.println("[문의사항 상세보기]");
    int freeNo = Prompt.inputInt("번호? ");

    FreeBoard free = findByNo(freeNo);

    if (free == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }


    System.out.printf("제목: %s\n", free.getFreeBoardTitle());
    System.out.printf("내용: %s\n", free.getFreeBoardContent                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ());
    System.out.printf("작성자: %s\n", free.getFreeBoardWriter());
    System.out.printf("등록일: %s\n", free.getFreeBoardRegisteredDate());

    free.setAskVeiwCount(ask.getAskVeiwCount() + 1);
    System.out.printf("조회수: %d\n", free.getFreeBoardViewcount());
  }

  //------------------------------------------------------------------------------------------------






  public void update() {
    System.out.println("[기업회원 정보변경]");

    String inputceoEmail = Prompt.inputString("이메일? ");
    CeoMember ceoMember = findByEmail(inputceoEmail);



    if (ceoMember == null) {
      System.out.println("해당 이메일의 기업회원이 없습니다.");
      return;
    }

    String ceoName = Prompt.inputString("이름(" + ceoMember.getCeoName()  + ")? ");
    String ceoEmail = Prompt.inputString("이메일(" + ceoMember.getCeoEmail() + ")? ");
    String ceoPassword = Prompt.inputString("암호? ");
    String ceoStoreName = Prompt.inputString("점포명(" + ceoMember.getCeoStoreName() + ")? ");
    String ceoStoreDetailAddress = Prompt.inputString("점포주소(" + ceoMember.getCeoStoreDetailAddress() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("기업회원 변경을 취소하였습니다.");
      return;
    }

    ceoMember.setCeoName(ceoName);
    ceoMember.setCeoEmail(ceoEmail);
    ceoMember.setCeoPassword(ceoStoreName);
    ceoMember.setCeoPhoto(ceoStoreDetailAddress);

    System.out.println("기업회원 정보를 변경하였습니다.");
  }

  public void delete() {
    System.out.println("[개인회원 탈퇴]");

    String inputceoEmail = Prompt.inputString("이메일? ");
    CeoMember ceoMember = findByEmail(inputceoEmail);

    if (ceoMember == null) {
      System.out.println("해당 이메일의 회원이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 탈퇴하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("회원 탈퇴를 취소하였습니다.");
      return;
    }

    ceoMemberList.remove(ceoMember);

    System.out.println("회원이 탈퇴되었습니다.");
  }



  public CeoMember findByEmail(String ceoEmail) {
    CeoMember[] arr = ceoMemberList.toArray(new CeoMember[0]);
    for (Object obj : arr) {
      CeoMember ceoMember = (CeoMember) obj;
      if (ceoMember.getCeoEmail().equals(ceoEmail)) {
        return ceoMember;
      }
    }
    return null;
  }




  //-------------prompt는 login에서 사용------------------------------------------------
  public boolean exist(String ceoEmail) {
    CeoMember[] list = ceoMemberList.toArray(new CeoMember[0]);
    for (CeoMember ceoMember : list) {
      if (ceoMember.getCeoEmail().equals(ceoEmail)) {
        return true;
      }
    }
    return false;
  }

  public String promptCeoEmail(String ceoEmail) {
    while (true) {
      String ceoEmailBox = Prompt.inputString(ceoEmail);
      if (this.exist(ceoEmailBox)) {
        return ceoEmailBox;
      } else if (ceoEmailBox.length() == 0) {
        return null;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }

  public String promptMemberPassword(String ceoPassWord) {
    while (true) {
      String ceoPasswordBox = Prompt.inputString(ceoPassWord);
      if (this.exist(ceoPasswordBox)) {
        return ceoPasswordBox;
      } else if (ceoPasswordBox.length() == 0) {
        return null;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }
  }



  private FreeBoard findByNo(int freeNo) {
    FreeBoard[] arr = freeBoardList.toArray(new FreeBoard[0]);
    for (FreeBoard ask : arr) {
      if (ask.getFreeBoardNo() == freeNo) {
        return ask;
      }
    }
    return null;
  }


}

