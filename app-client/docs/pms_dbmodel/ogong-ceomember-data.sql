-- 회원 test 값
insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(7, '김사장', '김사장카페', 'cafe1@test.com', password('c1'), '010-1111-0000',
'cafe1.gif', '2020-02-02', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(8, '송사장', '송사장카페', 'cafe2@test.com', password('c2'), '010-2222-0000',
'cafe2.gif', '2021-04-15', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(9, '우사장', '우사장카페', 'cafe3@test.com', password('c3'), '010-3333-0000',
'cafe3.gif','2021-09-16', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(10, '조사장', '조사장카페', 'cafe4@test.com', password('c4'), '010-4444-0000', 
'cafe4.gif', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(11, '반사장', '반사장카페', 'cafe5@test.com', password('c5'), '010-5555-0000', 
'cafe5.gif', 2);

-------------------------------------------------------------------------------------------------

-- 사장회원 test 값
insert into ceo_member(member_no, bossname, license_no) 
values(7, '김사장', '123-45-12345');

insert into ceo_member(member_no, bossname, license_no) 
values(8, '송사장', '321-45-98745');

insert into ceo_member(member_no, bossname, license_no) 
values(9, '우사장', '546-85-14545');

insert into ceo_member(member_no, bossname, license_no) 
values(10, '조사장', '333-28-15464');

insert into ceo_member(member_no, bossname, license_no) 
values(11, '반사장', '632-94-37294');