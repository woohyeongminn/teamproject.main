-- 관리자 test 값 
insert into admin(admin_no, email, password, nickname) 
values(1, 'ogong@test.com', password('1234'), '오늘의공부');
insert into admin(admin_no, email, password, nickname) 
values(2, 'admin@test.com', password('1234'), '관리자테스트');

-------------------------------------------------------------------------------------------------
-- 공지사항 test 값
insert into notice(notice_no, title, content, create_dt)
values(1, '오늘의 공부 회원 가입을 환영합니다!',
'오늘의 공부와 함께 목표를 향해 으쌰으쌰!', '2020-01-01');
insert into notice(notice_no, title, content, create_dt)
values(2, '오늘의 공부 홈페이지 이용 방법',
'내용 업데이트 예정입니다.', '2020-02-03');
insert into notice(notice_no, title, content, create_dt)
values(3, 'ID 및 PW 찾기에 관하여',
'해당 홈페이지 로그인 페이지 하단에 ID/PW를 이용해 주세요. 그 밖의 문의사항은 [ ogong@test.com ]로 메일 주세요.', '2020-03-07');

-- 공지사항 test 값 >> 첨부파일
insert into notice_file(notice_file_no, notice_no, filepath)
values(1, 1, 'gif');
insert into notice_file(notice_file_no, notice_no, filepath)
values(3, 3, 'jpeg');

-- 확인용
/*
select
 n.notice_no,n.title,n.content,n.create_dt,nf.notice_file_no,nf.filepath 
from notice n left
 outer join notice_file nf on n.notice_no=nf.notice_no 
order by n.notice_no asc
 */
-------------------------------------------------------------------------------------------------

-- 문의게시판 test 값
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, create_dt, use_secret) 
values(1, 1, '문의합니다.', '예약 방법에 대해 알고 싶습니다.', 0, '2020-02-01', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, create_dt, use_secret) 
values(2, 7, '가게 등록..', '가게 승인 요청 어떻게 하나요?', 0, '2020-12-10', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, create_dt, use_secret) 
values(3, 3, '관리자님', '이메일로 문의 드리고 싶어요!', 0, '2021-04-18', 2);

-------------------------------------------------------------------------------------------------
-- 문의게시판 댓글 test 값
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(1, 1, '문의합니다', '공지게시판을 참조해 주세요.', '2020-02-01');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(3, 3, '관리자님', 'ogong@test.com 여기로 이메일 보내 주세요~!', '2021-04-19');


-- 목록
/*
select
  ab.ask_board_no,
  ab.title,
  ab.content,
  m.member_no,
  m.nickname,
  m.status member_status,
  ab.use_secret ask_status,
  ab.create_dt,
  ab.view_cnt,
  abr.reply_title r_title,
  abr.reply_content r_content,
  abr.reply_dt r_dt
 from
  ask_board ab
  left outer join member m on m.member_no=ab.member_no
  left outer join ask_board_reply abr on abr.ask_board_no=ab.ask_board_no
 order by ab.ask_board_no asc
 */
