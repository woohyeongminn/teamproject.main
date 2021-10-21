-- 카페 리뷰 상태 컬럼 추가
--alter table studycafe_review add column status integer;
-- 카페 예약 이용시간 도메인 숫자로 변경
--alter table studycafe_reservation modify using_time integer;

-- 카페 운영상태
insert into studycafe_operating_status(operating_status_no, name)
values (1, '승인대기');
insert into studycafe_operating_status(operating_status_no, name)
values (2, '운영중');
insert into studycafe_operating_status(operating_status_no, name)
values (3, '운영중단');
insert into studycafe_operating_status(operating_status_no, name)
values (4, '삭제');

-- 카페
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, ceo_member_no)
values (1, '에이스터디카페', '스터디 모임 전문 공간 에이스터디카페 입니다.', 
'서울 강남구 강남대로94길 11 맨하탄어학원빌딩 4층', '02-111-1111', '08:00', '21:00', 2, 1);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, ceo_member_no)
values (2, '해피해피스터디카페', '안녕하세요! 강남에 위치한 해피해피스터디카페 입니다.', 
'서울 강남구 강남대로92길 13 5층', '02-123-1234', '07:00', '23:00', 2, 2);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, ceo_member_no)
values (3, '광명스터디카페', '공부가 잘 되는 공간, 광명스터디카페 입니다.', 
'경기 광명시 철산로 12 2층 2호', '010-5555-5555', '06:00', '22:00', 2, 3);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, ceo_member_no)
values (4, '비트스터디카페 판교센터', '안녕하세요! 비트스터디카페 판교센터 입니다.', 
'경기 성남시 분당구 서판교로 36 나이스빌딩 4층 402호', '010-5555-5555', '05:00', '23:00', 1, 4);

-- 카페 사진
insert into studycafe_photo(photo_no, name, cafe_no)
values (1, 'aaa.jpg', 1);
insert into studycafe_photo(photo_no, name, cafe_no)
values (2, 'bbb.jpg', 2);
insert into studycafe_photo(photo_no, name, cafe_no)
values (3, 'ccc.jpg', 3);
insert into studycafe_photo(photo_no, name, cafe_no)
values (4, 'ddd.jpg', 4);

-- 카페 휴무일
insert into studycafe_holiday(holiday_no, cafe_no, date)
values (1, 1, '2021-10-30');
insert into studycafe_holiday(holiday_no, cafe_no, date)
values (2, 1, '2021-11-20');
insert into studycafe_holiday(holiday_no, cafe_no, date)
values (3, 1, '2021-11-21');

-- 카페 스터디룸
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount)
values (1, 'A타입(2~3인)', 1, '2~3인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
3, 6000);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount)
values (2, 'A타입(2인)', 2, '최대 2인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
2, 6000);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount)
values (3, 'B타입(3~4인)', 1, '3~4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount)
values (4, 'B타입(4인)', 2, '최대 4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount)
values (5, 'C타입(5~6인)', 1, '5~6인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
6, 15000);

-- 카페 스터디룸 사진
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (1, 1, 'study_room_img_01.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (2, 1, 'study_room_img_02.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (3, 3, 'study_room_img_03.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (4, 5, 'study_room_img_04.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (5, 5, 'study_room_img_05.jpg');

-- 카페 예약상태
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (1, '예약완료');
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (2, '결제완료');
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (3, '예약취소(개인)');
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (4, '결제취소(개인)');
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (5, '예약거절(사장)');
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (6, '결제거절(사장)');

-- 카페 예약 
-- review : 1 => 리뷰 작성 아직 안함,, 2 => 리뷰 작성 완료
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, per_member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (1, 1, 1, '2021-7-22', '2021-8-1', '10:00', 1, 2, 12000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, per_member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (2, 2, 2, '2021-8-22', '2021-9-1', '10:00', 1, 2, 12000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, per_member_no, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (3, 5, 1, '2021-11-30', '10:00', 3, 5, 45000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, per_member_no, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (4, 5, 2, '2021-11-30', '15:00', 2, 5, 30000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, per_member_no, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (5, 2, 2, '2021-12-12', '12:00', 1, 2, 12000, 1, 1);









--insert into studycafe_holiday(holiday_no, cafe_no, date)
--values (4, 1, '2021-11-22');

/*
select c.cafe_no, c.name, c.location, c.open_time, c.close_time
from studycafe c
join studycafe_operating_status cs on c.operating_status_no=cs.operating_status_no
where c.operating_status_no != 1 and c.operating_status_no != 4
and c.location like '%서울%'
order by cafe_no asc;

select c.cafe_no, c.name, c.info, c.location, c.phone, c.open_time, c.close_time,
c.view_cnt, sp.name, sh.date
from studycafe c
left outer join studycafe_photo sp on c.cafe_no = sp.cafe_no
left outer join studycafe_holiday sh on c.cafe_no = sh.cafe_no
where c.operating_status_no != 1 and c.operating_status_no !=4
order by cafe_no asc

--and c.cafe_no = 1
order by cafe_no asc

select review_no, studycafe_rsv_no, content, grade, create_dt
from studycafe_review;


select studyroom_no, name
from studycafe_room
where cafe_no = 1;

select r.studyroom_no, r.name room_name, r.cafe_no, r.introduction, r.people, r.hourly_amount, rp.photo_no, rp.name photo_name
from studycafe_room r
left outer join studycafe_room_photo rp on r.studyroom_no=rp.studyroom_no
where r.studyroom_no = 1
            
select rs.studycafe_rsv_no, rs.studyroom_no, rs.per_member_no, rs.rsv_dt, rs.using_dt, rs.start_time, 
rs.using_time, rs.people, rs.total_price, rs.review, rst.rsv_status_no, rst.rsv_name, c.cafe_no, c.name
from studycafe_reservation rs
join studycafe_reservation_status rst on rs.rsv_status_no=rst.rsv_status_no
join studycafe_room sr on rs.studyroom_no=sr.studyroom_no
join studycafe c on sr.cafe_no=c.cafe_no
join per_member pm on rs.per_member_no=pm.per_member_no
where pm.member_no = 1 and rs.studycafe_rsv_no =
order by rs.studycafe_rsv_no asc;
            
update studycafe_reservation set rsv_status_no = ? where studycafe_rsv_no = ?
            
select r.review_no, r.grade, r.content, r.create_dt, sr.cafe_no
from studycafe_review r
join studycafe_reservation rs on r.studycafe_rsv_no=rs.studycafe_rsv_no
join studycafe_room sr on rs.studyroom_no=sr.studyroom_no
join per_member pm on rs.per_member_no=pm.per_member_no
join member m on pm.member_no=m.member_no
where m.member_no = 1;


select c.cafe_no, c.name, c.info, c.location, c.phone, c.open_time, c.close_time,
c.view_cnt, sp.name, sh.date, c.operating_status_no
from studycafe c
left outer join studycafe_photo sp on c.cafe_no = sp.cafe_no
left outer join studycafe_holiday sh on c.cafe_no = sh.cafe_no
and c.cafe_no = ?

update member set nickname = '테스트' where member_no = (select member_no from ceo_member where ceo_member_no=2);
            


insert into studycafe_photo(photo_no, name, cafe_no)
values (8, 'xxx.jpg', 1);
insert into studycafe_photo(photo_no, name, cafe_no)
values (9, 'zzz.jpg', 1);
*/