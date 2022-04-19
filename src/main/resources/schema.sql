DROP TABLE IF EXISTS person; -- person 테이블이 존재할 경우 DROP

CREATE TABLE IF NOT EXISTS person ( -- person 테이블이 없을 경우 테이블 생성
    personId INT(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    personName VARCHAR(50) not null,
    personJob VARCHAR(50) not null
);