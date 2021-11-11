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
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (1, '에이스터디카페', '스터디 모임 전문 공간 에이스터디카페 입니다.\n매일 소독제 청소 & 정기 방역으로 쾌적한 환경 제공', 
'서울 강남구 역삼동 818-13, 맨하탄어학원빌딩 4층', '02-111-1111', '08:00', '21:00', 2, 8);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (2, '해피해피스터디카페', '안녕하세요! 강남에 위치한 해피해피스터디카페 입니다.\n담요, 독서대, 학용품, 핸드폰 충전기, 무선마우스, 개인 스탠드 조명 비치되어 있습니다', 
'서울 강남구 역삼동 819-9, 5층', '02-123-1234', '07:00', '23:00', 2, 9);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (3, '초심스터디카페', '안녕하세요! 초심스터디카페 입니다.', 
'서울 서초구 서초동 1316-28, 우송빌딩 지하 2층', '02-1245-9999', '09:00', '23:00', 2, 10);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (4, '와!스터디카페', '공부가 잘 되는 공간, 와!스터디카페 입니다.', 
'서울 강남구 강남대로98길 17, 3층', '02-1245-9999', '05:00', '23:00', 2, 11);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (5, '오호라스터디카페', '오호라스터디카페 입니다.', 
'서울 강남구 테헤란로5길 36, 1층', '02-1245-9999', '00:00', '23:00', 2, 12);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (6, '광명스터디카페', '공부가 잘 되는 공간, 광명스터디카페 입니다.', 
'경기 광명시 철산동 264, 2층 2호', '010-5555-5555', '06:00', '22:00', 2, 13);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (7, '비트스터디카페 판교센터', '안녕하세요! 비트스터디카페 판교센터 입니다.', 
'경기 성남시 분당구 판교동 577-3, 나이스빌딩 4층 402호', '010-6789-5555', '05:00', '23:00', 2, 14);
insert into studycafe(cafe_no, name, info, location, phone, open_time, close_time, operating_status_no, member_no)
values (8, '어라운드스터디카페', '어라운드스터디카페 강남역삼점입니다\n3,4층 : 스터디존 5층 : 카페존 & 테라스', 
'서울 강남구 역삼동 817-19, 3~5층, 나이스빌딩 4층 402호', '010-4545-7878', '05:00', '23:00', 1, 15);

