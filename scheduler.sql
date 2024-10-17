# CREATE 일정 테이블
CREATE TABLE IF NOT EXISTS schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY comment '일정 테이블의 PK',
    writer VARCHAR(15) NOT NULL comment '작성자명',
    title VARCHAR(60) NOT NULL comment '할 일 제목',
    content VARCHAR(800)  NOT NULL comment '할 일 내용',
    created_at DATETIME  NOT NULL comment '최초 작성일',
    updated_at DATETIME  NOT NULL comment '수정일'
);

# CREATE 댓글 테이블
CREATE TABLE IF NOT EXISTS comment
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY comment '댓글 테이블의 PK',
    content VARCHAR(80)  NOT NULL comment '댓글 내용',
    created_at DATETIME  NOT NULL comment '최초 작성일',
    updated_at DATETIME  NOT NULL comment '수정일',
    writer VARCHAR(15) NOT NULL comment '작성자명',
    schedule_id BIGINT comment '댓글 테이블의 FK; 일정 테이블의 PK',
    user_id BIGINT comment '댓글 테이블의 FK; 유저 테이블의 PK',
    FOREIGN KEY (schedule_id) REFERENCES schedule(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

# CREATE 유저 테이블
CREATE TABLE IF NOT EXISTS user
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY comment '유저 테이블의 PK',
    writer VARCHAR(15) NOT NULL comment '유저명',
    email VARCHAR(40)  NOT NULL comment '유저의 이메일',
    created_at DATETIME  NOT NULL comment '유저 등록일',
    updated_at DATETIME  NOT NULL comment '유저 정보 수정일',
    
);

# CREATE 일정 관리 유저 테이블
CREATE TABLE IF NOT EXISTS schedule_manager
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY comment '일정 관리 유저 테이블의 PK',
    schedule_id BIGINT comment '일정 관리 유저 테이블의 FK; 일정 테이블의 PK',
    user_id BIGINT comment '일정 관리 유저 테이블의 FK; 유저 테이블의 PK',
    FOREIGN KEY (schedule_id) REFERENCES schedule(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
    
);
