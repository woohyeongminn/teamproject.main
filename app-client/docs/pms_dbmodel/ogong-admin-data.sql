-- 관리자 test 값 
insert into admin(admin_no, email, password, nickname) 
values(1, 'ogong@test.com', password('1234'), '오늘의공부');
insert into admin(admin_no, email, password, nickname) 
values(2, 'admin@test.com', password('1234'), '관리자테스트');

-------------------------------------------------------------------------------------------------

-- 확인용
/*
select
 n.notice_no,n.title,n.content,n.create_dt,nf.notice_file_no,nf.filepath 
from notice n left
 outer join notice_file nf on n.notice_no=nf.notice_no 
order by n.notice_no asc
 */
-------------------------------------------------------------------------------------------------