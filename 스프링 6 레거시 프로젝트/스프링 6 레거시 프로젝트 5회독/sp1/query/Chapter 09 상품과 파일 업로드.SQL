/* 상품, 상품이미지 */
SELECT
    *
FROM
    tbl_product
ORDER BY
    pno DESC;

SELECT
    *
FROM
    tbl_product_image
ORDER BY
    pno DESC,
    ord ASC;

-- 상품목록 조회
SELECT
    p.pno AS pno,
    p.pname AS pname,
    p.pdesc AS pdesc,
    p.price AS price,
    p.sale AS sale,
    p.writer AS writer,
    p.regdate AS regdate,
    i.ino AS ino,
    i.uuid AS uuid,
    i.filename AS filename,
    i.ord AS ord
FROM
    tbl_product AS p
    LEFT OUTER JOIN tbl_product_image AS i ON p.pno = i.pno
WHERE
    1 = 1
    AND i.ord = 0
ORDER BY
    p.pno DESC
LIMIT
    10
OFFSET
    0;