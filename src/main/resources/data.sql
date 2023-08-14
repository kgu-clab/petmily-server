-- User
insert into user(id, password, nickname, email, address, contact, type, role, provider)
values ('admin', '{bcrypt}$2a$10$ri5DhfHYNcqjN3HGP4oCYuZ7d8sxULvUOl4gE3OONygd4QUE.1AG2', 'admin', 'admin@gmail.com', '수원시', '010-1234-5678', 'INDIVIDUAL', 'ADMIN', 'LOCAL');
insert into user(id, password, nickname, email, address, contact, type, role, provider)
values ('tester1', '{bcrypt}$2a$10$TA0btCubK9nayV8yJHmNo.kKTG30ECMVWQT8ed6LRl1yzZY1oZTsG', 'tester1', 'tester1@gmail.com', '수원시', '010-1234-5678', 'INDIVIDUAL', 'USER', 'LOCAL');
insert into user(id, password, nickname, email, address, contact, type, role, provider)
values ('tester2', '{bcrypt}$2a$10$owcdmT6My36VUzlJQin6.ujxwDPJtk32hHQWIdqiU74vSZewFgD1u', 'tester2', 'tester2@gmail.com', '수원시', '010-1234-5678', 'INDIVIDUAL', 'USER', 'LOCAL');
insert into user(id, password, nickname, email, address, contact, type, role, provider)
values ('tester3', '{bcrypt}$2a$10$H1olmR5kIM6Z8MrB7EtjXeO.YrhvE4OaWcujVrnAUsS2IgBKjSEDa', 'tester3', 'tester3@gmail.com', '수원시', '010-1234-5678', 'SHELTER', 'USER', 'LOCAL');
insert into user(id, password, nickname, email, address, contact, type, role, provider)
values ('tester4', '{bcrypt}$2a$10$UlsA3WbfmBNa99hrn4/9oejidNpvqCmm0c43GOkWcXJQanmR3CklG', 'tester4', 'tester4@gmail.com', '수원시', '010-1234-5678', 'SHELTER', 'USER', 'LOCAL');

-- Animal
insert into animal(animal_type, species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('DOG', 'animal1', 1, 'male', null, 'yes', true, 'reason1', 'home', 'person', 'person', 1);
insert into animal(animal_type, species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('CAT', 'animal2', 2, 'male', null, 'yes', false, 'reason2', 'company', 'hotdog', 'person', 1);
insert into animal(animal_type, species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('DOG', 'animal3', 3, 'female', null, 'no', true, 'reason3', 'home', 'fox', 'person', 2);
insert into animal(animal_type, species, age, gender, special_notes, vaccine, is_neutered, reason_for_adoption, previous_home_environment, likes, dislikes, user)
values ('BIRD', 'animal4', 4, 'female', null, 'no', false, 'reason4', 'toilet', 'tiger', 'person', 4);

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

-- TradeBoard
insert into trade_board(seller, title, content, price, location)
values (1, 'trade_board1', 'content1', 1000, '수원시');
insert into trade_board(seller, title, content, price, location)
values (1, 'trade_board2', 'content2', 2000, '서울시');
insert into trade_board(seller, title, content, price, location)
values (1, 'trade_board3', 'content3', 3000, '안양시');
insert into trade_board(seller, title, content, price, location)
values (2, 'trade_board4', 'content4', 4000, '군포시');
insert into trade_board(seller, title, content, price, location)
values (2, 'trade_board5', 'content5', 5000, '하남시');
insert into trade_board(seller, title, content, price, location)
values (3, 'trade_board6', 'content6', 6000, '용인시');
insert into trade_board(seller, title, content, price, location)
values (4, 'trade_board7', 'content7', 7000, '화성시');

-- TradeComment
insert into trade_comment(trade_board, writer, content)
values (1, 1, 'comment1');
insert into trade_comment(trade_board, writer, content)
values (1, 2, 'comment2');
insert into trade_comment(trade_board, writer, content)
values (1, 3, 'comment3');
insert into trade_comment(trade_board, writer, content)
values (1, 4, 'comment4');
insert into trade_comment(trade_board, writer, content)
values (2, 1, 'comment5');
insert into trade_comment(trade_board, writer, content)
values (2, 2, 'comment6');
insert into trade_comment(trade_board, writer, content)
values (2, 3, 'comment7');
insert into trade_comment(trade_board, writer, content)
values (2, 4, 'comment8');
insert into trade_comment(trade_board, writer, content)
values (3, 3, 'comment9');
insert into trade_comment(trade_board, writer, content)
values (4, 2, 'comment10');
insert into trade_comment(trade_board, writer, content)
values (5, 5, 'comment11');
insert into trade_comment(trade_board, writer, content)
values (6, 1, 'comment12');
insert into trade_comment(trade_board, writer, content)
values (6, 2, 'comment13');