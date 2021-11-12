package com.ogong.pms.servlet.ceoCafe;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeRoom;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/ceomember/cafe/room/add")
public class CeoCafeRoomAddContoller extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CafeDao cafeDao;
  CafeRoomDao cafeRoomDao;
  SqlSession sqlSession;

  @Override
  public void init() throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    cafeDao = (CafeDao) 웹애플리케이션공용저장소.getAttribute("cafeDao");
    cafeRoomDao = (CafeRoomDao) 웹애플리케이션공용저장소.getAttribute("cafeRoomDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int cafeNo = Integer.parseInt(request.getParameter("cafeno"));    //카페번호

      Cafe cafe = cafeDao.findByCafeNo(cafeNo);

      CafeRoom cafeRoom = new CafeRoom();

      cafeRoom.setCafe(cafe);
      cafeRoom.setRoomName(request.getParameter("name"));
      cafeRoom.setRoomInfo(request.getParameter("info"));
      cafeRoom.setPeople(Integer.parseInt(request.getParameter("people")));
      cafeRoom.setRoomPrice(Integer.parseInt(request.getParameter("roomPrice")));
      cafeRoom.setRoomStatus(1);

      cafe.setInfo(cafe.getInfo().replace("\n", "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"));

      // 사진
      Part photoPart = request.getPart("photo");
      //      if (photoPart.getSize() > 0) {
      if (photoPart != null) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(getServletContext().getRealPath("/upload/cafe") + "/" + filename);
        cafeRoom.setRoomImg(filename);

        Thumbnails.of(getServletContext().getRealPath("/upload/cafe") + "/" + filename)
        .size(40, 40)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_40x40";
          }
        });

        Thumbnails.of(getServletContext().getRealPath("/upload/cafe") + "/" + filename)
        .size(80, 80)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_80x80";
          }
        });
      }


      //fileNames.add(new CafeImage(fileName));

      //      if (!fileNames.isEmpty()) {
      //        HashMap<String,Object> params = new HashMap<>();
      //        params.put("fileNames", fileNames);
      //        params.put("cafeRoomNo", cafeRoom.getRoomNo());
      //
      //        cafeRoomDao.insertCafeRoomImage(params);
      //      }

      cafeRoomDao.insertCafeRoom(cafeRoom);
      sqlSession.commit();

      response.sendRedirect("list?cafeno="+ cafe.getNo());


    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
