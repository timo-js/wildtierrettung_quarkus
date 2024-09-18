
    set client_min_messages = WARNING;

    alter table if exists Revier_ansprechpartner 
       drop constraint if exists FKbao4jq27fyuichw6gwhy3wt62;

    drop table if exists Piloten cascade;

    drop table if exists Reviere cascade;

    drop table if exists Revier_ansprechpartner cascade;

    drop sequence if exists Pilot_SEQ;

    drop sequence if exists Revier_SEQ;
