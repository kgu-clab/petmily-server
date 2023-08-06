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

-- Comment
insert into comment(board, writer, content)
values (1, 1, 'comment1');
insert into comment(board, writer, content)
values (1, 2, 'comment2');
insert into comment(board, writer, content)
values (1, 3, 'comment3');
insert into comment(board, writer, content)
values (1, 4, 'comment4');
insert into comment(board, writer, content)
values (1, 5, 'comment5');
insert into comment(board, writer, content)
values (2, 1, 'comment6');
insert into comment(board, writer, content)
values (2, 2, 'comment7');
insert into comment(board, writer, content)
values (2, 3, 'comment8');
insert into comment(board, writer, content)
values (3, 4, 'comment9');
insert into comment(board, writer, content)
values (3, 5, 'comment10');
insert into comment(board, writer, content)
values (4, 5, 'comment11');
insert into comment(board, writer, content)
values (5, 1, 'comment12');
insert into comment(board, writer, content)
values (6, 1, 'comment13');
insert into comment(board, writer, content)
values (7, 1, 'comment14');