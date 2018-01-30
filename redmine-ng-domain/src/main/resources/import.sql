INSERT INTO trackerentity (id, name)
VALUES
  (35, 'R&D INNOVATION - Task'),
  (36, 'R&D INNOVATION - User story'),
  (37, 'R&D INNOVATION - Defect'),
  (40, 'R&D INNOVATION - Improvement'),
  (41, 'R&D INNOVATION - Requirement');
INSERT INTO statusentity (id, `name`)
VALUES
  (1, 'Nouveau'),
  (2, 'En cours'),
  (5, 'Fermé'),
  (6, 'Rejeté'),
  (14, 'En attente d''un retour'),
  (21, 'Défini'),
  (22, 'Estimé'),
  (23, 'Réalisé'),
  (24, 'A valider');
INSERT INTO trackerentity_statusentity (statusList_id, TrackerEntity_id)
VALUES
  (1,35), (1,36), (1,37),
  (2,35), (2,36), (2,37),
  (5,35), (5,36), (5,37),
  (6,35), (6,36), (6,37),
  (14,35), (14,36), (14,37),
  (21,35), (21,36), (21,37),
  (22,35), (22,36), (22,37),
  (23,35), (23,36), (23,37),
  (24,35), (24,36), (24,37);