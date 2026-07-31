-- 게시물 테이블
SELECT
    *,
    COUNT(bno) OVER () AS total
FROM
    tbl_board
WHERE
    1 = 1
ORDER BY
    bno DESC;

-- 게시물 목록 조회
SELECT
    bno,
    title,
    writer,
    regdate
FROM
    tbl_board
WHERE
    1 = 1
    AND delflag = FALSE
    AND bno >= 1
ORDER BY
    bno DESC;

-- 페이지네이션
SELECT
    *
FROM
    tbl_board
WHERE
    1 = 1
    AND bno >= 1
    AND delflag = FALSE
ORDER BY
    bno DESC
LIMIT
    10
OFFSET
    10;