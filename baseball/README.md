create database baseball;

use baseball;

## 테이블
````
-- 각 테이블 생성
create table stadium(
s_id int primary key auto_increment,
s_name varchar(20) not null,
created_at timestamp not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table team (
t_id int primary key auto_increment,
stadium_id int unique not null,
t_name varchar(50) not null,
created_at timestamp not null,
foreign key (stadium_id) references stadium(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table player(
p_id int primary key auto_increment,
team_id int,
p_name varchar(20) not null,
position varchar(20) not null,
created_at timestamp not null,

    constraint uc_team_position unique(team_id, position),
    foreign key (team_id) references team(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table out_player(
out_id int primary key auto_increment,
player_id int unique not null,
reason varchar(500) not null,
created_at timestamp not null,

    foreign key (player_id) references player(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
````

##더미데이터
````
-- 야구장 데이터 생성
insert into stadium(s_name, created_at) values("트루이스트 파크", now());
insert into stadium(s_name, created_at) values("론디포 파크", now());
insert into stadium(s_name, created_at) values("시티즌스 뱅크 파크", now());

-- team 데이터 생성
insert into team(stadium_id, t_name, created_at) values(1, "애틀랜타 브레이브스", now());
insert into team(stadium_id, t_name, created_at) values(2, "마이애미 말린소", now());
insert into team(stadium_id, t_name, created_at) values(3, "필라델피아 필리스", now());

-- player 데이터 생성
insert into player(team_id, p_name, position, created_at) values(1, "로날드 아쿠나", "우익수", now());
insert into player(team_id, p_name, position, created_at) values(1, "마르셀 오즈나", "좌익수", now());
insert into player(team_id, p_name, position, created_at) values(1, "스펜서 스트러이더", "투수", now());
insert into player(team_id, p_name, position, created_at) values(1, "맷 올슨", "1루수", now());
insert into player(team_id, p_name, position, created_at) values(1, "오자이노 알비스", "2루스", now());
insert into player(team_id, p_name, position, created_at) values(1, "오스틴 소로카", "3루수", now());
insert into player(team_id, p_name, position, created_at) values(1, "숀 머피", "포수", now());
insert into player(team_id, p_name, position, created_at) values(1, "마이클 해리스2세", "중견수", now());
insert into player(team_id, p_name, position, created_at) values(1, "올란도 아르시아", "유격수", now());

insert into player(team_id, p_name, position, created_at) values(2, "호르헤 솔레르", "우익수", now());
insert into player(team_id, p_name, position, created_at) values(2, "헤수스 산체스", "좌익수", now());
insert into player(team_id, p_name, position, created_at) values(2, "샌디 알칸타라", "투수", now());
insert into player(team_id, p_name, position, created_at) values(2, "율리에스키 구리엘", "1루수", now());
insert into player(team_id, p_name, position, created_at) values(2, "루이스 아라에즈", "2루스", now());
insert into player(team_id, p_name, position, created_at) values(2, "존 버티", "3루수", now());
insert into player(team_id, p_name, position, created_at) values(2, "제이콥 스탈링스", "포수", now());
insert into player(team_id, p_name, position, created_at) values(2, "크리스티안 옐리치", "중견수", now());
insert into player(team_id, p_name, position, created_at) values(2, "진 세구라", "유격수", now());

insert into player(team_id, p_name, position, created_at) values(3, "브라이스 하퍼", "우익수", now());
insert into player(team_id, p_name, position, created_at) values(3, "카일 슈와버", "좌익수", now());
insert into player(team_id, p_name, position, created_at) values(3, "아론 놀라", "투수", now());
insert into player(team_id, p_name, position, created_at) values(3, "데릭 홀", "1루수", now());
insert into player(team_id, p_name, position, created_at) values(3, "조시 해리슨", "2루스", now());
insert into player(team_id, p_name, position, created_at) values(3, "드류 엘리스", "3루수", now());
insert into player(team_id, p_name, position, created_at) values(3, "J.T.리얼무토", "포수", now());
insert into player(team_id, p_name, position, created_at) values(3, "크리스티안 파체", "중견수", now());
insert into player(team_id, p_name, position, created_at) values(3, "에드문도 소사", "유격수", now());


select * from player;
````
````
-- Dynamic pivot query
DROP PROCEDURE IF EXISTS POSITION_PIVOT;

DELIMITER $$
CREATE PROCEDURE POSITION_PIVOT(
)
BEGIN

SET @sql = (
SELECT GROUP_CONCAT(
CONCAT(
'MAX(CASE WHEN t.id = ', id, ' THEN p.name ELSE '''' END) AS `', name, '`'
)
ORDER BY id
)
FROM team
);

SET @query = CONCAT(
'SELECT p.position, ', @sql, '
FROM player p
LEFT JOIN team t ON p.team_id = t.id
GROUP BY p.position
ORDER BY p.position'
);

PREPARE stmt FROM @query;
EXECUTE stmt;

END$$
DELIMITER ;


-- JDBC에서 프로시저 호출로 실행
CALL POSITION_PIVOT();

````

