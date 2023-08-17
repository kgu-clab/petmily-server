-- User
insert into user(id, password, nickname, email, address, contact, type, role, provider, created_at)
values ('admin', '{bcrypt}$2a$10$ri5DhfHYNcqjN3HGP4oCYuZ7d8sxULvUOl4gE3OONygd4QUE.1AG2', 'admin', 'admin@gmail.com',
        '수원시', '01051476788', 'INDIVIDUAL', 'ADMIN', 'LOCAL', '2023-08-18 01:57:42.000000');

-- LoginFailInfo
insert into login_fail_info(user, login_fail_count, is_lock)
values (1, 0, false);