-- 카페 사진
insert into studycafe_photo(photo_no, name, cafe_no)
values (1, 'aaa', 1);
insert into studycafe_photo(photo_no, name, cafe_no)
values (2, 'bbb', 1);
insert into studycafe_photo(photo_no, name, cafe_no)
values (3, 'ccc', 1);
insert into studycafe_photo(photo_no, name, cafe_no)
values (4, 'ddd', 2);
insert into studycafe_photo(photo_no, name, cafe_no)
values (5, 'eee', 3);
insert into studycafe_photo(photo_no, name, cafe_no)
values (6, 'fff', 4);
insert into studycafe_photo(photo_no, name, cafe_no)
values (7, 'ggg', 5);
insert into studycafe_photo(photo_no, name, cafe_no)
values (8, 'hhh', 6);
insert into studycafe_photo(photo_no, name, cafe_no)
values (9, 'iii', 7);
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
values (1, 'A타입(2~3인)', 1, '2~3인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
3, 6000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (2, 'A타입(2인)', 2, '최대 2인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
2, 6000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (3, 'B타입(3~4인)', 1, '3~4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (4, 'B타입(4인)', 2, '최대 4인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
4, 9000, 1);
insert into studycafe_room(studyroom_no, name, cafe_no, introduction, people, hourly_amount, status)
values (5, 'C타입(5~6인)', 1, '5~6인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
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
values (10, 'B타입(5~6인)', 7, '5~6인이 이용할 수 있는 스터디룸 입니다.\n기본설비 : 화이트보드, 무선인터넷',
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
insert into studycafe_reservation_status(rsv_status_no, rsv_name)
values (7, '이용완료');

-- 카페 예약 
-- review : 1 => 리뷰 작성 아직 안함,, 2 => 리뷰 작성 완료
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (1, 1, 1, '2021-7-22', '2021-8-1', '10:00', 1, 2, 12000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (2, 3, 2, '2021-8-22', '2021-9-1', '11:00', 1, 2, 12000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (3, 3, 3, '2021-9-22', '2021-10-11', '12:00', 1, 2, 12000, 1, 2);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (4, 4, 1, '2021-10-01', '2021-10-11', '10:00', 3, 5, 45000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (5, 3, 1, '2021-10-10', '2021-12-1', '15:00', 2, 5, 30000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, rsv_dt, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (6, 2, 2, '2021-11-02', '2021-11-01', '12:00', 1, 2, 12000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (7, 5, 1, '2021-11-30', '10:00', 3, 5, 45000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (8, 5, 2, '2021-11-30', '15:00', 2, 5, 30000, 1, 1);
insert into studycafe_reservation(studycafe_rsv_no, studyroom_no, member_no, using_dt, start_time, using_time, people, total_price, rsv_status_no, review)
values (9, 6, 2, '2021-12-12', '12:00', 1, 2, 12000, 1, 1);


--카페 리뷰
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (1, 1, '괜찮아요', 4, '2021-8-11');
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (2, 2, '좋아요', 5, '2021-9-3');
insert into studycafe_review(review_no, studycafe_rsv_no, content, grade, create_dt)
values (3, 3, '별로;;', 2, '2021-10-20');

-- 카페관련만 드롭, 생성하기
/*

-- 리뷰
DROP TABLE IF EXISTS studycafe_review RESTRICT;

-- 결제
DROP TABLE IF EXISTS studycafe_payment RESTRICT;

-- 결제유형
DROP TABLE IF EXISTS studycafe_payment_type RESTRICT;

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

-- 결제현황
DROP TABLE IF EXISTS studycafe_payment_status RESTRICT;

-- 스터디카페사진
DROP TABLE IF EXISTS studycafe_photo RESTRICT;

-- 스터디카페
DROP TABLE IF EXISTS studycafe RESTRICT;

-- 카페운영상태
DROP TABLE IF EXISTS studycafe_operating_status RESTRICT;

-- 스터디카페룸사진
CREATE TABLE studycafe_room_photo (
  photo_no     INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
  studyroom_no INTEGER      NOT NULL COMMENT '스터디룸번호', -- 스터디룸번호
  name         VARCHAR(255) NOT NULL COMMENT '사진명' -- 사진명
)
COMMENT '스터디카페룸사진';

-- 스터디카페룸사진
ALTER TABLE studycafe_room_photo
  ADD CONSTRAINT PK_studycafe_room_photo -- 스터디카페룸사진 기본키
    PRIMARY KEY (
      photo_no -- 사진번호
    );

ALTER TABLE studycafe_room_photo
  MODIFY COLUMN photo_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

-- 스터디카페룸
CREATE TABLE studycafe_room (
  studyroom_no  INTEGER     NOT NULL COMMENT '스터디룸번호', -- 스터디룸번호
  name          VARCHAR(50) NOT NULL COMMENT '스터디룸명', -- 스터디룸명
  cafe_no       INTEGER     NOT NULL COMMENT '스터디카페번호', -- 스터디카페번호
  Introduction  TEXT        NOT NULL COMMENT '소개글', -- 소개글
  people        INTEGER     NOT NULL DEFAULT 1 COMMENT '인원제한수', -- 인원제한수
  hourly_amount INTEGER     NOT NULL COMMENT '시간당금액', -- 시간당금액
  status        INTEGER     NOT NULL DEFAULT 1 COMMENT '상태' -- 상태
)
COMMENT '스터디카페룸';

-- 스터디카페룸
ALTER TABLE studycafe_room
  ADD CONSTRAINT PK_studycafe_room -- 스터디카페룸 기본키
    PRIMARY KEY (
      studyroom_no -- 스터디룸번호
    );

-- 스터디카페룸 유니크 인덱스
CREATE UNIQUE INDEX UIX_studycafe_room
  ON studycafe_room ( -- 스터디카페룸
    cafe_no ASC, -- 스터디카페번호
    name ASC     -- 스터디룸명
  );

ALTER TABLE studycafe_room
  MODIFY COLUMN studyroom_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '스터디룸번호';

-- 리뷰
CREATE TABLE studycafe_review (
  review_no        INTEGER      NOT NULL COMMENT '리뷰번호', -- 리뷰번호
  studycafe_rsv_no INTEGER      NOT NULL COMMENT '스터디카페예약번호', -- 스터디카페예약번호
  content          VARCHAR(255) NOT NULL COMMENT '내용', -- 내용
  grade            INTEGER      NOT NULL COMMENT '평점', -- 평점
  create_dt        DATE         NOT NULL DEFAULT curdate() COMMENT '등록일', -- 등록일
  status           INTEGER      NOT NULL DEFAULT 1 COMMENT '상태' -- 상태
)
COMMENT '리뷰';

-- 리뷰
ALTER TABLE studycafe_review
  ADD CONSTRAINT PK_studycafe_review -- 리뷰 기본키
    PRIMARY KEY (
      review_no -- 리뷰번호
    );

ALTER TABLE studycafe_review
  MODIFY COLUMN review_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '리뷰번호';

-- 스터디카페예약상태
CREATE TABLE studycafe_reservation_status (
  rsv_status_no INTEGER     NOT NULL COMMENT '예약상태번호', -- 예약상태번호
  rsv_name      VARCHAR(50) NOT NULL COMMENT '예약상태명' -- 예약상태명
)
COMMENT '스터디카페예약상태';

-- 스터디카페예약상태
ALTER TABLE studycafe_reservation_status
  ADD CONSTRAINT PK_studycafe_reservation_status -- 스터디카페예약상태 기본키
    PRIMARY KEY (
      rsv_status_no -- 예약상태번호
    );

-- 스터디카페예약상태 유니크 인덱스
CREATE UNIQUE INDEX UIX_studycafe_reservation_status
  ON studycafe_reservation_status ( -- 스터디카페예약상태
    rsv_name ASC -- 예약상태명
  );

-- 스터디카페예약
CREATE TABLE studycafe_reservation (
  studycafe_rsv_no INTEGER  NOT NULL COMMENT '스터디카페예약번호', -- 스터디카페예약번호
  studyroom_no     INTEGER  NOT NULL COMMENT '스터디룸번호', -- 스터디룸번호
  member_no        INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  rsv_dt           DATETIME NOT NULL DEFAULT now() COMMENT '예약일', -- 예약일
  using_dt         DATE     NOT NULL COMMENT '이용날짜', -- 이용날짜
  start_time       TIME     NOT NULL COMMENT '시작시간', -- 시작시간
  using_time       INTEGER  NOT NULL COMMENT '이용시간', -- 이용시간
  people           INTEGER  NOT NULL DEFAULT 1 COMMENT '사용인원수', -- 사용인원수
  total_price      INTEGER  NOT NULL COMMENT '총금액', -- 총금액
  rsv_status_no    INTEGER  NOT NULL COMMENT '예약상태번호', -- 예약상태번호
  review           INTEGER  NOT NULL COMMENT '리뷰작성여부' -- 리뷰작성여부
)
COMMENT '스터디카페예약';

-- 스터디카페예약
ALTER TABLE studycafe_reservation
  ADD CONSTRAINT PK_studycafe_reservation -- 스터디카페예약 기본키
    PRIMARY KEY (
      studycafe_rsv_no -- 스터디카페예약번호
    );

ALTER TABLE studycafe_reservation
  MODIFY COLUMN studycafe_rsv_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '스터디카페예약번호';

-- 스터디카페사진
CREATE TABLE studycafe_photo (
  photo_no INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
  name     VARCHAR(255) NOT NULL COMMENT '사진명', -- 사진명
  cafe_no  INTEGER      NOT NULL COMMENT '스터디카페번호' -- 스터디카페번호
)
COMMENT '스터디카페사진';

-- 스터디카페사진
ALTER TABLE studycafe_photo
  ADD CONSTRAINT PK_studycafe_photo -- 스터디카페사진 기본키
    PRIMARY KEY (
      photo_no -- 사진번호
    );

ALTER TABLE studycafe_photo
  MODIFY COLUMN photo_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

-- 결제유형
CREATE TABLE studycafe_payment_type (
  payment_type_no INTEGER     NOT NULL COMMENT '결제유형번호', -- 결제유형번호
  name            VARCHAR(50) NOT NULL COMMENT '결제유형명' -- 결제유형명
)
COMMENT '결제유형';

-- 결제유형
ALTER TABLE studycafe_payment_type
  ADD CONSTRAINT PK_studycafe_payment_type -- 결제유형 기본키
    PRIMARY KEY (
      payment_type_no -- 결제유형번호
    );

-- 결제유형 유니크 인덱스
CREATE UNIQUE INDEX UIX_studycafe_payment_type
  ON studycafe_payment_type ( -- 결제유형
    name ASC -- 결제유형명
  );

-- 결제
CREATE TABLE studycafe_payment (
  payment_no        INTEGER  NOT NULL COMMENT '결제번호', -- 결제번호
  studycafe_rsv_no  INTEGER  NOT NULL COMMENT '스터디카페예약번호', -- 스터디카페예약번호
  payment_type_no   INTEGER  NOT NULL COMMENT '결제유형번호', -- 결제유형번호
  payment_status_no INTEGER  NOT NULL COMMENT '결제현황', -- 결제현황
  payment_dt        DATETIME NOT NULL DEFAULT now() COMMENT '결제일' -- 결제일
)
COMMENT '결제';

-- 결제
ALTER TABLE studycafe_payment
  ADD CONSTRAINT PK_studycafe_payment -- 결제 기본키
    PRIMARY KEY (
      payment_no -- 결제번호
    );

ALTER TABLE studycafe_payment
  MODIFY COLUMN payment_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '결제번호';

-- 카페운영상태
CREATE TABLE studycafe_operating_status (
  operating_status_no INTEGER     NOT NULL COMMENT '운영상태번호', -- 운영상태번호
  name                VARCHAR(50) NOT NULL COMMENT '운영상태명' -- 운영상태명
)
COMMENT '카페운영상태';

-- 카페운영상태
ALTER TABLE studycafe_operating_status
  ADD CONSTRAINT PK_studycafe_operating_status -- 카페운영상태 기본키
    PRIMARY KEY (
      operating_status_no -- 운영상태번호
    );

-- 카페운영상태 유니크 인덱스
CREATE UNIQUE INDEX UIX_studycafe_operating_status
  ON studycafe_operating_status ( -- 카페운영상태
    name ASC -- 운영상태명
  );

-- 카페휴무일
CREATE TABLE studycafe_holiday (
  holiday_no INTEGER NOT NULL COMMENT '휴무일번호', -- 휴무일번호
  cafe_no    INTEGER NOT NULL COMMENT '스터디카페번호', -- 스터디카페번호
  date       DATE    NOT NULL COMMENT '날짜' -- 날짜
)
COMMENT '카페휴무일';

-- 카페휴무일
ALTER TABLE studycafe_holiday
  ADD CONSTRAINT PK_studycafe_holiday -- 카페휴무일 기본키
    PRIMARY KEY (
      holiday_no -- 휴무일번호
    );

ALTER TABLE studycafe_holiday
  MODIFY COLUMN holiday_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '휴무일번호';

-- 스터디카페
CREATE TABLE studycafe (
  cafe_no             INTEGER      NOT NULL COMMENT '스터디카페번호', -- 스터디카페번호
  name                VARCHAR(50)  NOT NULL COMMENT '점포명', -- 점포명
  info                TEXT         NOT NULL COMMENT '소개글', -- 소개글
  location            VARCHAR(255) NOT NULL COMMENT '주소', -- 주소
  phone               VARCHAR(30)  NOT NULL COMMENT '전화번호', -- 전화번호
  open_time           TIME         NOT NULL COMMENT '오픈시간', -- 오픈시간
  close_time          TIME         NOT NULL COMMENT '마감시간', -- 마감시간
  bookable            INTEGER      NULL     COMMENT '예약가능인원', -- 예약가능인원
  view_cnt            INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  operating_status_no INTEGER      NOT NULL COMMENT '운영상태번호', -- 운영상태번호
  member_no           INTEGER      NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '스터디카페';

-- 스터디카페
ALTER TABLE studycafe
  ADD CONSTRAINT PK_studycafe -- 스터디카페 기본키
    PRIMARY KEY (
      cafe_no -- 스터디카페번호
    );

-- 스터디카페 인덱스
CREATE INDEX IX_studycafe
  ON studycafe( -- 스터디카페
    name ASC -- 점포명
  );

ALTER TABLE studycafe
  MODIFY COLUMN cafe_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '스터디카페번호';
  
-- 결제현황
CREATE TABLE studycafe_payment_status (
  payment_status_no INTEGER     NOT NULL COMMENT '결제현황', -- 결제현황
  name              VARCHAR(50) NOT NULL COMMENT '현황' -- 현황
)
COMMENT '결제현황';

-- 결제현황
ALTER TABLE studycafe_payment_status
  ADD CONSTRAINT PK_studycafe_payment_status -- 결제현황 기본키
    PRIMARY KEY (
      payment_status_no -- 결제현황
    );

-- 결제현황 유니크 인덱스
CREATE UNIQUE INDEX UIX_studycafe_payment_status
  ON studycafe_payment_status ( -- 결제현황
    name ASC -- 현황
  );
  

-- 스터디카페룸사진
ALTER TABLE studycafe_room_photo
  ADD CONSTRAINT FK_studycafe_room_TO_studycafe_room_photo -- 스터디카페룸 -> 스터디카페룸사진
    FOREIGN KEY (
      studyroom_no -- 스터디룸번호
    )
    REFERENCES studycafe_room ( -- 스터디카페룸
      studyroom_no -- 스터디룸번호
    );

-- 스터디카페룸
ALTER TABLE studycafe_room
  ADD CONSTRAINT FK_studycafe_TO_studycafe_room -- 스터디카페 -> 스터디카페룸
    FOREIGN KEY (
      cafe_no -- 스터디카페번호
    )
    REFERENCES studycafe ( -- 스터디카페
      cafe_no -- 스터디카페번호
    );

-- 리뷰
ALTER TABLE studycafe_review
  ADD CONSTRAINT FK_studycafe_reservation_TO_studycafe_review -- 스터디카페예약 -> 리뷰
    FOREIGN KEY (
      studycafe_rsv_no -- 스터디카페예약번호
    )
    REFERENCES studycafe_reservation ( -- 스터디카페예약
      studycafe_rsv_no -- 스터디카페예약번호
    );

-- 스터디카페예약
ALTER TABLE studycafe_reservation
  ADD CONSTRAINT FK_studycafe_reservation_status_TO_studycafe_reservation -- 스터디카페예약상태 -> 스터디카페예약
    FOREIGN KEY (
      rsv_status_no -- 예약상태번호
    )
    REFERENCES studycafe_reservation_status ( -- 스터디카페예약상태
      rsv_status_no -- 예약상태번호
    );

-- 스터디카페예약
ALTER TABLE studycafe_reservation
  ADD CONSTRAINT FK_studycafe_room_TO_studycafe_reservation -- 스터디카페룸 -> 스터디카페예약
    FOREIGN KEY (
      studyroom_no -- 스터디룸번호
    )
    REFERENCES studycafe_room ( -- 스터디카페룸
      studyroom_no -- 스터디룸번호
    );

-- 스터디카페예약
ALTER TABLE studycafe_reservation
  ADD CONSTRAINT FK_member_TO_studycafe_reservation -- 회원 -> 스터디카페예약
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES member ( -- 회원
      member_no -- 회원번호
    );

-- 스터디카페사진
ALTER TABLE studycafe_photo
  ADD CONSTRAINT FK_studycafe_TO_studycafe_photo -- 스터디카페 -> 스터디카페사진
    FOREIGN KEY (
      cafe_no -- 스터디카페번호
    )
    REFERENCES studycafe ( -- 스터디카페
      cafe_no -- 스터디카페번호
    );

-- 결제
ALTER TABLE studycafe_payment
  ADD CONSTRAINT FK_studycafe_payment_type_TO_studycafe_payment -- 결제유형 -> 결제
    FOREIGN KEY (
      payment_type_no -- 결제유형번호
    )
    REFERENCES studycafe_payment_type ( -- 결제유형
      payment_type_no -- 결제유형번호
    );

-- 결제
ALTER TABLE studycafe_payment
  ADD CONSTRAINT FK_studycafe_payment_status_TO_studycafe_payment -- 결제현황 -> 결제
    FOREIGN KEY (
      payment_status_no -- 결제현황
    )
    REFERENCES studycafe_payment_status ( -- 결제현황
      payment_status_no -- 결제현황
    );

-- 결제
ALTER TABLE studycafe_payment
  ADD CONSTRAINT FK_studycafe_reservation_TO_studycafe_payment -- 스터디카페예약 -> 결제
    FOREIGN KEY (
      studycafe_rsv_no -- 스터디카페예약번호
    )
    REFERENCES studycafe_reservation ( -- 스터디카페예약
      studycafe_rsv_no -- 스터디카페예약번호
    );

-- 카페휴무일
ALTER TABLE studycafe_holiday
  ADD CONSTRAINT FK_studycafe_TO_studycafe_holiday -- 스터디카페 -> 카페휴무일
    FOREIGN KEY (
      cafe_no -- 스터디카페번호
    )
    REFERENCES studycafe ( -- 스터디카페
      cafe_no -- 스터디카페번호
    );

-- 스터디카페
ALTER TABLE studycafe
  ADD CONSTRAINT FK_studycafe_operating_status_TO_studycafe -- 카페운영상태 -> 스터디카페
    FOREIGN KEY (
      operating_status_no -- 운영상태번호
    )
    REFERENCES studycafe_operating_status ( -- 카페운영상태
      operating_status_no -- 운영상태번호
    );

-- 스터디카페
ALTER TABLE studycafe
  ADD CONSTRAINT FK_ceo_member_TO_studycafe -- 사장 -> 스터디카페
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES ceo_member ( -- 사장
      member_no -- 회원번호
    );

 */
