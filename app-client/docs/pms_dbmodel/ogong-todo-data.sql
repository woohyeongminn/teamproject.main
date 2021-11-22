-- 투두 진행 상태 필수 값
insert into study_todolist_progress(progress_no,name) 
values(1,'진행');

insert into study_todolist_progress(progress_no,name) 
values(2,'완료');

-------------------------------------------------------------------------------------------------

-- 투두 리스트 값
insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(1, 1, 1,'단어장 구매하기','노트 5개',1);

insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(2, 1, 2,'자격증 정리하기','날짜 정리',2);

insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(3, 1, 3,'자격증 접수하기','토요일',1);

insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(4, 1, 4,'면접 준비','옷 사기',2);

insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(5, 1, 1,'이력서 작성하기','주말까지',1);

insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(6, 1, 2,'백엔드 정리하기','내용 정리',2);

-- todolist_no 넣어 준 이유: 강제로 번호 주입
-- 테스트 추가하고 싶으면 제외하고 넣으면 됨

-------------------------------------------------------------------------------------------------

/*
-- findAll
select
todo.todolist_no,
todo.study_no,
todo.content,
todo.note,
todo.create_dt,
m.member_no,
m.nickname,
tp.progress_no
progress_no
from
study_todolist todo
inner join
study s on
todo.study_no=s.study_no
inner
join
member m on todo.member_no=m.member_no
inner join
study_todolist_progress tp
on todo.progress_no=tp.progress_no
where
todo.study_no=1
order by
todo.todolist_no asc;

-- findByNo
select
todo.todolist_no todolist_no,
todo.study_no,
todo.content,
todo.note,
todo.create_dt,
m.member_no,
m.nickname,
tp.progress_no
progress_no
from
study_todolist todo
inner join
study s on
todo.study_no=s.study_no
inner
join
member m on todo.member_no=m.member_no
inner join
study_todolist_progress tp
on todo.progress_no=tp.progress_no
where
todo.todolist_no=1;

-- update
update study_todolist set
content='test',
note='test',
progress_no=1
where todolist_no=1;

-- delete
delete from study_todolist
where todolist_no=1;
*/