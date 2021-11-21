package com.ogong.pms.web.ceoCafe;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeRoomDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeRoom;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class CeoCafeRoomController {

  @Autowired CafeDao cafeDao;
  @Autowired CafeRoomDao cafeRoomDao;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;

  // 룸 목록
  @GetMapping("/ceomember/cafe/room/list")
  public ModelAndView roomList(int cafeno) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    List<CafeRoom> cafeRoomList = cafeRoomDao.findCafeRoomListByCafe(cafeno);

    System.out.println(cafeRoomList);


    ModelAndView mv = new ModelAndView();

    mv.addObject("cafeNo", cafe.getNo());
    mv.addObject("cafe", cafe);
    mv.addObject("cafeRoomList", cafeRoomList);

    mv.addObject("pageTitle", "👩‍🏫 " + cafe.getName() + " - 스터디룸 목록");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeRoomList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  // 룸 상세
  @GetMapping("/ceomember/cafe/room/detail")
  public ModelAndView roomDetail(int roomno) throws Exception {

    CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomno);

    Cafe cafe = cafeDao.findByCafeNo(cafeRoom.getCafe().getNo());

    cafeRoom.setRoomInfo(cafeRoom.getRoomInfo().replace("\n", "<br><br>"));

    ModelAndView mv = new ModelAndView();

    mv.addObject("cafeRoom", cafeRoom);
    mv.addObject("pageTitle", "👩‍🏫 " + cafe.getName() + " -" + " 스터디룸 상세");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeRoomDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  // 룸 등록 폼
  @GetMapping("/ceomember/cafe/room/addform")
  public ModelAndView roomAddForm(int cafeno) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("cafeNo", cafe.getNo());
    mv.addObject("cafe", cafe);

    mv.addObject("pageTitle", "👩‍🏫 " + cafe.getName() + " -" + " 스터디룸 등록");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeRoomAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  // 룸 등록
  @PostMapping("/ceomember/cafe/room/add")
  public ModelAndView roomAdd(CafeRoom cafeRoom, int cafeno, Part photoFile) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    cafeRoom.setCafe(cafe);
    cafeRoom.setRoomStatus(1);

    // 사진
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/cafe") + "/" + filename);
      cafeRoom.setRoomImg(filename);

      Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
      .size(80, 80)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_80x80";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
      .size(250, 180)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_250x180";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
      .size(450, 300)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_450x300";
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
    cafeRoomDao.insertCafeRoomImage( )
    sqlSessionFactory.openSession().commit();



    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?cafeno="+ cafe.getNo());
    return mv;
  }

  @GetMapping("/ceomember/cafe/room/updateform")
  public ModelAndView roomUpdateForm(int roomno) throws Exception {

    CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomno);

    if (cafeRoom == null) {
      throw new Exception("해당 스터디룸이 없습니다.");
    }

    Cafe cafe = cafeDao.findByCafeNo(cafeRoom.getCafe().getNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafeRoom", cafeRoom);

    mv.addObject("pageTitle", "👩‍🏫 " + cafe.getName() + " -" + " 스터디룸 수정");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeRoomUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/ceomember/cafe/room/update")
  public ModelAndView roomUpdate(int cafeno, CafeRoom cafeRoom) throws Exception {

    CafeRoom oldcafeRoom = cafeRoomDao.findByRoomNo(cafeRoom.getRoomNo());

    if (oldcafeRoom == null) {
      throw new Exception("등록된 스터디룸이 없습니다.");
    }

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    cafeRoom.setRoomStatus(1);
    cafeRoom.setCafe(cafe);

    //    사진 어떻게 할까 고민....
    //    String mainImg = Prompt.inputString(String.format(" 스터디룸 사진(%s) : ", cafeRoom.getRoomImg()));
    //    cafeRoom.setRoomImg(mainImg);

    cafeRoomDao.updateCafeRoom(cafeRoom);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?roomno="+ cafeRoom.getRoomNo());
    return mv;
  }

  @GetMapping("/ceomember/cafe/room/delete")
  public ModelAndView roomDelete(int cafeno, int roomno) throws Exception {

    CafeRoom cafeRoom = cafeRoomDao.findByRoomNo(roomno);

    if (!cafeRoom.getRoomImgs().isEmpty()) {
      HashMap<String,Object> deleteParams = new HashMap<>();
      deleteParams.put("fileNames", cafeRoom.getRoomImgs());
      cafeRoomDao.deleteCafeRoomImage(deleteParams);
    }

    cafeRoomDao.updateCafeRoomStatus(cafeRoom.getRoomNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?cafeno="+ cafeno);
    return mv;

  }


  @GetMapping("/ceomember/cafe/room/search")
  public ModelAndView search(String where, String keyword, int cafeno) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    List<CafeRoom> cafeRoomList = cafeRoomDao.findRoomByKeyword(where, keyword, cafeno);

    ModelAndView mv = new ModelAndView();

    mv.addObject("cafeRoomList", cafeRoomList);
    mv.addObject("keyword", keyword);
    mv.addObject("cafeNo", cafe.getNo());
    mv.addObject("pageTitle", "👩‍🏫 " + cafe.getName() + " - 스터디룸 목록");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeRoomList.jsp");
    mv.setViewName("template1");

    return mv;
  }
}