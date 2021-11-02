package com.ogong.pms.handler.Askboard;

import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.handler.CommandRequest;

@WebServlet("askboard/peradd")
public class AskBoardAddHandler extends HttpServlet {

  AskBoardDao askBoardDao;
  SqlSession sqlSession;

  public AskBoardAddHandler(AskBoardDao askBoardDao, SqlSession sqlSession) {
    this.askBoardDao = askBoardDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

  }
}