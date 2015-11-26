CREATE TABLE IF NOT EXISTS offers(id SERIAL, name varchar(100), email varchar(60), text text);

INSERT INTO offers(name, email, text) VALUES('html', 'user1@example.com', 'get your own html website');
INSERT INTO offers(name, email, text) VALUES('design', 'user1@example.com', 'cheap and good designs');
INSERT INTO offers(name, email, text) VALUES('holi', 'user2@example.com', 'exciting holi offer');