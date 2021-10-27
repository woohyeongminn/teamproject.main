package com.ogong.pms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.ogong.pms.dao.AdminDao;
import com.ogong.pms.dao.AskBoardDao;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReservationDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.dao.CommentDao;
import com.ogong.pms.dao.FreeBoardDao;
import com.ogong.pms.dao.MemberDao;
import com.ogong.pms.dao.NoticeDao;
import com.ogong.pms.dao.StudyDao;
import com.ogong.pms.dao.ToDoDao;

@WebListener
public class AppInitListener implements ServletContextListener {

  SqlSession sqlSession = null;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("애플리케이션 시작됨!");

    try {
      // Mybatis의 SqlSession 객체 준비
      sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
          "com/ogong/pms/conf/mybatis-config.xml")).openSession();

      // SqlSession 객체를 통해 MemberDao 구현체를 자동 생성한다.
      AdminDao adminDao = sqlSession.getMapper(AdminDao.class);
      MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
      CeoMemberDao ceoMemberDao = sqlSession.getMapper(CeoMemberDao.class);
      NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
      AskBoardDao askBoardDao = sqlSession.getMapper(AskBoardDao.class);
      CafeDao cafeDao = sqlSession.getMapper(CafeDao.class);
      CafeReservationDao cafeReservationDao = sqlSession.getMapper(CafeReservationDao.class);
      CafeReviewDao cafeReviewDao = sqlSession.getMapper(CafeReviewDao.class);
      CafeRoomDao cafeRoomDao = sqlSession.getMapper(CafeRoomDao.class);
      StudyDao studyDao = sqlSession.getMapper(StudyDao.class);
      FreeBoardDao freeBoardDao = sqlSession.getMapper(FreeBoardDao.class);
      ToDoDao toDoDao = sqlSession.getMapper(ToDoDao.class);
      CommentDao commentDao = sqlSession.getMapper(CommentDao.class);


      // 모든 웹 애플리케이션의 컴포넌트(서블릿, 리스너, 필터)가 공유할 객체를 두는 저장소
      ServletContext 웹애플리케이션공용저장소 = sce.getServletContext();

      // 웹 애플리케이션 공용 저장소에 DAO 객체를 보관한다.
      // => 이 저장소에 보관된 객체는 서블릿에서 사용할 것이다.
      웹애플리케이션공용저장소.setAttribute("memberDao", memberDao);

      // 여기 주석 풀어서 사용
      //      웹애플리케이션공용저장소.setAttribute("ceoMemberDao", ceoMemberDao);
      //      웹애플리케이션공용저장소.setAttribute("adminDao", adminDao);
      //      웹애플리케이션공용저장소.setAttribute("noticeDao", noticeDao);
      //      웹애플리케이션공용저장소.setAttribute("askBoardDao", askBoardDao);
      웹애플리케이션공용저장소.setAttribute("cafeDao", cafeDao);
      //      웹애플리케이션공용저장소.setAttribute("cafeReservationDao", cafeReservationDao);
      웹애플리케이션공용저장소.setAttribute("cafeReviewDao", cafeReviewDao);
      웹애플리케이션공용저장소.setAttribute("cafeRoomDao", cafeRoomDao);
      //      웹애플리케이션공용저장소.setAttribute("studyDao", studyDao);
      //      웹애플리케이션공용저장소.setAttribute("freeBoardDao", freeBoardDao);
      //      웹애플리케이션공용저장소.setAttribute("toDoDao", toDoDao);
      //      웹애플리케이션공용저장소.setAttribute("commentDao", commentDao);

      웹애플리케이션공용저장소.setAttribute("sqlSession", sqlSession);      

    } catch (Exception e) {
      System.out.println("DAO 객체 준비 중 오류 발생!");
    }

  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("애플리케이션 종료됨!");

    sqlSession.close();
  }
}
