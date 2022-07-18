TRUNCATE TABLE `movie`;
TRUNCATE TABLE `movie_genres`;
TRUNCATE TABLE `rating`;
TRUNCATE TABLE `person`;
TRUNCATE TABLE `movie_cast`;

INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('1', 'Movie1', 1980);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('2', 'Movie2', 1990);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('3', 'Movie3', 1990);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('4', 'Movie4', 2000);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('5', 'Movie5', 2001);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('6', 'Movie6', 2018);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('7', 'Movie7', 1980);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('8', 'M8', 2000);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('9', 'M9', 2000);
INSERT INTO `movie`(`id`, `title`, `release_year`) VALUES ('10', 'M10', 2009);

INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (1, '1', 'sport');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (2, '2', 'romantic');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (3, '3', 'dram');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (4, '4', 'sport');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (5, '5', 'dram');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (6, '6', 'dram');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (7, '7', 'sport');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (8, '8', 'sport');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (9, '9', 'sport');
INSERT INTO `movie_genres`(`id`, `movie_id`, `genres`) VALUES (10, '10', 'sport');


INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('1', 2.5, 3);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('10', 10, 7);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('2', 3.1, 2);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('3', 1.5, 2);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('4', 2.4, 3);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('5', 2, 4);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('6', 4, 2);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('7', 4, 3);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('8', 5.1, 2);
INSERT INTO `rating`(`movie_id`, `average_rate`, `votes`) VALUES ('9', 1, 1);

INSERT INTO `person`(`id`, `name`, `alive`) VALUES ('1', 'Alex Alexian', 1);
INSERT INTO `person`(`id`, `name`, `alive`) VALUES ('2', 'Daniel Opus', 0);
INSERT INTO `person`(`id`, `name`, `alive`) VALUES ('3', 'Salman Omid', 1);
INSERT INTO `person`(`id`, `name`, `alive`) VALUES ('4', 'Ziba Zavari', 1);

INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (1, '1', '2', 0);
INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (2, '1', '1', 1);
INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (3, '1', '3', 2);
INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (4, '1', '4', 2);
INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (5, '2', '1', 0);
INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (6, '2', '1', 1);
INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (7, '2', '4', 2);
INSERT INTO `movie_cast`(`id`, `movie_id`, `person_id`, `role`) VALUES (8, '2', '2', 2);
