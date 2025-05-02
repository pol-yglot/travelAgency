-- travelagency.tb_activity_log definition

CREATE TABLE `tb_activity_log` (
                                   `LOG_ID` int NOT NULL AUTO_INCREMENT,
                                   `USER_ID` int DEFAULT NULL,
                                   `ACTIVITY_TYPE` varchar(50) DEFAULT NULL,
                                   `ACTIVITY_DETAIL` text,
                                   `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                   PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 활동 기록 테이블';


-- travelagency.tb_authority definition

CREATE TABLE `tb_authority` (
                                `AUTHORITY_ID` int NOT NULL AUTO_INCREMENT,
                                `AUTHORITY_NAME` varchar(50) DEFAULT NULL,
                                `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 권한 테이블';


-- travelagency.tb_banner definition

CREATE TABLE `tb_banner` (
                             `BANNER_ID` int NOT NULL AUTO_INCREMENT,
                             `BANNER_IMAGE_URL` varchar(255) NOT NULL,
                             `BANNER_LINK_URL` varchar(255) DEFAULT NULL,
                             `START_DATE` date DEFAULT NULL,
                             `END_DATE` date DEFAULT NULL,
                             `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`BANNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='배너 정보 테이블';


-- travelagency.tb_category definition

CREATE TABLE `tb_category` (
                               `CATEGORY_ID` int NOT NULL AUTO_INCREMENT,
                               `CATEGORY_TYPE` varchar(50) DEFAULT NULL,
                               `CATEGORY_NAME` varchar(100) DEFAULT NULL,
                               `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리 테이블';


-- travelagency.tb_dashboard definition

CREATE TABLE `tb_dashboard` (
                                `DASHBOARD_ID` int NOT NULL AUTO_INCREMENT,
                                `DASHBOARD_NAME` varchar(100) DEFAULT NULL,
                                `DASHBOARD_DATA` json DEFAULT NULL,
                                `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`DASHBOARD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='관리자 대시보드 테이블';


-- travelagency.tb_product definition

CREATE TABLE `tb_product` (
                              `PRODUCT_ID` int NOT NULL AUTO_INCREMENT,
                              `PRODUCT_NAME` varchar(255) NOT NULL,
                              `PRODUCT_PRICE` decimal(10,2) NOT NULL,
                              `PRODUCT_DESCRIPTION` text,
                              `PRODUCT_IMAGE_URL` varchar(255) DEFAULT NULL,
                              `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `PRODUCT_TYPE` varchar(50) DEFAULT NULL,
                              PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 정보 테이블';


-- travelagency.tb_statistics definition

CREATE TABLE `tb_statistics` (
                                 `STATISTICS_ID` int NOT NULL AUTO_INCREMENT,
                                 `STATISTICS_TYPE` varchar(50) DEFAULT NULL,
                                 `STATISTICS_DATA` json DEFAULT NULL,
                                 `STATISTICS_DATE` date DEFAULT NULL,
                                 `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`STATISTICS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='웹사이트 통계 테이블';


-- travelagency.tb_user definition

CREATE TABLE `tb_user` (
                           `USER_ID` int NOT NULL AUTO_INCREMENT,
                           `USER_ACCOUNT` varchar(50) NOT NULL,
                           `USER_PASSWORD` varchar(255) NOT NULL,
                           `USER_NAME` varchar(100) NOT NULL,
                           `USER_PHONE` varchar(20) DEFAULT NULL,
                           `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `USER_EMAIL` varchar(255) DEFAULT NULL,
                           `USER_TYPE` varchar(100) DEFAULT NULL,
                           PRIMARY KEY (`USER_ID`),
                           UNIQUE KEY `USER_ACCOUNT` (`USER_ACCOUNT`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 정보 테이블';


-- travelagency.tb_board definition

CREATE TABLE `tb_board` (
                            `BOARD_ID` int NOT NULL AUTO_INCREMENT,
                            `USER_ID` int NOT NULL,
                            `BOARD_TITLE` varchar(255) NOT NULL,
                            `BOARD_CONTENT` text,
                            `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                            `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `BOARD_HITS` int DEFAULT '0',
                            `USER_ACCOUNT` varchar(50) DEFAULT NULL,
                            `USER_NAME` varchar(100) DEFAULT NULL,
                            PRIMARY KEY (`BOARD_ID`),
                            KEY `USER_ID` (`USER_ID`),
                            CONSTRAINT `tb_board_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시판 테이블';


-- travelagency.tb_comment definition

CREATE TABLE `tb_comment` (
                              `COMMENT_ID` int NOT NULL AUTO_INCREMENT COMMENT '댓글 ID',
                              `BOARD_ID` int NOT NULL COMMENT '게시글 ID',
                              `COMMENT_WRITER` varchar(50) NOT NULL COMMENT '댓글 작성자',
                              `COMMENT_CONTENT` text NOT NULL COMMENT '댓글 내용',
                              `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 등록일',
                              `UPDATED_AT` timestamp NULL DEFAULT NULL COMMENT '댓글 수정일',
                              PRIMARY KEY (`COMMENT_ID`),
                              KEY `BOARD_ID` (`BOARD_ID`),
                              CONSTRAINT `tb_comment_ibfk_1` FOREIGN KEY (`BOARD_ID`) REFERENCES `tb_board` (`BOARD_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='댓글 테이블';


-- travelagency.tb_inquiry definition

CREATE TABLE `tb_inquiry` (
                              `INQUIRY_ID` int NOT NULL AUTO_INCREMENT,
                              `USER_ID` int DEFAULT NULL,
                              `PRODUCT_TYPE` varchar(50) DEFAULT NULL,
                              `INQUIRY_CONTENT` text NOT NULL,
                              `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `USER_ACCOUNT` varchar(50) DEFAULT NULL,
                              `USER_NAME` varchar(100) NOT NULL,
                              `USER_PHONE` varchar(20) DEFAULT NULL,
                              `INQUIRY_TITLE` varchar(100) NOT NULL,
                              `USER_EMAIL` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`INQUIRY_ID`),
                              KEY `USER_ID` (`USER_ID`),
                              KEY `PRODUCT_ID` (`PRODUCT_TYPE`),
                              CONSTRAINT `tb_inquiry_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='견적 문의 정보 테이블';


-- travelagency.tb_notification definition

CREATE TABLE `tb_notification` (
                                   `NOTIFICATION_ID` int NOT NULL AUTO_INCREMENT,
                                   `USER_ID` int DEFAULT NULL,
                                   `NOTIFICATION_TYPE` varchar(50) DEFAULT NULL,
                                   `NOTIFICATION_CONTENT` text,
                                   `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                   `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   PRIMARY KEY (`NOTIFICATION_ID`),
                                   KEY `USER_ID` (`USER_ID`),
                                   CONSTRAINT `tb_notification_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 알림 테이블';


-- travelagency.tb_product_detail definition

CREATE TABLE `tb_product_detail` (
                                     `PRODUCT_DETAIL_ID` int NOT NULL AUTO_INCREMENT,
                                     `PRODUCT_ID` int NOT NULL,
                                     `DETAIL_DESCRIPTION` text,
                                     `SPECIFICATION` text,
                                     `STOCK_QUANTITY` int DEFAULT NULL,
                                     `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                     `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`PRODUCT_DETAIL_ID`),
                                     KEY `PRODUCT_ID` (`PRODUCT_ID`),
                                     CONSTRAINT `tb_product_detail_ibfk_1` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `tb_product` (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품 상세 정보 테이블';


-- travelagency.tb_question definition

CREATE TABLE `tb_question` (
                               `QUESTION_ID` int NOT NULL AUTO_INCREMENT,
                               `USER_ID` int DEFAULT NULL,
                               `QUESTION_TYPE` varchar(50) DEFAULT NULL,
                               `QUESTION_TITLE` varchar(255) DEFAULT NULL,
                               `QUESTION_CONTENT` text,
                               `ANSWER` text,
                               `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`QUESTION_ID`),
                               KEY `USER_ID` (`USER_ID`),
                               CONSTRAINT `tb_question_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 문의 테이블';


-- travelagency.tb_review definition

CREATE TABLE `tb_review` (
                             `REVIEW_ID` int NOT NULL AUTO_INCREMENT,
                             `USER_ID` int NOT NULL,
                             `PRODUCT_ID` int DEFAULT NULL,
                             `REVIEW_RATING` int DEFAULT NULL,
                             `REVIEW_CONTENT` text,
                             `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`REVIEW_ID`),
                             KEY `USER_ID` (`USER_ID`),
                             KEY `PRODUCT_ID` (`PRODUCT_ID`),
                             CONSTRAINT `tb_review_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`),
                             CONSTRAINT `tb_review_ibfk_2` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `tb_product` (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품/서비스 후기 테이블';


-- travelagency.tb_user_detail definition

CREATE TABLE `tb_user_detail` (
                                  `USER_DETAIL_ID` int NOT NULL AUTO_INCREMENT,
                                  `USER_ID` int NOT NULL,
                                  `USER_ADDRESS` varchar(255) DEFAULT NULL,
                                  `USER_PROFILE_IMAGE_URL` varchar(255) DEFAULT NULL,
                                  `USER_PREFERENCE` text,
                                  `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`USER_DETAIL_ID`),
                                  KEY `USER_ID` (`USER_ID`),
                                  CONSTRAINT `tb_user_detail_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `tb_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='사용자 상세 정보 테이블';


-- travelagency.tb_file definition

CREATE TABLE `tb_file` (
                           `FILE_ID` int NOT NULL AUTO_INCREMENT,
                           `BOARD_ID` int DEFAULT NULL,
                           `REVIEW_ID` int DEFAULT NULL,
                           `PRODUCT_DETAIL_ID` int DEFAULT NULL,
                           `FILE_PATH` varchar(255) DEFAULT NULL,
                           `FILE_TYPE` varchar(50) DEFAULT NULL,
                           `FILE_SIZE` int DEFAULT NULL,
                           `CREATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                           `UPDATED_AT` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (`FILE_ID`),
                           KEY `BOARD_ID` (`BOARD_ID`),
                           KEY `REVIEW_ID` (`REVIEW_ID`),
                           KEY `PRODUCT_DETAIL_ID` (`PRODUCT_DETAIL_ID`),
                           CONSTRAINT `tb_file_ibfk_1` FOREIGN KEY (`BOARD_ID`) REFERENCES `tb_board` (`BOARD_ID`),
                           CONSTRAINT `tb_file_ibfk_2` FOREIGN KEY (`REVIEW_ID`) REFERENCES `tb_review` (`REVIEW_ID`),
                           CONSTRAINT `tb_file_ibfk_3` FOREIGN KEY (`PRODUCT_DETAIL_ID`) REFERENCES `tb_product_detail` (`PRODUCT_DETAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='파일 정보 테이블';