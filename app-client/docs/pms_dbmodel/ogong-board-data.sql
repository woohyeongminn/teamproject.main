-- 문의게시판 test 값
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, create_dt,
use_secret, reply_title, reply_content, reply_dt) 
values(1, 1, '문의합니다.', '예약 방법에 대해 알고 싶습니다.', 0, '2020-02-01',
1, 'RE : 문의합니다', '공지게시판을 참조해 주세요.', '2020-02-01'); -- 답변 o
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, create_dt, use_secret) 
values(2, 2, '가게 등록..', '가게 승인 요청 어떻게 하나요?', 0, '2020-12-10', 1); -- 답변 x

-------------------------------------------------------------------------------------------------
-- 문의게시판 댓글 test 값
-- insert into ask_board(reply_title, reply_content, reply_dt) 
