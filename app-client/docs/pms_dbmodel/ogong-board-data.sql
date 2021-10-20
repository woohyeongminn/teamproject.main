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
values(1, 1, 'RE : 문의합니다', '공지게시판을 참조해 주세요.', '2020-02-01');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(3, 3, 'RE : 관리자님', 'ogong@test.com 여기로 이메일 보내 주세요~!', '2021-04-19');


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
