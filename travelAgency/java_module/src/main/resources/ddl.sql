
/**
  * JSON 데이터 유형은 MySQL 5.7 이상 버전에서 지원. 이전 버전에서는 TEXT 데이터 유형을 사용해야 함
  */

-- 상품 테이블: 상품 정보(이름, 가격, 설명, 이미지 등)를 저장
CREATE TABLE TB_PRODUCT (
                            PRODUCT_ID INT PRIMARY KEY AUTO_INCREMENT, -- 상품 ID (기본 키, 자동 증가)
                            PRODUCT_NAME VARCHAR(255) NOT NULL, -- 상품 이름 (필수)
                            PRODUCT_PRICE DECIMAL(10, 2) NOT NULL, -- 상품 가격 (필수)
                            PRODUCT_DESCRIPTION TEXT, -- 상품 설명
                            PRODUCT_IMAGE_URL VARCHAR(255), -- 상품 이미지 URL
                            CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                            UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
) COMMENT '상품 정보 테이블';

-- 상품 상세 테이블: 상품의 상세 정보(추가 설명, 규격, 재고 등)를 저장
CREATE TABLE TB_PRODUCT_DETAIL (
                                   PRODUCT_DETAIL_ID INT PRIMARY KEY AUTO_INCREMENT, -- 상품 상세 ID (기본 키, 자동 증가)
                                   PRODUCT_ID INT NOT NULL, -- 상품 ID (외래 키, 필수)
                                   DETAIL_DESCRIPTION TEXT, -- 상세 설명
                                   SPECIFICATION TEXT, -- 규격
                                   STOCK_QUANTITY INT, -- 재고 수량
                                   CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                                   UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                                   FOREIGN KEY (PRODUCT_ID) REFERENCES TB_PRODUCT(PRODUCT_ID) -- 상품 테이블과의 외래 키 관계 설정
) COMMENT '상품 상세 정보 테이블';

-- 사용자 테이블: 사용자 정보(아이디, 비밀번호, 이름, 연락처 등)를 저장
CREATE TABLE TB_USER (
                         USER_ID INT PRIMARY KEY AUTO_INCREMENT, -- 사용자 ID (기본 키, 자동 증가)
                         USER_ACCOUNT VARCHAR(50) UNIQUE NOT NULL, -- 사용자 계정 (고유, 필수)
                         USER_PASSWORD VARCHAR(255) NOT NULL, -- 사용자 비밀번호 (필수)
                         USER_NAME VARCHAR(100) NOT NULL, -- 사용자 이름 (필수)
                         USER_PHONE VARCHAR(20), -- 사용자 전화번호
                         CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                         UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
) COMMENT '사용자 정보 테이블';

ALTER TABLE TB_USER
    ADD USER_EMAIL VARCHAR(255);

ALTER TABLE TB_USER
    ADD USER_TYPE VARCHAR(100);

ALTER TABLE tb_comment CHANGE COMMENT_REGDATE CREATED_AT timestamp DEFAULT CURRENT_TIMESTAMP  NULL COMMENT '댓글 등록일';
ALTER TABLE tb_comment CHANGE COMMENT_UPDATDATE UPDATED_AT timestamp NULL COMMENT '댓글 수정일';
ALTER TABLE travelagency.tb_comment MODIFY COLUMN COMMENT_WRITER int NOT NULL COMMENT '댓글 작성자';

-- 사용자 상세 테이블: 사용자의 추가 정보(주소, 프로필 사진, 선호 설정 등)를 저장
CREATE TABLE TB_USER_DETAIL (
                                USER_DETAIL_ID INT PRIMARY KEY AUTO_INCREMENT, -- 사용자 상세 ID (기본 키, 자동 증가)
                                USER_ID INT NOT NULL, -- 사용자 ID (외래 키, 필수)
                                USER_ADDRESS VARCHAR(255), -- 사용자 주소
                                USER_PROFILE_IMAGE_URL VARCHAR(255), -- 사용자 프로필 이미지 URL
                                USER_PREFERENCE TEXT, -- 사용자 선호 설정
                                CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                                UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                                FOREIGN KEY (USER_ID) REFERENCES TB_USER(USER_ID) -- 사용자 테이블과의 외래 키 관계 설정
) COMMENT '사용자 상세 정보 테이블';

