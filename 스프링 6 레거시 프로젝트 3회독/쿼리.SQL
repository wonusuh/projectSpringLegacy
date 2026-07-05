-- p. 156, 게시물 관리 분석과 설계
SELECT
    *
FROM
    tbl_board;

-- 게시물 목록 조회
SELECT
    bno,
    title,
    writer,
    regDate,
FROM
    tbl_board
WHERE
    1 = 1
    AND delflag = false
ORDER BY
    bno DESC;

-- 더미 데이터 삽입
INSERT INTO
    tbl_board (title, content, writer)
SELECT
    title,
    content,
    writer
FROM
    tbl_board
WHERE
    1 = 1;

-- 페이징을 위한 게시물 목록 조회
SELECT
    *
FROM
    tbl_board
WHERE
    1 = 1
    AND bno > 0
    AND delflag = FALSE
ORDER BY
    bno DESC
LIMIT
    10
OFFSET
    10;
    SELECT COUNT(*) from tbl_board;