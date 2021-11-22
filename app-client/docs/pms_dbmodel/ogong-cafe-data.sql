-- 카페 운영상태
insert into studycafe_operating_status(operating_status_no, name)
values (1, '승인대기');
insert into studycafe_operating_status(operating_status_no, name)
values (2, '운영중');
insert into studycafe_operating_status(operating_status_no, name)
values (3, '운영중단');
insert into studycafe_operating_status(operating_status_no, name)
values (4, '삭제');

-- 카페 예약상태
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (1, '예약완료(현장결제)');
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
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (7, '이용완료');

-- 카페
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (1, '비트 스터디 카페 판교센터', '안녕하세요! 비트스터디카페 판교센터 입니다.', 
'경기 성남시 분당구 판교동 577-3, 나이스빌딩 4층 402호', '010-6789-5555', '05:00', '23:00', 2, 14);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (2, '광명 스터디카페', '공부가 잘 되는 공간, 광명스터디카페 입니다.', 
'경기 광명시 철산동 264, 2층 2호', '010-5555-5555', '06:00', '22:00', 2, 13);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (3, '오호라 스터디 카페', '오호라스터디카페 입니다.', 
'서울 강남구 테헤란로5길 36, 1층', '02-1245-9999', '00:00', '23:00', 2, 12);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (4, '와! 스터디 카페', '공부가 잘 되는 공간, 와!스터디카페 입니다.', 
'서울 강남구 강남대로98길 17, 3층', '02-1245-9999', '05:00', '23:00', 2, 11);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (5, '초심 스터디 카페', '안녕하세요! 초심스터디카페 입니다.', 
'서울 서초구 서초동 1316-28, 우송빌딩 지하 2층', '02-1245-9999', '09:00', '23:00', 2, 10);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (6, '해피해피 스터디 카페', '안녕하세요! 강남에 위치한 해피해피스터디카페 입니다.\n담요, 독서대, 학용품, 핸드폰 충전기, 무선마우스, 개인 스탠드 조명 비치되어 있습니다', 
'서울 강남구 역삼동 819-9, 5층', '02-123-1234', '07:00', '23:00', 2, 9);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (7, '에이 스터디 카페', '스터디 모임 전문 공간 에이스터디카페 입니다.\n매일 소독제 청소 & 정기 방역으로 쾌적한 환경 제공', 
'서울 강남구 역삼동 818-13, 맨하탄어학원빌딩 4층', '02-111-1111', '08:00', '21:00', 2, 8);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (8, '어라운드 스터디 카페', '어라운드스터디카페 강남역삼점입니다\n3,4층 : 스터디존 5층 : 카페존 & 테라스', 
'서울 강남구 역삼동 817-19, 3~5층, 나이스빌딩 4층 402호', '010-4545-7878', '05:00', '23:00', 1, 15);

-- 카페 사진
insert into studycafe_photo(photo_no, name, cafe_no)
values (1, 'iii', 1);
insert into studycafe_photo(photo_no, name, cafe_no)
values (2, 'ddd', 2);
insert into studycafe_photo(photo_no, name, cafe_no)
values (3, 'eee', 3);
insert into studycafe_photo(photo_no, name, cafe_no)
values (4, 'fff', 4);
insert into studycafe_photo(photo_no, name, cafe_no)
values (5, 'ggg', 5);
insert into studycafe_photo(photo_no, name, cafe_no)
values (6, 'hhh', 6);
insert into studycafe_photo(photo_no, name, cafe_no)
values (7, 'aaa', 7);
insert into studycafe_photo(photo_no, name, cafe_no)
values (8, 'bbb', 7);
insert into studycafe_photo(photo_no, name, cafe_no)
values (9, 'ccc', 7);
insert into studycafe_photo(photo_no, name, cafe_no)
values (10, 'jjj', 8);

-- 카페 휴무일
insert into studycafe_holiday(holiday_no, cafe_no, date)
values (1, 1, '2021-10-30');
insert into studycafe_holiday(holiday_no, cafe_no, date)
values (2, 1, '2021-11-20');
insert into studycafe_holiday(holiday_no, cafe_no, date)
values (3, 1, '2021-11-21');

