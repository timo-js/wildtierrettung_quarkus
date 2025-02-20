INSERT INTO Pilot (id, vorname, nachname) VALUES ( 1001, 'Timo', 'Vink');
INSERT INTO Pilot (id, vorname, nachname) VALUES ( 1002, 'Magnus', 'Sprehe');
INSERT INTO Pilot (id, vorname, nachname) VALUES ( 1003, 'Arne', 'Kramer');

INSERT INTO Revier (id, name) VALUES ( 1001, 'Revier 6' );
INSERT INTO Revier_ansprechpartner (Revier_id, ansprechpartner) VALUES (1001, 'Hermann');
INSERT INTO Revier (id, name) VALUES ( 1002, 'Revier 7' );
INSERT INTO Revier_ansprechpartner (Revier_id, ansprechpartner) VALUES (1002, 'Max Mustermann');

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES (1, 1001, TO_DATE('17.12.2015', 'DD.MM.YYYY'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES (1, 1001);
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, 1);

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES (2, 1002, TO_DATE('17.12.2016', 'DD.MM.YYYY'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES (2, 1001);
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, 2);

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES (3, 1001, TO_DATE('17.12.2017', 'DD.MM.YYYY'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES (3, 1001);
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, 3);

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES (4, 1002, TO_DATE('17.12.2018', 'DD.MM.YYYY'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES (4, 1001);
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, 4);
