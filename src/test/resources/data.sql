INSERT INTO QUESTS(ID, QUESTLINEID NAME, DESCRIPTION, TYPE, LOCATION, DUE_DATE, REWARD, COMPLETED)
VALUES (0, 'Quest1', NULL, 'This is a quest', 'NONE', 'Home', '2023-06-21', 'A cookie!', 0);
INSERT INTO OBJECTIVES(ID, QUESTID, ORDER, NAME, COMPLETED)
VALUES (0, 0, 0, 'Objective1', 0);
INSERT INTO OBJECTIVES(ID, QUESTID, ORDER, NAME, COMPLETED)
VALUES (1, 0, 1, 'Objective2', 0);