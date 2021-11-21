/* 1 = 승인대기 / 2 = 승인 / 3 = 거절 */

-- 스터디구성원 test 값

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,2,'2021-3-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,3,'2021-3-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,5,'2021-07-30',1);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(1,7,'2021-10-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(2,8,'2021-10-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(3,1,'2021-08-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(3,4,'2021-10-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(3,5,'2021-10-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(4,1,'2021-03-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(5,1,'2021-07-25',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(6,1,'2021-07-30',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(6,2,'2021-07-30',1);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(7,1,'2021-06-05',2);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(7,2,'2021-06-05',1);

insert into study_guilder(member_no,study_no,created_dt,status) 
values(7,6,'2021-10-25',2);

-- 포인트 테스트값
insert into point(member_no, study_no, point) values(1, 1, 100);