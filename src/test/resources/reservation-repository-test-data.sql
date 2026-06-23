
-- user 데이터
INSERT INTO users values (1, '파도');
INSERT INTO users values (2, '코코');

-- theme 데이터
INSERT INTO themes values (1, '테마 제목', '테마 내용입니다.', '테마url');
INSERT INTO themes values (2, '테마 제목2', '2테마 내용입니다.', '테마url2');

-- reservation time 데이터
INSERT INTO reservation_times values (1, '13:00');
INSERT INTO reservation_times values (2, '15:00');

-- reservation 데이터
INSERT INTO reservations
values (1, '2026-10-03', 1, 1, 1);
INSERT INTO reservations
values (2, '2026-10-04', 1, 2, 1);
INSERT INTO reservations
values (3, '2026-10-03', 2, 1, 2);