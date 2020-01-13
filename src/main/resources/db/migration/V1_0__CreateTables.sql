create table AUTHORENTITY
(
    ID           bigint      not null auto_increment,
    FirstName   varchar(255) not null,
    LastName    varchar(255) not null,

    primary key (ID)
);

create table POSTENTITY
(
    ID                  bigint          not null auto_increment,
    BODY                varchar(255)    not null ,
    DATE                datetime        not null,
    TITLE               varchar(255),
    AUTHORENTITY_ID     bigint,
    primary key (ID)
);

CREATE TABLE hibernate_sequence (
    id bigint NOT NULL,
    next_val BIGINT NOT NULL
                                );
INSERT INTO hibernate_sequence VALUES (0, 1);

alter table AUTHORENTITY
    add constraint ID unique (ID);

alter table POSTENTITY
    add constraint ID unique (ID);

alter table POSTENTITY
    add constraint POSTENTITY_ID_AUTHORENTITY_ID
        foreign key (AUTHORENTITY_ID) references AUTHORENTITY(ID) ON DELETE CASCADE;


