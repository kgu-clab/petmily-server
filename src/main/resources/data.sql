-- User
insert into user(password, nickname, email, address, contact, type, role, provider)
values ('admin', 'admin', 'admin@gmail.com', '수원시', '010-1234-5678', 'INDIVIDUAL', 'ADMIN', 'LOCAL');
insert into user(password, nickname, email, address, contact, type, role, provider)
values ('tester1', 'tester1', 'tester1@gmail.com', '수원시', '010-1234-5678', 'INDIVIDUAL', 'USER', 'LOCAL');
insert into user(password, nickname, email, address, contact, type, role, provider)
values ('tester2', 'tester2', 'tester2@gmail.com', '수원시', '010-1234-5678', 'INDIVIDUAL', 'USER', 'LOCAL');
insert into user(password, nickname, email, address, contact, type, role, provider)
values ('tester3', 'tester3', 'tester3@gmail.com', '수원시', '010-1234-5678', 'SHELTER', 'USER', 'LOCAL');
insert into user(password, nickname, email, address, contact, type, role, provider)
values ('tester4', 'tester4', 'tester4@gmail.com', '수원시', '010-1234-5678', 'SHELTER', 'USER', 'LOCAL');

-- Animal
insert into animal(species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('animal1', 1, 'male', null, 'yes', true, 'reason1', 'home', 'person', 'person', 1);
insert into animal(species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('animal2', 2, 'male', null, 'yes', false, 'reason2', 'company', 'hotdog', 'person', 1);
insert into animal(species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('animal3', 3, 'female', null, 'no', true, 'reason3', 'home', 'fox', 'person', 2);
insert into animal(species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('animal4', 4, 'female', null, 'no', false, 'reason4', 'toilet', 'tiger', 'person', 4);

-- Board
insert into board(writer, title, content)
values (1, 'title1', 'content1');
insert into board(writer, title, content)
values (1, 'title2', 'content2');
insert into board(writer, title, content)
values (2, 'title3', 'content3');
insert into board(writer, title, content)
values (2, 'title4', 'content4');
insert into board(writer, title, content)
values (3, 'title5', 'content5');
insert into board(writer, title, content)
values (3, 'title6', 'content6');
insert into board(writer, title, content)
values (4, 'title7', 'content7');