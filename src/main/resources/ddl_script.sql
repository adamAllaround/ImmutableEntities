CREATE TABLE FINANCIAL_TRANSACTION
(id bigint auto_increment primary key , amount decimal not null , timestamp timestamp not null );

CREATE TABLE RECEIPT
(id bigint auto_increment primary key , transaction_date timestamp not null);

CREATE TABLE LINE_ITEM
(id bigint auto_increment primary key , item_name varchar (100) not null ,
 price decimal not null, quantity int not null, receipt_id bigint,
  foreign key (receipt_id) references RECEIPT(id) );

