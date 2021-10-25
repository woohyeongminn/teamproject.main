-- 투두진행상태 test 값
insert into study_todolist_progress(progress_no,name) 
values(1,'1');

-- 투두리스트 test 값
insert into study_todolist(study_no,member_no,content,note,progress_no) 
values(1,1,'1','1',1);

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