-- 배너 테이블: 웹사이트에 표시되는 배너 정보(이미지, 링크, 노출 기간 등)를 저장
CREATE TABLE TB_BANNER (
                           BANNER_ID INT PRIMARY KEY AUTO_INCREMENT, -- 배너 ID (기본 키, 자동 증가)
                           BANNER_IMAGE_URL VARCHAR(255) NOT NULL, -- 배너 이미지 URL (필수)
                           BANNER_LINK_URL VARCHAR(255), -- 배너 링크 URL
                           START_DATE DATE, -- 노출 시작일
                           END_DATE DATE, -- 노출 종료일
                           CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                           UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
) COMMENT '배너 정보 테이블';

-- 견적 문의 테이블: 사용자가 제출한 견적 문의 정보(문의 내용, 연락처, 상품 정보 등)를 저장
CREATE TABLE TB_INQUIRY (
                            INQUIRY_ID INT PRIMARY KEY AUTO_INCREMENT, -- 견적 문의 ID (기본 키, 자동 증가)
                            USER_ID INT, -- 사용자 ID (외래 키)
                            PRODUCT_ID INT, -- 상품 ID (외래 키)
                            INQUIRY_CONTENT TEXT NOT NULL, -- 문의 내용 (필수)
                            CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                            UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                            FOREIGN KEY (USER_ID) REFERENCES TB_USER(USER_ID), -- 사용자 테이블과의 외래 키 관계 설정
                            FOREIGN KEY (PRODUCT_ID) REFERENCES TB_PRODUCT(PRODUCT_ID) -- 상품 테이블과의 외래 키 관계 설정
) COMMENT '견적 문의 정보 테이블';

ALTER TABLE tb_inquiry
ADD COLUMN USER_ACCOUNT VARCHAR(50),
ADD COLUMN USER_NAME VARCHAR(100) NOT NULL, -- 사용자 이름 (필수)
ADD COLUMN USER_PHONE VARCHAR(20), -- 사용자 전화번호
ADD COLUMN USER_EMAIL VARCHAR(255),
ADD COLUMN  INQUIRY_TITLE VARCHAR(100) NOT NULL; -- 문의제목
ALTER TABLE travelagency.tb_inquiry DROP FOREIGN KEY tb_inquiry_ibfk_2;
ALTER TABLE tb_inquiry CHANGE PRODUCT_TYPE CATEGORY_TYPE varchar(50) NULL;

-- 후기 테이블: 상품 또는 서비스에 대한 사용자 후기(별점, 내용, 작성자 등)를 저장
CREATE TABLE TB_REVIEW (
                           REVIEW_ID INT PRIMARY KEY AUTO_INCREMENT, -- 후기 ID (기본 키, 자동 증가)
                           USER_ID INT NOT NULL, -- 사용자 ID (외래 키, 필수)
                           PRODUCT_ID INT, -- 상품 ID (외래 키)
                           REVIEW_RATING INT, -- 별점
                           REVIEW_CONTENT TEXT, -- 후기 내용
                           CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                           UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                           FOREIGN KEY (USER_ID) REFERENCES TB_USER(USER_ID), -- 사용자 테이블과의 외래 키 관계 설정
                           FOREIGN KEY (PRODUCT_ID) REFERENCES TB_PRODUCT(PRODUCT_ID) -- 상품 테이블과의 외래 키 관계 설정
) COMMENT '상품/서비스 후기 테이블';

