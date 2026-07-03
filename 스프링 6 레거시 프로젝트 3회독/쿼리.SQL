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