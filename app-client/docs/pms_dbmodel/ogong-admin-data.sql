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
values(2, 2, 'jpeg');

-- 확인용
/*
select
 n.notice_no,n.title,n.content,n.create_dt,nf.notice_file_no,nf.filepath 
from notice n left
 outer join notice_file nf on n.notice_no=nf.notice_no 
order by n.notice_no asc
 */
-------------------------------------------------------------------------------------------------