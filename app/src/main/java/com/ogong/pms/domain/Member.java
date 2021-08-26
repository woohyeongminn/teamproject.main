package com.ogong.pms.domain;

import java.sql.Date;

public class Member {

  private String personal;
  private int perno;
  private String pernickname;
  private String peremail;
  private String perpassword;
  private String perphoto;
  private Date perregisteredDate;


  public String getPersonal() {
    return personal;
  }
  public void setPersonal(String personal) {
    this.personal = personal;
  }
  public int getPerno() {
    return perno;
  }
  public void setPerno(int perno) {
    this.perno = perno;
  }
  public String getPernickname() {
    return pernickname;
  }
  public void setPernickname(String pernickname) {
    this.pernickname = pernickname;
  }
  public String getPeremail() {
    return peremail;
  }
  public void setPeremail(String peremail) {
    this.peremail = peremail;
  }
  public String getPerpassword() {
    return perpassword;
  }
  public void setPerpassword(String perpassword) {
    this.perpassword = perpassword;
  }
  public String getPerphoto() {
    return perphoto;
  }
  public void setPerphoto(String perphoto) {
    this.perphoto = perphoto;
  }
  public Date getPerregisteredDate() {
    return perregisteredDate;
  }
  public void setPerregisteredDate(Date perregisteredDate) {
    this.perregisteredDate = perregisteredDate;
  }




  //  private String ceo;
  //  private int ceono;
  //  private int ceoid;
  //  private String ceoname;
  //  private String ceopassword;
  //  private String ceophoto;
  //  private String ceo사업자등록번호;
  //  private String ceo대표자명;
  //  private String ceo점포명;
  //  private String ceo점포주소상세주소;
  //  private Date ceoregisteredDate;








}