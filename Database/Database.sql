DROP DATABASE Casino;
CREATE DATABASE Casino;
Use Casino;
DROP USER 'casinoworker'@'localhost';
CREATE USER 'casinoworker'@'localhost' IDENTIFIED BY 'Cernel_001_TP_02';
GRANT INSERT,SELECT,UPDATE ON Casino.* TO 'casinoworker'@'localhost' WITH GRANT OPTION;

CREATE TABLE balance(
    id integer auto_increment,
    chips double,
    money double,
    lastUpdated Datetime,   
    PRIMARY KEY (id)
);

CREATE TABLE game(
    id integer auto_increment,
    gameName varchar(255),
    PRIMARY KEY (id)
);  

CREATE TABLE statistic(
    id INTEGER auto_increment,
    bet double,
    result varchar(255),
    amount double,
    playDate Datetime,
    PRIMARY KEY (id)
);

CREATE TABLE user(
    id INTEGER auto_increment,
    username varchar(255),
    password varchar(255),
    email varchar(255),
    role varchar(255),
    balance_id integer,
    PRIMARY KEY (id),
    FOREIGN KEY (balance_id) REFERENCES balance(id)
);

CREATE TABLE statistictoplayer(
    user_id INTEGER,
    statistic_id INTEGER,
    game_id INTEGER,
    FOREIGN KEY (game_id) REFERENCES game(id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (statistic_id) REFERENCES statistic(id)
);

INSERT INTO game(gameName) VALUES("Baccara");
INSERT INTO game(gameName) VALUES("Roulette");
INSERT INTO game(gameName) VALUES("BlackJack");
INSERT INTO game(gameName) VALUES("Yatzy");

INSERT INTO balance(chips,money,lastUpdated) VALUES(0,5000.0,NOW());
INSERT INTO balance(chips,money,lastUpdated) VALUES(0,5000.0,NOW());

INSERT INTO user(username,password,role,balance_id,email) VALUES("Muster","$2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK","Player",1,"nick.flueckiger@outlook.de");
INSERT INTO user(username,password,role,balance_id,email) VALUES("Admin","$2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK","Admin",2,"nick.flueckiger@outlook.de");

INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Lost",-50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Lost",-50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());
INSERT INTO statistic(bet,result,amount,playDate) VALUES(50,"Won",50,NOW());

INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(1,1,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(1,2,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(1,3,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(1,4,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(1,5,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(1,6,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(2,7,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(2,8,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(2,9,1);
INSERT INTO statistictoplayer(user_id,statistic_id,game_id) VALUES(2,10,1);