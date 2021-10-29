-- 스터디 과목 값
insert into study_subject(subject_no, name) values(1, '어학');
insert into study_subject(subject_no, name) values(2, '자격증');
insert into study_subject(subject_no, name) values(3, '취업');
insert into study_subject(subject_no, name) values(4, 'IT');
insert into study_subject(subject_no, name) values(5, '예체능');
insert into study_subject(subject_no, name) values(6, '기타');

-- 대면 상태 값
insert into study_face_status(face_no, name) values(1, '대면');
insert into study_face_status(face_no, name) values(2, '비대면');
insert into study_face_status(face_no, name) values(3, '대면/비대면');

-- 스터디그룹 test 값
insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no) 
values(1, '삼성뿌셔', 3, 5, 3, '취업 뿌셔뿌셔', '2021-1-1', 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no) 
values(2, '정처기준비', 2, 10, 2, '한 번에 붙자', '2021-2-2', 1);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, area) 
values(3, '하반기 삼성 공모전', 3, 6, 3, '공모전 아자', '2021-3-3', 2,'경기도');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, area) 
values(4, '중앙대 컴공 기말고사', 6, 3, 1, '시험 아자', '2021-3-3', 3,'서울시');

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no) 
values(5, '알고리즘 스터디', 4, 20, 2, '지옥같은 SI 탈출', '2021-3-3', 3);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no, whether_report) 
values(6, '불법광고', 4, 20, 2, '흑채 팔아요~', '2021-3-3', 7, 2);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, member_no) 
values(7, 'MVC', 5, 10, 1, 'MBC', '2021-6-6', 1);

update study set status=1;
update study set whether_report=1;
-------------------------------------------------------------------------------------------------

-- 스터디 북마크 test 값
insert into study_bookmark(member_no, study_no) values(3, 1);

-- 스터디 길더 test 값
insert into study_guilder(study_no, member_no, status) values(1, 4, 1);

-------------------------------------------------------------------------------------------------
-- 캘린더 중요도 값
insert into study_calender_importance(importance_no, importance) values(5, '★☆☆☆☆');
insert into study_calender_importance(importance_no, importance) values(4, '★★☆☆☆');
insert into study_calender_importance(importance_no, importance) values(3, '★★★☆☆');
insert into study_calender_importance(importance_no, importance) values(2, '★★★★☆');
insert into study_calender_importance(importance_no, importance) values(1, '★★★★★');

-- 포인트 테스트값
insert into point(member_no, study_no, point) values(1, 1, 100);

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