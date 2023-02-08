BEGIN;

DROP SCHEMA flashstudy;

CREATE SCHEMA flashstudy;

USE flashstudy;


CREATE TABLE IF NOT EXISTS Users (
	username varchar(20) NOT null,
    email varchar(100) NOT null,
    password varchar(200) NOT null,
    regDate date,
    
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS Decks (
	createdBy varchar(20) NOT null,
	id int NOT null,
    deckTitle varchar(400) NOT null,
    
    PRIMARY KEY (id),
    FOREIGN KEY (createdBy) REFERENCES Users(username)
);

CREATE TABLE IF NOT EXISTS Flashcards (
	createdBy varchar(20) NOT null,
	id int NOT null,
    deckID int NOT null,
    question varchar(400) NOT null,
    answer varchar(400) NOT null,
    
    PRIMARY KEY (id),
    FOREIGN KEY (deckID) REFERENCES Decks(id),
    FOREIGN KEY (createdBy) REFERENCES Users(username)
)