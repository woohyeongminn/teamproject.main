-- 회원 test 값
insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(1, '김초보', '초보초보쌩초보', 'naver@test.com', password('n1'), '010-1111-1111',
'perProfile_color', '2020-01-01', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(2, '반미술', '미술부장', 'gmail@test.com', password('g1'), '010-2222-2222',
'perProfile_color', '2021-03-15', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(3, '이코딩', '코딩부장', 'kakao@test.com', password('k1'), '010-3333-3333',
'perProfile_color','2021-10-16', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(4, '우음악', '음악대장', 'daum@test.com', password('d1'), '010-4444-4444', 
'perProfile_color', '2021-08-23', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(5, '조고수', '고수고수왕고수', 'hanmail@test.com', password('h11'), '010-5555-5555',
'perProfile_color', '2021-02-03', 1, 1); 

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(6, '송중수', '중수중수중중수', 'todaystudymail@gmail.com', password('t1'), '010-6666-6666',
'perProfile_color', '2021-04-14', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active, report) 
values(7, '박명수', '한머리두냄새', 'onehair@twosmell.com', password('o1'), '010-7777-7777',
'perProfile_color', '2021-10-15', 1, 1, 6);

-- ceomember가 16번까지 있으니까 member 더 추가하려면 17번부터~

----------------------------------------------------------------------------------------------------
/*
-- findByEmailAndPassword
select
  m.member_no,
  m.nickname,
  m.email
from 
  member m
where
  email='gmail@test.com' and password=password('g1');

-- findAll
select
  m.member_no,
  m.name,
  m.nickname,
  m.email,
  m.tel,
  m.photo,
  m.created_dt,
  m.active,
  m.status
from
  member m
order by
  name asc;

-- findByNo
select
  m.member_no,
  m.name,
  m.nickname,
  m.email,
  m.tel,
  m.photo,
  m.created_dt,
  m.active,
  m.status
from
  member m
where
  m.member_no=2;

----------------------------------------------------------------------------------------------------

-- insert
insert into member(name,nickname,email,password,tel,photo,status)
values('이런','이런','haha@test.com',password('h1'),'01004270427','haha.jpg',1);

-- findByNickName
select
  pm.per_member_no,
  m.name,
  m.nickname,
  m.email,
  m.tel,
  m.photo,
  m.created_dt,
  m.active,
  m.status
from
  member m
join
  per_member pm on m.member_no=pm.member_no
where
  nickname='미술부장';

-- findByEmail
select
  pm.per_member_no member_no,
  m.name,
  m.nickname,
  m.email,
  m.tel,
  m.photo,
  m.created_dt,
  m.active,
  m.status
from
  member m
join
  per_member pm on m.member_no=pm.member_no
where
  email='gmail@test.com';

-- updateName
update member set
  name='반미술'
where
  member_no=
(select
  member_no
from
  per_member
where
  per_member_no=2);

-- updateNickname
update member set
  nickname=#{nickname}
where
  member_no=
(select
  member_no
from
  per_member
where
  per_member_no=#{no})

-- updatePhoto
update member set
  photo=#{photo}
where
  member_no=
(select
  member_no
from
  per_member
where
  per_member_no=#{no})

-- updateTel
update member set
  tel=#{tel}
where
  member_no=
(select
  member_no
from
  per_member
where
  per_member_no=#{no})

-- updateEmail
update member set
  email=#{email}
where
  member_no=
(select
  member_no
from
  per_member
where
  per_member_no=#{no})

-- updatePassword
update member set
  password=password(#{password})
where
  member_no=
(select
  member_no
from
  per_member
where
  per_member_no=#{no})

-- updateActive
update member set
  active=2 
where
  member_no=
(select
  member_no
from
  per_member
where
  per_member_no=#{no})
*/