-- 게시판 테이블: 게시판 글 정보(제목, 내용, 작성자, 작성일 등)를 저장
CREATE TABLE TB_BOARD (
                          BOARD_ID INT PRIMARY KEY AUTO_INCREMENT, -- 게시글 ID (기본 키, 자동 증가)
                          USER_ID INT NOT NULL, -- 사용자 ID (외래 키, 필수)
                          BOARD_TITLE VARCHAR(255) NOT NULL, -- 게시글 제목 (필수)
                          BOARD_CONTENT TEXT, -- 게시글 내용
                          CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                          UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                          FOREIGN KEY (USER_ID) REFERENCES TB_USER(USER_ID) -- 사용자 테이블과의 외래 키 관계 설정
) COMMENT '게시판 테이블';

ALTER TABLE TB_BOARD ADD BOARD_HITS INTEGER DEFAULT 0;
ALTER TABLE tb_board ADD USER_ACCOUNT varchar(50) NULL;
ALTER TABLE tb_board ADD USER_NAME varchar(100) NULL;


-- 댓글 테이블
CREATE TABLE TB_COMMENT (
                            COMMENT_ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '댓글 ID',
                            BOARD_ID INT NOT NULL COMMENT '게시글 ID',
                            COMMENT_WRITER VARCHAR(50) NOT NULL COMMENT '댓글 작성자',
                            COMMENT_CONTENT TEXT NOT NULL COMMENT '댓글 내용',
                            COMMENT_REGDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 등록일',
                            COMMENT_UPDATDATE TIMESTAMP COMMENT '댓글 수정일',
                            USER_ACCOUNT VARCHAR(50) COMMENT '댓글 작성자 ID (외래키 - TB_USER)',
                            FOREIGN KEY (BOARD_ID) REFERENCES TB_BOARD(BOARD_ID) ON DELETE CASCADE,
                            FOREIGN KEY (USER_ACCOUNT) REFERENCES TB_USER(USER_ACCOUNT) ON DELETE SET NULL
) COMMENT '댓글 테이블';

-- 활동 기록 테이블: 사용자 활동 기록(로그인, 상품 조회, 구매 등)을 저장
CREATE TABLE TB_ACTIVITY_LOG (
                                 LOG_ID INT PRIMARY KEY AUTO_INCREMENT, -- 활동 기록 ID (기본 키, 자동 증가)
                                 USER_ID INT, -- 사용자 ID (외래 키)
                                 ACTIVITY_TYPE VARCHAR(50), -- 활동 유형
                                 ACTIVITY_DETAIL TEXT, -- 활동 상세 내용
                                 CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 생성 일시 (기본값: 현재 시간)
) COMMENT '사용자 활동 기록 테이블';

-- 대시보드 테이블: 관리자 대시보드에 표시될 데이터(통계, 요약 정보 등)를 저장
CREATE TABLE TB_DASHBOARD (
                              DASHBOARD_ID INT PRIMARY KEY AUTO_INCREMENT, -- 대시보드 ID (기본 키, 자동 증가)
                              DASHBOARD_NAME VARCHAR(100), -- 대시보드 이름
                              DASHBOARD_DATA JSON, -- 대시보드 데이터 (JSON 형식)
                              CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                              UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
) COMMENT '관리자 대시보드 테이블';

-- 통계 테이블: 웹사이트 운영 관련 통계 데이터(방문자 수, 판매량, 상품별 조회수 등)를 저장
CREATE TABLE TB_STATISTICS (
                               STATISTICS_ID INT PRIMARY KEY AUTO_INCREMENT, -- 통계 ID (기본 키, 자동 증가)
                               STATISTICS_TYPE VARCHAR(50), -- 통계 유형
                               STATISTICS_DATA JSON, -- 통계 데이터 (JSON 형식)
                               STATISTICS_DATE DATE, -- 통계 날짜
                               CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                               UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
) COMMENT '웹사이트 통계 테이블';

