
INSERT INTO Utilisateur (id, nom, prenom, email, mot_de_passe, preferences_alimentaires, restrictions_dietetiques, objectif) VALUES
                                                                                                                                 (1, 'Dupont', 'Jean', 'jean.dupont@example.com', 'password1', 'Préférence1', 'Restriction1', 'Objectif1'),
                                                                                                                                 (2, 'Martin', 'Marie', 'marie.martin@example.com', 'password2', 'Préférence2', 'Restriction2', 'Objectif2'),
                                                                                                                                 (3, 'Bernard', 'Pierre', 'pierre.bernard@example.com', 'password3', 'Préférence3', 'Restriction3', 'Objectif3'),
                                                                                                                                 (4, 'Dubois', 'Sophie', 'sophie.dubois@example.com', 'password4', 'Préférence4', 'Restriction4', 'Objectif4'),
                                                                                                                                 (5, 'Thomas', 'Luc', 'luc.thomas@example.com', 'password5', 'Préférence5', 'Restriction5', 'Objectif5'),
                                                                                                                                 (6, 'Robert', 'Julie', 'julie.robert@example.com', 'password6', 'Préférence6', 'Restriction6', 'Objectif6'),
                                                                                                                                 (7, 'Richard', 'Paul', 'paul.richard@example.com', 'password7', 'Préférence7', 'Restriction7', 'Objectif7'),
                                                                                                                                 (8, 'Petit', 'Laura', 'laura.petit@example.com', 'password8', 'Préférence8', 'Restriction8', 'Objectif8'),
                                                                                                                                 (9, 'Durand', 'Marc', 'marc.durand@example.com', 'password9', 'Préférence9', 'Restriction9', 'Objectif9'),
                                                                                                                                 (10, 'Leroy', 'Emma', 'emma.leroy@example.com', 'password10', 'Préférence10', 'Restriction10', 'Objectif10');

INSERT INTO Ingredient (id, nom, type_ingredient, valeurs_nutritionnelles) VALUES
                                                                               (1, 'Tomate', 0, 'Calories: 18kcal, Glucides: 3.9g, Protéines: 0.9g, Lipides: 0.2g'),
                                                                               (2, 'Poulet', 2, 'Calories: 165kcal, Glucides: 0g, Protéines: 31g, Lipides: 3.6g'),
                                                                               (3, 'Riz', 0, 'Calories: 130kcal, Glucides: 28g, Protéines: 2.7g, Lipides: 0.3g'),
                                                                               (4, 'Saumon', 3, 'Calories: 208kcal, Glucides: 0g, Protéines: 20g, Lipides: 13g'),
                                                                               (5, 'Pomme', 1, 'Calories: 52kcal, Glucides: 14g, Protéines: 0.3g, Lipides: 0.2g'),
                                                                               (6, 'Lait', 4, 'Calories: 42kcal, Glucides: 5g, Protéines: 3.4g, Lipides: 1g'),
                                                                               (7, 'Carotte', 0, 'Calories: 41kcal, Glucides: 9.6g, Protéines: 0.9g, Lipides: 0.2g'),
                                                                               (8, 'Amande', 6, 'Calories: 579kcal, Glucides: 21.6g, Protéines: 21.2g, Lipides: 49.9g'),
                                                                               (9, 'Basilic', 5, 'Calories: 23kcal, Glucides: 2.7g, Protéines: 3.2g, Lipides: 0.6g'),
                                                                               (10, 'Œuf', 4, 'Calories: 155kcal, Glucides: 1.1g, Protéines: 13g, Lipides: 11g');

INSERT INTO recette (id, titre, description, instructions, temps_preparation, temps_cuisson, degre_difficultes, categorie_recette, image_url, valeurs_nutritionnelles)
VALUES
    (1, 'Spaghetti Bolognese', 'A classic Italian pasta dish', 'Cook pasta, prepare sauce, mix together', 15, 30, 1, 1, 'http://example.com/spaghetti.jpg', '500 kcal'),
    (2, 'Chicken Curry', 'A spicy and flavorful dish', 'Cook chicken, prepare curry sauce, mix together', 20, 40, 2, 1, 'http://example.com/chicken_curry.jpg', '600 kcal');

INSERT INTO plan_repas (id, utilisateur_id, date, objectif)
VALUES
    (1, 1, '2023-10-01', 'Weight Loss'),
    (2, 2, '2023-10-02', 'Muscle Gain');

INSERT INTO plan_repas_recettes (plan_repas_id, recettes_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1);

INSERT INTO composition (recette_id, ingredient_id, quantite, unite)
VALUES
    (1, 1, 200, 'grams'),
    (1, 2, 300, 'grams'),
    (1, 3, 200, 'ml'),
    (2, 4, 250, 'grams'),
    (2, 5, 2, 'tablespoons');