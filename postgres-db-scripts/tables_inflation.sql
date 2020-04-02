INSERT INTO resume (id, name, surname, second_name, birthday, gender)
VALUES (1, 'Петр', 'Петров', 'Петрович', '1986-12-12', 'male'),
       (2, 'Иван', 'Иванов', 'Иванович', '1997-04-04', 'male'),
       (3, 'Мария', 'Морская', 'Васильевна', '1999-07-11', 'female');

INSERT INTO technology (id, name)
VALUES (1, 'Git'),
       (2, 'Spring Boot'),
       (3, 'HTML'),
       (4, 'JavaEE'),
       (5, 'Java Core'),
       (6, 'Maven'),
       (7, 'Rest'),
       (8, 'Spring');

INSERT INTO contact_type (id, type)
VALUES (1, 'phone'),
       (2, 'email'),
       (3, 'linkedin'),
       (4, 'github'),
       (5, 'skype'),
       (6, 'facebook'),
       (7, 'other');

INSERT INTO contact (id, value, contact_type_id)
VALUES (11, '+375(29)123-45-67', 1),
       (12, 'petrovich@gmail.com', 2),
       (13, '+375(29)87-65-43', 1),
       (14, 'http://github.com/petya', 4),
       (15, 'http://github.com/vanya', 4),
       (16, 'ivanko', 5),
       (17, '+375(29)999-99-99', 1),
       (18, 'https://www.linkedin.com/in/mariya/', 3);

INSERT INTO resume_contact (resume_id, contact_id)
VALUES (1, 11),
       (1, 12),
       (2, 13),
       (1, 14),
       (2, 15),
       (2, 16),
       (3, 17),
       (3, 18);

INSERT INTO resume_technology (resume_id, technology_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 4),
       (2, 5),
       (3, 6),
       (3, 7),
       (3, 8);
