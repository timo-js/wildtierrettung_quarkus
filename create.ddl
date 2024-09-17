
    create sequence Pilot_SEQ start with 1 increment by 50;

    create sequence Revier_SEQ start with 1 increment by 50;

    create table Pilot (
        id bigint not null,
        nachname varchar(50),
        vorname varchar(50),
        primary key (id)
    );

    create table Revier (
        id bigint not null,
        name varchar(50),
        primary key (id)
    );

    create table Revier_ansprechpartner (
        Revier_id bigint not null,
        ansprechpartner varchar(50)
    );

    alter table if exists Revier_ansprechpartner 
       add constraint FKbao4jq27fyuichw6gwhy3wt62 
       foreign key (Revier_id) 
       references Revier;
INSERT INTO Pilot (id, vorname, nachname) VALUES ( 1001, 'Timo', 'Vink');
INSERT INTO Pilot (id, vorname, nachname) VALUES ( 1002, 'Magnus', 'Sprehe');
INSERT INTO Pilot (id, vorname, nachname) VALUES ( 1003, 'Arne', 'Kramer');
