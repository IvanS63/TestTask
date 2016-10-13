DROP TABLE student IF EXISTS;

DROP TABLE groups IF EXISTS ;
--group TABLE
CREATE TABLE groups(id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1),number VARCHAR(10) NOT NULL, faculty VARCHAR (50) NOT NULL, PRIMARY KEY (id));

--student TABLE
CREATE TABLE student (  id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1),   first_name VARCHAR(50) NOT NULL,middle_name  VARCHAR(50) NOT NULL, last_name  VARCHAR(50) NOT NULL,birth_date  DATE NOT NULL,group_id BIGINT ,PRIMARY KEY (id),FOREIGN KEY(group_id) REFERENCES groups(id) ON DELETE RESTRICT ON UPDATE CASCADE);
