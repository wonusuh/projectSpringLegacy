CREATE TABLE tbl_account (
uid VARCHAR(50) PRIMARY KEY,
upw VARCHAR(100) NOT NULL,
uname VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE,
enabled BOOLEAN DEFAULT TRUE,
createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updatedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE tbl_account_roles (
uid VARCHAR(50) NOT NULL,
rolename VARCHAR(50) NOT NULL,
FOREIGN KEY (uid) REFERENCES tbl_account(uid) -- 동일 계정에 동일한 역할 중복 방지
);

SELECT * FROM tbl_account
ORDER BY uid ASC;

SELECT * FROM tbl_account_roles
ORDER BY uid ASC;

SELECT
ac.uid, ac.upw, ac.uname, ac.email, ar.rolename
FROM
tbl_account AS ac INNER JOIN tbl_account_roles AS ar
ON ac.uid = ar.uid
WHERE ac.uid = 'user100';

CREATE TABLE persistent_logins (
username VARCHAR(64) NOT NULL,
series VARCHAR(64) PRIMARY KEY,
token VARCHAR(64) NOT NULL,
last_used TIMESTAMP NOT NULL
);

SELECT *
FROM persistent_logins;