-- 카페 스터디룸
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (1, 'A타입(2~3인)', 7, '2~3인이 이용할 수 있는 스터디룸 입니다.
\n- 화이트보드, 무선인터넷\n- 24인치 LCD모니터와 컴퓨터 (스피커 사용가능)\n- HDMI케이블, 멀티탭 대여가능\n* 당일예약 불가능합니다.',3, 1000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (2, 'A타입(2인)', 2, '최대 2인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
2, 6000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (3, 'B타입(3~4인)', 7, '3~4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (4, 'B타입(4인)', 2, '최대 4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (5, 'C타입(5~6인)', 7, '5~6인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
6, 15000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (6, 'A타입(2~3인)', 3, '2~3인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
3, 6000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (7, 'A타입(2인)', 4, '최대 2인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
2, 6000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (8, 'A타입(3~4인)', 5, '3~4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (9, 'A타입(4인)', 6, '최대 4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (10, 'B타입(5~6인)', 1, '5~6인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
6, 15000, 1);

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
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (6, 6, 'study_room_img_06.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (7, 7, 'study_room_img_07.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (8, 8, 'study_room_img_08.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (9, 9, 'study_room_img_09.jpg');
insert into studycafe_room_photo(photo_no, studyroom_no, name)
values (10, 10, 'study_room_img_10.jpg');

-- 카페 예약 
-- review : 1 => 리뷰 작성 아직 안함,, 2 => 리뷰 작성 완료
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (1, 1, 1, '2021-7-22', '2021-7-25', '10:00', 1, 2, 12000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (2, 3, 2, '2021-7-25', '2021-7-27', '11:00', 1, 2, 18000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (3, 3, 3, '2021-7-30', '2021-8-1', '15:00', 1, 2, 18000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (4, 5, 4, '2021-8-5', '2021-8-6', '18:00', 1, 2, 30000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (5, 1, 5, '2021-8-10', '2021-8-15', '12:00', 1, 2, 12000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (6, 5, 1, '2021-8-30', '2021-9-2', '10:00', 1, 3, 45000, 1, 2);

insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (7, 4, 1, '2021-10-01', '2021-10-11', '10:00', 3, 5, 45000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (8, 3, 1, '2021-10-10', '2021-10-20', '15:00', 2, 5, 30000, 3, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (9, 1, 1, '2021-10-12', '2021-11-24', '12:00', 1, 2, 12000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (10, 5, 1, '2021-10-20','2021-11-25', '10:00', 3, 5, 45000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (11, 1, 1, '2021-11-1','2021-11-26', '14:00', 2, 5, 30000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (12, 6, 1, '2021-11-5','2021-11-27', '12:00', 1, 2, 12000, 3, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (13, 1, 2, '2021-11-17','2021-11-30', '10:00', 4, 2, 8000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (14, 1, 3, '2021-11-18','2021-11-30', '16:00', 2, 2, 4000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (15, 2, 1, '2021-11-19','2021-11-28', '19:00', 3, 2, 6000, 3, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (16, 6, 1, '2021-11-20','2021-12-2', '12:00', 1, 2, 12000, 1, 1);

--카페 리뷰
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (1, 1, '괜찮아요', 4, '2021-7-26');
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (2, 2, '좋아요~', 5, '2021-7-29');
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (3, 3, '사장님이 친절해요ㅎㅎ', 5, '2021-8-5');
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (4, 4, '깨끗하고 넓어요', 4, '2021-8-20');
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (5, 5, '별로입니다 가지마세요.', 2, '2021-9-10');
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (6, 6, '쾌적하고 괜찮네요~!', 4, '2021-10-1');

-- 카페관련만 드롭, 생성하기
/*

-- 리뷰
DROP TABLE IF EXISTS studycafe_review RESTRICT;

-- 스터디카페룸사진
DROP TABLE IF EXISTS studycafe_room_photo RESTRICT;

-- 스터디카페예약
DROP TABLE IF EXISTS studycafe_reservation RESTRICT;

-- 스터디카페예약상태
DROP TABLE IF EXISTS studycafe_reservation_status RESTRICT;

-- 스터디카페룸
DROP TABLE IF EXISTS studycafe_room RESTRICT;

-- 카페휴무일
DROP TABLE IF EXISTS studycafe_holiday RESTRICT;

-- 스터디카페사진
DROP TABLE IF EXISTS studycafe_photo RESTRICT;

-- 스터디카페
DROP TABLE IF EXISTS studycafe RESTRICT;

-- 카페운영상태
DROP TABLE IF EXISTS studycafe_operating_status RESTRICT;



 */
