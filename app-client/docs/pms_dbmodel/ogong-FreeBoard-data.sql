--스터디 조장, 구성원 테스트값 보면서 넣어야함

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (1, '만남 일정 궁금', '이번에 대면으로 모이실 분 계신가요?? 화상 채팅보다 한 번 모였으면 좋겠는데 언제쯤이 좋으신가요~?', '15', '2021-10-05', '1' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (2, '예상 커리큘럼입니다.', '주 3회 모임 예정으로 주 2회는 코딩 테스트 준비, 주 1회는 CS에 시간 분배할 생각인데 어떠신가요?', '12', '2021-10-10', '3' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (3, '필요한 거 있나요?', '깃허브는 알겠는데.. 더 필요한 게 있나 궁금해요!', '7', '2021-10-20', '5' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (4, '다들 준비 어떻게 하고 계세요?', '백엔드 처음 접한 초보 개발자라 공부 방법을 모르겠어요 ㅜㅜ', '5', '2021-09-01', '2' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (5, '뭘 해야 할지 1도 모르겠는 개린이입니다. 도와주세요 ㅠㅠ', '백엔드 개발하려면 java, spring 두 개를 다 사용해서 만드는 건가요?(프론트에서 HTML, CSS, Javascript 같이 사용하는 것 처럼요.)', '6', '2021-09-01', '4' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no, whether_report)
values (6, '현재까지 진행한 목록', '이 게시글에는 지금까지 진행된 부분을 댓글로 정리해서 올리면 좋을 것 같아요!', '11', '2021-09-01', '6' , '1', '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no, whether_report)
values (7, '1일차', '나 홀로 1일차 달리기! 하는 방법에 대해 혼자 끄적끄적', '3', '2021-09-01', '7' , '1', '2');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (8, '화상 채팅 하실 분~', '화상 채팅에서 모이실 분 시간 정해요~!', '17', '2021-10-05', '1' , '12');

-- 목록
/*
select
  s.study_no study_no,
  sb.st_board_no,
  sb.title,
  sb.content,
  sb.view_ct,
  sb.created_dt,
  m.member_no per_no,
  m.nickname nickname,
  m.status member_status,
  sbf.file_no file_no,
  sbf.name atcFileName,
  (select count(*) from study_board_file sbf where sb.st_board_no=sbf.st_board_no) count_file,
  (select count(*) from study_board_comment sbc where sb.st_board_no=sbc.st_board_no) count_comment,
  (select count(*) from study_board_like sbl where sb.st_board_no=sbl.st_board_no) count_like
 from study_board sb
   left outer join study s on sb.study_no=s.study_no
   left outer join member m on sb.member_no=m.member_no
   left outer join study_board_file sbf on sb.st_board_no=sbf.st_board_no
  where
   sb.study_no=1
   order by sb.st_board_no
 */
