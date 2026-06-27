CREATE TABLE
        tbl_product (
                -- 상품번호
                pno INT AUTO_INCREMENT PRIMARY KEY,
                -- 상품 이름
                pname VARCHAR(200) NOT NULL,
                -- 상품 설명
                pdesc VARCHAR(200) NOT NULL,
                -- 상품 가격
                price INT NOT NULL,
                -- 판매 여부(false)
                sale BOOLEAN DEFAULT FALSE,
                -- 상품 등록자
                writer VARCHAR(100) NOT NULL,
                regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
                moddate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        );

CREATE TABLE
        tbl_product_image (
                ino INT AUTO_INCREMENT PRIMARY KEY, -- 이미지 번호 (고유 식별자)
                pno INT NOT NULL, -- 상품번호 (외래 키)
                filename VARCHAR(300) NOT NULL, -- 실제 파일명 또는 저장된 경로
                uuid CHAR(36) NOT NULL, -- 파일명 중복 방지를 위한 uuid
                ord INT DEFAULT 0, -- 이미지 정렬 순서
                regdate DATETIME DEFAULT CURRENT_TIMESTAMP, -- 등록일
                FOREIGN KEY (pno) REFERENCES tbl_product (pno) ON DELETE CASCADE
        );

CREATE INDEX idx_product_image_pno ON tbl_product_image (pno, ord);

SELECT
        *
FROM
        tbl_product_image;

SELECT
        *
FROM
        tbl_product;

SELECT
        p.pno,
        pname,
        pdesc,
        price,
        sale,
        writer,
        p.regdate,
        ino,
        UUID,
        filename,
        ord
FROM
        tbl_product p
        LEFT OUTER JOIN tbl_product_image pimg ON p.pno = pimg.pno
WHERE
        1 = 1
        AND p.pno = 1
ORDER BY
        pimg.ord ASC;