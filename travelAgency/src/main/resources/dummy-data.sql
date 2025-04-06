-- TB_USER 테이블 더미 데이터 삽입
INSERT INTO TB_USER (USER_ACCOUNT, USER_PASSWORD, USER_NAME, USER_PHONE) VALUES
                                                                             ('user1', 'password123', '홍길동', '010-1234-5678'),
                                                                             ('user2', 'password456', '김철수', '010-9876-5432'),
                                                                             ('user3', 'password789', '이영희', '010-5678-1234');
UPDATE TB_USER SET USER_EMAIL = 'user123@example.com';
UPDATE TB_USER SET USER_TYPE = 'personal' WHERE USER_ACCOUNT = 'user1';
UPDATE TB_USER SET USER_TYPE = 'copr' WHERE USER_ACCOUNT = 'user2';
UPDATE TB_USER SET USER_TYPE = 'org' WHERE USER_ACCOUNT = 'user3';

-- TB_USER_DETAIL 테이블 더미 데이터 삽입
INSERT INTO TB_USER_DETAIL (USER_ID, USER_ADDRESS, USER_PROFILE_IMAGE_URL, USER_PREFERENCE) VALUES
                                                                                                (1, '서울시 강남구', 'http://example.com/user1.jpg', '{"color": "blue", "size": "large"}'),
                                                                                                (2, '경기도 성남시', 'http://example.com/user2.jpg', '{"color": "red", "size": "medium"}'),
                                                                                                (3, '부산시 해운대구', 'http://example.com/user3.jpg', '{"color": "green", "size": "small"}');

-- TB_PRODUCT 테이블 더미 데이터 삽입
INSERT INTO TB_PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DESCRIPTION, PRODUCT_IMAGE_URL) VALUES
                                                                                                 ('상품1', 10000, '상품1 설명', 'http://example.com/product1.jpg'),
                                                                                                 ('상품2', 20000, '상품2 설명', 'http://example.com/product2.jpg'),
                                                                                                 ('상품3', 30000, '상품3 설명', 'http://example.com/product3.jpg');
-- USER_ID 1번 고객 선호도 데이터 (키 값 영어로 수정)
UPDATE TB_USER_DETAIL
SET USER_PREFERENCE = '{
  "travelStyle": "자유 여행",
  "accommodationType": "호텔",
  "hotelAmenities": ["수영장", "조식 포함"],
  "interestedRegions": ["제주도", "부산"],
  "activityPreference": {
    "oceanActivities": "높음",
    "culturalSites": "보통",
    "shopping": "낮음"
  },
  "foodType": ["해산물", "한식"],
  "preferredPriceRangeAccommodation": "10만원-20만원"
}'
WHERE USER_ID = 1;

-- USER_ID 2번 고객 선호도 데이터 (키 값 영어로 수정)
UPDATE TB_USER_DETAIL
SET USER_PREFERENCE = '{
  "travelStyle": "패키지 여행",
  "accommodationType": "리조트",
  "resortAmenities": ["전용 해변", "올인클루시브"],
  "interestedRegions": ["발리", "푸켓"],
  "activityPreference": {
    "oceanActivities": "매우 높음",
    "relaxation": "최고",
    "localCultureExperience": "보통"
  },
  "foodType": ["동남아 음식", "양식"],
  "preferredPriceRangeAccommodation": "20만원 이상"
}'
WHERE USER_ID = 2;

-- USER_ID 3번 고객 선호도 데이터 (키 값 영어로 수정)
UPDATE TB_USER_DETAIL
SET USER_PREFERENCE = '{
  "travelStyle": "배낭 여행",
  "accommodationType": "게스트하우스",
  "guesthouseFeatures": ["저렴한 가격", "다른 여행객과의 교류"],
  "interestedRegions": ["유럽", "남미"],
  "activityPreference": {
    "walkingTour": "높음",
    "publicTransportation": "높음",
    "meetingNewPeople": "매우 높음"
  },
  "foodType": ["localStreetFood", "diverseCuisineExperience"],
  "preferredPriceRangeAccommodation": "5만원 이하"
}'
WHERE USER_ID = 3;

-- TB_PRODUCT_DETAIL 테이블 더미 데이터 삽입
INSERT INTO TB_PRODUCT_DETAIL (PRODUCT_ID, DETAIL_DESCRIPTION, SPECIFICATION, STOCK_QUANTITY) VALUES
                                                                                                  (1, '상품1 상세 설명', '{"size": "large", "color": "blue"}', 100),
                                                                                                  (2, '상품2 상세 설명', '{"size": "medium", "color": "red"}', 50),
                                                                                                  (3, '상품3 상세 설명', '{"size": "small", "color": "green"}', 200);

