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
    tabl_board
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

SELECT
    COUNT(*)
from
    tbl_board;

-- 게시글 테이블
SELECT
    *
FROM
    tbl_board
ORDER BY
    bno DESC;

-- 댓글 테이블
SELECT
    *
FROM
    tbl_reply
WHERE
    1 = 1
    AND bno = '7148'
ORDER BY
    rno DESC;

-- 게시물과 댓글의 조인
SELECT
    board.bno,
    title,
    content,
    writer,
    regDate,
    board.updateDate,
    COUNT(rno) AS replyCnt
FROM
    tbl_board board
    LEFT OUTER JOIN tbl_reply reply ON board.bno = reply.bno
WHERE
    1 = 1
    AND board.delflag = false
GROUP BY
    board.bno
ORDER BY
    board.bno DESC;

-- 상품, 상품이미지 테이블
SELECT
    *
FROM
    tbl_product;

SELECT
    *
FROM
    tbl_product_image;

-- 상품과 상품이미지 조인쿼리
SELECT
    p.pno,
    pname,
    pdesc,
    price,
    sale,
    writer,
    p.regdate,
    ino,
    uuid,
    filename,
    ord
FROM
    tbl_product AS p
    LEFT OUTER JOIN tbl_product_image AS pimg ON p.pno = pimg.pno
WHERE
    1 = 1
    AND p.pno = 48;

--
SELECT
    *
FROM
    tbl_product
WHERE
    1 = 1
ORDER BY
    pno DESC;

-- 상품 목록 조회
SELECT
    p.pno,
    pname,
    pdesc,
    price,
    sale,
    writer,
    p.regdate,
    ino,
    uuid,
    filename,
    ord
FROM
    tbl_product AS p
    LEFT OUTER JOIN tbl_product_image AS pimg ON p.pno = pimg.pno
WHERE
    1 = 1
    AND pimg.ord = 0
ORDER BY
    p.pno DESC
LIMIT
    10
OFFSET
    0;