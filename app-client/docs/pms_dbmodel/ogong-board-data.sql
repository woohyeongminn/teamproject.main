-- 공지사항 test 값
insert into notice(notice_no, title, content, created_dt)
values(1, 'Q. 개인정보를 오픈해야 하나요? 어떻게 진행하면 될까요?',
'A. 오픈 채팅방, 온라인 화상 채팅 등 다양한 툴을 활용해 스터디를 자유롭게 진행해 보세요. 모집 시 별도의 개인 정보가 필요하지 않기 때문에 큰 부담 없이 진행하실 수 있습니다.', '2020-01-01');
insert into notice(notice_no, title, content, created_dt)
values(2, '스터디 모집 및 참여는 어디에서 하나요?',
'페이지 상단 메뉴 탭 > 스터디 찾기 || 스터디 목록 > 참여 신청하기', '2020-01-03');
insert into notice(notice_no, title, content, created_dt)
values(3, '[업데이트 소식] - 3',
'함께 하고 싶은 스터디가 있나요? 직접 만들어 보세요. 코드 리뷰, 영어 회화 스터디부터 공부를 위한 소규모 모임까지! 함께 할 스터디원을 모집해 보세요. 서로의 원동력이 되어 함께 학습해보세요. 분명 우리는 같이 성장할 거예요!', '2020-03-07');
insert into notice(notice_no, title, content, created_dt)
values(4, '[업데이트 소식] - 4',
'많은 오공생들이 "학습 동기 부여"가 있으면 좋겠다고 제안해 주시고는 해요. 아무래도 혼자서 공부하니 자꾸 미루게 되고, 나도 모르게 의지가 약해지고는 하죠. 이럴 때 "강력한 동기 부여" 혹은 지치지 않고 이어갈 수 있는 "느슨한 연대"가 필요하다고 생각됩니다. 오늘의 공부에서 함께 시작해 보세요!', '2020-05-05');
insert into notice(notice_no, title, content, created_dt)
values(5, '홈페이지 개정 안내',
'기존 서비스에서 이전하지 못한 다음과 같은 기능들을 추가할 예정입니다. 이후에도 학습을 위한 편하고 재밌는 기능들이 업데이트 될 예정이에요. 업데이트나 새로운 스터디 카페가 올라오는 소식은 앞으로 자주 전달해 드리도록 하겠습니다.', '2020-06-17');
insert into notice(notice_no, title, content, created_dt)
values(6, '파일 업로드 장애(2020.07.23) - 불편을 끼쳐드려 죄송합니다.',
'오늘 새벽 (2020년 07월 23일)에 약 2시간 가량 파일 업로드 장애가 있었습니다. 오늘의 공부 홈페이지에서 사용하는 파일 업로드 서비스로부터 발생한 문제였습니다. 현재는 정상화 된 상태이며 원인을 파악 중에 있습니다. 장애 시간에 불편을 겪은 분들께 진심으로 죄송합니다.', '2020-07-23');
insert into notice(notice_no, title, content, created_dt)
values(7, '[공지] 커뮤니티 기능이 추가되었어요.',
'내가 참여 중인 스터디에서 채팅 기능을 통해 스터디에 관한 모든 질문과 답변을 주고받을 수 있어요. 내가 궁금한 질문에 대한 해답을 찾아볼 수도, 반대로 대답해 줄 수도 있어요. 질문을 보고 만약 내가 알고 있는 내용이라면 지식을 나눠 주세요! 답변을 받은 구성원이 분명 기뻐하실 거예요. 여러분 배우고 나누고, 물어보세요!', '2020-08-29');
insert into notice(notice_no, title, content, created_dt)
values(8, '[업데이트 소식] 2021년도 잘부탁드려요.',
'메인 페이지 커버 이미지가 변경되었어요! 이제 팝업창을 통해 메인에서 배경음악도 들을 수 있게 되었습니다!', '2021-01-16');
insert into notice(notice_no, title, content, created_dt)
values(9, '일반 무통장 입금 결제 방법 종료',
'세금계산서를 발급 받을 수 있었던 기존의 무통장 입금 결제 방식이 종료되었습니다. 세금계산서 정보가 잘못되거나, 입금이 늦어지는 등 지속적인 문제 뿐만이 아니라 팀 내에서 관리하기 어려운 상황이 되어서, 일반 결제에서는 서비스를 종료하게 되었습니다. 증빙 서류가 필요하시다면, 신용카드 매출 전표 또는 현금 영수증 이용을 권장 드립니다.', '2021-04-03');
insert into notice(notice_no, title, content, created_dt)
values(10, '[업데이트 소식] - 10 | 스터디 카페 이용권 쿠폰',
'카카오톡 검색 > bitcamp-ogong > 오늘 배포된 신규 기능으로 찾아왔어요! 바로 [스터디 카페 이용 쿠폰]입니다. 오예! 쿠폰이다!! 하고 발급 후 기간 내에 사용하지 않으면 기억 소멸과 함께 만료되어 버려 애써 받은 이용권이 사라져 버린답니다. ㅠㅠ!! 모든 쿠폰에 기한이 있고 단 1회만 사용할 수 있기 때문에 이용권에 당첨되신 분들은 기간을 다시 한번 확인해 주세요!', '2021-05-28');


