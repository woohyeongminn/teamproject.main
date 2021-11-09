<%-- <%@ page import="com.oreilly.servlet.MultipartRequest" %> 
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.io.File" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 처리</title>
</head>
<body>
<%
  //String realFolder = "uploadedFiles"; //파일 업로드 절대 경로
  String realFolder = request.getRealPath("uploadedFiles");
  //String saveFolder = "../uploadedFiles";//파일 업로드 경로
  String encType = "UTF-8"; //인코딩 타입
  int maxSize = 1024 * 1024 * 20; //파일 최대 업로드 사이즈, 20MB
  
  //파일 업로드 절대 경로 구하기
  //realFolder = application.getRealPath(saveFolder);
  
  out.println("파일 업로드 절대 경로 : " + realFolder + "<br>");
  out.println("-------------------------------------<br>");
  try{
  MultipartRequest multi = 
      new MultipartRequest(request,
                       realFolder, //upload 절대 경로
                       maxSize, //파일 최대 업로드 사이즈
                       encType, //인코딩 타입
                       new DefaultFileRenamePolicy() //이미 업로드된 파일과 동일한 파일명일 경우 파일명 교체
                       );
  
  System.out.println("제목 : " + multi.getParameter("title") + "<br>");
  System.out.println("내용 : " + multi.getParameter("content") + "<br>");
  System.out.println("------------------------------------------<br>");
  
  //파일 정보 처리
  //전송전 원래의 파일 이름
  String original = multi.getOriginalFileName("file");
  
  //서버에 저장된 파일 이름
  String file = multi.getFilesystemName("file");
  
  //전송된 파일의 컨텐트 타입
  String type = multi.getContentType("file");
  
  System.out.println("전송전 원래의 파일 이름 : " + original + "<br>");
  System.out.println("서버에 저장된 파일 이름 : " + file + "<br>");
  System.out.println("컨텐트 타입 : " + type + "<br>");
  
  //파일의 용량 구하기
  File filebox = multi.getFile("file");
	  if(filebox!=null){
	    out.println("파일 용량 : " + filebox.length() + "bytes");
	  }
  } catch (Exception e){
    e.printStackTrace();
  }
%>

파일 업로드 성공!
  <a href="../adminNotice/add"><input type="button" value="Upload"></a>
  
</body>
</html>
 --%>

<!-- 클라이언트가 보낸 한글을 읽을 때 깨지는 문제 해결?
=> 다음 코드의 주석을 풀고 테스트 해보라!
정상적으로 잘 출력될 것이다.
req.setCharacterEncoding("UTF-8"); -->


<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- <form action="../adminNotice/add" method="post" enctype="multipart/form-data"> -->
<%

    String filepath = request.getRealPath("upload");
    int size = 1024 * 1024 * 20; //20MB
    String str, filename, original_filename;
    try{
      MultipartRequest multiRequest = new MultipartRequest(request, filepath, size, "UTF-8", new DefaultFileRenamePolicy());
      
      Enumeration files = multiRequest.getFileNames();
      str = (String)files.nextElement();
      filename = multiRequest.getFilesystemName(str);
      original_filename = multiRequest.getOriginalFileName(str);
      
      System.out.println("제목 : " + multiRequest.getParameter("title"));
      System.out.println("내용 : " + multiRequest.getParameter("content"));
      System.out.println("str : "+str);
      System.out.println("filepath : "+filename);
      System.out.println("original_file : "+original_filename);
      
      out.flush();
    } catch (Exception e){
      e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 성공 유무</title>
</head>
<body>
  파일 업로드 성공!
  <a href="../adminNotice/add"><input type="button" value="등록하기"></a>
  <a href="../adminNotice/list"><input type="button" value="돌아가기"></a>
  </form>
</body>
</html>