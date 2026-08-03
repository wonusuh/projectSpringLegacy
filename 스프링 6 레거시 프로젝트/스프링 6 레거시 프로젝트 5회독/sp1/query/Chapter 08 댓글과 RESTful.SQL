/* 댓글 목록 조회 */
SELECT
    *
FROM
    tbl_reply
WHERE
    1 = 1
    AND bno > 0
    AND bno = '7900'
ORDER BY
    rno ASC
LIMIT
    10
OFFSET
    0;

/* 특정 게시물의 전체 댓글수 조회 */
SELECT
    COUNT(rno)
FROM
    tbl_reply
WHERE
    1 = 1
    AND bno = 7900
    AND rno > 0;

/* 게시물과 댓글의 조인 */
SELECT
    board.bno,
    board.title,
    board.writer,
    board.regdate,
    board.updatedate,
    COUNT(reply.rno) AS replyCnt
FROM
    tbl_board AS board
    LEFT OUTER JOIN tbl_reply AS reply ON board.bno = reply.bno
WHERE
    1 = 1
    AND board.delflag = FALSE
GROUP BY
    board.bno
ORDER BY
    board.bno DESC;