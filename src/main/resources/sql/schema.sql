DROP DATABASE IF EXISTS ricemilling;

CREATE DATABASE IF NOT EXISTS  ricemilling;

SHOW DATABASES;

USE ricemilling;

CREATE TABLE users(
                      user_name VARCHAR(35) PRIMARY KEY,
                      password VARCHAR(155) NOT NULL,
                      email VARCHAR(155) NOT NULL


);

CREATE TABLE supplier(
                         su_id VARCHAR(35) PRIMARY KEY,
                         su_name VARCHAR(155) NOT NULL,
                         su_address VARCHAR(155) NOT NULL,
                         contact VARCHAR(35) NOT NULL,
                         su_email VARCHAR(155) NOT NULL

);

CREATE TABLE supplier_order(

                               order_id   VARCHAR(35) PRIMARY KEY,
                               su_id         VARCHAR(35) NOT NULL ,
                               dueDate           DATE ,


                               CONSTRAINT FOREIGN KEY (su_id) REFERENCES supplier (su_id) ON UPDATE CASCADE ON DELETE CASCADE

);


CREATE TABLE IF NOT EXISTS employee(

    emp_id       VARCHAR(15) PRIMARY KEY,
    emp_name VARCHAR(45) NOT NULL,
    emp_dob VARCHAR(45) NOT NULL,
    emp_address      VARCHAR(45) NOT NULL,
    job_title      VARCHAR(45) NOT NULL,
    emp_nic   VARCHAR(45)  NOT NULL,
    contact   VARCHAR(45)NOT NULL,
    emp_email VARCHAR(155) NOT NULL,
    salary    DOUBLE


    );



CREATE TABLE IF NOT EXISTS salary(

    salary_id VARCHAR(15)PRIMARY KEY ,
    salary_amount      DOUBLE,
    att_count VARCHAR(155) NOT NULL ,
    date        DATE,
    emp_id VARCHAR(15) NOT NULL ,
    emp_name VARCHAR(20) NOT NULL ,
    CONSTRAINT FOREIGN KEY  (emp_id) REFERENCES employee (emp_id) ON UPDATE CASCADE ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS stock(


    id       VARCHAR(25)PRIMARY KEY,
    material_name VARCHAR(25) NOT NULL ,
    qty         INT,
    description TEXT,
    unit_price       DOUBLE

    );

CREATE TABLE IF NOT EXISTS item(


    item_code     VARCHAR (25)PRIMARY KEY,
    name VARCHAR(45) NOT NULL ,
    description TEXT,
    unit_price   DECIMAL,
    qty         INT


    );

CREATE TABLE customer(
                         cu_id VARCHAR(35) PRIMARY KEY,
                         cu_name VARCHAR(155) NOT NULL,
                         address VARCHAR(155) NOT NULL,
                         cu_contact VARCHAR(35) NOT NULL,
                         cu_email VARCHAR(35) NOT NULL
);



CREATE TABLE orders (

                        order_id VARCHAR(35) PRIMARY KEY,
                        cu_id     VARCHAR(35)  ,
                        dueDate           DATE ,

                        CONSTRAINT FOREIGN KEY  (cu_id) REFERENCES customer (cu_id) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS order_details(

    order_id    VARCHAR(35) ,
    item_code VARCHAR(35),
    qty      DECIMAL,
    unit_price   DECIMAL,
    total DECIMAL,
    CONSTRAINT FOREIGN KEY  (order_id) REFERENCES orders (order_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY  (item_code) REFERENCES item (item_code) ON UPDATE CASCADE ON DELETE CASCADE

    );







CREATE TABLE supplier_lord_details(

                                      order_id VARCHAR(35),
                                      item_code       VARCHAR(15),
                                      qty   INT,
                                      unit_price DOUBLE,



                                      CONSTRAINT FOREIGN KEY  (order_id) REFERENCES supplier_order (order_id) ON UPDATE CASCADE ON DELETE CASCADE,
                                      CONSTRAINT FOREIGN KEY  (item_code) REFERENCES item (item_code) ON UPDATE CASCADE ON DELETE CASCADE

);














