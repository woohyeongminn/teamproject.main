-- 회원 test 값
insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(1, '김초보', '초보초보쌩초보', 'chobo@naver.com', password('c123456!'), '010-1352-2269',
'perProfile_color', '2020-01-01', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(2, '반미술', '미술부장', 'banmi@gmail.com', password('b123456!'), '010-2147-2568',
'perProfile_color', '2020-03-15', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(3, '이코딩', '코딩부장', 'kk123@kakao.com', password('k1'), '010-2254-7588',
'perProfile_color','2020-05-16', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(4, '우음악', '음악대장', 'dd123@daum.com', password('d1'), '010-5711-5440', 
'perProfile_color', '2020-08-23', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(5, '조고수', '고수고수왕고수', 'hh123@hanmail.com', password('h1'), '010-7587-1120',
'perProfile_color', '2020-09-03', 1, 1); 

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(6, '송중수', '중수중수중중수', 'todaystudymail@gmail.com', password('t1'), '010-9688-2311',
'perProfile_color', '2020-10-14', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(7, '김명수', '한머리두냄새', 'onehair@naver.com', password('o1'), '010-8755-7129',
'park', '2020-12-15', 1, 1);


insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(17, '유재석', '메뚜기월드', 'uu123@naver.com', password('u1'), '010-2441-5709',
'Yoo', '2021-01-15', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(18, '하하', '하하하', 'hh123@gmail.com', password('h1'), '010-8755-2788',
'haha', '2021-01-30', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(19, '정준하', '정주나요안정주나요', 'jj123@daum.com', password('j1'), '010-2410-8871',
'junha', '2021-02-18', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(20, '정형돈', '도니도니', 'dony123@naver.com', password('d1'), '010-2337-1024',
'dony', '2021-05-20', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(21, '이지훈', '훈훈', 'hun123@gmail.com', password('h1'), '010-2188-2121-9576',
'perProfile_color', '2021-05-30', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(22, '김고은', '은은', 'eun123@naver.com', password('e1'), '010-2355-7328',
'perProfile_color', '2021-06-02', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(23, '김혜수', '수수', 'su123@naver.com', password('s1'), '010-7122-6530',
'perProfile_color', '2021-06-02', 1, 1);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status, active) 
values(24, '전혜진', '진진', 'jin123@naver.com', password('j1'), '010-8562-7821',
'perProfile_color', '2021-06-02', 1, 1);


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
