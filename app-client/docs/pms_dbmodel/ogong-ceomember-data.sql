-- 회원 test 값
insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(8, '김사장', '김사장카페', 'cafe1@test.com', password('c1'), '010-1111-0000',
'cafe1.gif', '2020-02-02', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(9, '송사장', '송사장카페', 'cafe2@test.com', password('c2'), '010-2222-0000',
'cafe2.gif', '2021-04-15', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(10, '우사장', '우사장카페', 'cafe3@test.com', password('c3'), '010-3333-0000',
'cafe3.gif','2021-09-16', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(11, '조사장', '조사장카페', 'cafe4@test.com', password('c4'), '010-4444-0000', 
'cafe4.gif', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(12, '반사장', '반사장카페', 'cafe5@test.com', password('c5'), '010-5555-0000', 
'cafe5.gif', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(13, '이사장', '이사장카페', 'cafe6@test.com', password('c6'), '010-6666-0000', 
'cafe6.gif', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(14, '박사장', '박사장카페', 'cafe7@test.com', password('c7'), '010-7777-0000', 
'cafe7.gif', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(15, '한사장', '한사장카페', 'cafe8@test.com', password('c8'), '010-8888-0000', 
'cafe7.gif', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(16, '최사장', '최사장카페', 'cafe9@test.com', password('c9'), '010-9999-0000', 
'cafe7.gif', 2);

/*
insert into member(name,nickname,email,password,tel,photo,status)
    values(#{ceoName},#{ceoNickname},#{ceoEmail},password(#{ceoPassword}),#{ceoTel},#{ceoPhoto},#{ceoStatus})
*/    
    
-------------------------------------------------------------------------------------------------

-- 사장회원 test 값
insert into ceo_member(member_no, bossname, license_no) 
values(8, '김사장', '123-45-12345');

insert into ceo_member(member_no, bossname, license_no) 
values(9, '송사장', '321-45-98745');

insert into ceo_member(member_no, bossname, license_no) 
values(10, '우사장', '546-85-14545');

insert into ceo_member(member_no, bossname, license_no) 
values(11, '조사장', '333-28-15464');

insert into ceo_member(member_no, bossname, license_no) 
values(12, '반사장', '632-94-37294');

insert into ceo_member(member_no, bossname, license_no) 
values(13, '이사장', '584-12-37294');

insert into ceo_member(member_no, bossname, license_no) 
values(14, '박사장', '125-65-45895');

insert into ceo_member(member_no, bossname, license_no) 
values(15, '한사장', '125-65-45895');

insert into ceo_member(member_no, bossname, license_no) 
values(16, '최사장', '125-65-45895');