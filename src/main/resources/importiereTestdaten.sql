INSERT INTO Pilot (id, vorname, nachname) VALUES ( 'fc9aa722-73da-46ca-9471-a1ee83d97959', 'Pilot1', 'Pilot1');
INSERT INTO Pilot (id, vorname, nachname) VALUES ( 'b465c9a0-7910-4bd0-8c07-d1995e3d52b1', 'Pilot2', 'Pilot2');
INSERT INTO Pilot (id, vorname, nachname) VALUES ( 'a3f000d8-29a4-480d-975e-e3c74fbed647', 'pilot3', 'Pilot3');

INSERT INTO Revier (id, name) VALUES ( 'c4bb094f-23b1-4c06-9d9c-c496ed0c5e0d', 'Revier1' );
INSERT INTO Revier_ansprechpartner (Revier_id, ansprechpartner) VALUES ('c4bb094f-23b1-4c06-9d9c-c496ed0c5e0d', 'Ansprechpartner1');
INSERT INTO Revier (id, name) VALUES ( '05224c4e-ca30-4a03-a6fd-32ec3e79b382', 'Revier2' );
INSERT INTO Revier_ansprechpartner (Revier_id, ansprechpartner) VALUES ('05224c4e-ca30-4a03-a6fd-32ec3e79b382', 'Ansprechpartner2');

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES ('a522b17d-5906-43c9-8d53-eb9634da03bf', 'c4bb094f-23b1-4c06-9d9c-c496ed0c5e0d', PARSEDATETIME('17.12.2015', 'dd.MM.yyyy', 'en'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES ('a522b17d-5906-43c9-8d53-eb9634da03bf', 'fc9aa722-73da-46ca-9471-a1ee83d97959');
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, 'a522b17d-5906-43c9-8d53-eb9634da03bf');

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES ('7d7d7bba-9d8c-42df-87cd-28f9322dc65d', '05224c4e-ca30-4a03-a6fd-32ec3e79b382', PARSEDATETIME('17.12.2016', 'dd.MM.yyyy', 'en'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES ('7d7d7bba-9d8c-42df-87cd-28f9322dc65d', 'fc9aa722-73da-46ca-9471-a1ee83d97959');
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, '7d7d7bba-9d8c-42df-87cd-28f9322dc65d');

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES ('9bb1a8ae-10bc-487a-8be1-85dfa5c51cfa', 'c4bb094f-23b1-4c06-9d9c-c496ed0c5e0d', PARSEDATETIME('17.12.2017', 'dd.MM.yyyy', 'en'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES ('9bb1a8ae-10bc-487a-8be1-85dfa5c51cfa', 'fc9aa722-73da-46ca-9471-a1ee83d97959');
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, '9bb1a8ae-10bc-487a-8be1-85dfa5c51cfa');

INSERT INTO Flugmission (id, revier_id, datum, abgesuchteHektar, anzahlHasen, anzahlKitze, sonstigeWildtiere, kommentar, latitude, longitude)
VALUES ('8cb0a020-7154-4c5b-bc18-50c469aa68ac', '05224c4e-ca30-4a03-a6fd-32ec3e79b382', PARSEDATETIME('17.12.2018', 'dd.MM.yyyy', 'en'), 10, 10, 10, 'weitere Tiere gefunden ...', 'Kommentar ...', 5.3112, 6.3112);
INSERT INTO Flugmission_Pilot (Flugmission_id, piloten_id)
VALUES ('8cb0a020-7154-4c5b-bc18-50c469aa68ac', 'fc9aa722-73da-46ca-9471-a1ee83d97959');
INSERT INTO flugmission_gelege (anzahlEier, anzahlKuecken, wildart, flugmission_id)
VALUES (5, 0, 1, '8cb0a020-7154-4c5b-bc18-50c469aa68ac');

INSERT INTO users (id, username, password, roles)
VALUES (RANDOM_UUID(), 'testadmin', '$2a$10$w4WgpkZTCyJtxu2CG.R.juQ3JSnsVq6r0Kf2VVJHQ4O9z69MZCjeq', 'admin,pilot'); -- passwort: testadmin

INSERT INTO users (id, username, password, roles)
VALUES (RANDOM_UUID(), 'testpilot', '$2a$10$jj761prJw411HBCvr/KGIegdDrernOQJEImCOxCy9GT7GEA35CrtS', 'pilot'); -- passwort: testadmin
