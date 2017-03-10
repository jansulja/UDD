INSERT INTO `udd_project`.`user` (`user_id`, `firstname`, `lastname`, `password`, `type`, `username`) VALUES ('1', 'user1', 'usic', 'user1', 'administrator', 'user1');
INSERT INTO `udd_project`.`user` (`user_id`, `firstname`, `lastname`, `password`, `type`, `username`) VALUES ('2', 'lala', 'lalic', 'user2', 'pretplatnik', 'user2');

INSERT INTO `udd_project`.`language` (`language_id`,`name`) VALUES ('1','English'); 

INSERT INTO `udd_project`.`category` (`category_id`,`name`) VALUES ('1','Krimic'); 

INSERT INTO `udd_project`.`ebook` (`ebook_id`, `author`, `filename`, `keywords`, `mime`, `publication_year`, `title`, `language_language_id`, `category_category_id`) VALUES ('1', 'autor1', 'sadks.pdf', 'asds', 'pdf', '2000', 'Title of book','1','1');
INSERT INTO `udd_project`.`ebook` (`ebook_id`, `author`, `filename`, `keywords`, `mime`, `publication_year`, `title`, `language_language_id`, `category_category_id`) VALUES ('2', 'autor2', 'sadsadasadks.pdf', 'qqq', 'doc', '1950', 'Yet another title','1','1');