-- 문의 테이블: 사용자의 문의 정보(문의 유형, 제목, 내용, 답변 등)를 저장
CREATE TABLE TB_QUESTION (
                             QUESTION_ID INT PRIMARY KEY AUTO_INCREMENT, -- 문의 ID (기본 키, 자동 증가)
                             USER_ID INT, -- 사용자 ID (외래 키)
                             QUESTION_TYPE VARCHAR(50), -- 문의 유형
                             QUESTION_TITLE VARCHAR(255), -- 문의 제목
                             QUESTION_CONTENT TEXT, -- 문의 내용
                             ANSWER TEXT, -- 답변 내용
                             CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                             UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                             FOREIGN KEY (USER_ID) REFERENCES TB_USER(USER_ID) -- 사용자 테이블과의 외래 키 관계 설정
) COMMENT '사용자 문의 테이블';

-- 권한 테이블: 사용자 권한 정보(관리자, 일반 사용자 등)를 저장
CREATE TABLE TB_AUTHORITY (
                              AUTHORITY_ID INT PRIMARY KEY AUTO_INCREMENT, -- 권한 ID (기본 키, 자동 증가)
                              AUTHORITY_NAME VARCHAR(50), -- 권한 이름
                              CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                              UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
) COMMENT '사용자 권한 테이블';

-- 파일 테이블: 게시판, 후기, 상품 상세 등에 첨부되는 파일 정보를 저장
CREATE TABLE TB_FILE (
                         FILE_ID INT PRIMARY KEY AUTO_INCREMENT, -- 파일 ID (기본 키, 자동 증가)
                         BOARD_ID INT, -- 게시글 ID (외래 키)
                         REVIEW_ID INT, -- 후기 ID (외래 키)
                         PRODUCT_DETAIL_ID INT, -- 상품 상세 ID (외래 키)
                         FILE_PATH VARCHAR(255), -- 파일 경로
                         FILE_TYPE VARCHAR(50), -- 파일 유형
                         FILE_SIZE INT, -- 파일 크기
                         CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                         UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                         FOREIGN KEY (BOARD_ID) REFERENCES TB_BOARD(BOARD_ID), -- 게시판 테이블과의 외래 키 관계 설정
                         FOREIGN KEY (REVIEW_ID) REFERENCES TB_REVIEW(REVIEW_ID), -- 후기 테이블과의 외래 키 관계 설정
                         FOREIGN KEY (PRODUCT_DETAIL_ID) REFERENCES TB_PRODUCT_DETAIL(PRODUCT_DETAIL_ID) -- 상품 상세 테이블과의 외래 키 관계 설정
) COMMENT '파일 정보 테이블';

-- 알림 테이블: 사용자에게 발송되는 알림 정보를 저장
CREATE TABLE TB_NOTIFICATION (
                                 NOTIFICATION_ID INT PRIMARY KEY AUTO_INCREMENT, -- 알림 ID (기본 키, 자동 증가)
                                 USER_ID INT, -- 사용자 ID (외래 키)
                                 NOTIFICATION_TYPE VARCHAR(50), -- 알림 유형
                                 NOTIFICATION_CONTENT TEXT, -- 알림 내용
                                 CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                                 UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
                                 FOREIGN KEY (USER_ID) REFERENCES TB_USER(USER_ID) -- 사용자 테이블과의 외래 키 관계 설정
) COMMENT '사용자 알림 테이블';

-- 카테고리 테이블
CREATE TABLE TB_CATEGORY (
                             CATEGORY_ID int PRIMARY KEY AUTO_INCREMENT,
                             CATEGORY_TYPE varchar(50) DEFAULT NULL,
                             CATEGORY_NAME varchar(100) DEFAULT NULL,
                             CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 일시 (기본값: 현재 시간)
                             UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 일시 (기본값: 현재 시간, 업데이트 시 자동 갱신)
) COMMENT='카테고리 테이블';