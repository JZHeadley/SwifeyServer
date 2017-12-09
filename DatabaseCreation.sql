DROP DATABASE IF EXISTS Swifey;
CREATE DATABASE Swifey;
USE Swifey;

CREATE TABLE PhoneNumbers (
  phoneId     INTEGER(42) AUTO_INCREMENT,
  countryCode INTEGER(4),
  areaCode    INTEGER(4),
  exchangeNum INTEGER(4),
  lineNum     INTEGER(4),
  PRIMARY KEY (phoneId)
);

CREATE TABLE Addresses (
  addressId      INTEGER(42) AUTO_INCREMENT,
  buildingNumber INTEGER(8),
  streetName     VARCHAR(128),
  city           VARCHAR(128),
  state          VARCHAR(64),
  zipCode        INTEGER(10),
  PRIMARY KEY (addressId)
);

CREATE TABLE Users (
  userID       VARCHAR(42),
  firstName    VARCHAR(255),
  lastName     VARCHAR(255),
  dob          DATE,
  creationDate DATETIME,
  numSwipes    INTEGER(4),
  messagingId  VARCHAR(255),
  phoneId      INTEGER(42),
  PRIMARY KEY (userId),
  FOREIGN KEY (phoneId) REFERENCES PhoneNumbers (phoneId)
);

CREATE TABLE Restaurants (
  restaurantId       INTEGER(42) AUTO_INCREMENT,
  restaurantName     VARCHAR(255),
  #   restaurantDescription VARCHAR(140),
  restaurantPhotoUrl VARCHAR(255),
  addressId          INTEGER(42),
  phoneId            INTEGER(42),
  PRIMARY KEY (restaurantId),
  FOREIGN KEY (phoneId) REFERENCES PhoneNumbers (phoneId),
  FOREIGN KEY (addressId) REFERENCES Addresses (addressId)
);

CREATE TABLE CheckIn (
  checkInId       INTEGER(42) AUTO_INCREMENT,
  checkInDate     DATE,
  restaurantId    INTEGER(42),
  maxNumOrders    INTEGER(3),
  userId          VARCHAR(42),
  acceptingOrders BOOLEAN,
  PRIMARY KEY (checkInId),
  FOREIGN KEY (userId) REFERENCES Users (userId),
  FOREIGN KEY (restaurantId) REFERENCES Restaurants (restaurantId)
);

CREATE TABLE Meals (
  mealId       INTEGER(42) AUTO_INCREMENT,
  restaurantId INTEGER(42),
  mealName     VARCHAR(128),
  mealDesc     VARCHAR(2048),
  mealCost     INTEGER(3),
  PRIMARY KEY (mealId),
  FOREIGN KEY (restaurantId) REFERENCES Restaurants (restaurantId)
);

CREATE TABLE SwipeTimes (
  swipeTimeID  INTEGER(42) AUTO_INCREMENT,
  startTime    TIME,
  endTime      TIME,
  restaurantId INTEGER(42),
  PRIMARY KEY (swipeTimeId),
  FOREIGN KEY (restaurantId) REFERENCES Restaurants (restaurantId)
);

CREATE TABLE Followings (
  userId     VARCHAR(42),
  followerId VARCHAR(42),
  PRIMARY KEY (userId, followerId),
  FOREIGN KEY (userId) REFERENCES Users (userId),
  FOREIGN KEY (followerId) REFERENCES Users (userId)
);

CREATE TABLE RestaurantHours (
  hoursId      INTEGER(42) AUTO_INCREMENT,
  openTime     TIME,
  closeTime    TIME,
  dayOfWeek    VARCHAR(16),
  restaurantId INTEGER(42),
  PRIMARY KEY (hoursId),
  FOREIGN KEY (restaurantId) REFERENCES Restaurants (restaurantId),
  CHECK (dayOfWeek IN ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'))
);

CREATE TABLE Orders (
  orderId        INTEGER(42) AUTO_INCREMENT,
  orderDate      DATETIME,
  checkInId      INTEGER(42),
  userId         VARCHAR(42),
  specialRequest VARCHAR(140),
  PRIMARY KEY (orderId),
  FOREIGN KEY (checkInId) REFERENCES CheckIn (checkInId),
  FOREIGN KEY (userId) REFERENCES Users (userId)
);

CREATE TABLE Order_meals (
  orderId INTEGER(42),
  mealId  INTEGER(42),
  PRIMARY KEY (orderId, mealId),
  FOREIGN KEY (orderId) REFERENCES Orders (orderId),
  FOREIGN KEY (mealId) REFERENCES Meals (mealId)
);

CREATE TABLE Discounts (
  discountId     INTEGER(42) PRIMARY KEY AUTO_INCREMENT,
  discountAmount INTEGER(4),
  discountCode   VARCHAR(30)
);

CREATE TABLE MEAL_DISCOUNTS (
  mealId     INTEGER(42),
  discountId INTEGER(42),
  PRIMARY KEY (mealId, discountId),
  FOREIGN KEY (mealId) REFERENCES Meals (mealId),
  FOREIGN KEY (discountId) REFERENCES Discounts (discountId)
);


CREATE PROCEDURE checkDiscount