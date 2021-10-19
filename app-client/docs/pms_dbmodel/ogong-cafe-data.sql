insert into studycafe_operating_status(operating_status_no, name)
values (1, '승인대기');
insert into studycafe_operating_status(operating_status_no, name)
values (2, '운영중');
insert into studycafe_operating_status(operating_status_no, name)
values (3, '운영중단');
insert into studycafe_operating_status(operating_status_no, name)
values (4, '삭제');



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



insert into studycafe_photo(photo_no, name, cafe_no)
values (1, 'aaa.jpg', 1);
insert into studycafe_photo(photo_no, name, cafe_no)
values (2, 'bbb.jpg', 2);
insert into studycafe_photo(photo_no, name, cafe_no)
values (3, 'ccc.jpg', 3);
insert into studycafe_photo(photo_no, name, cafe_no)
values (4, 'ddd.jpg', 4);



insert into studycafe_holiday(holiday_no, cafe_no, date)
values (1, 1, '2021-11-30');


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