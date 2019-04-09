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

CREATE TABLE Game(
    gameID integer auto_increment,
    spielName varchar(255),
    PRIMARY KEY (gameID)
);  

CREATE TABLE Statistic(
    statisticID INTEGER auto_increment,
    gameID integer,
    bet double,
    result varchar(255),
    amount double,
    PRIMARY KEY (statisticID),
    FOREIGN KEY (gameID) REFERENCES Game(gameID)
);

CREATE TABLE Player(
    playerID INTEGER auto_increment,
    username varchar(255),
    password varchar(255),
    balanceID integer,
    PRIMARY KEY (playerID),
    FOREIGN KEY (balanceID) REFERENCES Balance(balanceID)
);

CREATE TABLE StatisticToPlayer(
    playerID INTEGER,
    statisticID INTEGER,
    FOREIGN KEY (playerID) REFERENCES Player(playerID),
    FOREIGN KEY (statisticID) REFERENCES Statistic(statisticID)
);


INSERT INTO Game(spielName) VALUES("Baccara");
INSERT INTO Statistic(gameID,bet,result,amount) VALUES(1,50,"Won",100);
INSERT INTO Balance(Balance,LastUpdated) VALUES(1000.0,CURDATE());
-- Username == "Muster" -- Password: "1234"
-- Hash $2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK
INSERT INTO StatisticToPlayer(playerID,statisticID) VALUES(1,1);
INSERT INTO Player(username,password,balanceID) VALUES("Muster","$2a$10$VeufAquh14j2F7GVuQa/.uHT0TGfg3yejOdPPvKN0RMjR6IL9ibeK",1);