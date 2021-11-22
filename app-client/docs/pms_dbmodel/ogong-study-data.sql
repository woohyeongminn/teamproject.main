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
values(1, '백엔드 취업 스터디 모집', 4, 6, 2, 'CS, Java, Spring, JPA 위주로 공부', '2021-11-1', 1, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(2, 'Salesforce 개발자 자격증', 2, 4, 1, '함께 동영상을 보면서 하나씩 공부 예정', '2021-11-2', 2, 1, '서울시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(3, '영어 공부 하실 분', 1, 2, 2, '진지하게 영어 공부하실 분만 신청', '2021-11-3', 3, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(4, '코딩테스트 스터디 모집합니다', 4, 8, 3, '각자 풀이 후 자신의 풀이 설명', '2021-11-4', 4, 2, '강남구');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(5, '기타 치실 분', 5, 10, 2, '기타 잘 못 쳐도 환영합니다', '2021-11-5', 5, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(6, '주말 등산 스터디', 6, 12, 1, '등산은 장비빨', '2021-11-6', 6, 2, '안양시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(7, '프랑스어 공부 하실 분', 1, 14, 2, '열심히 참여하실 분 환영', '2021-11-7', 7, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(8, '컴퓨터 활용능력 1급 스터디', 2, 16, 3, '인강을 통해 학습, 대면 스터디로 공유', '2021-11-8', 17, 2, '시흥시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(9, '모의 면접 스터디 팀원 구합니다', 3, 18, 2, 'CS, Java, Spring 모의 면접 진행', '2021-11-9', 18, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(10, 'c언어 알고리즘 스터디', 4, 20, 1, '알고리즘 문제 푼 후 코드리뷰', '2021-11-10', 19, 2, '양주시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(11, '에어로빅 배우실 분 구해요', 5, 22, 2, '몸치 대환영', '2021-11-11', 20, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(12, '식물 키우기 스터디 모집', 6, 24, 3, '식물 자주 죽이시는 분 대환영', '2021-11-12', 21, 2, '평택시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(13, '독일어 공부 하실 분', 1, 26, 2, '성실하게 참여하실 분만 신청', '2021-11-13', 22, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(14, '컴퓨터 초보를 위한 스터디', 2, 28, 1, '방학 동안 자격증 취득할 수 있는 스터디', '2021-11-14', 23, 2, '제주시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(15, '취업용 포트폴리오 스터디 모집', 3, 30, 2, '취업을 위한 웹사이트 제작 및 배포', '2021-11-15', 24, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(16, '스프링인액션 제5판', 4, 5, 3, '도서 "스프링인액션(5판)" 제대로 파보기', '2021-11-16', 1, 2, '강원도');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(17, '스우파 헤이마마 춤 마스터', 5, 15, 2, '스우파 애청자 대환영', '2021-11-17', 1, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(18, '클래식 독서 모임', 6, 25, 1, '클래식 들으며 같이 책 읽어요', '2021-11-18', 1, 2, '전라도');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(19, '일본어 공부 하실 분', 1, 3, 2, '수강 인증 날짜 잘 지키기', '2021-11-19', 1, 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status, area) 
values(20, '웹 디자인 기능사 실기 시험', 2, 13, 3, '최소 주2회 공부한 내용 공유', '2021-11-20', 1, 2, '부천시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, status) 
values(21, '불법 광고', 3, 3, 2, '불법 광고', '2021-11-21', 1, 1);

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
values(17,8,'2021-11-8',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(18,9,'2021-11-9',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(19,10,'2021-11-10',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(20,11,'2021-11-11',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(21,12,'2021-11-12',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(22,13,'2021-11-13',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(23,14,'2021-11-14',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(24,15,'2021-11-15',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,16,'2021-11-16',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,17,'2021-11-17',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,18,'2021-11-18',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,19,'2021-11-19',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,20,'2021-11-20',2);

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