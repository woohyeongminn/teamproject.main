package com.ogong.pms;

import com.ogong.util.Prompt;

public class App2 {

  public static void main(String[] args) {
    System.out.println("[스터디 목록]");
    System.out.println("1 . 자바스터디" );
    System.out.println("2 . 영어회화" );

    int input = Prompt.inputInt(">");
    switch (input) {
      case 1 : javaStudy();
      case 2 : englishStudy();
    }
  }

  private static void englishStudy() {
    System.out.println("스터디명: 영어스터디");
    System.out.println("조장: 초보개발자");
    System.out.println("분야: 자바언어");
    System.out.println("지역: 서울");
    System.out.println("장소: 대치동");
    System.out.println("인원수: 6명");
    System.out.println("대면: 비대면");
    System.out.println("소개글: 초보개발자들 많이 와주세요");
  }

  private static void javaStudy() {
    System.out.println("스터디명: 자바스터디");
    System.out.println("조장: 원어민");
    System.out.println("분야: 영어");
    System.out.println("지역: 강원");
    System.out.println("장소: 원주");
    System.out.println("인원수: 10명");
    System.out.println("대면: 대면");
    System.out.println("소개글: 토익 500점 이상 분들 원해요");

  }

}