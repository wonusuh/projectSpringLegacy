-- 트랜잭션 테스트
SELECT
    *
FROM
    tbl_testA;

SELECT
    *
FROM
    tbl_testB;

-- 게시글 테이블
SELECT
    *
FROM
    tbl_board;

SELECT
    *
FROM
    tbl_reply;

-- 게시물 목록
SELECT
    bno,
    title,
    writer,
    regDate
FROM
    tbl_board
WHERE
    1 = 1
    AND delflag = false
ORDER BY
    bno DESC;

-- 페이지네이션
INSERT INTO
    tbl_board (title, content, writer)
SELECT
    title,
    content,
    writer
FROM
    tbl_board;

SELECT
    *
FROM
    tbl_board
WHERE
    1 = 1
    AND bno > 0
    AND delflag = false
ORDER BY
    bno DESC
LIMIT
    10
OFFSET
    0;

-- 댓글
SELECT
    *
FROM
    tbl_reply;

SELECT
    *
FROM
    tbl_reply
WHERE
    1 = 1
ORDER BY
    rno DESC;

-- 게시물 목록에 댓글개수 join 처리
SELECT
    board.bno,
    title,
    content,
    writer,
    regDate,
    board.updateDate,
    COUNT(reply.rno) AS replyCnt
FROM
    tbl_board AS board
    LEFT OUTER JOIN tbl_reply AS reply ON board.bno = reply.bno
WHERE
    1 = 1
    AND board.delflag = FALSE
    AND board.bno > 0
GROUP BY
    board.bno
ORDER BY
    board.bno DESC
LIMIT
    1000
OFFSET
    0;