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


CREATE TABLE following_log (
  logId      INTEGER(42) PRIMARY KEY AUTO_INCREMENT,
  timestamp  TIMESTAMP,
  logMessage VARCHAR(512)
);

INSERT addresses(buildingNumber, streetName, city, state, zipCode)
    VALUES (301,'West Main Street', 'Richmond', 'VA', 23283);

INSERT phonenumbers (countryCode, areaCode, exchangeNum, lineNum)
    VALUES ('1',804,828,6657);

INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
    VALUES ('Bleecker St. Cafe',NULL,1,1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'), 'Breakfast Sandwich', 'Egg & Cheese; Bacon, Egg & Cheese; Ham, Egg & Cheese or Sausage, Egg & Cheese served with your choice of 4oz. yogurt or whole fruit and your choice of 4 oz. milk, 12 oz. coffee or 16 oz. Pepsi fountain beverage', 1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'), 'Made to Order Deli Sandwich', 'Chicken Breast, Tuna Salad, Chicken Salad, Smoked Turkey, Honey Ham, Roast Beef, Hummus or Portobello Mushroom', 1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'), 'Grab-N-Go Sandwich', 'Your choice of any Grab-N-Go sandwich or wrap', 1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'),
            'Soup & Salad Combo', 'Soup & Salad Combo', 1);

/*Raising Canes*/
INSERT INTO addresses(buildingNumber, streetName, city, state, zipCode)
    VALUES (805,'West Grace Street', 'Richmond', 'VA', 23283);

INSERT INTO phonenumbers (countryCode, areaCode, exchangeNum, lineNum)
    VALUES ('1',804,828,1018);

INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
    VALUES ('Raising Cane''s', NULL , 2, 2);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'),'The 3 Finger Combo', '3 Chicken fingers, crinkle-cut fries, 1 Cane’s sauce, Texas toast and a 16 oz. Pepsi fountain beverage',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'),'The Sandwich Combo','3 Chicken fingers, Cane’s sauce, lettuce, Kaiser roll, crinkle-cut fries and a 16 oz. Pepsi fountain beverage',1);


/**/
INSERT INTO addresses(buildingNumber, streetName, city, state, zipCode)
    VALUES (907,'Floyd Ave', 'Richmond', 'VA', 23284);

INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId)
    VALUES ('Chick-fil-A',NULL ,3);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'), 'Chick-fil-A® Sandwich Combo','Your choice of hand-breaded, grilled or spicy chicken sandwich served with waffle potato fries and a 16 oz. Pepsi fountain beverage or Iced Tea', 1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'), 'Chick-fil-A® Nuggets Combo','hand-breaded 8 piece chicken nuggets served with waffle potato fries and a 16 oz. Pepsi fountain beverage or Iced Tea', 1);
/**/
INSERT INTO addresses(buildingNumber, streetName, city, state, zipCode)
    VALUES (907,'Floyd Ave', 'Richmond', 'VA', 23284);

INSERT INTO phonenumbers (countryCode, areaCode, exchangeNum, lineNum)
    VALUES ('1',804,828,2989);

INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
    VALUES ('Croutons', NULL, 4, 3);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Croutons%'),'Create Your Own Salad','Select your choice of lettuce, meat or vegetarian protein, four toppings & dressing served with a piece of fruit or bag of chips and 16.9 oz. bottle water',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Croutons%'),'Create Your Own Wrap','Select your choice of wrap, meat or vegetarian protein, four toppings & deli spread served with a piece of fruit or bag of chips and 16.9 oz. bottle wate',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Croutons%'),'Soup & Salad Combo','12 ounce cup of soup and 1/2 Create Your Own Salad with choice of four toppings and a 16.9 ounce bottle of water',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Croutons%'),'Maui Bento Box','Assortment of fresh Sushi and our signature seaweed salad (6 pieces of Maki, 4 pieces of Tekka Maki & 2 pieces of Nigiri) served with a 16.9 oz. bottle water',2);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Croutons%'), 'Maki Roll (9 pieces)', 'Assortment of fresh Sushi and our signature seaweed salad served with a 16.9 oz. bottle wate',1);
/**/
INSERT INTO addresses (buildingNumber, streetName, city, state, zipCode)
    VALUES (355,'West Cary Street', 'Richmond', 'VA', 23284);

INSERT INTO phonenumbers (countryCode, areaCode, exchangeNum, lineNum)
    VALUES ('1',804,828,1272);

INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
    VALUES ('Cary Street Market & Deli', NULL, 5,4);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Cary%'),'Chef’s Daily Special','Served with your choice of two 4 oz. sides and a 16 oz. Pepsi fountain beverage',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Cary%'),'Made to Order Sandwich or Wrap','Build your own sandwich or wrap served with your choice of chips or a piece of whole fruit and a 16 oz. Pepsi fountain beverage',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Cary%'),'Grilled Burger','Your choice of Angus, Veggie or Turkey burger served with French fries and choice of one 4 oz. side and a 16 oz. Pepsi fountain beverage',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Cary%'),'Pizza Combo','7” cheese or pepperoni served with your choice of chips or a piece of whole fruitand a 16 oz. Pepsi fountain beverage',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Cary%'),'Signature Melt','Signature Melt sandwich served with your choice of chips or a piece of whole fruit and a 16 oz. Pepsi fountain beverage',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Cary%'),'Pasta Bowl','Penne pasta with Marinara or Alfredo served with garlic bread and a 16 oz. Pepsi fountain beverage',1);
/**/
/**/
/**//**//**//**//**//**//**//**//**//**//**//**//**/
INSERT INTO restaurants( restaurantName, restaurantPhotoUrl, addressId, phoneId)
    VALUES ('Einstein Bros. Bagels', NULL , 6,5);

INSERT INTO addresses (buildingNumber, streetName, city, state, zipCode)
    VALUES (1200,'East Marshall Street', 'Richmond', 'VA', 23298);

INSERT INTO phonenumbers (countryCode, areaCode, exchangeNum, lineNum)
    VALUES ('1',804,628,3364);


INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Bagel Breakfast Sandwich','Egg & Cheese; Bacon, Egg & Cheese; Ham, Egg & Cheese or Sausage, Egg & Cheese',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Deli Sandwich','Tasty Turkey, Veg Out, Chicken Salad, Tuna Salad Turkey Deli or Ham Deli',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Grab-and-Go Cold Sandwich or Wrap','Your choice of any grab-and-go sandwich or wrap',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Grab-and-Go Entree Salad','Your choice of any grab-and-go entree salad',1);

/**/
INSERT INTO restaurants( restaurantName, restaurantPhotoUrl, addressId, phoneId)
    VALUES ('Einstein Bros. Bagels', NULL , 7, 6);

INSERT INTO addresses (buildingNumber, streetName, city, state, zipCode)
    VALUES (810,'Cahedral Place', 'Richmond', 'VA', 23284);

INSERT INTO phonenumbers (countryCode, areaCode, exchangeNum, lineNum)
    VALUES ('1',804,828,2939);


INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Bagel Breakfast Sandwich','Egg & Cheese; Bacon, Egg & Cheese; Ham, Egg & Cheese or Sausage, Egg & Cheese',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Deli Sandwich','Tasty Turkey, Veg Out, Chicken Salad, Tuna Salad Turkey Deli or Ham Deli',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Grab-and-Go Cold Sandwich or Wrap','Your choice of any grab-and-go sandwich or wrap',1);

INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
    VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Einst%'),'Grab-and-Go Entree Salad','Your choice of any grab-and-go entree salad',1);

# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('C-Store', NULL, 8, 7);
#
# INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
#     VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Croutons%'),'Maui Bento Box','Assortment of fresh Sushi and our signature seaweed salad (6 pieces of Maki, 4 pieces of Tekka Maki & 2 pieces of Nigiri) served with a 16.9 oz. bottle water',2);
#
# INSERT INTO meals(restaurantId, mealName, mealDesc, mealCost)
#     VALUES ((SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Croutons%'), 'Maki Roll (9 pieces)', 'Assortment of fresh Sushi and our signature seaweed salad served with a 16.9 oz. bottle wate',1);
# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('IHOP Express', NULL , 9, 8);
# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('Market 810-2-Go', NULL , 10,9);
# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('Panda Express', NULL , 11, 10);
# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('Pizza Hut', NULL , 12,11);
# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('POD Market', NULL , 13,12);
# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('Subway',NULL ,14,13);
# /**/
# INSERT INTO restaurants(restaurantName, restaurantPhotoUrl, addressId, phoneId)
#     VALUES ('Taco Bell', NULL, 15, 14);
# /**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**/

/**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**/

/*Bleecker*/
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','20:00:00', 'MONDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','20:00:00', 'TUESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','20:00:00', 'WEDNESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','20:00:00', 'THURSDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','15:00:00', 'FRIDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));
# INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
#     VALUES ('00:00:00','00:00:00', 'SATURDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));
# INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
#     VALUES ('00:00:00','00:00:00', 'SUNDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));

INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','22:00:00','MONDAY',(SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));

INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','20:00:00', 'TUESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));

INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00','20:00:00', 'WEDNESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));

