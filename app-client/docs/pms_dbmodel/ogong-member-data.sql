-- 회원 test 값
insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(1, '김초보', '초보초보쌩초보', 'naver@test.com', password('n1'), '010-1111-1111',
'naver.gif', '2020-01-01', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(2, '반미술', '미술부장', 'gmail@test.com', password('g1'), '010-2222-2222',
'gmail.gif', '2021-03-15', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(3, '이코딩', '코딩부장', 'kakao@test.com', password('k1'), '010-3333-3333',
'kakao.gif','2021-10-16', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(4, '우음악', '음악대장', 'daum@test.com', password('d1'), '010-4444-4444', 
'daum.gif', '2021-08-23', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(5, '조고수', '고수고수왕고수', 'hanmail@test.com', password('h1'), '010-5555-5555',
'hanmail.gif', '2021-02-03', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(6, '송중수', '중수중수중중수', 'todaystudymail@gmail.com', password('t1'), '010-6666-6666',
'gmail.jpg', '2021-04-14', 1, 1);

-------------------------------------------------------------------------------------------------

-- 개인회원 test 값
insert into per_member(per_member_no, member_no) values(1,1);
insert into per_member(per_member_no, member_no) values(2,2);
insert into per_member(per_member_no, member_no) values(3,3);
insert into per_member(per_member_no, member_no) values(4,4);
insert into per_member(per_member_no, member_no) values(5,5);
insert into per_member(per_member_no, member_no) values(6,6);

