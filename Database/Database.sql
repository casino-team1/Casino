
DROP DATABASE Casino;
CREATE DATABASE Casino;
Use Casino;
DROP USER 'casinoworker'@'localhost';
CREATE USER 'casinoworker'@'localhost' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON Casino.* TO 'casinoworker'@'localhost' WITH GRANT OPTION;

CREATE TABLE Balance(
    balanceID integer auto_increment,
    balance double,
    lastUpdated Datetime,
    PRIMARY KEY (BalanceID)
);

CREATE TABLE Statistic(
    statisticID INTEGER auto_increment,
    gamePlayed varchar(255),
    result varchar(255)
);

CREATE TABLE Player(
    playerID INTEGER auto_increment,
    username varchar(255),
    password varchar(255),
    balanceID integer,
    statisticID integer,
    PRIMARY KEY (playerID),
    FOREIGN KEY (balanceID) REFERENCES Balance(balanceID),
    FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID)
);

INSERT INTO Statistic(gamePlayed,result) VALUES("Baccara","Won");
INSERT INTO Balance(Balance,LastUpdated) VALUES(1000.0,CURDATE());
-- Username == "Muster" -- Password: "1234"
-- Hash $2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK

INSERT INTO Player(username,password,statisticID,balanceID) VALUES("Muster","$2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK",1,1);