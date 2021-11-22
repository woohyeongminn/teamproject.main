package com.ogong.pms.web.ceoCafe;

import static com.ogong.pms.domain.Cafe.DELETE;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeImage;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.web.cafe.CafeHandlerHelper;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class CeoCafeController {

  @Autowired CeoMemberDao ceoMemberDao;
  @Autowired CafeDao cafeDao;
  @Autowired CafeReviewDao cafeReviewDao;
  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  //기업 MY카페 (상세 1개)
  @GetMapping("/ceomember/cafe/detail")
  public ModelAndView ceoCafeDetail(HttpSession session) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");

    if (loginCeo == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    if (ceoMember == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    Cafe cafe = cafeDao.findByCeoMember(loginCeo.getCeoNo());

    ModelAndView mv = new ModelAndView();

    if (cafe == null) {
      mv.addObject("ceoMember", ceoMember);
      mv.addObject("pageTitle", "👩‍🏫 내 카페");
      mv.addObject("contentUrl", "ceoCafe/CeoCafeMyDetail.jsp");
      mv.setViewName("template1");
      return mv;

    } else {

      /* cafe.setInfo(cafe.getInfo().replace("\n", "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")); */

      HashMap<String,Object> params = new HashMap<>();
      params.put("cafeNo", cafe.getNo());
      cafe.setHoliday(cafeDao.getCafeHoliday(params));

      String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());
      List<CafeReview> reviewList = cafeReviewDao.findReviewListByCafeNo(cafe.getNo());
      //List<CafeImage> cafeImgList = cafe.getCafeImageNames();


      mv.addObject("ceoMember", ceoMember);
      mv.addObject("cafe", cafe);
      mv.addObject("cafeStatus", status);
      mv.addObject("reviewList", reviewList);

      mv.addObject("pageTitle", "👩‍🏫 내 카페 - "+ cafe.getName());
      mv.addObject("contentUrl", "ceoCafe/CeoCafeMyDetail.jsp");
      mv.setViewName("template1");
      return mv;
    }
  }

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  // 카페 등록 폼
  @GetMapping("/ceomember/cafe/addform")
  public ModelAndView ceoCafeAddForm(HttpSession session) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");

    if (loginCeo == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    ModelAndView mv = new ModelAndView();

    mv.addObject("ceoMember", ceoMember);
    mv.addObject("pageTitle", "👩‍🏫 스터디카페 등록");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeAddForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  //카페 등록
  @PostMapping("/ceomember/cafe/add")
  public ModelAndView ceoCafeAdd(
      HttpSession session, Cafe cafe,
      String inputOpenTime, String inputCloseTime,
      String tel1, String tel2, String tel3,
      String addr1, String addr2, String addr3,
      Collection<Part> photoFileList) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");

    if (loginCeo == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    cafe.setCeoMember(ceoMember);

    ArrayList<CafeImage> cafeImageList = new ArrayList<>();

    for (Part photoFile : photoFileList) {
      if (photoFile.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoFile.write(sc.getRealPath("/upload/cafe") + "/" + filename);

        CafeImage cafeImage = new CafeImage();
        cafeImage.setName(filename);

        Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
        .size(680, 264)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_680x264";   // 카페 상세용 사이즈
          }
        });

        Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
        .size(329, 247)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_329x247";   // 카페 목록용 사이즈
          }
        });

        Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
        .size(488, 300)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_488x300";   // 사장 마이카페 사이즈
          }
        });

        cafeImageList.add(cafeImage);
      }
    }
    cafe.setCafeImgs(cafeImageList);
    cafe.setOpenTime(LocalTime.parse(inputOpenTime));
    cafe.setCloseTime(LocalTime.parse(inputCloseTime));
    cafe.setPhone(tel1 + "-" + tel2 + "-" + tel3);
    cafe.setLocation(addr2 + ", " + addr3);

    cafeDao.insertCafe(cafe);
    sqlSessionFactory.openSession().commit();

    Cafe myCafe = cafeDao.findByCeoMember(loginCeo.getCeoNo());

    for (int i = 0; i < cafe.getCafeImgs().size(); i++) {
      String img = cafe.getCafeImgs().get(i).getName();
      cafeDao.insertCafeImage(img, myCafe.getNo());
    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;
  }

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  // 카페 삭제 폼
  @GetMapping("/ceomember/cafe/deleteform")
  public ModelAndView ceoCafeDeleteForm(int cafeno) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    if (cafe == null) {
      throw new Exception("등록된 카페가 없습니다.");
    }

    System.out.println("*************" + cafe.getCafeImgs());
    System.out.println("*************" + cafe.getCafeImgs());



    String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());

    ModelAndView mv = new ModelAndView();

    mv.addObject("cafe", cafe);
    mv.addObject("cafeStatus", status);

    mv.addObject("pageTitle", "👩‍🏫 " + cafe.getName() + " - 내 카페 삭제");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeDeleteForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  // 카페 삭제
  @GetMapping("/ceomember/cafe/delete")
  public ModelAndView ceoCafeDelete(int cafeno) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    if (cafe == null) {
      throw new Exception("등록된 카페가 없습니다.");
    }

    cafe.setName("");
    cafe.setMainImg("");
    cafe.setInfo("");
    cafe.setLocation("");
    cafe.setPhone("");
    cafe.setOpenTime(LocalTime.of(00, 00));
    cafe.setCloseTime(LocalTime.of(00, 00));
    cafe.setHoliday("");
    cafe.setBookable(0);
    cafe.setTimePrice(0);
    cafe.setCafeStatus(DELETE);

    cafeDao.updateCafe(cafe);
    cafeDao.deleteCafe(cafe.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;
  }

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  // 카페 수정 폼
  @GetMapping("/ceomember/cafe/updateform")
  public ModelAndView ceoCafeUpdateForm(int cafeno) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    if (cafe == null) {
      throw new Exception("등록된 카페가 없습니다.");
    }

    String status = CafeHandlerHelper.getCafeStatusLabel(cafe.getCafeStatus());

    ModelAndView mv = new ModelAndView();
    mv.addObject("cafe", cafe);
    mv.addObject("cafeStatus", status);

    mv.addObject("pageTitle", "👩‍🏫 " + cafe.getName() + " - 내 카페 수정");
    mv.addObject("contentUrl", "ceoCafe/CeoCafeUpdateForm.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/ceomember/cafe/update")
  public ModelAndView ceoCafeUpdate(Cafe cafe, Collection<Part> photoFileList, String inputOpenTime, String inputCloseTime) throws Exception {

    Cafe oldcafe = cafeDao.findByCafeNo(cafe.getNo());

    if (oldcafe == null) {
      throw new Exception("등록된 카페가 없습니다.");
    }

    ArrayList<CafeImage> cafeImageList = new ArrayList<>();

    if (!cafeImageList.isEmpty()) {
      for (Part photoFile : photoFileList) {
        if (photoFile.getSize() > 0) {
          String filename = UUID.randomUUID().toString();
          photoFile.write(sc.getRealPath("/upload/cafe") + "/" + filename);

          CafeImage cafeImage = new CafeImage();
          cafeImage.setName(filename);

          Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
          .size(680, 264)
          .outputFormat("jpg")
          .crop(Positions.CENTER)
          .toFiles(new Rename() {
            @Override
            public String apply(String name, ThumbnailParameter param) {
              return name + "_680x264";   // 카페 상세용 사이즈
            }
          });

          Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
          .size(329, 247)
          .outputFormat("jpg")
          .crop(Positions.CENTER)
          .toFiles(new Rename() {
            @Override
            public String apply(String name, ThumbnailParameter param) {
              return name + "_329x247";   // 카페 목록용 사이즈
            }
          });

          Thumbnails.of(sc.getRealPath("/upload/cafe") + "/" + filename)
          .size(488, 300)
          .outputFormat("jpg")
          .crop(Positions.CENTER)
          .toFiles(new Rename() {
            @Override
            public String apply(String name, ThumbnailParameter param) {
              return name + "_488x300";   // 사장 마이카페 사이즈
            }
          });

          cafeImageList.add(cafeImage);
        }
      }

      cafe.setCafeImgs(cafeImageList);

      for (int i = 0; i < cafe.getCafeImgs().size(); i++) {
        String img = cafe.getCafeImgs().get(i).getName();
        cafeDao.insertCafeImage(img, cafe.getNo());
      }

    } else {
      // 기존 정보로 
      cafe.setCafeImgs(oldcafe.getCafeImgs());
    }

    cafe.setOpenTime(LocalTime.parse(inputOpenTime));
    cafe.setCloseTime(LocalTime.parse(inputCloseTime));

    cafeDao.updateCafe(cafe);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;
  }

}
