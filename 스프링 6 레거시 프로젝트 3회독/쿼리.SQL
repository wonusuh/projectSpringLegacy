-- 트랜잭션 테스트용 테이블
SELECT
    *
FROM
    tbl_testA;

SELECT
    *
FROM
    tbl_testB;

DELETE FROM tbl_testA
WHERE
    1 = 1;

DELETE FROM tbl_testB
WHERE
    1 = 1;

-- p. 156, 게시물 관리 분석과 설계
SELECT
    *
FROM
    tbl_board;

DELETE FROM tbl_board
WHERE
    1 = 1;

select
    *
from
    tbl_reply;

delete from tbl_reply
WHERE
    1 = 1;