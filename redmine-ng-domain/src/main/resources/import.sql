INSERT INTO trackerentity (id, name)
VALUES
  (35, 'R&D INNOVATION - Task'),
  (36, 'R&D INNOVATION - User story'),
  (37, 'R&D INNOVATION - Defect'),
  (40, 'R&D INNOVATION - Improvement'),
  (41, 'R&D INNOVATION - Requirement')
ON DUPLICATE KEY UPDATE
  `name` = VALUES(`name`);

INSERT INTO statusentity (id, `name`)
VALUES
  (1, 'Nouveau'),
  (2, 'En cours'),
  (3, 'Résolu'),
  (4, 'Commentaire'),
  (5, 'Fermé'),
  (6, 'Rejeté'),
  (7, 'En tests'),
  (8, 'En documentation'),
  (9, 'Réouvert'),
  (10, 'Testé par le Client'),
  (11, 'Testé par Visiativ'),
  (12, 'Livré en prod (released)'),
  (13, 'Assigné'),
  (14, 'En attente d''un retour'),
  (15, 'Pris en compte'),
  (16, 'Livré en preprod (delivered)'),
  (17, 'Livré en recette'),
  (18, 'Testé en recette'),
  (21, 'Défini'),
  (22, 'Estimé'),
  (23, 'Réalisé'),
  (24, 'A valider')
ON DUPLICATE KEY UPDATE
  `name` = VALUES(`name`);

INSERT INTO trackerentity_statusentity (statusList_id, TrackerEntity_id)
VALUES
  (1,35), (9,35), (2,35), (23,35), (5,35), (6,35), (24,35),
  (1,37), (9,37), (14,37), (15,37), (2,37), (23,37), (5,37), (6,37), (24,37);

-- R&D INNOVATION - Defect
INSERT INTO workflowentity (id, tracker_id, status_id)
VALUES
  (9, 37, null),
  (10, 37, 1),
  (11, 37, 9),
  (12, 37, 14),
  (13, 37, 15),
  (14, 37, 2),
  (15, 37, 23),
  (16, 37, 5),
  (17, 37, 6),
  (18, 37, 24);

INSERT INTO workflowentity_statusentity (WorkflowEntity_id, availableStatusList_id)
VALUES
  (9,1),
  (10,14),(10,15),(10,2),(10,23),(10,5),(10,6),(10,24),
  (11,14),(11,15),(11,2),(11,23),(11,5),(11,6),(11,24),
  (12,15),(12,2),(12,23),(12,5),(12,6),(12,24),
  (13,14),(13,2),(13,23),(13,5),(13,6),(13,24),
  (14,1),(14,14),(14,15),(14,23),(14,5),(14,6),(14,24),
  (15,9),(15,14),(15,2),(15,9),(15,5),(15,6),(15,24),
  (16,9),
  (17,9),
  (18,9),(18,14),(18,2),(18,23),(18,5),(18,6);

-- R&D INNOVATION - Task
INSERT INTO workflowentity (id, tracker_id, status_id)
VALUES
  (1, 35, null),
  (2, 35, 1),
  (3, 35, 9),
  (4, 35, 2),
  (5, 35, 23),
  (6, 35, 5),
  (7, 35, 6),
  (8, 35, 24);

INSERT INTO workflowentity_statusentity (WorkflowEntity_id, availableStatusList_id)
VALUES
  (1,1),(1,2),(1,23),(1,5),(1,6),(1,24),
  (2,2),(2,23),(2,5),(2,6),(2,24),
  (3,2),(3,23),(3,5),(3,6),(3,24),
  (4,1),(4,23),(4,5),(4,6),(4,24),
  (5,9),(5,2),(5,5),(5,6),(5,24),
  (6,9),
  (7,9),
  (8,9),(8,2),(8,23),(8,5),(8,6);
