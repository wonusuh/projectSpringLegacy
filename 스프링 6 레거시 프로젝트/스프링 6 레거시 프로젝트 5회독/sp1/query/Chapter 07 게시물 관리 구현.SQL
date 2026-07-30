-- 게시물
SELECT
    *
FROM
    tbl_board
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