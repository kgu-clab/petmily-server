-- User
insert into user(id, password, nickname, email, address, contact, type, role, provider)
values ('admin', '{bcrypt}$2a$10$ri5DhfHYNcqjN3HGP4oCYuZ7d8sxULvUOl4gE3OONygd4QUE.1AG2', 'admin', 'admin@gmail.com',
        '수원시', '01051476788', 'INDIVIDUAL', 'ADMIN', 'LOCAL');