INSERT INTO swipetimes(startTime, endTime, restaurantId, dayOfWeek)
    VALUES ('07:00:00', '20:00:00', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'), 'THURSDAY');

INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('07:00:00', '15:00:00', 'FRIDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));

# INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
#     VALUES ('00:00:00','00:00:00', 'SATURDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));
#
# INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
#     VALUES ('00:00:00','00:00:00', 'SUNDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Blee%'));

/*raising canes*/
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'MONDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'TUESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'WEDNESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'THURSDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'FRIDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'SATURDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'SUNDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));

INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:00:00','01:00:00','MONDAY',(SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:00:00','01:00:00', 'TUESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:00:00','01:00:00', 'WEDNESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO swipetimes(startTime, endTime, restaurantId, dayOfWeek)
    VALUES ('17:00:00', '01:00:00', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'), 'THURSDAY');
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:00:00', '01:00:00', 'FRIDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:00:00','01:00:00', 'SATURDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:00:00','01:00:00', 'SUNDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Raising%'));

/*chic fil a*/
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:30:00','22:00:00', 'MONDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:30:00','22:00:00', 'TUESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:30:00','22:00:00', 'WEDNESDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:30:00','22:00:00', 'THURSDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('07:30:00','17:00:00', 'FRIDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'));
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('11:00:00','17:00:00', 'SATURDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'));
# INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
#     VALUES ('00:00:00','00:00:00', 'SUNDAY', (SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'));


INSERT INTO swipetimes(startTime, endTime, restaurantId, dayOfWeek)
    VALUES ('11:00:00','17:00:00',(SELECT restaurantId FROM restaurants WHERE restaurantName LIKE '%Chick%'),'SATURDAY');


/*Croutons*/
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'MONDAY', 4);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'TUESDAY', 4);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'WEDNESDAY', 4);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'THURSDAY', 4);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'FRIDAY', 4);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'SATURDAY', 4);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'SUNDAY', 4);

INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00','MONDAY',4);
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'TUESDAY', 4);
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'WEDNESDAY', 4);
INSERT INTO swipetimes(startTime, endTime, restaurantId, dayOfWeek)
    VALUES ('10:30:00', '01:00:00', 4, 'THURSDAY');
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00', '01:00:00', 'FRIDAY', 4);
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'SATURDAY', 4);
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','01:00:00', 'SUNDAY', 4);

/*Cary Street Market & Deli*/
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','21:00:00', 'MONDAY', 5);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','21:00:00', 'TUESDAY', 5);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','21:00:00', 'WEDNESDAY', 5);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','21:00:00', 'THURSDAY', 5);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','19:00:00', 'FRIDAY', 5);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('00:00:00','00:00:00', 'SATURDAY', 5);
INSERT INTO restauranthours(openTime, closeTime, dayOfWeek, restaurantId)
    VALUES ('00:00:00','00:00:00', 'SUNDAY', 5);

INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00','21:00:00','MONDAY',5);
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:30:00','21:00:00', 'TUESDAY', 5);
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('17:30:00','21:00:00', 'WEDNESDAY', 5);
INSERT INTO swipetimes(startTime, endTime, restaurantId, dayOfWeek)
    VALUES ('10:30:00', '21:00:00', 5, 'THURSDAY');
INSERT INTO swipetimes(startTime, endTime, dayOfWeek, restaurantId)
    VALUES ('10:30:00', '19:00:00', 'FRIDAY', 5);


INSERT INTO discounts(discountAmount, discountCode)
    VALUES (2, '2for1');

INSERT INTO discounts(discountAmount, discountCode)
    VALUES (3, '3for1');

INSERT meal_discounts(mealId, discountId)
    VALUES (3,1);

INSERT meal_discounts(mealId, discountId)
    VALUES (5,1);

INSERT meal_discounts(mealId, discountId)
    VALUES (10,1);

CREATE TRIGGER prevent_gt_max_num_orders
  BEFORE INSERT
  ON orders
  FOR EACH ROW
  BEGIN
    SET @maxNumOrders = (SELECT maxNumOrders
                         FROM checkIn
                         WHERE checkInId = NEW.checkInId);
    IF (SELECT count(*)
        FROM Order_meals
        WHERE Orders.orderId = NEW.orderId) >= @maxNumOrders
    THEN
      SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'No more orders are allowed for this check in';
    END IF;
  END;


CREATE TRIGGER log_following_additions
  BEFORE INSERT
  ON Followings
  FOR EACH ROW
  BEGIN
    CALL log_following_change(NEW.userId, ' gained a follower with id ', NEW.followerId);
  END;

CREATE TRIGGER log_following_losses
  BEFORE DELETE
  ON Followings
  FOR EACH ROW
  BEGIN
    CALL log_following_change(OLD.userId, ' lost a follower with id ', OLD.followerId);
  END;

CREATE FUNCTION log_following_change(userId VARCHAR(42), message VARCHAR(255), followerId VARCHAR(42))
  RETURNS INT
  BEGIN
    INSERT INTO following_log (timestamp, logMessage)
    VALUES (current_timestamp, CONCAT(userId, message, followerId));
    RETURN 0;
  END;

CREATE OR REPLACE VIEW followings_named AS
  SELECT
    concat(u.firstName, ' ', u.lastName),
    concat(f.firstName, ' ', f.lastName)
  FROM users u
    JOIN followings s ON u.userID = s.userId
    JOIN users f ON s.followerId = f.userID;
