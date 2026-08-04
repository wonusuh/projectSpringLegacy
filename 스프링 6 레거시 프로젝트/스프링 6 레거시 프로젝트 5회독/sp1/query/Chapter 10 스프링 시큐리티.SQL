/* 계정 조회 */
SELECT
    *
FROM
    tbl_account
ORDER BY
    uid ASC;

/* 권한 조회 */
SELECT
    *
FROM
    tbl_account_roles
ORDER BY
    rolename ASC,
    uid DESC;

/* 계정 & 권한 조회 */
SELECT
    ac.uid,
    ac.upw,
    ac.email,
    ar.rolename
FROM
    tbl_account AS ac
    INNER JOIN tbl_account_roles AS ar ON ac.uid = ar.uid
WHERE
    1 = 1
    AND ac.uid = 'user100'
ORDER BY
    ar.rolename ASC;