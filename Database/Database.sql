DROP DATABASE Casino;
CREATE DATABASE Casino;
Use Casino;
DROP USER 'casinoworker'@'localhost';
CREATE USER 'casinoworker'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON Casino.* TO 'casinoworker'@'localhost' WITH GRANT OPTION;

CREATE TABLE balance(
    id integer auto_increment,
    balance double,
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
    game_id integer,
    bet double,
    result varchar(255),
    amount double,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES game(id)
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
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (statistic_id) REFERENCES statistic(id)
);


INSERT INTO game(gameName) VALUES("Baccara");
INSERT INTO game(gameName) VALUES("Roulette");
INSERT INTO game(gameName) VALUES("BlackJack");
INSERT INTO game(gameName) VALUES("Yatzy");

INSERT INTO statistic(game_id,bet,result,amount) VALUES(1,50,"Won",100);
INSERT INTO balance(balance,lastUpdated) VALUES(1000.0,CURDATE());

INSERT INTO statistictoplayer(player_id,statistic_id) VALUES(1,1);
-- Username == "Muster" -- Password: "1234"
-- Hash $2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK
INSERT INTO user(username,password,role,balance_id,email) VALUES("Muster","$2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK","Player",1,"nick.flueckiger@outlook.de");