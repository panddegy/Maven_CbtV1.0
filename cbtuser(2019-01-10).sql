create table tbl_cbt(
    cb_id number primary key,
    cb_question nvarchar2(200) not null,
    cb_answer nvarchar2(500) not null
);

alter table tbl_cbt add cb_example nvarchar2(500) not null;
alter table tbl_cbt modify cb_answer nvarchar2(200);
desc tbl_cbt;

select * from tbl_cbt;

create table tbl_result(
    re_user nvarchar2(50) primary key,
    re_qid nvarchar2(100) not null,
    re_answer nvarchar2(100) not null
);

select * from tbl_result;

create sequence seq_cbt
start with 1 increment by 1
NOCACHE
NOCYCLE;
