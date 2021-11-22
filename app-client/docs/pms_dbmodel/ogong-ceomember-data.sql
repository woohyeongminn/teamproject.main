-- 회원 test 값
insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(8, '김은채', '채채', 'cafe1@naver.com', password('c1'), '010-2473-1038',
'ceoProfile', '2020-01-19', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(9, '송다혜', '혜혜', 'cafe2@naver.com', password('c2'), '010-4755-5124',
'ceoProfile', '2020-02-11', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, created_dt, status) 
values(10, '우형민', '민민', 'cafe3@naver.com', password('c3'), '010-1024-9845',
'ceoProfile_color','2020-03-01', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(11, '조솔', '솔솔', 'cafe4@naver.com', password('c4'), '010-2245-7522', 
'ceoProfile_color','2020-03-07', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(12, '반계령', '령령', 'cafe5@naver.com', password('c5'), '010-7158-8691', 
'ceoProfile','2020-04-28', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(13, '이승호', '호호사장', 'cafe6@naver.com', password('c6'), '010-7122-7584', 
'ceoProfile_color','2020-05-11', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(14, '박성빈', '빈이네', 'cafe7@naver.com', password('c7'), '010-7869-2013', 
'ceoProfile','2020-06-12', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(15, '한재영', '영재쓰', 'cafe8@naver.com', password('c8'), '010-8752-5274', 
'ceoProfile_color','2020-07-27', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(16, '차은우', '얼천', 'cafe9@naver.com', password('c9'), '010-9415-2257', 
'ceoProfile','2020-12-12', 2);


insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(25, '박태은', '오호라', 'cafe10@naver.com', password('c10'), '010-5231-7851', 
'ceoProfile','2021-03-18', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(26, '홍수정', '이런저런', 'cafe11@naver.com', password('c11'), '010-4675-0215', 
'ceoProfile','2021-08-22', 2);

insert into member(member_no, name, nickname, email, password, tel, photo, status) 
values(27, '구시영', '구구구', 'cafe12@naver.com', password('c12'), '010-3648-3485', 
'ceoProfile_color','2021-11-11', 2);


-- member가 24번까지 있으니까 ceoMember 더 추가하려면 25번부터~

/*
insert into member(name,nickname,email,password,tel,photo,status)
    values(#{ceoName},#{ceoNickname},#{ceoEmail},password(#{ceoPassword}),#{ceoTel},#{ceoPhoto},#{ceoStatus})
*/    
    
-------------------------------------------------------------------------------------------------

-- 사장회원 test 값
insert into ceo_member(member_no, bossname, license_no) 
values(8, '김은채', '1234512345');

insert into ceo_member(member_no, bossname, license_no) 
values(9, '송다혜', '3214598745');

insert into ceo_member(member_no, bossname, license_no) 
values(10, '우형민', '5468514545');

insert into ceo_member(member_no, bossname, license_no) 
values(11, '조솔', '3332815464');

insert into ceo_member(member_no, bossname, license_no) 
values(12, '반계령', '6329437294');

insert into ceo_member(member_no, bossname, license_no) 
values(13, '이승호', '5841237294');

insert into ceo_member(member_no, bossname, license_no) 
values(14, '박성빈', '1256545895');

insert into ceo_member(member_no, bossname, license_no) 
values(15, '한재영', '7452154211');

insert into ceo_member(member_no, bossname, license_no) 
values(16, '차은우', '3264105781');

insert into ceo_member(member_no, bossname, license_no) 
values(25, '박태은', '9240875122');

insert into ceo_member(member_no, bossname, license_no) 
values(26, '홍수정', '1024572132');

insert into ceo_member(member_no, bossname, license_no) 
values(27, '구시영', '9584612522');