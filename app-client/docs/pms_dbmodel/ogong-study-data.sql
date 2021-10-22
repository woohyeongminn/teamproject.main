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
study_no, name, subject_no, no_people, face_no, introduction, created_dt, per_member_no, score) 
values(1, '삼성뿌셔', 3, 5, 3, '취업 뿌셔뿌셔', '2021-1-1', 1, 0);

insert into study(
study_no, name, subject_no, no_people, face_no, introduction, created_dt, per_member_no, score) 
values(2, '정처기준비', 2, 5, 2, '한 번에 붙자', '2021-2-2', 2, 0);

-- 캘린더 중요도 값
insert study_calender_importance(importance_no, importance) values(5, ★☆☆☆☆);
insert study_calender_importance(importance_no, importance) values(4, ★★☆☆☆);
insert study_calender_importance(importance_no, importance) values(3, ★★★☆☆);
insert study_calender_importance(importance_no, importance) values(2, ★★★★☆);
insert study_calender_importance(importance_no, importance) values(1, ★★★★★);









