DROP TABLE IF EXISTS Beers;
CREATE TABLE Beers (
    Id INTEGER IDENTITY NOT NULL,
    Name VARCHAR (100),
    BrewerId INTEGER,
    CategoryId INTEGER,
    Price FLOAT,
    Stock INTEGER,
    Alcohol FLOAT,
    Version INTEGER,
    Image LONGVARBINARY,
    PRIMARY KEY (Id)
);

DROP TABLE IF EXISTS Brewers;
CREATE TABLE Brewers (
    Id INTEGER IDENTITY NOT NULL,
    Name VARCHAR (50),
    Address VARCHAR (50),
    ZipCode VARCHAR (6),
    City VARCHAR (50),
    Turnover INTEGER,
    PRIMARY KEY (Id)
);

DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories (
    Id INTEGER IDENTITY NOT NULL,
    Category VARCHAR (50),
    PRIMARY KEY (Id)
);

DROP TABLE IF EXISTS BeerOrders;
CREATE TABLE BeerOrders (
    Id INTEGER IDENTITY NOT NULL,
    Name VARCHAR (100),
    PRIMARY KEY (Id)
);

DROP TABLE IF EXISTS BeerOrderItems;
CREATE TABLE BeerOrderItems (
    Id INTEGER IDENTITY NOT NULL,
    Number INTEGER,
    BeerId INTEGER,
    BeerOrderId INTEGER,
    PRIMARY KEY (Id)
);