-- 공지사항 test 값 >> 첨부파일
insert into notice_file(notice_file_no, notice_no, filepath)
values(1, 1, 'notice1.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(2, 2, 'notice2.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(3, 3, 'notice3.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(4, 4, 'notice4.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(5, 5, 'notice5.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(6, 6, 'notice6.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(7, 7, 'notice7.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(8, 8, 'notice8.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(9, 9, 'notice9.jpg');
insert into notice_file(notice_file_no, notice_no, filepath)
values(10, 10, 'notice10.jpg');

-- 첨부파일
DROP TABLE IF EXISTS notice_file RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS notice RESTRICT;
----------------------------------------------------------------------------------------------
-- 첨부파일
CREATE TABLE notice_file (
  notice_file_no INTEGER      NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  notice_no      INTEGER      NOT NULL COMMENT '공지사항번호', -- 공지사항번호
  filepath       VARCHAR(255) NOT NULL COMMENT '첨부파일명' -- 첨부파일명
)
COMMENT '첨부파일';

-- 첨부파일
ALTER TABLE notice_file
  ADD CONSTRAINT PK_notice_file -- 첨부파일 기본키
    PRIMARY KEY (
      notice_file_no -- 첨부파일번호
    );

ALTER TABLE notice_file
  MODIFY COLUMN notice_file_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

-- 공지사항
CREATE TABLE notice (
  notice_no  INTEGER      NOT NULL COMMENT '공지사항번호', -- 공지사항번호
  title      VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content    VARCHAR(255) NOT NULL COMMENT '내용', -- 내용
  created_dt DATETIME     NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE notice
  ADD CONSTRAINT PK_notice -- 공지사항 기본키
    PRIMARY KEY (
      notice_no -- 공지사항번호
    );

-- 공지사항 인덱스
CREATE INDEX IX_notice
  ON notice( -- 공지사항
    title ASC -- 제목
  );

ALTER TABLE notice
  MODIFY COLUMN notice_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '공지사항번호';


-- 확인용
/*
select
 n.notice_no,n.title,n.content,n.created_dt,nf.notice_file_no,nf.filepath 
from notice n left
 outer join notice_file nf on n.notice_no=nf.notice_no 
order by n.notice_no asc
 */
-------------------------------------------------------------------------------------------------

-- 문의게시판 test 값
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(1, 1, '제가 북마크 누른 스터디는 어디에서 볼 수 있나요?', '북마크 누르면 담기는 곳이 있는 줄 알고 북마크로 담았는데, 제가 북마크 한 스터디는 어디에서 볼 수 있나요! 따로 볼 수는 없는지 궁금합니당', 55, '2020-02-01', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(2, 13, '가게 등록..', '가게 승인 요청 어떻게 하나요?', 30, '2020-05-29', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(3, 3, '현금영수증을 발행하고 싶어요', '이메일로 문의 드리고 싶어요!', 3, '2020-04-18', 2);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(4, 8, '이용 중 오류가 발생해요.', '스터디 카페 이용하고 싶은데 누를 때마다 오류가 떠요', 12, '2020-05-01', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(5, 2, '현재 이용 중인 계정 문의', '예약 방법에 대해 알고 싶습니다.', 41, '2020-05-25', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(6, 6, '비밀번호를 찾고 싶어요.', '비밀번호를 잊어서 로그아웃을 못하겠어요 ㅠㅠ', 10, '2020-10-15', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(7, 17, '관리자님', '이메일 계정을 변경하고 싶어요. 어떻게 하나요?', 4, '2021-01-15', 2);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(8, 16, '등록한 카페 문의.', '등록한 카페를 수정하고 싶어요. 승인 대기 중인 카페지만 수정이 가능한가요?', 22, '2021-02-28', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(9, 19, '안녕하세요? 스터디와 무관한 질문 드립니다.', '홈페이지 이용 중에 노래가 들리고 이런 건 뭔가요?', 11, '2021-03-18', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(10, 21, '문의합니다.', '예약 방법에 대해 알고 싶습니다.', 15, '2021-05-30', 1);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(11, 22, '관리자님', '이메일로 문의 드리고 싶어요!', 2, '2021-06-12', 2);
insert into ask_board(ask_board_no, member_no, title, content, view_cnt, created_dt, use_secret) 
values(12, 24, '쿠폰이나 할인 문의요', '할인과 쿠폰은 중복 적용이 안 되나요?', 33, '2021-10-29', 1);

-------------------------------------------------------------------------------------------------
-- 문의게시판 댓글 test 값
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(1, 1, 'RE: 제가 북마크 누른 스터디는 어디에서 볼 수 있나요?', '로그인 > 마이 프로필 > 내 북마크 버튼 클릭', '2020-02-01');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(2, 2, 'RE: 가게 등록..', '로그인 > 내 카페 > 카페 등록 > 등록 후 승인 대기 > 승인 완료 후 영업 가능', '2020-05-30');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(3, 3, 'RE: 현금영수증을 발행하고 싶어요', '현금 영수증은 무통장 입금(가상 계좌) 결제 화면에서 직접 신청이 가능해요. 결제를 완료하셨다면 이용일 2~5일 후 문의 게시판에 다시 글을 남겨 주시면 직접 확인하실 수 있습니다.', '2020-04-19');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(4, 4, 'RE: 이용 중 오류가 발생해요.', '오늘의 공부는 구글 크롬 브라우저에 최적화되어 있어요. 서비스 이용 중 오류가 발생할 경우 크롬 브라우저에서 다시 진행해 주세요.', '2020-05-01');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(5, 5, 'RE: 현재 이용 중인 계정 문의', '현재 계정 간 이동 서비스를 제공하고 있지 않아요. 저작권에 문제가 발생할 수 있기 때문에 각 계정은 개별 사용자로 인식하고 있어요.', '2020-05-26');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(6, 6, 'RE: 비밀번호를 찾고 싶어요.', '로그인 화면에서 [아이디/비밀번호 찾기]를 클릭해 주세요. 사용 중인 계정을 입력하면 이메일을 통해 임시 비밀번호를 보내 드려요.', '2020-10-15');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(7, 7, 'RE: 관리자님', '로그인 > 마이 프로필 > 프로필 수정 > 이메일 수정 > 수정한 이메일로 재로그인', '2021-01-16');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(8, 8, 'RE: 등록한 카페 문의', '등록된 카페는 수정 가능하지만 승인 대기 중인 카페는 초기화 기능 및 수정 기능이 제공되지 않아요.', '2021-02-28');
insert into ask_board_reply(ask_board_no, reply_no, reply_title, reply_content, reply_dt)
values(9, 9, 'RE: 안녕하세요? 스터디와 무관한 질문 드립니다.', '잠깐! 오늘의 공부 서비스 운영 관련 문의는 1:1 문의하기를 이용해 주세요.', '2021-03-21');


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
