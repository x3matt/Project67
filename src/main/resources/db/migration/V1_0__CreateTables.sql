create table AUTHOR
(
    ID           bigint (20)  not null auto_increment,
    FIRST_NAME   varchar(255) not null,
    LAST_NAME    varchar(255) not null,

    primary key (ID)
);

create table POST
(
    ID                  bigint (20)     not null auto_increment,
    BODY                varchar(255)    not null ,
    DATE                datetime        not null,
    TITLE               varchar(255)    not null,
    AUTHOR_ID           bigint (20)     not null,
    primary key (ID)
);

alter table AUTHOR
    add constraint ID unique (ID);

alter table POST
    add constraint ID unique (ID);

alter table POST
    add constraint POST_AUTHOR_ID_AUTHOR_ID
        foreign key (AUTHOR_ID) references AUTHOR(ID) ON DELETE CASCADE;