-- TB_BANNER 테이블 더미 데이터 삽입
INSERT INTO TB_BANNER (BANNER_IMAGE_URL, BANNER_LINK_URL, START_DATE, END_DATE) VALUES
                                                                                    ('http://example.com/banner1.jpg', 'http://example.com/link1', '2024-01-01', '2024-01-31'),
                                                                                    ('http://example.com/banner2.jpg', 'http://example.com/link2', '2024-02-01', '2024-02-28'),
                                                                                    ('http://example.com/banner3.jpg', 'http://example.com/link3', '2024-03-01', '2024-03-31');

-- TB_INQUIRY 테이블 더미 데이터 삽입
INSERT INTO TB_INQUIRY (USER_ID, PRODUCT_ID, INQUIRY_CONTENT, CONTACT_INFO) VALUES
                                                                                (1, 1, '상품1 견적 문의', 'user1@example.com'),
                                                                                (2, 2, '상품2 견적 문의', 'user2@example.com'),
                                                                                (3, 3, '상품3 견적 문의', 'user3@example.com');

-- TB_REVIEW 테이블 더미 데이터 삽입
INSERT INTO TB_REVIEW (USER_ID, PRODUCT_ID, REVIEW_RATING, REVIEW_CONTENT) VALUES
                                                                               (1, 1, 5, '상품1 후기 좋습니다.'),
                                                                               (2, 2, 4, '상품2 후기 괜찮습니다.'),
                                                                               (3, 3, 3, '상품3 후기 보통입니다.');

-- TB_BOARD 테이블 더미 데이터 삽입
INSERT INTO TB_BOARD (USER_ID, BOARD_TITLE, BOARD_CONTENT) VALUES
                                                               (1, '게시글1 제목', '게시글1 내용'),
                                                               (2, '게시글2 제목', '게시글2 내용'),
                                                               (3, '게시글3 제목', '게시글3 내용');

-- TB_ACTIVITY_LOG 테이블 더미 데이터 삽입
INSERT INTO TB_ACTIVITY_LOG (USER_ID, ACTIVITY_TYPE, ACTIVITY_DETAIL) VALUES
                                                                          (1, 'login', '사용자1 로그인'),
                                                                          (2, 'product_view', '상품2 조회'),
                                                                          (3, 'purchase', '상품3 구매');

-- TB_DASHBOARD 테이블 더미 데이터 삽입
INSERT INTO TB_DASHBOARD (DASHBOARD_NAME, DASHBOARD_DATA) VALUES
                                                              ('대시보드1', '{"sales": 100, "users": 50}'),
                                                              ('대시보드2', '{"sales": 200, "users": 100}'),
                                                              ('대시보드3', '{"sales": 300, "users": 150}');

-- TB_STATISTICS 테이블 더미 데이터 삽입
INSERT INTO TB_STATISTICS (STATISTICS_TYPE, STATISTICS_DATA, STATISTICS_DATE) VALUES
                                                                                  ('sales', '{"product1": 50, "product2": 100}', '2024-01-01'),
                                                                                  ('users', '{"male": 30, "female": 20}', '2024-02-01'),
                                                                                  ('views', '{"product1": 1000, "product2": 2000}', '2024-03-01');

-- TB_QUESTION 테이블 더미 데이터 삽입
INSERT INTO TB_QUESTION (USER_ID, QUESTION_TYPE, QUESTION_TITLE, QUESTION_CONTENT, ANSWER) VALUES
                                                                                               (1, '상품 문의', '상품1 문의 제목', '상품1 문의 내용', '상품1 문의 답변'),
                                                                                               (2, '배송 문의', '배송2 문의 제목', '배송2 문의 내용', '배송2 문의 답변'),
                                                                                               (3, '환불 문의', '환불3 문의 제목', '환불3 문의 내용', '환불3 문의 답변');

-- TB_AUTHORITY 테이블 더미 데이터 삽입
INSERT INTO TB_AUTHORITY (AUTHORITY_NAME) VALUES
                                              ('admin'),
                                              ('user'),
                                              ('guest');

-- TB_FILE 테이블 더미 데이터 삽입
INSERT INTO TB_FILE (BOARD_ID, REVIEW_ID, PRODUCT_DETAIL_ID, FILE_PATH, FILE_TYPE, FILE_SIZE) VALUES
                                                                                                  (1, NULL, NULL, '/files/file1.jpg', 'image/jpeg', 1024),
                                                                                                  (NULL, 1, NULL, '/files/file2.png', 'image/png', 2048),
                                                                                                  (NULL, NULL, 1, '/files/file3.pdf', 'application/pdf', 4096);

-- TB_NOTIFICATION 테이블 더미 데이터 삽입
INSERT INTO TB_NOTIFICATION (USER_ID, NOTIFICATION_TYPE, NOTIFICATION_CONTENT) VALUES
                                                                                   (1, 'message', '새로운 메시지가 도착했습니다.'),
                                                                                   (2, 'order', '주문이 완료되었습니다.'),
                                                                                   (3, 'event', '이벤트에 참여해주셔서 감사합니다.');