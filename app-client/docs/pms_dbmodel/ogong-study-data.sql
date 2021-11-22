-- 스터디 과목 값
insert into study_subject(subject_no, name) values(1, '어학');
insert into study_subject(subject_no, name) values(2, '자격증');
insert into study_subject(subject_no, name) values(3, '취업');
insert into study_subject(subject_no, name) values(4, 'IT');
insert into study_subject(subject_no, name) values(5, '예체능');
insert into study_subject(subject_no, name) values(6, '기타');

-------------------------------------------------------------------------------------------------

-- 대면 상태 값
insert into study_face_status(face_no, name) values(1, '대면');
insert into study_face_status(face_no, name) values(2, '비대면');
insert into study_face_status(face_no, name) values(3, '대면/비대면');

-------------------------------------------------------------------------------------------------

-- 스터디 값
insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(1, '백엔드 취업 스터디 모집', 4, 6, 3, '스터디 주제 : 백엔드 개발자로 취업을 위한 스터디 

스터디 목표 : 취업

예상 스터디 일정(횟수) : 모여서 협의

예상 커리큘럼 간략히 : 내년 상반기까지 꾸준히

스터디 소개와 개설 이유 : 내년 상반기 취업 및 재취업을 목표로 서로 정보공유를 하며 꾸준히 같이 달리실 분 모집합니다! 먼저 CS, Java, Spring, JPA 위주로 공부합니다. 백엔드 개발자라 하더라도 실제 면접에서 Docker 및 k8s와 같은 인프라 지식도 물어볼 수 있기 때문에 해당 내용도 어느정도 공부할 예정입니다. 저도 직장인이기 때문에 재직자도 환영입니다.

스터디 관련 주의사항 : 열심히 하실 분만 신청 바랍니다', '2021-11-1', 1, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(2, 'Salesforce 개발자 자격증', 2, 4, 2, '스터디 주제 : 세일즈포스 플랫폼 스터디

스터디 목표 : 세일즈포스 플랫폼 개발자 자격증 획득

예상 스터디 일정(횟수) : 15회

예상 커리큘럼 간략히 : 정보가 많이 없네요. 함께 동영상(영어로 제작된 교육)을 보면서 하나씩 공부 예정. 

세일즈포스 홈페이지에 많이 자료가 있어서 함께 찾아가면서 공부할 분을 찾아요. 

스터디 소개와 개설 이유 : 틈새시장을 공략하고 싶네요. 함께 공부해보아요. 12월에 자격증 시험이 있네요. 함께 접수해서 진행해요. 

스터디 관련 주의사항 : 저도 이제 막 시작하려고 합니다. 함께 맨 땅에 헤딩할 동료분을 찾아요. 영문 자료가 많아서 영어는 어느정도 필요해보입니다. ', '2021-11-2', 2, 2, '서울시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(3, '영어 공부 하실 분', 1, 2, 1, '진지하게 영어 공부하실 분만 신청 바랍니다', '2021-11-3', 3, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(4, '코딩테스트 스터디 모집합니다', 4, 8, 1, '스터디 주제 : 파이썬 혹은 각자 원하는 언어 기반 코딩테스트 연습

스터디 목표 : 코딩테스트 연습

예상 스터디 일정(횟수) : 주 1회

예상 커리큘럼 간략히 : 온라인 모임으로 백준 기준 골드 혹은 프로그래머스 2,3단계 - 수준 3~4 문제 위주로 각자 풀이 후 모임에서 자신의 풀이 설명

스터디 소개와 개설 이유 : 내년 상하반기 코딩테스트 목표 연습

스터디 관련 주의사항 : 진지하게 오래 하실 분을 희망합니다.', '2021-11-4', 4, 2, '강남구');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(5, '기타 치실 분', 5, 10, 2, '기타 잘 못 쳐도 환영합니다 같이 배워요', '2021-11-5', 5, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(6, '주말 등산 스터디', 6, 12, 3, '등산은 장비빨', '2021-11-6', 6, 2, '안양시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(7, '프랑스어 공부 하실 분', 1, 14, 1, '열심히 참여하실 분 환영 합니다', '2021-11-7', 7, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(8, '컴퓨터 활용능력 1급 스터디', 2, 16, 2, '
    스터디 주제 : 컴퓨터 활용능력 1급 필기/실기 준비

    스터디 목표 : 컴퓨터 활용능력 1급 자격증 취득

    예상 스터디 일정(횟수) : 취득할때까지

    예상 커리큘럼 간략히 : 인강을 통해 학습, 메신저와 대면 스터디로 질답 공유

    스터디 소개와 개설 이유 : 이직을 위한 스펙업을 위해 

    스터디 관련 주의사항 : 때론 의지가 약해져도 끝까지 가늘고 길게 완성하자

', '2021-11-8', 8, 2, '시흥시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(9, 'CS / Java / Spring 모의 면접 스터디 팀원 구합니다 !', 3, 18, 3, '스터디 주제 : CS, Java, Spring 모의 면접

스터디 목표 : 취업

예상 스터디 일정(횟수) : 주 2회

예상 커리큘럼 간략히 : 다같이 의논 후 진행 방식 결정

스터디 소개와 개설 이유 : CS, Java, Spring 모의 면접 진행할 팀원 모집 

스터디 관련 주의사항 : CS, Java, Spring 모의 면접 스터디이기 때문에 아예 모르시는 분은 정말 죄송하지만 힘들 것 같습니다. 각자 주어진 키워드를 공부하고 모의 면접 때 대답하면서 부족한 부분을 채워갔으면 좋겠습니다. 정말 열심히 하실분들만 지원하셨으면 좋겠습니다 !', '2021-11-9', 9, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(10, 'c언어 알고리즘 스터디', 4, 20, 1, '스터디 주제 : C언어 백준 알고리즘

스터디 목표 : 알고리즘 실력 향상

예상 스터디 일정(횟수) : 주 3회 1시간

예상 커리큘럼 간략히 :  알고리즘 문제 푼 후 코드리뷰

스터디 소개와 개설 이유 : 저는 현재 백준 실버 5 수준입니다. 비슷한 분들끼리 알고리즘 풀어가며 공부했으면 좋겠습니다. 

스터디 관련 주의사항 : 코딩과 알고리즘에 열심이신 분들만 오셨으면 좋겠습니다. ', '2021-11-10', 10, 2, '양주시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(11, '에어로빅 배우실 분 구해요', 5, 22, 2, '몸치 대환영', '2021-11-11', 11, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(12, '식물 키우기 스터디 모집', 6, 24, 3, '식물 자주 죽이시는 분 대환영', '2021-11-12', 12, 2, '평택시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(13, '독일어 공부 하실 분', 1, 26, 1, '성실하게 참여하실 분 아니면 신청하지도 마세요', '2021-11-13', 13, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(14, '방학기념 컴퓨터 초보를 위한 스터디', 2, 28, 2, '스터디 주제 : 컴퓨터활용능력시험 준비

스터디 목표 : 컴퓨터활용능력 자격증 취득

예상 스터디 일정(횟수) : 주 3회

예상 커리큘럼 간략히 : 인프런 강의 활용(mos excel 2016 core+expert, word 등 강의)

스터디 소개와 개설 이유 : 방학 동안 컴퓨터활용능력을 기르고 자격증까지 취득할 수 있는 스터디를 진행하고자 합니다.

 원하시는 다른 주제가 있다면 스터디원분들과 같이 정하고 싶습니다!

스터디 관련 주의사항 : 

방학을 실용적인 활동을 통해 알차고 슬기롭게 보내고 싶으신 분 신청해주시기 바랍니다! 또한 온라인으로 스터디를 진행하고자 합니다. 자세한 일정은 함께 정해요!', '2021-11-14', 14, 2, '제주시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(15, '취업용 포트폴리오 스터디 모집', 3, 30, 3, '
    스터디 주제 :취업용 포트폴리오 제작

    스터디 목표 : 웹프로그램 제작 후 AWS 배포

    예상 스터디 일정(횟수) : 주 5일 9 to 6 ~ 9 일정은 조율 가능합니다. 

    예상 커리큘럼 간략히 : 

    기간 - 2개월~3개월(모집 후 논의 후 결정) 

    주제선정, 기획, 분석 , 설계 , 구현 , 배포

    frontend 2명, ui/ux 1명

    스터디 소개와 개설 이유 : 취업을 위한 웹사이트 제작 및 배포가 목표 입니다. 혼자서 포트폴리오 제작 하시는데 어려움을 겪고 계신분들 환영 합니다. 

    스터디 관련 주의사항 : 2~3달간 스터디에 시간을 쏟으실 수 있으신분', '2021-11-15', 15, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(16, '스프링인액션 제5판', 4, 5, 1, '
    스터디 주제 :스프링인액션 스터디

    스터디 목표 : 도서 "스프링인액션(5판)" 제대로 파보기

    예상 스터디 일정(횟수) : 주1회

    예상 커리큘럼 간략히 : 팀 결성 후 결정

    예상 모집인원 : 5명

    스터디 소개와 개설 이유 : 함께 마음맞는 분들과 스터디를 진행하며 실력향상을 도모하고 싶습니다!', '2021-11-16', 1, 2, '강원도');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(17, '스우파 헤이마마 춤 마스터', 5, 15, 2, '스우파 애청자 대환영', '2021-11-17', 1, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(18, '클래식 독서 모임', 6, 25, 3, '클래식 들으며 같이 책 읽어요', '2021-11-18', 1, 2, '전라도');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(19, '일본어 공부 하실 분', 1, 3, 2, '수강 인증 날짜 잘 지키시면 좋겠습니다', '2021-11-19', 1, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(20, '8월말 실기시험을 목표로 코딩웍스 웹디자인기능사 실기시험 완벽 가이드(HTML+CSS+JQUERY) 완강까지 함께 하실 분~!', 2, 13, 3, '스터디 주제 : 웹디자인 기능사 실기 공부

스터디 목표 : 코딩웍스 웹디자인기능사 실기시험 완벽 가이드(HTML+CSS+JQUERY) 완강

예상 스터디 일정(횟수) : 최소 주2회 공부한 내용 올리고 공유하기

예상 커리큘럼 간략히 : 강의를 수강하며 공부한 내용을 정리해서 사진 인증

스터디 소개와 개설 이유 : 완강하고 자격증 시험 합격까지 !! 

스터디 관련 주의사항 : 성실히 참여하실 분들만 모집합니다.', '2021-11-20', 1, 2, '부천시');

-------------------------------------------------------------------------------------------------

-- 길더 (조장도 길더에 들어 가야 함)

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,1,'2021-11-1',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(2,2,'2021-11-2',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(3,3,'2021-11-3',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(4,4,'2021-11-4',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(5,5,'2021-11-5',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(6,6,'2021-11-6',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(7,7,'2021-11-7',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(8,8,'2021-11-8',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(9,9,'2021-11-9',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(10,10,'2021-11-10',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(11,11,'2021-11-11',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(12,12,'2021-11-12',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(13,13,'2021-11-13',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(14,14,'2021-11-14',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(15,15,'2021-11-15',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(16,16,'2021-11-16',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(17,17,'2021-11-17',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(18,18,'2021-11-18',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(19,19,'2021-11-19',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(20,20,'2021-11-20',2);

-------------------------------------------------------------------------------------------------

-- 캘린더 중요도 값
insert into study_calendar_importance(importance_no, importance) values(5, '★☆☆☆☆');
insert into study_calendar_importance(importance_no, importance) values(4, '★★☆☆☆');
insert into study_calendar_importance(importance_no, importance) values(3, '★★★☆☆');
insert into study_calendar_importance(importance_no, importance) values(2, '★★★★☆');
insert into study_calendar_importance(importance_no, importance) values(1, '★★★★★');

insert into study_calendar(calendar_dt, content, importance_no, member_no, study_no)
 values('2021-10-15', '생일', 1, 1, 1);
insert into study_calendar(calendar_dt, content, importance_no, member_no, study_no)
 values('2021-8-15', '광복', 2, 1, 2);
insert into study_calendar(calendar_dt, content, importance_no, member_no, study_no)
 values('2021-4-1', '만우절', 3, 1, 3); 
insert into study_calendar(calendar_dt, content, importance_no, member_no, study_no)
 values('2021-6-6', '현충일', 5, 1, 3); 

-------------------------------------------------------------------------------------------------

-- 스터디 전체 조회 (북마크한 사람들 카운트만, 구성원들 카운드만)
/*
select
     s.study_no,
     s.name study_title,
     ss.subject_no subject_no,
     ss.name subject_name,
     s.area,
     s.no_people,
     sfs.face_no face_no,
     sfs.name face_name,
     s.introduction,
     s.created_dt,
     s.score study_score,
     s.member_no owner_no,
     m.nickname owner_name,
    (select count(*) from study_guilder where study_no=s.study_no and status=2) count_guilder,
    (select count(*) from study_guilder where study_no=s.study_no and status=1) count_wating_guilder,
    (select count(*) from study_bookmark where study_no=s.study_no) count_bookmark
   from study s
     left outer join study_subject ss on s.subject_no=ss.subject_no
     left outer join study_face_status sfs on s.face_no=sfs.face_no
     left outer join member m on s.member_no=m.member_no
     left outer join study_guilder sg on s.study_no=sg.study_no
     left outer join member m2 on sg.member_no=m2.member_no
     left outer join study_bookmark sb on s.study_no=sb.study_no
     group by s.study_no
     order by s.study_no
*/