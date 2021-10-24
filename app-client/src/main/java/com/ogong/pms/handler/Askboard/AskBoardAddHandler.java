package com.ogong.pms.handler.Askboard;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.handler.AuthCeoMemberLoginHandler;
import com.ogong.pms.handler.AuthPerMemberLoginHandler;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.util.Prompt;

public class AskBoardAddHandler implements Command {

  AskBoardDao askBoardDao;
  SqlSession sqlSession;

  public AskBoardAddHandler(AskBoardDao askBoardDao, SqlSession sqlSession) {
    this.askBoardDao = askBoardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println();
    System.out.println("▶ 문의사항");
    System.out.println();

    AskBoard askBoard = new AskBoard();

    int statusNo = 0;

    if (AuthPerMemberLoginHandler.getLoginUser() != null) {

      askBoard.setAskTitle(Prompt.inputString(" 제목 : "));
      askBoard.setAskContent(Prompt.inputString(" 내용 : "));
      askBoard.setAskVeiwCount(0);
      askBoard.setAskMemberWriter(AuthPerMemberLoginHandler.getLoginUser());
      askBoard.setAskRegisteredDate(new Date(System.currentTimeMillis()));

      while (true) {

        try {
          statusNo = Prompt.inputInt("\n 1: 공개 / 2: 비공개 > ");
          System.out.println();
          if (statusNo >= 3) {
            System.out.println(" >> 번호를 다시 입력하세요.\n");
            continue;
          } else if ((statusNo > 0) && (statusNo < 3)) {
            if (statusNo == 2) {
              while (true) {
                int tempPW = Prompt.inputInt(" 🔑 문의글 비밀번호(4자리) : ");
                System.out.println();
                if ((tempPW > 999) && (tempPW < 9999)) {
                  askBoard.setAskTempPW(tempPW);
                  break;

                } else {
                  System.out.println(" >> 4자리 숫자만 입력 가능합니다.");
                  continue;
                }
              }
            }

            String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
            if (!input.equalsIgnoreCase("네")) {
              System.out.println(" >> 문의글 등록을 취소하였습니다.");
              return;
            }

            break;
          }
        } catch (NumberFormatException e) {
          System.out.println(" >> 번호만 입력 가능합니다.\n");
          continue;
        }
        break;
      }

      askBoard.setAskStatus(statusNo);

      if ((statusNo > 0) && (statusNo < 3)) {
        askBoardDao.insertPer(askBoard);
        sqlSession.commit();
        System.out.println(" >> 문의글이 등록되었습니다.");
        request.getRequestDispatcher("/askBoard/perMyList").forward(request);
        return;
      }
    }

    else if (AuthCeoMemberLoginHandler.getLoginCeoMember() != null) {

      askBoard.setAskTitle(Prompt.inputString(" 제목 : "));
      askBoard.setAskContent(Prompt.inputString(" 내용 : "));
      askBoard.setAskVeiwCount(0);
      askBoard.setAskCeoWriter(AuthCeoMemberLoginHandler.getLoginCeoMember());
      askBoard.setAskRegisteredDate(new Date(System.currentTimeMillis()));

      while (true) {

        try {
          statusNo = Prompt.inputInt("\n 1: 공개 / 2: 비공개 > ");
          System.out.println();

          if (statusNo >= 3) {
            System.out.println(" >> 번호를 다시 입력하세요.\n");
            continue;

          } else if ((statusNo > 0) && (statusNo < 3)) {

            if (statusNo == 2) {
              while (true) {
                int tempPW = Prompt.inputInt(" 🔑 문의글 비밀번호(4자리) : ");
                System.out.println();
                if ((tempPW < 999) && (tempPW > 9999)) {
                  System.out.println(" >> 4자리 숫자만 입력가능합니다.");
                  continue;
                }
                askBoard.setAskTempPW(tempPW);
                break;
              }
            }

            String input = Prompt.inputString(" 정말 등록하시겠습니까? (네 / 아니오) ");
            if (!input.equalsIgnoreCase("네")) {
              System.out.println(" >> 문의글 등록을 취소하였습니다.");
              return;
            }
            break;
          }
        } catch (NumberFormatException e) {
          System.out.println(" >> 번호만 입력 가능합니다.\n");
          continue;
        }
        break;
      }

      askBoard.setAskStatus(statusNo);

      if ((statusNo > 0) && (statusNo < 3)) {
        askBoardDao.insertCeo(askBoard);
        sqlSession.commit();
        System.out.println(" >> 문의글이 등록되었습니다.");
        request.getRequestDispatcher("/askBoard/ceoMyList").forward(request);
        return;
      }
    }

    if (statusNo == 0) {
      System.out.println(" >> 이전 화면으로 돌아갑니다.");
      return;
    }
  }
}

// 마지막 고유번호를 찾아서 신규 등록시 +1 되도록 기능 구현
// AskBoard lastAskBoard = null;
// if (!askBoardList.isEmpty()) {
// lastAskBoard = askBoardList.get(askBoardList.size() - 1);
// askBoard.setAskNo(lastAskBoard.getAskNo() + 1);
// } else {
// askBoard.setAskNo(1);
// }
