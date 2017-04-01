--Airport
CREATE TABLE IF NOT EXISTS Airport(
  Id	varchar(5)	PRIMARY KEY,
  Name		varchar(70) NOT NULL,
  City		varchar(50) 	NOT NULL,
  Country VARCHAR (50) NOT NULL,
);

--Flight
CREATE TABLE IF NOT EXISTS Flight(
  Id	IDENTITY PRIMARY KEY ,
  ArrivalAirportId	varchar(3)  NOT NULL REFERENCES Airport,
  DepartureAirportId	varchar(3)  NOT NULL REFERENCES Airport,
  DepartureDate	DATE 	NOT NULL,
  OPT_LOCK_VERSION INTEGER,
  Duration			integer		NOT NULL
);

--Passenger
CREATE TABLE IF NOT EXISTS Passenger(
  Id	IDENTITY PRIMARY KEY,
  Name			varchar(45) NOT NULL,
  Surname			varchar(45) NOT NULL,
  Age 			integer		NOT NULL CHECK(Age>0 AND Age<120),
  Nationality		varchar(50)	NOT NULL,
  OPT_LOCK_VERSION INTEGER
);

--ticketSale
CREATE TABLE IF NOT EXISTS Flight_Passenger(
  FlightId      INTEGER,
  PassengerId       INTEGER,
  PRIMARY KEY (FlightId, PassengerId),
  FOREIGN KEY (FlightId) REFERENCES Flight (ID),
  FOREIGN KEY (PassengerId) REFERENCES Passenger (ID)
);
