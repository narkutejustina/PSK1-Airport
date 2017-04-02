--Airport
CREATE TABLE IF NOT EXISTS Airport(
  Code	varchar(5)	PRIMARY KEY,
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
  PC	double PRIMARY KEY,
  Name			varchar(45) NOT NULL,
  Surname			varchar(45) NOT NULL,
  OPT_LOCK_VERSION INTEGER
);

--ticketSale
CREATE TABLE IF NOT EXISTS Flight_Passenger(
  FlightId   INTEGER,
  PassengerPC   double,
  PRIMARY KEY (FlightId, PassengerPC),
  FOREIGN KEY (FlightId) REFERENCES Flight (Id),
  FOREIGN KEY (PassengerPC) REFERENCES Passenger (PC)
);



