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
    