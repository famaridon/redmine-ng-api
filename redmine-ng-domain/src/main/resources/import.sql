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
  (1,35), (1,36), (1,37),
  (2,35), (2,36), (2,37),
  (5,35), (5,36), (5,37),
  (6,35), (6,36), (6,37),
  (14,35), (14,36), (14,37),
  (21,35), (21,36), (21,37),
  (22,35), (22,36), (22,37),
  (23,35), (23,36), (23,37),
  (24,35), (24,36), (24,37);

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
