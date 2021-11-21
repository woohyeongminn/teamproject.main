package com.ogong.pms.web.ceoCafe;

import static com.ogong.pms.domain.Cafe.DELETE;
import java.time.LocalTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ogong.pms.dao.CafeDao;
import com.ogong.pms.dao.CafeReviewDao;
import com.ogong.pms.dao.CeoMemberDao;
import com.ogong.pms.domain.Cafe;
import com.ogong.pms.domain.CafeImage;
import com.ogong.pms.domain.CafeReview;
import com.ogong.pms.domain.CeoMember;
import com.ogong.pms.web.cafe.CafeHandlerHelper;

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

  // 카페 등록
  @ResponseBody
  @PostMapping("/ceomember/cafe/add")
  public ModelAndView ceoCafeAdd(
      HttpSession session, Cafe cafe,
      String inputOpenTime, String inputCloseTime,
      String tel1, String tel2, String tel3,
      String addr1, String addr2, String addr3,
      Part[] photoFileList) throws Exception {

    CeoMember loginCeo = (CeoMember) session.getAttribute("loginCeoUser");

    if (loginCeo == null) {
      throw new Exception("로그인한 회원이 없습니다.");
    } 

    CeoMember ceoMember = ceoMemberDao.findByNo(loginCeo.getCeoNo());

    cafe.setCeoMember(ceoMember);


    System.out.println(photoFileList);


    if (photoFileList.length > 0) {

      ArrayList<CafeImage> cafeImageList = new ArrayList<>();

      for (Part photoFile : photoFileList) {
        String filename = UUID.randomUUID().toString();
        photoFile.write(sc.getRealPath("/upload/cafe") + "/" + filename);

        CafeImage cafeImage = new CafeImage();
        cafeImage.setName(filename);

        cafeImageList.add(cafeImage);

      }
      cafe.setCafeImgs(cafeImageList);
    }

    //    if (!multipartFile.isEmpty()) {
    //      fileUpload(multipartFile, cafe);
    //    }

    //      List<CafeImage> imageList = cafe.getCafeImgs();
    //
    //      if (!imageList.isEmpty()) {
    //        CafeImage cafeImage = new CafeImage();
    //        cafeImage.setName(request.getParameter("filename[]"));
    //        cafeImage.setCafeNo(cafe.getNo());
    //
    //        ArrayList<CafeImage> cafeImageList = new ArrayList<>();
    //        cafeImageList.add(cafeImage);
    //
    //        HashMap<String,Object> params = new HashMap<>(); 
    //        params.put("fileNames", cafeImageList);
    //        params.put("cafeNo", cafe.getNo());
    //
    //        cafeDao.insertCafeImage(params);
    //        sqlSession.commit();
    //      }

    //sqlSession.rollback();
    cafe.setOpenTime(LocalTime.parse(inputOpenTime));
    cafe.setCloseTime(LocalTime.parse(inputCloseTime));
    cafe.setPhone(tel1 + "-" + tel2 + "-" + tel3);
    cafe.setLocation(addr2 + " " + addr3 + "(" + addr1 + ")");

    cafeDao.insertCafe(cafe);
    sqlSessionFactory.openSession().commit();

    Cafe myCafe = cafeDao.findByCafeNoMember(loginCeo.getCeoNo());

    for (int i = 0; i < myCafe.getCafeImgs().size(); i++) {
      String img = myCafe.getCafeImgs().get(i).getName();
      cafeDao.insertCafeImage(img, myCafe.getNo());
    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;
  }

  //  @ResponseBody
  //  @RequestMapping(value = "/file-upload", method = RequestMethod.POST)
  //  public String fileUpload(List<MultipartFile> multipartFile, Cafe cafe) throws Exception {
  //    String strResult = "{ \"result\":\"FAIL\" }";
  //
  //    //String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
  //    String contextRoot = sc.getRealPath("/");
  //    String fileRoot;
  //
  //    ArrayList<CafeImage> cafeImageList = new ArrayList<>();
  //    try {
  //      // 파일이 있을때
  //      if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
  //        for(MultipartFile file:multipartFile) {
  //          fileRoot = contextRoot + "upload/cafe";
  //          System.out.println(fileRoot);
  //
  //          String originalFileName = file.getOriginalFilename();   //오리지날 파일명
  //          String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   //파일 확장자
  //          String savedFileName = UUID.randomUUID() + extension;   //저장될 파일 명
  //
  //          File targetFile = new File(fileRoot + savedFileName);   
  //          try {
  //            InputStream fileStream = file.getInputStream();
  //            FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
  //
  //            CafeImage cafeImage = new CafeImage();
  //            cafeImage.setName(savedFileName);
  //            cafeImage.setCafeNo(cafe.getNo());
  //            cafeImageList.add(cafeImage);
  //
  //            cafeDao.insertCafeImage(savedFileName, cafe.getNo());
  //
  //          } catch (Exception e) {
  //            //파일삭제
  //            FileUtils.deleteQuietly(targetFile);    //저장된 현재 파일 삭제
  //            e.printStackTrace();
  //            break;
  //          }
  //        }
  //        strResult = "{ \"result\":\"OK\" }";
  //      } else {
  //        strResult = "{ \"result\":\"OK\" }";
  //      }
  //    } catch(Exception e){
  //      e.printStackTrace();
  //    }
  //    return strResult;
  //  }



  //  public String fileUpload(List<MultipartFile> multipartFile) {
  //    String strResult = "{ \"result\":\"FAIL\" }";
  //    //String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
  //    String contextRoot = sc.getRealPath("/");
  //    String fileRoot;
  //    try {
  //      // 파일이 있을때 탄다.
  //      if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
  //
  //        for(MultipartFile file:multipartFile) {
  //          fileRoot = contextRoot + "upload/cafe";
  //          System.out.println(fileRoot);
  //
  //          String originalFileName = file.getOriginalFilename();   //오리지날 파일명
  //          String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   //파일 확장자
  //          String savedFileName = UUID.randomUUID() + extension;   //저장될 파일 명
  //
  //          File targetFile = new File(fileRoot + savedFileName);   
  //          try {
  //            InputStream fileStream = file.getInputStream();
  //            FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
  //
  //          } catch (Exception e) {
  //            //파일삭제
  //            FileUtils.deleteQuietly(targetFile);    //저장된 현재 파일 삭제
  //            e.printStackTrace();
  //            break;
  //          }
  //        }
  //        strResult = "{ \"result\":\"OK\" }";
  //      }
  //     
  //      // 파일 아무것도 첨부 안했을때 탄다.(게시판일때, 업로드 없이 글을 등록하는경우)
  //      else
  //        strResult = "{ \"result\":\"OK\" }";
  //    } catch(Exception e){
  //      e.printStackTrace();
  //    }
  //    return strResult;
  //  }

  //------------------------------------------------------------------------------------------------------------------------------------------------------
  // 카페 삭제 폼
  @GetMapping("/ceomember/cafe/deleteform")
  public ModelAndView ceoCafeDeleteForm(int cafeno) throws Exception {

    Cafe cafe = cafeDao.findByCafeNo(cafeno);

    if (cafe == null) {
      throw new Exception("등록된 카페가 없습니다.");
    }

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
  public ModelAndView ceoCafeUpdate(Cafe cafe, String inputOpenTime, String inputCloseTime) throws Exception {

    Cafe oldcafe = cafeDao.findByCafeNo(cafe.getNo());

    if (oldcafe == null) {
      throw new Exception("등록된 카페가 없습니다.");
    }

    //cafe.setCafeImgs(request.getParameter("filename[]"));

    //      List<CafeImage> imageList = cafe.getCafeImgs();
    //
    //      if (!imageList.isEmpty()) {
    //        CafeImage cafeImage = new CafeImage();
    //        cafeImage.setName(request.getParameter("filename[]"));
    //        cafeImage.setCafeNo(cafe.getNo());
    //
    //        ArrayList<CafeImage> cafeImageList = new ArrayList<>();
    //        cafeImageList.add(cafeImage);
    //
    //        HashMap<String,Object> params = new HashMap<>(); 
    //        params.put("fileNames", cafeImageList);
    //        params.put("cafeNo", cafe.getNo());
    //
    //        cafeDao.insertCafeImage(params);
    //        sqlSession.commit();
    //      }

    //sqlSession.rollback();

    cafe.setOpenTime(LocalTime.parse(inputOpenTime));
    cafe.setCloseTime(LocalTime.parse(inputCloseTime));

    cafeDao.updateCafe(cafe);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    return mv;
  }













}
