-- 스터디 조장, 구성원 테스트 값 보면서 넣어야 함

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (1, '뭔가 공부를 허투루 한 기분인데 이게 맞는건가요?', 'C++강의를 들으면서 기본적인 문법은 어느정도 알겠다 싶어서 콘솔로 게임을 만들어보려고하는데
테트리스를 만들어보라길래 딱 앉았더니 머리가 백지가 되버리는겁니다 도저히 어떻게 만들어야할지 생각이안나서
여기저기 찾아보니까 뭐 학부 1학년도 문법떼자마자 바로 만들 수 있는 수준이라던데 갑자기 자괴감이 들더군요..
프로그래밍이 재능의 영역인가 싶기도하고; 이게 제가 공부를 똑바로 안하고 넘어가서 그런건가요.. 
선생님의 강의를 들으면서 공부가 재밌어질정도가 되었었는데 갑자기 현타가 와버렸습니다', '3', '2021-11-1', '1' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (2, '난이도 급상승', '이 DFS 파트부터 난이도 갑자기 급상승

데몬 유적부터 소머리데몬이 필드몹으로 나오는 느낌이네요', '1', '2021-11-1', '1' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (3, '코드 작성하기까지가 복잡하네요', '몇년간 DEV C++만 사용하다가 비쥬얼 스튜디오로 넘어왔는데 새 파일 만들기가 이렇게 번거로울 줄이야.. 설마 매번 이렇게 해야하는건가요 dev에선 그냥 파일에서 새창 프로젝트 열기 누르면 바로 코딩할수 있었는데 ', '4', '2021-11-2', '2' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (4, '첫번째 로드맵 순서대로 강의를 보시는 걸 추천하시나요?', '두 가지 로드맵 중 첫번째 로드맵으로 강의를 선택하여 듣게되었습니다. 현재 구현해보며 자바를 공부 중이어서 자바 8에 대한 강의부터 보려고 하는데 시작부터 약간 어렵게 느껴지네요.. 하하.. 그래서 로드맵으로 제공하는 강의 순서대로 보시는 걸 추천하시는지, 아니면 필요한 강의(애플리케이션, 코드를 조작하는 다양한 방법, 자바8 강의 중 하나) 먼저 찾아 들어도 문제 없는지 궁금합니다', '2', '2021-11-3', '3' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (5, '제가 공부를 너무 안한건가요', '따라하면서 하고있는데 하나도 이해가 안가네요..ㅠ
다른분들은 어떻게 이렇게 질문 하나도없이 쭉쭉 나가시는지 모르겠어요.
다시 문법 공부부터 시작해야하는 건가요?아니면 그냥 계속 들어도 되나요', '4', '2021-11-4', '4' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (6, '왜 이렇게 작성하는건지 이해가 안되는데', '따라치면서 왜 이렇게 해야되는건지 이해가 안되는데 어떻게 해야되나요?

자바 문법은 이해가 됩니다만.. 왜 이렇게 만드는건지 이해가 안돼요.', '3', '2021-11-5', '1' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (7, '에러 해결 공유', '# cerbot-auto wget으로 설치 시 404 Not Found

강좌에 나오는 cerbot-auto 스크립트는 서비스 종료됐어요. >> 관련링크

제로초님이 잘 정리해놓으신 블로그 글이 있어요 그대로 따라하시면 돼요 >> 제로초님 블로그 글

# nginx 서버 시작했을 때 certbot (:: address already in use)

기존 노드 서버가 실행 중일 때 (http 80포트를 사용 중일 때) 나타나요. 저 같은 경우는 강의에서 안내해주신 kill -9 [PID] 명령을 실행했는데도 lsof -i tcp:80 했을때 노드 서버가 계속 확인돼서, npx pm2 kill 로 pm2를 통해 노드 서버를 종료하니 nginx 가 정상 실행됐어요.

# nginx status 했을 때 nginx: [warn] conflicting server name "__URL__" on 0.0.0.0:__PORT__, ignored

중복된 server_name을 nginx 설정 파일에 넣었을 때 발생해요. 

1. cat /etc/nginx/nginx.conf | grep "server_name"
2. cat /etc/nginx/sites-enabled/default | grep "server_name"

두 가지 명령을 사용해서 중복된 server_name이 있는지 확인하고, 있다면 중복을 제거해주세요. 2번의 default 설정이 1번의 설정을 덮어씌운다는 점에 주의해주세요.

아래는 위에 올린 제로초님 글 중의 내용입니다.

# nginx reload, restart 등이 안된다면

1. 설정 파일 내 세미콜론 누락 확인
2. 누락이 없다면 다음 글 참고 >> stackoverflow

모두 좋은 강의로 즐코하세용', '1', '2021-11-6', '2' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (8, '안녕하세요', '오늘의 공부 처음 사용하는데 되게 괜찮은거같아요!', '2', '2021-11-7', '3' , '1');

insert into study_board (st_board_no, title, content, view_ct, created_dt, member_no, study_no)
values (9, ' 추천드립니다!!', '고민하시는분은.. 무조건 들어보세요!!!', '4', '2021-11-8', '4' , '1');

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