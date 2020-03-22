DELETE FROM user_role;
DELETE FROM voted_user_targets;
DELETE FROM user_table;

INSERT into user_table(id, password, username) values
(1, '$2a$05$FQ/x.iC7.ZjaPcmRwWKhge/XHeRt7W7d2kX2M0nxJaaSfbALXKTtO', 'admin'),
(2, '$2a$05$6Y8iIfN35Z04nZd5QelAlepriBHO0qNOJ8NsT/D91zvCbSi9XdhJu', 'user1'),
(3, '$2a$05$qmm9SP2NlCbafEjHBJU9y.efRsqNyI8mRLEFdKO2aVFa14extPncG', 'user2');

INSERT INTO user_role(user_id, roles) VALUES
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER'),
(3, 'USER');