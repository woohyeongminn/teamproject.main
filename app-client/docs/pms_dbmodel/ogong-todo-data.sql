-- 투두진행상태 필수 값
insert into study_todolist_progress(progress_no,name) 
values(1,'진행 중');
insert into study_todolist_progress(progress_no,name) 
values(2,'완료');

-- 투두리스트 test 값
insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(1, 1,1,'내일 맛있는 거 먹기','토요일',1);
insert into study_todolist(todolist_no, study_no,member_no,content,note,progress_no) 
values(2, 1,1,'대면 싫어요','월요일',2);

-- todolist_no 넣어 준 이유: 강제로 번호 주입
-- 테스트 추가하고 싶으면 제외하고 넣으면 됨

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
